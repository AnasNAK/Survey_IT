package org.NAK.surveyit.repository;

import org.NAK.surveyit.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findBySubjectId(Long id);
    Optional<Question> findByText(String text);
}
