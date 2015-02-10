package com.braten.pong.engine.collision;

import com.braten.pong.engine.utils.Vector;

public class CollisionRectangle extends CollisionShape {
	
	float w, h;
	
	public CollisionRectangle(float w, float h) {
		this.w = w;
		this.h = h;
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	@Override
	public Vector[] getAxes() {
		return new Vector[]{new Vector(1, 0), new Vector(0, 1), new Vector(0, -1), new Vector(-1, 0)};
	}

	@Override
	public Vector project(Vector axis) {
		float min = Float.MAX_VALUE; // +infinity
		float max = -Float.MAX_VALUE; // -infinity
		
		float x = parent.getPhysics().getX();
		float y = parent.getPhysics().getY();
		
		Vector points[] = {new Vector(x-w/2, y-h/2), new Vector(x+w/2, y-h/2), new Vector(x+w/2, y+h/2), new Vector(x-w/2, y+h/2)};
		
		for(int i = 0; i<4; i++)
		{
			float d = axis.dot(points[i]);
			min = Math.min(min, d);
			max = Math.max(max, d);
		}
		
		return new Vector(min, max);
	}
}
