package com.braten.pong.game.objects;

import android.graphics.Color;
import android.view.MotionEvent;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionRectangle;
import com.braten.pong.engine.render.RenderRectangle;

public class HumanPaddle extends GameObject {

	float targetY;
	
	public HumanPaddle(State s) {
		super(s, "HumanPaddle");
	}

	@Override
	public void init() {
		physics.setPosition(state.getGame().getWidth() - 100, state.getGame().getHeight() / 2);
		physics.setMaxSpeed(20);
		targetY = physics.getY();
		
		RenderRectangle rect = new RenderRectangle(25, 100);
		rect.setColor(Color.WHITE);
		drawable = rect;
		
		CollisionRectangle cr = new CollisionRectangle(25, 100);
		cr.setValue(2);
		this.setCollision(cr);
	}
	
	@Override
	public void preUpdate() {
		physics.setVy(targetY - physics.getY());
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				targetY = event.getY();
				return true;
		}
		
		return false;
	}
}
