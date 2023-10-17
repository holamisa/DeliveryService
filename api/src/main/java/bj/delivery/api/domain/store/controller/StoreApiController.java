package bj.delivery.api.domain.store.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.store.business.StoreBusiness;
import bj.delivery.api.domain.store.controller.model.StoreResponse;
import bj.delivery.db.store.enums.StoreCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
@Tag(name = "StoreApi", description = "가맹점 API Document")
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping("/category")
    @Operation(summary = "가맹점 조회 (카테고리)", description = "카테고리 기준 가맹점 조회")
    public Api<List<StoreResponse>> searchByCategory(
            @RequestParam(required = false)
            StoreCategory storeCategory
    ){
        var response = storeBusiness.searchByCategory(storeCategory);

        return Api.OK(response);
    }
}
