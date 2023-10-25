package bj.delivery.storeadmin.domain.sse.connection.ifs;

import bj.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;

public interface ConnectionPoolIfs<T, R> {

    void addSession(R key, T session);

    T getSession(R key);

    void onCompletionCallback(T session);
}
