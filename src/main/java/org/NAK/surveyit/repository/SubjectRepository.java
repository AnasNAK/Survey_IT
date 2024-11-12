package org.NAK.surveyit.repository;

import org.NAK.surveyit.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByParentId(Long id);
    List<Subject> findAllByParentIdIsNull();
    Optional<Subject> findByTitle(String title);
}
