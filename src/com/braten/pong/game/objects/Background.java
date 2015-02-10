package com.braten.pong.game.objects;

import android.graphics.Color;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.render.RenderCollection;
import com.braten.pong.engine.render.RenderRectangle;

public class Background extends GameObject{
	
	public Background(State s) {
		super(s, "background");
	}

	@Override
	public void init() {
		float sw = state.getGame().getWidth();
		float sh = state.getGame().getHeight();
		
		physics.setX(sw/2);
		
		RenderRectangle rr = new RenderRectangle(10, 50);
		rr.setColor(Color.GREEN);
		
		RenderCollection col = new RenderCollection();
		
		for (int i = 0; i < sh; i += 100)
			col.addChild(rr, 0, i);
	
		this.setDrawable(col);
}
}
