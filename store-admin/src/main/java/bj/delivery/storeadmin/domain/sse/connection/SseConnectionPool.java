package bj.delivery.storeadmin.domain.sse.connection;

import bj.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import bj.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<UserSseConnection, String> {

    private final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
        connectionPool.put(uniqueKey, userSseConnection);
    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection userSseConnection) {
        log.info("callback connection pool completion : {}", userSseConnection);
        connectionPool.remove(userSseConnection.getUniqueKey());
    }
}
