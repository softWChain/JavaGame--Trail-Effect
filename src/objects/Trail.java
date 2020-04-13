package objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;

public class Trail extends GameObject{
	
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private float life;
	private Random r = new Random();

	public Trail(float x, float y, ID id, float life ,Handler handler) {
		super(x, y, id);
		this.handler = handler;

		this.life=life;
		width=32;
		height=32;
	}

	@Override
	public void tick() {
		
		if(alpha>life){
			alpha -= life - 0.001f;
		}else{
			handler.removeObject(this);
		}
		
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setComposite(makeTransparent(alpha));
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),150));
		g.fillRect((int)x, (int)y, width, height);
		g.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type,alpha));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
