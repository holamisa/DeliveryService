package bj.delivery.api.domain.store.controller.model;

import bj.delivery.db.store.enums.StoreCategory;
import bj.delivery.db.store.enums.StoreStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "현재 상태")
    private StoreStatus status;

    @Schema(description = "카테고리")
    private StoreCategory category;

    @Schema(description = "별점")
    private double star;

    @Schema(description = "이미지 주소")
    private String thumbnailUrl;

    @Schema(description = "최소 주문 금액")
    private BigDecimal minimumAmount;

    @Schema(description = "최소 배달 금액")
    private BigDecimal minimumDeliveryAmount;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "등록일자")
    private LocalDateTime registeredAt;

    @Schema(description = "해지일자")
    private LocalDateTime unregisteredAt;
}
