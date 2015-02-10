package com.braten.pong.game.objects;

import java.util.Random;

import android.graphics.Color;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionCircle;
import com.braten.pong.engine.msg.Event;
import com.braten.pong.engine.msg.EventHandler;
import com.braten.pong.engine.msg.EventListener;
import com.braten.pong.engine.render.RenderCircle;

public class Ball extends GameObject implements EventListener{

	public Ball(State s) {
		super(s, "Ball");
	}
	
	@Override
	public void init() {
		this.reset();
		physics.setMaxSpeed(20);
		
		RenderCircle circle = new RenderCircle(25);
		circle.setColor(Color.WHITE);
		this.setDrawable(circle);
		
		CollisionCircle cc = new CollisionCircle(25);
		cc.setValue(3);
		cc.setBounce(true);
		cc.setSolid(true);
		this.setCollision(cc);
		
		EventHandler.get().addObserver(Event.COLLISION, this);
	}
	
	@Override
	public void postUpdate() {
		if (physics.getX() < 0) {
			EventHandler.get().sendEvent(Event.SCORE_HUMAN, null, null);
			EventHandler.get().sendEvent(Event.UPDATE_SCOREBOARD, null, null);
			this.reset();
		}
		if (physics.getX() > state.getGame().getWidth()) {
			EventHandler.get().sendEvent(Event.SCORE_CPU, null, null);
			EventHandler.get().sendEvent(Event.UPDATE_SCOREBOARD, null, null);
			this.reset();
		}
		
		if (Math.abs(physics.getVx()) < 5)
			physics.setVx(Math.signum(physics.getVx())*5);
	}
	
	public void reset() {
		Random r = new Random();
		physics.setPosition(state.getGame().getWidth() / 2, state.getGame().getHeight() / 2);
		physics.setVelocity(7*Math.signum(r.nextFloat()*2-1), r.nextFloat()*6-3);
	}

	@Override
	public void handleEvent(Event e, GameObject a, GameObject b) {
		if (a == this && (b instanceof HumanPaddle || b instanceof CpuPaddle))
			physics.multiplySpeed(1.07f);
	}
}
