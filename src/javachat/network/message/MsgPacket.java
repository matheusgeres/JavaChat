package javachat.network.message;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class MsgPacket implements Serializable{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
