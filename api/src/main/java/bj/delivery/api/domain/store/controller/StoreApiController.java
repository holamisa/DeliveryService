package bj.delivery.api.domain.store.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.store.business.StoreBusiness;
import bj.delivery.api.domain.store.controller.model.StoreResponse;
import bj.delivery.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping("/category")
    public Api<List<StoreResponse>> searchByCategory(
            @RequestParam(required = false)
            StoreCategory storeCategory
    ){
        var response = storeBusiness.searchByCategory(storeCategory);

        return Api.OK(response);
    }
}
