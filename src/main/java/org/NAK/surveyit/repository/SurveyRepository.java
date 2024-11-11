package org.NAK.surveyit.repository;

import org.NAK.surveyit.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
Optional<Survey> findByTitle(String title);
}
