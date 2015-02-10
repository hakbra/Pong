package com.braten.pong.engine.collision;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.utils.Vector;

public abstract class CollisionShape {

	GameObject parent;
	boolean isSolid = true;
	private boolean bounce = false;
	int value = 0;
	
	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public void setSolid(boolean move) {
		this.isSolid = move;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setBounce(boolean bounce) {
		this.bounce = bounce;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	

	public abstract Vector[] getAxes();
	public abstract Vector project(Vector axis);
	public abstract float getW();
	public abstract float getH();

	public boolean isBounce() {
		return bounce;
	}
}
