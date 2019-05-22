package dcomp.lpweb.vendas.api.exception;

import dcomp.lpweb.vendas.api.controller.response.Erro;
import dcomp.lpweb.vendas.api.controller.response.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiVendasExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        final String mensagem = messageSource.getMessage("parametro.invalido", null, new Locale("pt", "BR"));

        final Resposta resposta = Resposta.com(new Erro(mensagem, ex.getLocalizedMessage()));

        return super.handleExceptionInternal(ex, resposta, headers, HttpStatus.BAD_REQUEST, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        final List<Erro> erros = fieldErrors.stream()
                .map(fieldError -> new Erro(fieldError.getDefaultMessage(), ex.getLocalizedMessage()))
                .collect(Collectors.toList());

        return super.handleExceptionInternal(ex, Resposta.com(erros), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Resposta<Erro> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {

        String mensagem = String.format("Recurso nao encontrado, Quantidade Esperada %d, Quantidade Encontrada %d ",
                                         ex.getExpectedSize(), ex.getActualSize());
        return Resposta.com( new Erro(mensagem, ex.getMostSpecificCause().toString() ) );
    }
}
