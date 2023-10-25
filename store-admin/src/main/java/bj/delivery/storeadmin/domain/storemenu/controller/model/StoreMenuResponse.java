package bj.delivery.storeadmin.domain.storemenu.controller.model;

import bj.delivery.db.storemenu.enums.StoreMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuResponse {

    private Long id;

    private String name;

    private BigDecimal amount;

    private StoreMenuStatus status;

    private String thumbnailUrl;

    private double star;

    private int sequence;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
