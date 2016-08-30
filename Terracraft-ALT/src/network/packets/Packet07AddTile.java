package network.packets;

import Terracraft.Utils;
import network.Client;
import network.Server;

public class Packet07AddTile extends Packet {

	private int x,y;
	private String type;

	public Packet07AddTile(byte[] data) {
		super(PacketTypes.ADDTILE.getId());
		String[] message = readData(data).split(",");

		x = Utils.toInt(message[0]);
		y = Utils.toInt(message[1]);
		type = message[2];
	}

	public Packet07AddTile(int x, int y, String type) {
		super(PacketTypes.ADDTILE.getId());
		
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("07" + x + "," + y + "," + type).getBytes();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public String getType() {
		return type;
	}
}
