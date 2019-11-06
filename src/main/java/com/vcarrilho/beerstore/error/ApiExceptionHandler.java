package com.vcarrilho.beerstore.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 04/11/19
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    public static final String NO_MESSAGE_AVAILABLE = "No message available";
    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentNotValidException(MethodArgumentNotValidException exception, Locale locale) {

        List<ErrorResponse.ApiError> apiErrorList = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrorList);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException invalidFormatException, Locale locale) {
        final String errorCode = "beers-generic-1";
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale, invalidFormatException.getValue()));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ErrorResponse.ApiError toApiError(String code, Locale locale, Object... values) {
        String message = apiErrorMessageSource.getMessage(code, values, NO_MESSAGE_AVAILABLE, locale);
        return new ErrorResponse.ApiError(code, message);
    }

}
