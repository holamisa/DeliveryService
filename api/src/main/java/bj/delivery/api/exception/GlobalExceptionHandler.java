package bj.delivery.api.exception;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) // 최후에 실행되도록
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
        log.error("", exception);

        return ResponseEntity
                .status(ErrorCode.SERVER_ERROR.getErrorCode())
                .body(Api.ERROR(ErrorCode.SERVER_ERROR));
    }
}
