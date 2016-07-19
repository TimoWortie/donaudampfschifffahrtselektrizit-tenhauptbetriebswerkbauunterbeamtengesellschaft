package network;

import java.net.InetAddress;

import javax.swing.JFrame;

import network.mysql.*;

import Entity.Player;
import Terracraft.Game;
import Terracraft.Id;
import Tile.TestTile;
import network.abstracts.NetClient;
import network.packets.Packet;
import network.packets.Packet.PacketTypes;
import network.packets.Packet00Login;
import network.packets.Packet01Disconnect;
import network.packets.Packet02Move;
import network.packets.Packet05Spawn;
import network.packets.Packet06Message;
import network.packets.Packet07AddTile;

public class Client extends NetClient {

	private Game terracraft = null;

	public Client(Terracraft.Game game, int packetSize) {
		super(game, packetSize);
	}

	public Client(int packetSize) {
		super(packetSize);
	}

	protected void init() {
	}

	protected void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet00 = new Packet00Login(data);
			if (!username.equals(packet00.getUsername()))
				Terracraft.Game.handler.addEntity(
						new Player(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.player));
			break;
		case DISCONNECT:
			Packet01Disconnect packet01 = new Packet01Disconnect(data);
			Terracraft.Game.handler.removePlayer(packet01.getUsername());
			break;
		case MOVE:
			Packet02Move packet02 = new Packet02Move(data);
			Terracraft.Game.handler.setPlayerPosition(packet02.getUsername(), packet02.getX(), packet02.getY());
			break;
		case SPAWN:
			Packet05Spawn packet05 = new Packet05Spawn(data);
			terracraft = new Game(packet05.getX(), packet05.getY(), this);
			JFrame frame = new JFrame("TerraCraft");
			frame.add(terracraft);
			frame.pack();
			frame.setBounds(0, 0, 320 * 4, 180 * 4);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			terracraft.start();
			break;
		case MESSAGE:
			Packet06Message packet06 = new Packet06Message(data);
			if (packet06.getText()
					.equalsIgnoreCase("Du hast dich erfolgreich registriert und kannst dich nun anmelden!")) {
				Register.setStatus(packet06.getText());
			} else if (packet06.getText()
					.equalsIgnoreCase("Der angegeben Username ist bereits benutzt! Suche dir einen anderen aus.")) {
				Register.setStatus(packet06.getText());
			} else if (packet06.getText().equalsIgnoreCase("Benutzername oder Passwort falsch!")) {
				Login.setStatus(packet06.getText());
			} else {
				System.out.println("[FEHLER] MELDUNG SOLLTE AN CLIENT GESENDET WERDEN: " + packet06.getText());
			}
			break;
		case ADDTILE:
			Packet07AddTile packet07 = new Packet07AddTile(data);
			if (packet07.getType().equalsIgnoreCase("TestTile")) {
				terracraft.handler
						.addTile(new TestTile(packet07.getX(), packet07.getY(), 64, 64, terracraft.handler, Id.test));
			}
		break;
		}
	}

	@Override
	protected void shutdown() {

	}
}
