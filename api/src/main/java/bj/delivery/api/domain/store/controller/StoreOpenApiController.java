package bj.delivery.api.domain.store.controller;

import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.store.business.StoreBusiness;
import bj.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import bj.delivery.api.domain.store.controller.model.StoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/store")
@Tag(name = "StoreOpenApi", description = "가맹점 Open API Document")
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    @Operation(summary = "등록", description = "가맹점 등록")
    public Api<StoreResponse> register(
            @Valid @RequestBody Api<StoreRegisterRequest> request
    ){

        var response = storeBusiness.register(request.getBody());

        return Api.OK(response);
    }
}
