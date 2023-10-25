package bj.delivery.api.domain.userorder.controller;

import bj.delivery.api.common.annotation.UserSession;
import bj.delivery.api.common.api.Api;
import bj.delivery.api.domain.user.model.UserDTO;
import bj.delivery.api.domain.userorder.business.UserOrderBusiness;
import bj.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import bj.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import bj.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
@Tag(name = "UserOrderApi", description = "주문 API Document")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("/order")
    @Operation(summary = "주문", description = "가맹점의 메뉴 주문")
    public Api<UserOrderResponse> userOrderRegister(
            @Parameter(hidden = true)
            @UserSession UserDTO user,
            @Valid
            @RequestBody UserOrderRequest userOrderRequest

    ){
        var response = userOrderBusiness.userOrderRegister(user, userOrderRequest);

        return Api.OK(response);
    }

    @GetMapping("/current")
    @Operation(summary = "진행 주문 내역", description = "진행중인 주문 내역 리스트")
    public Api<List<UserOrderDetailResponse>> getCurrentOrder(
            @Parameter(hidden = true)
            @UserSession UserDTO user
    ){
        var response = userOrderBusiness.getCurrentOrder(user);

        return Api.OK(response);
    }

    @GetMapping("/past")
    @Operation(summary = "완료 주문 내역", description = "완료된 주문 내역 리스트")
    public Api<List<UserOrderDetailResponse>> getPastOrder(
            @Parameter(hidden = true)
            @UserSession UserDTO user
    ){
        var response = userOrderBusiness.getPastOrder(user);

        return Api.OK(response);
    }

    @GetMapping("/id/{userOrderId}")
    @Operation(summary = "주문 내역", description = "주문 내역 상세 정보")
    public Api<UserOrderDetailResponse> getOrderInfo(
            @Parameter(hidden = true)
            @UserSession UserDTO user,

            @PathVariable Long userOrderId
    ){
        var response = userOrderBusiness.getUserOrder(user, userOrderId);

        return Api.OK(response);
    }
}
