package bj.delivery.api.domain.userorder.producer;

import bj.delivery.api.common.rabbitmq.Producer;
import bj.delivery.common.message.model.UserOrderMessage;
import bj.delivery.db.userorder.UserOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

    private static final String EXCHANGE = "delivery.exchange";
    private static final String ROUTE_KEY = "delivery.key";

    private final Producer producer;

    public void sendOrder(UserOrderEntity userOrderEntity){

        var optionalUserOrderEntity = Optional.ofNullable(userOrderEntity);
        optionalUserOrderEntity.ifPresent(orderEntity -> sendOrder(orderEntity.getId()));
    }

    public void sendOrder(Long userOrderId){

        var message = UserOrderMessage.builder()
                .userOrderId(userOrderId)
                .build();

        producer.producer(EXCHANGE, ROUTE_KEY, message);
    }
}
