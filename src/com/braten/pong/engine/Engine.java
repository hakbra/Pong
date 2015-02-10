package com.braten.pong.engine;



import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public abstract class Engine extends SurfaceView implements SurfaceHolder.Callback {

	protected GameThread thread;
	
	public Engine(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);

		thread = new GameThread(getHolder(), this);
	}
	
	public abstract void render(Canvas canvas);
	public abstract void update();
	public abstract void init();
	public abstract boolean onTouch(MotionEvent event);
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.onTouch(event))
			return true;
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		thread.setRunning(true);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.d("Engine", "Surface destroyed");
		thread.setRunning(false);
		while (true) {
			try {
				thread.join();
				break;
			} catch (Exception e) {
				
			}
		}
	}
}