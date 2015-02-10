package com.braten.pong.engine.render;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RenderParticle extends RenderCircle{

	public long born;
	public long life;
	public float vx = 0, vy = 0;
	float dx = 0, dy = 0;
	
	public RenderParticle(float r, long life) {
		super(r);
		
		this.life = life;
		this.born = System.currentTimeMillis();
	}
	
	public void setSpeed(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void render(Canvas canvas, float x, float y) {
		dx += vx;
		dy += vy;
		
		long now = System.currentTimeMillis();
		float l = 1 - (float)(now - born) / (float)life;
		int alpha = (int) (255.0 * l);
		float trad = l*rad;
		
		Paint p = new Paint();
		p.setColor(color);
		p.setAlpha(alpha);
		
		canvas.drawCircle(x + dx, y + dy, trad, p);
	}
	
	public void setR(float r) {
		this.rad = r;
	}

}
