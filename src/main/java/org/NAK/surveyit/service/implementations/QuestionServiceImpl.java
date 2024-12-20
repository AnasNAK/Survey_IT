package org.NAK.surveyit.service.implementations;


import jakarta.persistence.EntityNotFoundException;
import org.NAK.surveyit.dto.question.QuestionCreateDTO;
import org.NAK.surveyit.dto.question.QuestionResponseDTO;
import org.NAK.surveyit.entity.Question;
import org.NAK.surveyit.entity.Subject;
import org.NAK.surveyit.mapper.QuestionMapper;
import org.NAK.surveyit.repository.QuestionRepository;
import org.NAK.surveyit.repository.SubjectRepository;
import org.NAK.surveyit.service.contracts.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public QuestionResponseDTO createQuestion(QuestionCreateDTO questionCreateDTO) {
        Question question = questionMapper.toQuestion(questionCreateDTO);
        if (subjectRepository.existsById(questionCreateDTO.getSubjectId())){
            Subject subject = subjectRepository.getReferenceById(questionCreateDTO.getSubjectId());
            if(subject.getParent() == null){
                throw new RuntimeException("You can not add question to the main subject");
            }

            question.setSubject(subject);
            Question questionsaved = questionRepository.save(question);
            return questionMapper.toResponse(questionsaved);
        }else{
            throw new EntityNotFoundException("Subject was not found");
        }

    }

    @Override
    public List<QuestionResponseDTO> findAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No Questions found");
        }
        return questions.stream().map(questionMapper::toResponse).toList();
    }

    @Override
    public QuestionResponseDTO findQuestionById(Long id) {
        if(questionRepository.existsById(id)){
            Question question = questionRepository.getReferenceById(id);
            return questionMapper.toResponse(question);
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Override
    public boolean deleteQuestion(Long id) {
        if(questionRepository.existsById(id)){
            Question question = questionRepository.getReferenceById(id);
            questionRepository.delete(question);
            return true;
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Override
    public QuestionResponseDTO updateQuestion(Long id, QuestionCreateDTO questionCreateDTO) {
        if (!questionRepository.existsById(id)){
            throw new EntityNotFoundException("Question was not found");
        }

        ValidateData(questionCreateDTO);

        Question existedQuestion = questionRepository.getReferenceById(id);

        Optional.ofNullable(questionCreateDTO.getText())
                .filter(newText -> !newText.equals(existedQuestion.getText()))
                .ifPresent(existedQuestion::setText);

        Optional.ofNullable(questionCreateDTO.getQuestionType())
                .filter(newType -> !newType.equals(existedQuestion.getQuestionType()))
                .ifPresent(existedQuestion::setQuestionType);

        Optional.ofNullable(questionCreateDTO.getSubjectId())
                .filter(newSubjectId -> !newSubjectId.equals(existedQuestion.getSubject().getId()))
                .ifPresent(newSubjectId -> {
                    Subject newSubject = subjectRepository.findById(newSubjectId)
                            .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
                    existedQuestion.setSubject(newSubject);
                });

        Question updatedQuestion = questionRepository.save(existedQuestion);
        return questionMapper.toResponse(updatedQuestion);
    }


    public void ValidateData(QuestionCreateDTO questionCreateDTO){
        if (questionCreateDTO.getText() == null
                && questionCreateDTO.getSubjectId() == null
                && questionCreateDTO.getQuestionType() == null){
            throw new RuntimeException("Data was not provided");
        }
    }


}
