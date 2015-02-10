package com.braten.pong.engine;

import com.braten.pong.engine.utils.MathUtil;
import com.braten.pong.engine.utils.Vector;

public class Physics {	
	float x, y;
	float vx, vy;
	float maxSpeed = 100;
	
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public float getVx() {
		return vx;
	}


	public void setVx(float vx) {
		this.vx = vx;
	}


	public float getVy() {
		return vy;
	}


	public void setVy(float vy) {
		this.vy = vy;
	}


	public void move() {
		if (getSpeed() > maxSpeed)
			normaliseSpeed();
		
		x += vx;
		y += vy;
	}

	private void normaliseSpeed() {
		float speed = getSpeed();
		vx *= maxSpeed / speed;
		vy *= maxSpeed / speed;
	}

	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}

	public void addPosition(float ax, float ay) {
		setX(x+ax);
		setY(y+ay);
	}
	
	public void setVelocity(float vx, float vy) {
		setVx(vx);
		setVy(vy);
	}
	
	public float getSpeed() {
		return (float) Math.sqrt(vx*vx+vy*vy);
	}

	public void multiplySpeed(float mult) {
		vx *= mult;
		vy *= mult;
	}

	public void mirrorVelocity(Vector displacement) {
		float dx = displacement.getX();
		float dy = displacement.getY();
		
		float deltadist = MathUtil.dist(dx,  dy);
		dx /= deltadist;
		dy /= deltadist;
		
		float dot = vx*dx + vy*dy;
		vx = vx - 2*dot*dx;
		vy = vy - 2*dot*dy;
	}
}
