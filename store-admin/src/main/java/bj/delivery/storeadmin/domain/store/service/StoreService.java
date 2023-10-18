package bj.delivery.storeadmin.domain.store.service;

import bj.delivery.db.store.StoreEntity;
import bj.delivery.db.store.StoreRepository;
import bj.delivery.db.store.enums.StoreStatus;
import bj.delivery.storeadmin.common.error.ErrorCode;
import bj.delivery.storeadmin.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    // 스토어 조회
    public StoreEntity getStoreWithThrow(Long id){
        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
