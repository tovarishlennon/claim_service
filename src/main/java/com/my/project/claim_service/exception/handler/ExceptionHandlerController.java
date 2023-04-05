package com.my.project.claim_service.exception.handler;

import com.my.project.claim_service.constant.ResultCodes;
import com.my.project.claim_service.dto.exception.ApiException;
import com.my.project.claim_service.dto.exception.ErrorResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ApiException.class)
    public ErrorResponseDto handleServiceException(ApiException ex) {
        return new ErrorResponseDto(ex.getCode().getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponseDto handleServiceException(MethodArgumentNotValidException ex) {

        StringBuilder builder = new StringBuilder("Validation exception: ");

        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            builder.append(fieldName).append(" - ").append(message).append("; ");
        });

        return new ErrorResponseDto(ResultCodes.VALIDATION_EXCEPTION.getCode(), builder.toString());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public  ErrorResponseDto handleException(Exception ex) {
        return new ErrorResponseDto(ResultCodes.SYSTEM_FAILURE.getCode(), "Service temporary unavailable");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public  ErrorResponseDto handleServiceException(ConstraintViolationException ex) {
        return new ErrorResponseDto(ResultCodes.VALIDATION_EXCEPTION.getCode(), ex.getMessage());
    }
}
