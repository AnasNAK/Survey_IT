package org.NAK.surveyit.annotation.UNIQUE;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.Optional;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private ApplicationContext applicationContext;

    private Class<?> repositoryClass;
    private String message;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.repositoryClass = constraintAnnotation.repository();
        this.message = constraintAnnotation.message();
        this.fieldName = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Value cannot be empty")
                    .addConstraintViolation();
            return false;
        }

        try {

            JpaRepository<?, ?> repository = (JpaRepository<?, ?>) applicationContext.getBean(repositoryClass);

            Method method = repository.getClass().getMethod("findBy" + capitalize(fieldName), String.class);

            Optional<?> result = (Optional<?>) method.invoke(repository, value);

            if (result.isPresent()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message.replace("${validatedValue}", value))
                        .addConstraintViolation();
                return false;
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Repository must have a method findBy" + capitalize(fieldName));
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Error checking uniqueness: " + e.getMessage())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
