package org.NAK.surveyit.annotation.EXIST;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.NAK.surveyit.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class ExistValidator implements ConstraintValidator<Exist, Long> {

    @Autowired
    private JpaRepository<?, Long> repository;

    private String message;

    @Override
    public void initialize(Exist constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("ID cannot be null")
                    .addConstraintViolation();
            return false;
        }

        try {
            boolean exists = repository.existsById(id);
            if (!exists) {
                String formattedMessage = message.replace("${validatedValue}", String.valueOf(id));
                throw new EntityNotFoundException("Owner", formattedMessage);
            }
        } catch (Exception e) {
            System.err.println("Error during validation: " + e.getMessage());
            throw new RuntimeException("Unexpected error during validation", e);
        }

        return true;
    }
}
