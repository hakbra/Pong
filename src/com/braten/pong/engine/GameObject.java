package com.braten.pong.engine;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.braten.pong.engine.collision.CollisionShape;
import com.braten.pong.engine.render.Drawable;

public class GameObject {

	String name;
	protected State state;
	protected Drawable drawable;
	protected CollisionShape collision;
	protected Physics physics = new Physics();
	
	public GameObject(State s, String name) {
		this.state = s;
		this.name = name;
	}
	
	public State getState() {
		return state;
	}

	public String getName() {
		return name;
	}
	
	public CollisionShape getCollision() {
		return collision;
	}

	public void setCollision(CollisionShape collision) {
		this.collision = collision;
		this.collision.setParent(this);
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable d) {
		this.drawable = d;
	}
	
	public void preUpdate() {	
	}
	
	public void postUpdate() {
	}
	
	public void update() {
		physics.move();
	}
	
	public void render(Canvas canvas) {
		if (drawable != null)
			drawable.render(canvas, physics.getX(), physics.getY());
	}

	public void init() {
	}
	
	public boolean onTouch(MotionEvent event) {
		return false;
	}
}
