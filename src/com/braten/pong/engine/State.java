package com.braten.pong.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.braten.pong.engine.msg.Event;
import com.braten.pong.engine.msg.EventHandler;
import com.braten.pong.engine.utils.CollisionUtil;
import com.braten.pong.engine.utils.Vector;

public class State {

	Map<String, GameObject> objectsMap = new HashMap<String, GameObject>();
	ArrayList<GameObject> objectsList = new ArrayList<GameObject>();
	protected Game game;
	Data data = new Data();
	Logic logic;
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setLogic(Logic logic) {
		this.logic = logic;
	}

	public Data getData() {
		return data;
	}

	public void update() {
		for (GameObject go : objectsList)
			go.preUpdate();
		
		for (GameObject go : objectsList)
			go.update();

		for (GameObject go : objectsList)
			go.postUpdate();

		checkCollisions();
	}
	
	public void checkCollisions() {for (GameObject go1 : objectsList)
		for (GameObject go2 : objectsList) {
			if (go1 == go2)
				continue;
			if (go1.getCollision() == null || go2.getCollision() == null)
				continue;
			if (go1.getCollision().getValue() < go2.getCollision().getValue())
				continue;
			

			if (! CollisionUtil.collides(go1.getCollision(), go2.getCollision()))
				continue;
			
			Vector displacement = null;
			if (go2.getCollision().isSolid()) {
				displacement = CollisionUtil.getResolutionVector(go1.getCollision(), go2.getCollision());
				go1.getPhysics().addPosition(displacement.getX(), displacement.getY());
			}
			
			if (go2.getCollision().isSolid() && go1.getCollision().isBounce())
				go1.getPhysics().mirrorVelocity(displacement);
			
			EventHandler.get().sendEvent(Event.COLLISION, go1, go2);
		}
	}
	
	public void render(Canvas canvas) {
		for (GameObject go : objectsList)
			go.render(canvas);
	}

	public List<GameObject> getObjects() {
		return objectsList;
	}

	public void addGameObject(GameObject go) {
		objectsMap.put(go.getName(), go);
		objectsList.add(go);
	}

	public void init() {
		for (GameObject go : objectsList)
			go.init();
		
		if (logic != null)
			logic.init();
	}
	
	public GameObject getGameObjectByName(String name) {
		return objectsMap.get(name);
	}
	
	public void deleteGameObjectByName(String name) {
		for (int i = 0; i < objectsList.size(); i++) {
			if (objectsList.get(i).getName() == name) {
				objectsList.remove(i);
				break;
			}
		}
		
		objectsMap.remove(name);
	}

	public boolean onTouch(MotionEvent event) {
		for (GameObject go : objectsList) {
			if (go.onTouch(event))
				return true;
		}
		return false;
	}
}
