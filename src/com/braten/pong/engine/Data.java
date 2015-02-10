package com.braten.pong.engine;

import java.util.HashMap;
import java.util.Map;

public class Data {
	Map<String, Integer> data = new HashMap<String, Integer>();
	
	public void set(String name, int value) {
		data.put(name, value);
	}
	
	public Integer get(String name) {
		return data.get(name);
	}
	
	public void inc(String name) {
		int value = data.get(name);
		this.set(name, value+1);
	}
}
