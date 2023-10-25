package bj.delivery.storeadmin.domain.sse.connection.ifs;

public interface ConnectionPoolIfs<T, R> {

    void addSession(R key, T session);

    T getSession(R key);

    void onCompletionCallback(T session);
}
