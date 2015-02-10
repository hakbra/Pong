package com.braten.pong.engine.render;

import android.graphics.Canvas;

public abstract class Drawable {
	float x, y;

	public abstract void render(Canvas canvas, float x, float y);
}
