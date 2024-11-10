package org.NAK.surveyit.annotation.EXIST;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistValidator.class)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exist {
    String message() default "Entity does not exist";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> repository();
    Class<?> entity();

}
