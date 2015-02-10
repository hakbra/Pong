package com.braten.pong.game.states;

import android.graphics.Color;
import android.view.MotionEvent;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.State;
import com.braten.pong.engine.render.RenderText;

public class TextState extends State {

	String t;
	
	public TextState(String t) {
		this.t = t;
	}
	
	@Override
	public void init() {
		GameObject go = new GameObject(this, "Text");
		
		RenderText rt = new RenderText();
		rt.setSize(100);
		rt.setColor(Color.WHITE);
		rt.setText(t);
		go.setDrawable(rt);
			
		go.getPhysics().setPosition(game.getWidth() / 2, game.getHeight() / 2);
		
		this.addGameObject(go);
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			game.popState();
		return true;
	}
}
