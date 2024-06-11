package com.java4rohit.accounts.Exception;

import com.java4rohit.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex ,
                                                                            WebRequest webRequest){

        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(webRequest.getDescription(false),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        LocalDateTime.now());

        return  new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(CustomerAlreadyExistsException ex ,
                                                                                 WebRequest webRequest){

        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(webRequest.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        LocalDateTime.now());

      return  new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(ResourceNotFoundException ex ,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        LocalDateTime.now());

        return  new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();


        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validationMgs = error.getDefaultMessage();

            validationErrors.put(fieldName,validationMgs);
        });

        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
}
