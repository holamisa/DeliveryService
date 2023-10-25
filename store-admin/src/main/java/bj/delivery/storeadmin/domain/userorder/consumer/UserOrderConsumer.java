package bj.delivery.storeadmin.domain.userorder.consumer;

import bj.delivery.common.message.model.UserOrderMessage;
import bj.delivery.storeadmin.domain.userorder.business.UserOrderBusiness;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserOrderConsumer {

    private final UserOrderBusiness userOrderBusiness;

    @RabbitListener(queues = "delivery.queue")
    public void userOrderConsumer(UserOrderMessage userOrderMessage){

        log.info("message queue : {}", userOrderMessage);
        userOrderBusiness.pushUserOrder(userOrderMessage);
    }
}
