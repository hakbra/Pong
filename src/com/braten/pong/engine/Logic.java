package com.braten.pong.engine;

public abstract class Logic {
	protected State state;
	public Logic(State s)
	{this.state = s;}
	public void init() {}
}
