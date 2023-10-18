package bj.delivery.api.domain.userordermenu.converter;

import bj.delivery.api.common.annotation.Converter;
import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.db.userorder.UserOrderEntity;
import bj.delivery.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
            UserOrderEntity userOrderEntity,
            StoreMenuEntity storeMenuEntity
    ){

        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build();
    }
}
