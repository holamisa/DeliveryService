package bj.delivery.api.domain.storemenu.service;

import bj.delivery.api.common.error.ErrorCode;
import bj.delivery.api.common.exception.ApiException;
import bj.delivery.db.storemenu.StoreMenuEntity;
import bj.delivery.db.storemenu.StoreMenuRepository;
import bj.delivery.db.storemenu.enums.StoreMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity){
        return Optional.ofNullable(storeMenuEntity)
                .map(x -> {
                    x.setStar(0);
                    x.setStatus(StoreMenuStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());

                    return storeMenuRepository.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuEntity getStoreMenuWithThrow(Long id){
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuEntity> searchAll(Long id){
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceAsc(id, StoreMenuStatus.REGISTERED);
    }
}
