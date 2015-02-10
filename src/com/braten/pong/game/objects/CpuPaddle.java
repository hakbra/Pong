package com.braten.pong.game.objects;

import android.graphics.Color;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionRectangle;
import com.braten.pong.engine.render.RenderRectangle;

public class CpuPaddle extends GameObject {

	public CpuPaddle(State s) {
		super(s, "CpuPaddle");
	}

	@Override
	public void init() {
		physics.setPosition(100, state.getGame().getHeight() / 2);
		physics.setMaxSpeed(7);
		
		RenderRectangle rect = new RenderRectangle(25, 100);
		rect.setColor(Color.WHITE);
		this.setDrawable(rect);
		
		CollisionRectangle cr = new CollisionRectangle(25, 100);
		cr.setValue(2);
		this.setCollision(cr);
	}
	
	@Override
	public void preUpdate() {
		GameObject ball = state.getGameObjectByName("Ball");
		float ballY = ball.getPhysics().getY();
		this.physics.setVy(ballY - physics.getY());
	}
}
