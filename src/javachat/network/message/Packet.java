package javachat.network.message;

import java.io.Serializable;

/**
 * Represents a message sent between the server and client in either direction.
 * 
 * A packet can either be a command packet or a message packet.
 * 
 * @author DrLabman
 */
public class Packet implements Serializable {
	private PacketType type;
	private String data[];
        private Object object;
	
	public Packet(PacketType type, String data[]){
		this.type = type;
		this.data = data;
	}
        
        public Packet(PacketType type, Object object){
		this.type = type;
		this.object = object;
	}
	
	public PacketType getType(){
		return type;
	}
	
	public String[] getData(){
		return data;
	}
        
        public Object getObject(){
                return object;
        }
	
	public static Packet createHeloPacket(String name){
		return new Packet(PacketType.HELO, new String[]{name});
	}
        
        public static Packet createHeloPacketObject(String name, String[] users){
		return new Packet(PacketType.HELO, new HeloPacket(name, users));
	}
	
	public static Packet createNamePacket(String oldName, String newName){
		return new Packet(PacketType.NAME, new String[]{oldName,newName});
	}
        
        public static Packet createNamePacketObject(String oldName, String newName, String[] users){
                return new Packet(PacketType.NAME, new NamePacket(oldName, newName, users));
        }
	
	public static Packet createMsgPacket(String msg){
		return new Packet(PacketType.MSG, new String[]{msg});
	}
	
	public static Packet createQuitPacket(){
		return new Packet(PacketType.QUIT, null);
	}
        
        public static Packet createQuitPacketObject(String userLogOut, String[] users){
            return new Packet(PacketType.QUIT, new QuitPacket(userLogOut,users));
        }
	
	public static Packet createPingPacket(){
		return new Packet(PacketType.PING, null);
	}
	
	public static Packet createPongPacket(){
		return new Packet(PacketType.PONG, null);
	}
}
