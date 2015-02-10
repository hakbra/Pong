package com.braten.pong.engine.msg;

import com.braten.pong.engine.GameObject;

public interface EventListener {
	public void handleEvent(Event e, GameObject a, GameObject b);

}
