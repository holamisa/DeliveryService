package bj.delivery.api.domain.storemenu.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.storemenu.business.StoreMenuBusiness;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> storeMenuRegisterRequest
    ){
        var response = storeMenuBusiness.register(storeMenuRegisterRequest.getBody());

        return Api.OK(response);
    }
}
