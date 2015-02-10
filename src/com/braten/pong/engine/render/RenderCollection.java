package com.braten.pong.engine.render;

import java.util.ArrayList;

import android.graphics.Canvas;

public class RenderCollection extends Drawable {

	public class Child {
		public Drawable d;
		float x, y;
	}
	
	ArrayList<Child> children = new ArrayList<Child>();
	
	public void addChild(Drawable d, float x, float y) {
		Child c = new Child();
		c.d = d;
		c.x = x;
		c.y = y;
		children.add(c);
	}
	
	@Override
	public void render(Canvas canvas, float x, float y) {
		for (Child c : children)
			c.d.render(canvas, x+c.x, y + c.y);
	}

	public ArrayList<Child> getChildren() {
		return children;
	}

}
