package bj.delivery.storeadmin.domain.storeuser.business;

import bj.delivery.storeadmin.common.annotation.Business;
import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.store.service.StoreService;
import bj.delivery.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import bj.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import bj.delivery.storeadmin.domain.storeuser.converter.StoreUserConverter;
import bj.delivery.storeadmin.domain.storeuser.service.StoreUserService;
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

    public StoreUserResponse me(UserSession userSession){
        return storeUserConverter.toResponse(userSession);
    }
}
