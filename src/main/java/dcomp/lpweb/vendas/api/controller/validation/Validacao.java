package dcomp.lpweb.vendas.api.controller.validation;

import dcomp.lpweb.vendas.api.controller.dto.CategoriaDTO;
import dcomp.lpweb.vendas.api.controller.response.Erro;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Validacao <T> {

    public List<Erro> valida(T dto) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> validate =
                validator.validate(dto);

        final List<Erro> erros = new ArrayList<>();

        validate.forEach(violation ->
           {
               final String campo = violation.getPropertyPath().toString();

               final String campoCapitalizado = campo.substring( 0, 1 ).toUpperCase()
                                   + campo.substring( 1 );

               erros.add(new Erro("Campo " + campoCapitalizado
                                + " " + violation.getMessage(),
                                violation.getInvalidValue().toString()
                                + violation.getMessageTemplate())); });
        return erros;
    }
}
