package com.braten.pong.game.objects;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionRectangle;

public class BottomBorder extends GameObject {

	float targetY;
	
	public BottomBorder(State s) {
		super(s, "BottomBorder");
	}

	@Override
	public void init() {
		float sw = state.getGame().getWidth();
		float sh = state.getGame().getHeight();
		
		float h = 100;
		
		physics.setPosition(sw / 2, sh + h/2);
		
		CollisionRectangle cr = new CollisionRectangle(sw, h);
		cr.setValue(0);
		this.setCollision(cr);
	}
}
