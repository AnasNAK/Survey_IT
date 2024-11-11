package org.NAK.surveyit.service.implementations;


import jakarta.persistence.EntityNotFoundException;
import org.NAK.surveyit.dto.answer.AnswerCreateDTO;
import org.NAK.surveyit.dto.answer.AnswerResponseDTO;
import org.NAK.surveyit.entity.Answer;
import org.NAK.surveyit.entity.Question;
import org.NAK.surveyit.mapper.AnswerMapper;
import org.NAK.surveyit.repository.AnswerRepository;
import org.NAK.surveyit.repository.QuestionRepository;
import org.NAK.surveyit.service.contracts.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public AnswerResponseDTO createAnswer(AnswerCreateDTO answerCreateDTO) {
        if (!questionRepository.existsById(answerCreateDTO.getQuestionId())){
            throw new EntityNotFoundException("Question id was not found");
        }
        Answer answer = answerMapper.toAnswer(answerCreateDTO);
        Question question = questionRepository.getReferenceById(answerCreateDTO.getQuestionId());
        answer.setQuestion(question);
        Answer createdAnswer =  answerRepository.save(answer);
        return answerMapper.toResponse(createdAnswer);
    }

    @Override
    public List<AnswerResponseDTO> getAllAnswers() {
        List<Answer> answerList = answerRepository.findAll();
        if(answerList.isEmpty()){
            throw new RuntimeException("Answer not found");
        }
        return answerList.stream().map(answerMapper::toResponse).toList();
    }

    @Override
    public AnswerResponseDTO getAnswerById(Long id) {
        if (!answerRepository.existsById(id)){
            throw new EntityNotFoundException("Asnwer was not found");
        }
        Answer answer = answerRepository.getReferenceById(id);
        return answerMapper.toResponse(answer);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!answerRepository.existsById(id)){
            throw new EntityNotFoundException("The answer with the id " + id + " does not exist");
        }
        answerRepository.deleteById(id);
        return true;
    }


}
