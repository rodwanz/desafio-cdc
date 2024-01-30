package br.wanzeler.desafio.cdc.valido;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.wanzeler.desafio.cdc.api.validador.ExistsIdValidator;


@Documented
@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface ExistsId {
	
	String message() default "{br.wanzeler.beanvalidation.existsId}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String fieldName();

	Class<?> domainClass();

}
