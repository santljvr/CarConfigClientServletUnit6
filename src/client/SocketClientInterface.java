package client;

/**
 * Created by santa on 6/23/15.
 * interface for default socket client
 */
public interface SocketClientInterface {
    boolean openConnection();
    void handleSession();
    void closeSession();
}
