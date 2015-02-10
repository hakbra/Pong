package com.braten.pong.engine.msg;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import com.braten.pong.engine.GameObject;

public class EventHandler {
	private class Payload{
		public Event e;
		public GameObject a;
		public GameObject b;
		public Payload(Event e, GameObject a, GameObject b) {
			this.e = e;
			this.a = a;
			this.b = b;
		}
	}

	private static EventHandler instance = null;
	
	public static EventHandler get() {
		if(instance == null)
			instance = new EventHandler();
		return instance;
	}
	
	HashMap<Event, ArrayList<EventListener>> observers = new HashMap<Event, ArrayList<EventListener>>();
	Deque<Payload> events = new LinkedList<Payload>();

	public void releaseEvent(Event e, GameObject a, GameObject b) {
		if (observers.get(e) == null)
			return;
		for (EventListener eh : observers.get(e))
			eh.handleEvent(e, a, b);
	}

	public void sendEvent(Event e, GameObject a, GameObject b) {
		events.addFirst(new Payload(e, a, b));
	}
	
	public void addObserver(Event e, EventListener eh) {
		if (observers.get(e) == null)
			observers.put(e, new ArrayList<EventListener>());
		if (!observers.get(e).contains(eh))
		observers.get(e).add(eh);
	}

	public void releaseAll() {
		while (!events.isEmpty()) {
			Payload p = events.removeLast();
			releaseEvent(p.e,  p.a,  p.b);
		}
	}
}
