package bj.delivery.api.common.api;

import bj.delivery.api.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static Api<Object> ERROR(Result result){
        return Api.<Object>builder()
                .result(result)
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeIfs))
                .build();
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String errorDescription){
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeIfs, errorDescription))
                .build();
    }

    // 해당 방식은 비추함. 사용자에게 내부 에러 정보가 공유될 수 있음
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable throwable){
        return Api.<Object>builder()
                .result(Result.ERROR(errorCodeIfs, throwable))
                .build();
    }
}
