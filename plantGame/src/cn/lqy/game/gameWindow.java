package cn.lqy.game;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JFrame;
public class gameWindow extends Frame {

	Image planeImg = GameUtil.getImage("images/plant.png");
	Image bg=GameUtil.getImage("images/bg.jpg");
	int planeX=250,planeY=250;
	
	PlaneObject plane=new PlaneObject(planeImg,250,250);
	Shell shell=new Shell();
	Shell[] shells=new Shell[50];
	Explode bao;
	Date startTime=new Date();
	Date endTime;
	int period;
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
//		g.setColor(Color.blue);
//		g.drawLine(100, 100, 300, 300);
//		g.draw3DRect(60, 60, 33, 33,true);
		
		g.drawImage(bg,0,0,null);
		
		plane.drawSelf(g);
		plane.speed=10;
		shell.draw(g);
//		shell.speed=20;
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);
			
			boolean peng=shells[i].getRect().intersects(plane.getRect());
			if(peng) {

				plane.live=false;
				if(bao==null) {
				bao=new Explode(plane.x,plane.y);
				endTime=new Date();
				period=(int)((endTime.getTime()-startTime.getTime())/1000);
				System.out.println(period);
				}
				bao.draw(g);
				
				
			}
			
		
		}
		if(!plane.live) {
			g.setColor(Color.pink);
			g.drawString("shijian"+period,(int)plane.x,(int)plane.y);
		}
	}
	
	 
	class PaintThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
//				System.out.println("paint");
				repaint();
				try {
				Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getKeyCode());
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getKeyCode());
			plane.minusDirection(e);
		}
		
	}
	public void launchFrame() {
		this.setTitle("飞机大战");
		this.setVisible(true);
		this.setSize(500,500);
		this.setLocation(300,300);
		this.addWindowListener((WindowListener) new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		for(int i=0;i<shells.length;i++) {
			shells[i]=new Shell();
		}
	}
	public static void main(String[] args) {
		
		System.out.println(GameUtil.class.getClassLoader().getResource(""));
		gameWindow frame=new gameWindow();
		frame.launchFrame();
	}
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
