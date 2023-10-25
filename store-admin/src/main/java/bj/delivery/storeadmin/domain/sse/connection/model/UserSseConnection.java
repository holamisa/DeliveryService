package bj.delivery.storeadmin.domain.sse.connection.model;

import bj.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {

    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ConnectionPoolIfs<UserSseConnection, String> connectionPoolIfs;
    private final ObjectMapper objectMapper;

    private UserSseConnection(
            String uniqueKey,
            ConnectionPoolIfs<UserSseConnection, String> connectionPoolIfs,
            ObjectMapper objectMapper){

        this.uniqueKey = uniqueKey;

        this.sseEmitter = new SseEmitter(1000L * 60);

        this.connectionPoolIfs = connectionPoolIfs;

        this.objectMapper = objectMapper;

        this.sseEmitter.onCompletion(() -> connectionPoolIfs.onCompletionCallback(this));

        this.sseEmitter.onTimeout(this.sseEmitter::complete);

        this.sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
            String uniqueKey,
            ConnectionPoolIfs<UserSseConnection, String> connectionPoolIfs,
            ObjectMapper objectMapper){
        return new UserSseConnection(uniqueKey, connectionPoolIfs, objectMapper);
    }

    public void sendMessage(String eventName, Object data) {

        try {
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(jsonData(data));

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {

        try {
            var event = SseEmitter.event()
                    .data(jsonData(data));

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    private String jsonData(Object data) throws JsonProcessingException {

        return this.objectMapper.writeValueAsString(data);
    }
}
