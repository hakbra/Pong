package com.braten.pong.engine.collision;

import java.util.ArrayList;

import com.braten.pong.engine.utils.Vector;

public class CollisionCircle extends CollisionShape {

	float rad;
	
	public CollisionCircle(float r) {
		this.rad = r;
	}

	public float getR() {
		return rad;
	}
	
	public float getW() {
		return rad;
	}
	
	public float getH() {
		return rad;
	}

	@Override
	public Vector[] getAxes() {
		ArrayList<Vector> axes = new ArrayList<Vector>();
		for (float i = 0; i <= 2*Math.PI; i += Math.PI / 20) {
			Vector axis = new Vector((float) Math.cos(i), (float) Math.sin(i));
			axes.add(axis);
		}
		return axes.toArray(new Vector[axes.size()]);
	}

	@Override
	public Vector project(Vector axis) {
		float min = Float.MAX_VALUE; // +infinity
		float max = -Float.MAX_VALUE; // -infinity
		
		float x = parent.getPhysics().getX();
		float y = parent.getPhysics().getY();
		
		float d = axis.dot(x, y);
		min = Math.min(min, d-rad);
		max = Math.max(max, d+rad);
		
		return new Vector(min, max);
	}
}
