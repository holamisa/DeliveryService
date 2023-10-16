package bj.delivery.api.common.api;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    // 2xx : Success Info
    // 4xx : Client Error
    // 5xx : Server Error
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription("에러 발생")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String errorDescription){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(errorDescription)
                .build();
    }

    // 해당 방식은 비추함. 사용자에게 내부 에러 정보가 공유될 수 있음
    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable throwable){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(throwable.getLocalizedMessage())
                .build();
    }
}
