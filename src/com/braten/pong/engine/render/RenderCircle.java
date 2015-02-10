package com.braten.pong.engine.render;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RenderCircle extends RenderShape {

	float rad;
	
	public RenderCircle(float r) {
		this.rad = r;
	}

	@Override
	public void render(Canvas canvas, float x, float y) {
		Paint paint = new Paint();
		paint.setColor(color);
		
		canvas.drawCircle(x, y, rad, paint);
	}

}
