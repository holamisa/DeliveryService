package bj.delivery.storeadmin.domain.storeuser.controller;

import bj.delivery.storeadmin.common.api.Api;
import bj.delivery.storeadmin.domain.storeuser.business.StoreUserBusiness;
import bj.delivery.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import bj.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
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
@RequestMapping("/open-api/store-user")
@Tag(name = "StoreUserOpenApi", description = "가맹점 사용자 Open API Document")
public class StoreUserOpenApiController {

    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("/register")
    @Operation(summary = "등록", description = "가맹점 사용자 등록")
    public Api<StoreUserResponse> register(
            @Valid
            @RequestBody StoreUserRegisterRequest storeUserRegisterRequest
    ){

        var response = storeUserBusiness.register(storeUserRegisterRequest);

        return Api.OK(response);
    }
}
