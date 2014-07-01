package javachat.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javachat.JavaChat;
import javachat.network.message.Packet;
import javachat.network.message.PacketType;
import javachat.network.socket.SocketController;
import javachat.network.util.IPUtil;
import javachat.network.util.UPnP;

/**
 * Chat server class. Creates a server sockets and accepts incoming connections
 * until disconnect is called. When a client connects a ClientSocket class is
 * created to handle incoming messages.
 *
 * @author DrLabman
 */
public class Server implements Runnable {

    private int port;
    private boolean connected;
    private boolean disconnect;
    private boolean useUPnP = false;
    private String welcomeMesage = "Seja bem vindo! Sinta-se a vontade para usar nossos servicos! :)";
    private ArrayList<ClientSocket> clients;
    private ServerSocket srvr;
    private KeepAlive keepAlive;

    public Server(int port) {
        this.port = port;
        disconnect = false;
        connected = false;

        clients = new ArrayList<ClientSocket>();

        // Print useful information for user
        IPUtil.printExternalIP();
        IPUtil.printInternalIP();
        // Register UPnP port mapping
        if(useUPnP){
            UPnP.RegisterPort(port);
        }
        

        keepAlive = new KeepAlive();
        Thread t1 = new Thread(keepAlive);
        t1.start();

        Thread t = new Thread(this);
        t.start();
    }

    /**
     * Handle incoming connections, create ClientSockets for them.
     */
    @Override
    public void run() {
        try {
            srvr = new ServerSocket(port);
            connected = true;
            while (!disconnect) {
                Socket skt = srvr.accept();
                ClientSocket client = new ClientSocket(skt);
                client.sendMsg(new Packet(PacketType.MSG, new String[] {welcomeMesage}));
                keepAlive.addToQueue(client);
                clients.add(client);
            }
        } catch (SocketException ex) {
            if (!ex.getMessage().equals("socket closed")) {
                JavaChat.println("Socket Exception: " + ex.getMessage());
            }
        } catch (IOException ex) {
            JavaChat.println("IO Exception: " + ex.getMessage());
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void sendMsg(SocketController sender, Packet msg) {
        for (ClientSocket client : clients) {
            if (client != sender) {
                client.sendMsg(msg);
            }
        }
    }

    public void disconnect() {
        disconnect = true;
        for (ClientSocket client : clients) {
            client.disconnect();
        }

        try {
            srvr.close();
        } catch (IOException ex) {
            JavaChat.println("Exception when closing socket: " + ex.getMessage());
        }
        JavaChat.println("No longer listening for connections.");

        if(useUPnP){
            UPnP.UnregisterPort();
        }
    }

    public void printClientNames() {
        StringBuilder sb = new StringBuilder();
        List<String> lUsers = new ArrayList<String>();
        for (ClientSocket client : clients) {
            sb.append(client.getName());
            sb.append(" ");
            lUsers.add(client.getName());
        }
        String users = "Users: " + sb.toString();
        System.out.println(users);
//        JavaChat.println(users); //usado para aparecer os usuarios nas linhas de chat
        JavaChat.setUsersOnline(lUsers.toArray(new String[0]));
    }
    
    public String[] getNamesClientOnline() {
        List<String> lUsers = new ArrayList<String>();
        for (ClientSocket client : clients) {
            lUsers.add(client.getName());
        }
        return lUsers.toArray(new String[0]);
    }
    
    public void removeClientQueue(ClientSocket client){
        if(client!=null){
            clients.remove(client);
        }
    }

    private class KeepAlive implements Runnable {

        LinkedList<ClientSocket> queue = new LinkedList<ClientSocket>();
        LinkedList<ClientSocket> pinged = new LinkedList<ClientSocket>();

        @Override
        public void run() {
            while (!disconnect) {
                long time = System.currentTimeMillis();

                if (pinged.size() > 0) {
                    if (pinged.peekFirst().getNextKeepAlive() < time + 15 * 1000) {
                        // Timed out on ping (more than 15 seconds to respond)
                        ClientSocket client = pinged.removeFirst();
                        client.disconnect();
                    } else if (pinged.peekFirst().getNextKeepAlive() > time) {
                        ClientSocket client = pinged.removeFirst();
                        queue.addLast(client);
                    }
                }

                if (queue.size() > 0) {
                    if (queue.peekFirst().getNextKeepAlive() < time) {
                        ClientSocket client = queue.removeFirst();
                        client.sendMsg(Packet.createPingPacket());
                        pinged.addLast(client);
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }

        public void addToQueue(ClientSocket client) {
            queue.addLast(client);
        }

        public void removeFromQueue(ClientSocket client) {
            queue.remove(client);
            pinged.remove(client);
        }
    }

    /**
     * Class for handling incoming messages and the disconnected callback.
     */
    private class ClientSocket extends SocketController {

        private String name;
        private long nextKeepAlive;

        public ClientSocket(Socket socket) {
            super(socket);
            nextKeepAlive = System.currentTimeMillis() + 1000;
        }

        //Send message received on the method to all clients connected
        public void sendMsgToAll(Packet msg, boolean yourSelf){
            for (ClientSocket client : clients) {
                if(!(this==client&&!yourSelf)){
                    client.sendMsg(msg);
                }
            }
        }
        
        //Send message received on the method to all clients connected
        public void sendMsgToAll(Packet pkt){
            for (ClientSocket client : clients) {
                client.sendMsg(pkt);
            }
        }

        @Override
        public void receiveMsg(Packet msg) {
            if (msg != null) {
                switch (msg.getType()) {
                    case MSG:
                        // Send message back to all other clients
                        sendMsgToAll(msg,false);
                        break;
                    case HELO:
                        name = msg.getData()[0];
                        String connectedMsg = name + " connected...";
//                        sendMsgToAll(Packet.createMsgPacket(connectedMsg),true);
                        sendMsgToAll(Packet.createHeloPacketObject(name, getNamesClientOnline()));
                        printClientNames();
                        break;
                    case NAME:
                        String names[] = msg.getData();
                        String newNameMsg = names[0] + " changed name to " + names[1];
                        name = names[1];
                        sendMsgToAll(Packet.createMsgPacket(newNameMsg),true);
                        break;
                    case PONG:
                        nextKeepAlive = System.currentTimeMillis() + 60 * 1000;
                        //JavaChat.println("Pong!");
                        break;
                    case QUIT:
                        this.disconnect();
//                        JavaChat.println("Client "+ this.getName() +" disconnected.");
                        removeClientQueue(this);
                        sendMsgToAll(Packet.createQuitPacketObject(this.getName(),getNamesClientOnline()));
                        break;
                    default:
                        JavaChat.println("Unknown packet type from connection: " + msg.getType());
                        break;
                }
            }
        }

        @Override
        public void disconnected() {
            clients.remove(this);
        }

        public String getName() {
            return name;
        }

        public long getNextKeepAlive() {
            return nextKeepAlive;
        }
    }
}
