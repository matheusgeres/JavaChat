package javachat.network.message;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class HeloPacket implements Serializable{
    private String name;
    private String[] users;

    public HeloPacket(){}
    
    public HeloPacket(String name, String[] users) {
        this.name = name;
        this.users = users;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
    
    
}
