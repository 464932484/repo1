package cn.lqy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	 Image img;
	 double x,y;
	 
	  int speed;
	 int width,height;
	 public void drawSelf(Graphics g) {
		 g.drawImage(img,(int)x,(int)y,null);
	 }
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	public GameObject() {
		
	}
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,width,height);
	}
}
