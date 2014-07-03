package javachat.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import javachat.JavaChat;
import javachat.network.message.HeloPacket;
import javachat.network.message.NamePacket;
import javachat.network.message.Packet;
import javachat.network.message.QuitPacket;
import javachat.network.socket.SocketController;

/**
 * This class acts as a pass through to the SocketController which handles the
 * actual reading and writing.
 *
 * Here we pass through messages to send to the SocketController and handle
 * incoming messages.
 *
 * @author DrLabman
 */
public class Client extends SocketController {

    private SocketController socketCtrl;
    private String name;
    private boolean server = false;
    private String securityKey;

    public static Client createClient(String hostname, int port, String name, boolean server) throws ConnectException {
        try {
            //Create a socket to connect with
            Socket skt = new Socket(hostname, port);
            // Create the client object using the socket
            Client client = new Client(skt);
            // Set the clients name and send the hello message to the server
            client.name = name;
            client.sendHello();
            //Set if client created is the server 
            client.setServer(server);
            return client;
        } catch (UnknownHostException ex) {
            JavaChat.println("Unknown Host: " + ex.getMessage());
        } catch (ConnectException ex) {
            JavaChat.println("Unable to connect to server: " + ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            JavaChat.println("IO Exception: " + ex.getMessage());
        }

        // Something went wrong.
        return null;
    }

    private Client(Socket socket) {
        super(socket);
    }

    @Override
    public void receiveMsg(Packet msg) {
        if (msg != null) {
            switch (msg.getType()) {
                case MSG:
                    // Send message back to all other clients
                    JavaChat.println(msg.getData()[0]);
                    break;
                case PING:
                    sendMsg(Packet.createPongPacket());
                    //JavaChat.println("Ping!");
                    break;
                case QUIT:
                    if (socketCtrl!=null&&socketCtrl.isDisconnecting()) {
                        disconnect();
                    }
                    if(msg.getObject()!=null&&msg.getObject() instanceof QuitPacket){
                        QuitPacket qp = (QuitPacket) msg.getObject();
                        JavaChat.setUsersOnline(qp.getUsers());
                        JavaChat.println("Client "+ qp.getUser() +" disconnected.");
                    }else{
                        JavaChat.println("Client disconnected.");
                    }
                    
                    break;
                case HELO:
                    if(msg.getObject()!=null&&msg.getObject() instanceof HeloPacket){
                        HeloPacket helo = (HeloPacket) msg.getObject();
                        JavaChat.println(helo.getName() + " connected...");
                        if(helo.getUsers()!=null&&helo.getUsers().length>0){
                            JavaChat.setUsersOnline(helo.getUsers());
                        }
                    }
                    break;
                case NAME:
                    if(msg.getObject()!=null&&msg.getObject() instanceof NamePacket){
                        NamePacket np = (NamePacket) msg.getObject();
                        JavaChat.println(np.getOldName()+" changed name to "+np.getNewName());
                        if(np.getUsers()!=null&&np.getUsers().length>0){
                            JavaChat.setUsersOnline(np.getUsers());
                        }
                    }
                    
                    // Not expected
//                    JavaChat.println("Received unexpected packet type: " + msg.getType().name());
                    break;
                case SECURITY:
                    if(msg.getData()!=null&&msg.getData().length>0){
                        securityKey = msg.getData()[0];
                        //JavaChat.println("Key from Chat: " + securityKey);
                    }
                    break;
                default:
                    JavaChat.println("Unknown packet type from connection: " + msg.getType().name());
                    break;
            }
        }
    }

    @Override
    public void disconnected() {
        JavaChat.disconnected();
    }

    /**
     * Override sendMsg because we need to prepend our name to our messages and
     * we want to print the message as it doesn't echo back to us.
     *
     * @param msg
     */
    @Override
    public void sendMsg(String msg) {
        String fullMessage = "[" + name + "] " + msg;
        super.sendMsg(fullMessage);
        JavaChat.println(fullMessage); //gerava duplicidade, pois o ja eh capturado quando roda a thread no metodo receiveMsg
    }

    private void sendHello() {
        sendMsg(Packet.createHeloPacket(name));
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
//        JavaChat.println(this.name + " changed name to " + name); 
        sendMsg(Packet.createNamePacket(this.name, name));
        this.name = name;
    }

    public boolean isServer() {
        return server;
    }

    public void setServer(boolean server) {
        this.server = server;
    }

}
