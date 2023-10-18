package bj.delivery.storeadmin.domain.user.business;

import bj.delivery.storeadmin.common.annotation.Business;
import bj.delivery.storeadmin.domain.store.service.StoreService;
import bj.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import bj.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import bj.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import bj.delivery.storeadmin.domain.user.service.StoreUserService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserService storeUserService;
    private final StoreUserConverter storeUserConverter;
    private final StoreService storeService;

    public StoreUserResponse register(
            StoreUserRegisterRequest storeUserRegisterRequest
    ){
        var entity = storeUserConverter.toEntity(storeUserRegisterRequest);
        var saveEntity = storeUserService.register(entity);

        var storeEntity = storeService.getStoreWithThrow(saveEntity.getStoreId());

        return storeUserConverter.toResponse(saveEntity, storeEntity);
    }
}
