package cn.lqy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class PlaneObject extends GameObject{
	
	boolean left,up,right,down;
	boolean live=true;
	public void drawSelf(Graphics g) {
		if(live) {
//		g.drawImage(img,(int)x,(int)y,null);
		g.drawImage(img, (int)x,(int) y, 10, 10, null);
		if(left) {
			x-=speed;
		}
		if(right) {
			x+=speed;
		}
		if(up) {
			y-=speed;
		}
		if(down) {
			y+=speed;
		}
		}else {
			
		}
//		x++;
	}
	public PlaneObject(Image img,double x,double y) {
		this.img=img;
		this.x=x;
		this.y=y;
		this.width=10;
		this.height=10;
	}
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			left=true;
			break;
		case 38:
			up=true;
			break;
		case 39:
			right=true;
			break;
		case 40:
			down=true;
			break;
		}
	}
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			left=false;
			break;
		case 38:
			up=false;
			break;
		case 39:
			right=false;
			break;
		case 40:
			down=false;
			break;
		}
	}
}
