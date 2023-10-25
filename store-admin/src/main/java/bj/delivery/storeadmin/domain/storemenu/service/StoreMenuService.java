package bj.delivery.storeadmin.domain.storemenu.service;

import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.db.storemenu.StoreMenuRepository;
import bj.delivery.db.storemenu.enums.StoreMenuStatus;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long storeMenuId){

        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(storeMenuId, StoreMenuStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
