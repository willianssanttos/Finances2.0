package br.com.sistema.controle.financas.pessoais.domain.exception.handler;

import br.com.sistema.controle.financas.pessoais.domain.exception.*;
import br.com.sistema.controle.financas.pessoais.domain.exception.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            Exception.class,
            CriarUsuarioException.class,
            CriarContaException.class
    })
    public ResponseEntity<ApiError> genericException(Exception ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiError> badCredentialsException(RuntimeException ex) {
//        ApiError apiError = ApiError
//                .builder()
//                .timestamp(LocalDateTime.now())
//                .code(HttpStatus.UNAUTHORIZED.value())
//                .status(HttpStatus.UNAUTHORIZED.name())
//                .errors(List.of(ex.getMessage()))
//                .build();
//        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler({
            TipoContaNotFoundException.class
             })
    public ResponseEntity<ApiError> notFoundException(RuntimeException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            EmailValidacaoException.class,
            NumeroCelularValidacaoException.class

    })
    public ResponseEntity<ApiError> badRequetException(RuntimeException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            EmailExistenteException.class
            })
    public ResponseEntity<ApiError> unavailableException(RuntimeException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errorList)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
