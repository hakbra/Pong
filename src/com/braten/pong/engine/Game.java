package com.braten.pong.engine;

import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.braten.pong.engine.msg.EventHandler;

public class Game extends Engine {
	public Game(Context c) {
		super(c);
	}
	
	public void run() {
		thread.start();
	}
	
	public void stop() {
		thread.setRunning(false);
		((Activity)getContext()).finish();
	}
	
	public boolean isStackOK() {
		if (states.empty()) {
			stop();
			return false;
		}
		return true;
			
	}

	Stack<State> states = new Stack<State>();

	@Override
	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
		if (isStackOK())
			states.peek().render(canvas);
	}

	@Override
	public void update() {
		if (isStackOK())
			states.peek().update();
		
		EventHandler.get().releaseAll();
	}
	
	@Override
	public void init() {
	  for (State s : states)
		  s.init();
	}

	public void pushState(State state) {
		state.setGame(this);
		if (thread.isRunning())
			state.init();
		states.push(state);
	}

	@Override
	public boolean onTouch(MotionEvent event) {
		if (isStackOK())
			return states.peek().onTouch(event);
		return false;
	}

	public void popState() {
		states.pop();
	}
}