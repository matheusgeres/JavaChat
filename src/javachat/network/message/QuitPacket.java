package javachat.network.message;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class QuitPacket implements Serializable{
    private String[] users;
    private String user;

    public QuitPacket() {}
    
    public QuitPacket(String user,String[] users) {
        this.users = users;
        this.user = user;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
