package org.NAK.surveyit.repository;

import org.NAK.surveyit.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
Optional<Owner> findByName(String name);
}
