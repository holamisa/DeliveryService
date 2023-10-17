package bj.delivery.api.domain.store.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.db.store.StoreEntity;
import bj.delivery.db.store.StoreRepository;
import bj.delivery.db.store.enums.StoreCategory;
import bj.delivery.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    // 스토어 등록
    public StoreEntity register(StoreEntity storeEntity){
        return Optional.ofNullable(storeEntity)
                .map(x -> {
                    x.setStar(0);
                    x.setStatus(StoreStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());

                    return storeRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 스토어 조회
    public StoreEntity getStoreWithThrow(Long id){
        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 카테고리로 스토어 조회
    public List<StoreEntity> searchAllByCategory(StoreCategory storeCategory){
        return storeRepository.findAllByCategoryAndStatusOrderByIdDesc(storeCategory, StoreStatus.REGISTERED);
    }

    // 전체 스토어 조회
    public List<StoreEntity> searchAll(){
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
