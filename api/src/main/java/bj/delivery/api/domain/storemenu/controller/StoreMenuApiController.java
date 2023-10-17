package bj.delivery.api.domain.storemenu.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.storemenu.business.StoreMenuBusiness;
import bj.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-menu")
@Tag(name = "StoreMenuApi", description = "메뉴 API Document")
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/all")
    @Operation(summary = "모든 메뉴 조회", description = "가맹점에 등록된 모든 메뉴 조회")
    public Api<List<StoreMenuResponse>> searchAllStoreMenu(
            @RequestParam(required = false)
            Long storeId
    ){
        var response = storeMenuBusiness.searchAllStoreMenu(storeId);

        return Api.OK(response);
    }

    @GetMapping("/menu")
    @Operation(summary = "메뉴 상세", description = "특정 메뉴의 상세 정보 조회")
    public Api<StoreMenuResponse> getStoreMenu(
            @RequestParam(required = false)
            Long storeMenuId
    ){
        var response = storeMenuBusiness.searchStoreMenu(storeMenuId);

        return Api.OK(response);
    }
}
