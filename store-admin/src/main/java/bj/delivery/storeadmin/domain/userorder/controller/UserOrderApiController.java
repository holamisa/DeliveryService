package bj.delivery.storeadmin.domain.userorder.controller;

import bj.delivery.storeadmin.common.api.Api;
import bj.delivery.storeadmin.domain.authorization.model.UserSession;
import bj.delivery.storeadmin.domain.userorder.business.UserOrderBusiness;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-order")
@Tag(name = "User Order Api", description = "가맹점 사용자 주문 API Document")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PatchMapping("/accept")
    public Api<List<UserOrderResponse>> acceptOrder(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession,
            @Valid
            @RequestBody UserOrderRequest userOrderRequest
    ){
        var response = userOrderBusiness.acceptOrder(userSession, userOrderRequest);

        return Api.OK(response);
    }

    @PatchMapping("/cook")
    public Api<List<UserOrderResponse>> cookOrder(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession,
            @Valid
            @RequestBody UserOrderRequest userOrderRequest
    ){
        var response = userOrderBusiness.cookOrder(userSession, userOrderRequest);

        return Api.OK(response);
    }

    @PatchMapping("/deliver")
    public Api<List<UserOrderResponse>> deliverOrder(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession,
            @Valid
            @RequestBody UserOrderRequest userOrderRequest
    ){
        var response = userOrderBusiness.deliverOrder(userSession, userOrderRequest);

        return Api.OK(response);
    }

    @PatchMapping("/receive")
    public Api<List<UserOrderResponse>> receiveOrder(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserSession userSession,
            @Valid
            @RequestBody UserOrderRequest userOrderRequest
    ){
        var response = userOrderBusiness.receiveOrder(userSession, userOrderRequest);

        return Api.OK(response);
    }
}
