package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import window.Game;

public class Player extends GameObject{
	
	private Handler handler;
	
	private Color color;
	private Random random = new Random();
	private float frameDelay =0;
	private float beforeX,beforeY ;
	private long frame;
	private float ticks = 0;
	

	public Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		width = 32;
		height = 32;
		velX=random.nextInt(10)+1;
		velY=random.nextInt(10)+1;

		frame = System.currentTimeMillis();

		
	}

	@Override
	public void tick() {
		
		
		
		x += velX;
		y += velY;
		frameDelay++;
		ticks = ticks + 0.5f;
		
		beforeX = x + random.nextInt(20);
		beforeY = y + random.nextInt(20);
		/*
		if(x<0 && velX<0) velX = -velX;
		if(x>Game.WIDTH - width  && velX>0) velX = -velX;
		if(y<0 && velY<0) velY = -velY;
		if(y>Game.HEIGHT - height && velY>0) velY = -velY;
		*/
		color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ID.Particles){
				if(frameDelay >= 4f){
					handler.removeObject(temp);
					frameDelay = 0;
				}

			}
		}
	
		
		if(x<0 ) x = 0;
		if(x>Game.WIDTH - width) x= Game.WIDTH - width;
		if(y<0 ) y = 0;
		if(y>Game.HEIGHT - height) y= Game.HEIGHT - height;

		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail,0.06f, handler));
		
		if(handler.isLeft() || handler.isRight()||handler.isUp()||handler.isDown()){
			
			if(ticks%1.5 == 0){
				handler.addObject(new Particle(beforeX,beforeY , ID.Particles, handler));
			}
			if(ticks>=202){
				ticks=0;
			}
		}

		
		if(handler.isRight()){
			velX = 3;
		}
		else if(!handler.isLeft()){
			velX=0;
		}
		if(handler.isLeft()){
			velX= -3;
		}else if(!handler.isRight()){
			velX=0;
		}
		if(handler.isDown()){
			velY= 3;
		}else if(!handler.isUp()){
			velY=0;
		}
		if(handler.isUp()){
			velY= -3;
		}else if(!handler.isDown()){
			velY=0;
		}

	

		
	}

	@Override
	public void render(Graphics2D g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//g.setColor(color);
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
		
	}

	@Override
	public Rectangle getBounds() {
		
		return null;

	
	}


}
