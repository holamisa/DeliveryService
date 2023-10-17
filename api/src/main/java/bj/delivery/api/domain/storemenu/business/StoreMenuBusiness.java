package bj.delivery.api.domain.storemenu.business;

import bj.delivery.api.common.annotation.Business;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import bj.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import bj.delivery.api.domain.storemenu.service.StoreMenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(
            StoreMenuRegisterRequest storeMenuRegisterRequest
    ){
        var entity = storeMenuConverter.toEntity(storeMenuRegisterRequest);
        var saveEntity = storeMenuService.register(entity);

        return storeMenuConverter.toResponse(saveEntity);
    }

    public StoreMenuResponse searchStoreMenu(Long storeMenuId){
        var entity = storeMenuService.getStoreMenuWithThrow(storeMenuId);

        return storeMenuConverter.toResponse(entity);
    }

    public List<StoreMenuResponse> searchAllStoreMenu(Long storeId){
        var entityList = storeMenuService.searchAll(storeId);

        return entityList.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
