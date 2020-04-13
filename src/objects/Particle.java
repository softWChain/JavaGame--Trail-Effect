package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;

public class Particle extends GameObject {

	private Handler handler;
	private int width,height;
	private Random r = new Random();
	private Color color;
	private int ticks=0;
	
	public Particle(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}

	@Override
	public void tick() {
		
		width = r.nextInt(10);
		height = r.nextInt(10);
		color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),200);
		
	
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		
	
		g.fillRect((int)x, (int)y, width, height);

		
	
	
		
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}

}
