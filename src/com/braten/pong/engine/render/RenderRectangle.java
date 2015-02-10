package com.braten.pong.engine.render;

import android.graphics.Canvas;
import android.graphics.Paint;


public class RenderRectangle extends RenderShape {

	float w, h;
	
	public RenderRectangle(float w, float h) {
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void render(Canvas canvas, float x, float y) {
		Paint paint = new Paint();
		paint.setColor(color);
		
		canvas.drawRect(x-w/2, y-h/2, x+w/2, y+h/2, paint);
	}

}
