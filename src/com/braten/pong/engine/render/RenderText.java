package com.braten.pong.engine.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class RenderText  extends RenderShape {

	int size;
	String text = "";
	float w;
	float h;
	
	public void setText(String text) {
		this.text = text;
		setSize(size);
	}

	public void setSize(int size) {
		this.size = size;
		
		Paint p = new Paint();
		p.setColor(color);
		p.setTextSize(size);
		w = p.measureText(text);

		Rect bounds = new Rect();
		p.getTextBounds("0", 0, 1, bounds);
		h = bounds.height();
	}

	@Override
	public void render(Canvas canvas, float x, float y) {
		Paint p = new Paint();
		p.setColor(color);
		p.setTextSize(size);
		
		canvas.drawText(text, x-w/2, y+h/2, p);
	}
}
