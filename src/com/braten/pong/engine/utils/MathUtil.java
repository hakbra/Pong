package com.braten.pong.engine.utils;

public class MathUtil {
	public static float dist(float x, float y, float x2, float y2) {
		return MathUtil.dist(x2-x,  y2-y);
	}
	
	public static float dist(float x, float y) {
		return (float) Math.sqrt(x*x+y*y);
	}
}
