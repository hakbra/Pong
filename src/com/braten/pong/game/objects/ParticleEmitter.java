package com.braten.pong.game.objects;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.Physics;
import com.braten.pong.engine.State;
import com.braten.pong.engine.collision.CollisionShape;
import com.braten.pong.engine.msg.Event;
import com.braten.pong.engine.msg.EventHandler;
import com.braten.pong.engine.msg.EventListener;
import com.braten.pong.engine.render.RenderCollection;
import com.braten.pong.engine.render.RenderParticle;

public class ParticleEmitter extends GameObject implements EventListener {
	
	public enum Dir {
		UP, DOWN, RIGHT, LEFT;
	}
	
	public ParticleEmitter(State s) {
		super(s, "ParticleEmitter");
	}

	RenderCollection particles = new RenderCollection();
	
	@Override
	public void init() {
		this.setDrawable(particles);
		
		EventHandler.get().addObserver(Event.COLLISION, this);
	}
	
	
	public void emitTrail(float x, float y) {
		Random r = new Random();
		
		RenderParticle p = new RenderParticle(r.nextFloat()*5+22, r.nextInt(100)+300);
		p.setSpeed(r.nextFloat()*2-1, r.nextFloat()*2-1);
		p.setColor(Color.rgb(255, r.nextInt(255), 0));
		particles.addChild(p, x, y);
	}

	public void emitSparks(int num, float x, float y, Dir d) {
		Random r = new Random();
		
		for (int i = 0; i < num; i++) {
			RenderParticle p = new RenderParticle(r.nextFloat()*5+15, r.nextInt(100)+300);
			
			float vx = 0;
			float vy = 0;
			
			if (d == Dir.UP) {
				vy = r.nextFloat()*3;
				vx = r.nextFloat()*8-4;
			}
			if (d == Dir.DOWN) {
				vy = r.nextFloat()*-3;
				vx = r.nextFloat()*8-4;
			}
			if (d == Dir.LEFT) {
				vx = r.nextFloat()*-3;
				vy = r.nextFloat()*8-4;
			}
			if (d == Dir.RIGHT) {
				vx = r.nextFloat()*3;
				vy = r.nextFloat()*8-4;
			}
			
			p.setSpeed(vx, vy);
			p.setColor(Color.YELLOW);
			particles.addChild(p, x, y);
		}
	}
	
	public void preUpdate() {
		long now = System.currentTimeMillis();
		ArrayList<RenderCollection.Child> children = particles.getChildren();
		for (int i = 0; i < children.size(); i++)
		{
			RenderParticle p = (RenderParticle) children.get(i).d;
			if (p.born + p.life < now) {
				children.remove(i);
				i--;
			}
		}
	}
	
	public void postUpdate() {
		Physics ballPhysics = state.getGameObjectByName("Ball").getPhysics();
		emitTrail(ballPhysics.getX(), ballPhysics.getY());
	}


	@Override
	public void handleEvent(Event e, GameObject a, GameObject b) {
		if (!(a instanceof Ball))
			return;

		Physics ballP = state.getGameObjectByName("Ball").getPhysics();
		CollisionShape ballC = state.getGameObjectByName("Ball").getCollision();
		
		float sx = ballP.getX();
		float sy = ballP.getY();
		Dir d = Dir.UP;

		if (b instanceof CpuPaddle) {
			sx = ballP.getX() - ballC.getW() / 2;
			d = Dir.RIGHT;
		}
		if (b instanceof HumanPaddle) {
			sx = ballP.getX() + ballC.getW() / 2;
			d = Dir.LEFT;
		}
		if (b instanceof TopBorder) {
			sy = ballP.getY() + ballC.getH() / 2;
			d = Dir.DOWN;
		}
		if (b instanceof BottomBorder) {
			sy = ballP.getY() - ballC.getH() / 2;
			d = Dir.UP;
		}

		emitSparks(30, sx, sy, d);
	}
}
