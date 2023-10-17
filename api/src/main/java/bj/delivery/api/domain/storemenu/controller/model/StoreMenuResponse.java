package bj.delivery.api.domain.storemenu.controller.model;

import bj.delivery.db.storemenu.enums.StoreMenuStatus;
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
public class StoreMenuResponse {

    private Long id;

    private Long storeId;

    private String name;

    private BigDecimal amount;

    private StoreMenuStatus status;

    private String thumbnailUrl;

    private double star; // DB에 디폴트 값이 0, 만약 null 들어가야하면 Double로 수정 필요

    private int sequence; // DB에 디폴트 값이 0, 만약 null 들어가야하면 Double로 수정 필요

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
