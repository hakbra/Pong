package com.braten.pong.engine.render;


public abstract class RenderShape extends Drawable {

	int color;
	int alpha;
	

	public void setColor(int c) {
		this.color = c;
	}
}
