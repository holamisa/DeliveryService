package bj.delivery.api.domain.store.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import bj.delivery.api.domain.store.controller.model.StoreResponse;
import bj.delivery.api.domain.store.converter.StoreConverter;
import bj.delivery.api.domain.store.service.StoreService;
import bj.delivery.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(
            StoreRegisterRequest storeRegisterRequest
    ){

        var entity = storeConverter.toEntity(storeRegisterRequest);
        var saveEntity = storeService.register(entity);

        return storeConverter.toResponse(saveEntity);
    }

    public List<StoreResponse> searchByCategory(
            StoreCategory storeCategory
    ){

        var entityList = storeService.searchAllByCategory(storeCategory);

        return entityList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }

    public List<StoreResponse> searchAll(){

        var entityList = storeService.searchAll();

        return entityList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }

    public StoreResponse searchStore(
            Long id
    ){

        var entity = storeService.getStoreWithThrow(id);

        return storeConverter.toResponse(entity);
    }
}
