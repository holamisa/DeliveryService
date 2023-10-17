package bj.delivery.api.domain.store.controller.model;

import bj.delivery.api.common.annotation.PhoneNumber;
import bj.delivery.db.store.enums.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    @Schema(description = "이름")
    private String name;

    @NotBlank
    @Schema(description = "주소")
    private String address;

    @NotNull
    @Schema(description = "카테고리")
    private StoreCategory category;

    @NotBlank
    @Schema(description = "이미지 주소")
    private String thumbnailUrl;

    @NotNull
    @Schema(description = "최소 주문 금액")
    private BigDecimal minimumAmount;

    @NotNull
    @Schema(description = "최소 배달 금액")
    private BigDecimal minimumDeliveryAmount;

    @PhoneNumber
    @Schema(description = "전화번호")
    private String phoneNumber;
}
