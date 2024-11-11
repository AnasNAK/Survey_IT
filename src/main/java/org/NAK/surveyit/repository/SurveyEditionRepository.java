package org.NAK.surveyit.repository;

import org.NAK.surveyit.entity.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Long> {
}
