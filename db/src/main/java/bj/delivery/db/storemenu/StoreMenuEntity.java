package bj.delivery.db.storemenu;


import bj.delivery.db.BaseEntity;
import bj.delivery.db.storemenu.enums.StoreMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder // 부모 상속 받았기에 해야함
@EqualsAndHashCode(callSuper = true) // 부모 상속 받았기에 해야함
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    private double star; // DB에 디폴트 값이 0, 만약 null 들어가야하면 Double로 수정 필요

    private int sequence; // DB에 디폴트 값이 0, 만약 null 들어가야하면 Double로 수정 필요

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
