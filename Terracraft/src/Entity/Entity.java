package Entity;

import java.awt.Graphics;

import Terracraft.Handler;
import Terracraft.Id;

public class Entity {

	public int x, y, breite, h�he, velX, velY;
	private boolean removed;
	Handler handler;
	Id id;
	
	public Entity(int x, int y, int breite, int h�he, Handler handler, Id id) {
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.h�he = h�he;
		this.handler = handler;
		this.id = id;
	}

	public void render(Graphics g){}
	public void tick(){}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getH�he() {
		return h�he;
	}

	public void setH�he(int h�he) {
		this.h�he = h�he;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
}
