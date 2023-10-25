package bj.delivery.storeadmin.domain.storeuser.controller;

import bj.delivery.storeadmin.common.api.Api;
import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.storeuser.business.StoreUserBusiness;
import bj.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-user")
@Tag(name = "StoreUserApi", description = "가맹점 사용자 API Document")
public class StoreUserApiController {

    public final StoreUserBusiness storeUserBusiness;

    @GetMapping("/me")
    public Api<StoreUserResponse> me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession
    ){
        var response = storeUserBusiness.me(userSession);

        return Api.OK(response);
    }
}
