package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import Terracraft.Game;
import Terracraft.Handler;
import Terracraft.Id;
import Tile.source.Tile;


public class Entity {

	public int x, y, breite, h�he, velX, velY;
	private boolean removed;
	Handler handler;
	Id id;
	public int frame,framedelay,frame2,framedelay2;
	public int moving=-1;
	public boolean jumping = false,falling = true;
	public float gravity = 0f;
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
	
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

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
	public void jumping(float grav){
		gravity -= grav;
		setVelY((int) -gravity);
		if (gravity <= 0.0f) {
			falling = true;
			jumping = false;
		}
	}
	
	
	public void falling(){
		if (falling) {
			gravity += 0.5f;
				if (y>Game.getFrameH�he()-200) {
					
						gravity = 0f;
						jumping = false;
						falling = false;
					
				}

				for(Tile ti : handler.tile2){

				if (getBottom().intersects(ti.getTop())) {
					
					
					
					
					gravity=0f;
					jumping = false;
					falling = false;
					
				}
			}
				
		}
			setVelY((int) gravity);
		}
	
	public Rectangle getBounds() {
		
		return new Rectangle(getX(), getY(),breite,h�he);
			
	}
	public Rectangle getBottom() {
		
		return new Rectangle(getX()+6, getY()+h�he-16,52,16);
		
	}
	public Rectangle getRight() {
		
		return new Rectangle(getX()+breite-5, getY()+5,5, h�he-10);
		
	}
	public Rectangle getLeft() {
		
		return new Rectangle(getX(), getY()+5,5, h�he-10);
		
	}
	public Rectangle getTop() {
		
		return new Rectangle(getX()+5, getY(),54,16);
		
	}
	
	
}
