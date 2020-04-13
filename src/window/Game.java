package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import framework.Handler;
import framework.ID;
import input.KeyInput;
import objects.Player;



public class Game extends Canvas implements Runnable{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 500;
	
	private Window window;
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private Random random = new Random();

	
	
	
	public Game(){
		setFocusable(true);
		window = new Window(WIDTH,HEIGHT,this);
		
	}
	
	public void init(){
		handler = new Handler();
		
		
		handler.addObject(new Player(random.nextInt(550), random.nextInt(450), ID.Player, handler));

		addKeyListener(new KeyInput(handler));
	}
	
	public void tick(){
		
		handler.tick();
		
	
	}
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(g);
		
		bs.show();
		g.dispose();
		
	}
	


	
	public void run(){
		init();
		
		int FPS = 60;
		double targetFPS = 1000000000 / FPS;
		double delta = 0;
		
		long lastTime = System.nanoTime();
		long now;
		long timer = System.currentTimeMillis();
		
		int ticks=0;
		int updates = 0;
		
		while(running){
			
			now = System.nanoTime();
			delta +=(now - lastTime ) / targetFPS;
			lastTime = now;
			
			if(delta>=1){
				tick();
				ticks++;
				delta--;
			}
			render();
			updates++;
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println("FPS : " + ticks + "  - UPDATES : " + updates);
				ticks = 0;
				updates = 0;
			}
			
			
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this,"ThreadGame");
		thread.start();
		
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String args[]){
		new Game().start();
	}
	

	

	
	

}
