package com.braten.pong.game.objects;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionRectangle;

public class TopBorder extends GameObject {

	float targetY;
	
	public TopBorder(State s) {
		super(s, "TopBorder");
	}

	@Override
	public void init() {
		float sw = state.getGame().getWidth();
		
		float h = 100;
		
		physics.setPosition(sw / 2, -h/2);
		
		CollisionRectangle cr = new CollisionRectangle(sw, h);
		cr.setValue(0);
		this.setCollision(cr);
	}
}
