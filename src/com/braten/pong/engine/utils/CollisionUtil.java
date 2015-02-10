package com.braten.pong.engine.utils;

import com.braten.pong.engine.collision.CollisionShape;

public class CollisionUtil {
	public static boolean collides(CollisionShape a, CollisionShape b) {
		Vector va[] = a.getAxes();
		Vector vb[] = b.getAxes();
		
		for(int i = 0; i<va.length; i++)
		{
			if(separates(a, b, va[i]))
				return false;
		}
		
		for(int i = 0; i<vb.length; i++)
		{
			if(separates(a, b, vb[i]))
				return false;
		}
		
		return true;
	}
	
	public static Vector getResolutionVector(CollisionShape as, CollisionShape bs) {
		// Get the axes for both polygons.
		Vector a[] = as.getAxes();
		Vector b[] = bs.getAxes();
		
		float minOverlap = Float.MAX_VALUE;
		Vector minVector = null;
		
		for(int i = 0; i<a.length; i++)
		{
			float overlap = getOverlap(as, bs, a[i]);
			if (overlap < minOverlap) {
				minOverlap = overlap;
				minVector = a[i];
			}
		}
		
		for(int i = 0; i<b.length; i++)
		{
			float overlap = getOverlap(as, bs, b[i]);
			if (overlap < minOverlap) {
				minOverlap = overlap;
				minVector = b[i];
			}
		}
		
		if(minVector == null)
			return null;
		
		
		return minVector.getMultiplied(-minOverlap);		
	}
	

	private static float getOverlap(CollisionShape as, CollisionShape bs, Vector axis) {
		Vector inta = as.project(axis);
		Vector intb = bs.project(axis);
		if (inta.getY() > intb.getX())
			return inta.getY() - intb.getX();
		return intb.getY() - inta.getX();
	}

	private static boolean separates(CollisionShape a, CollisionShape b,  Vector axis) {
		
		Vector ap = a.project(axis);
		Vector bp = b.project(axis);
		
		if (ap.getY() <= bp.getX())
			return true;
		
		if (bp.getY() <= ap.getX())
			return true;
		
		return false;
	}
}
