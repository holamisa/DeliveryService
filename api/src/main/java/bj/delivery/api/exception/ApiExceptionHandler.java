package bj.delivery.api.exception;

import bj.delivery.api.common.api.Api;
가import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) // 최우선 실행되도록
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(
            ApiException apiException
    ){
        log.error("", apiException);

        var errorCode = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(Api.ERROR(errorCode, apiException.getErrorDescription()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Object>> validationException(
            MethodArgumentNotValidException exception
    ){
        log.error("", exception);

        var errorMessageList = exception.getFieldErrors().stream()
                .map(x -> {
                    var format = "%s : { %s } 은 %s";
                    return String.format(format, x.getField(), x.getRejectedValue(), x.getDefaultMessage());
                })
                .collect(Collectors.toList());

        var message = String.join(", ", errorMessageList);

        return ResponseEntity
                .status(ErrorCode.BAD_REQUEST.getErrorCode())
                .body(Api.ERROR(ErrorCode.BAD_REQUEST, message));
    }
}
