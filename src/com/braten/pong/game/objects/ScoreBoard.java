package com.braten.pong.game.objects;

import android.graphics.Color;

import com.braten.pong.engine.Data;
import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.msg.Event;
import com.braten.pong.engine.msg.EventHandler;
import com.braten.pong.engine.msg.EventListener;
import com.braten.pong.engine.render.RenderCollection;
import com.braten.pong.engine.render.RenderText;

public class ScoreBoard extends GameObject implements EventListener {

	RenderText cpu;
	RenderText human;
	
	public ScoreBoard(State s) {
		super(s, "ScoreBoard");
	}
	
	@Override
	public void init() {
		float sw = state.getGame().getWidth();
		float sh = state.getGame().getHeight();
		
		physics.setPosition(sw / 2, sh / 2);
		
		RenderCollection col = new RenderCollection();
		
		cpu = new RenderText();
		cpu.setColor(Color.GREEN);
		cpu.setSize(200);
		cpu.setText("test");
		col.addChild(cpu, -sw / 4, 0);
		
		human = new RenderText();
		human.setColor(Color.GREEN);
		human.setSize(200);
		human.setText("test");
		col.addChild(human, sw / 4, 0);
		
		this.setDrawable(col);
		
		EventHandler.get().addObserver(Event.UPDATE_SCOREBOARD, this);
	}

	@Override
	public void handleEvent(Event e, GameObject a, GameObject b) {
		if (e != Event.UPDATE_SCOREBOARD)
			return;
		
		Data d = state.getData();
		cpu.setText(d.get("CpuScore").toString());
		human.setText(d.get("HumanScore").toString());
	}
}
