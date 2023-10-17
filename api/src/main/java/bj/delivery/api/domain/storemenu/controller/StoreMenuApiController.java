package bj.delivery.api.domain.storemenu.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.storemenu.business.StoreMenuBusiness;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/all")
    public Api<List<StoreMenuResponse>> searchAllStoreMenu(
            @RequestParam(required = false)
            Long storeId
    ){
        var response = storeMenuBusiness.searchAllStoreMenu(storeId);

        return Api.OK(response);
    }

    @GetMapping("/menu")
    public Api<StoreMenuResponse> getStoreMenu(
            @RequestParam(required = false)
            Long storeMenuId
    ){
        var response = storeMenuBusiness.searchStoreMenu(storeMenuId);

        return Api.OK(response);
    }
}
