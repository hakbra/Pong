package com.braten.pong.engine;


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	protected SurfaceHolder surfaceHolder;
	protected Engine gamePanel;

	private static final String TAG = GameThread.class.getSimpleName();
	
	public GameThread(SurfaceHolder sh, Engine gp)
	{
		this.surfaceHolder = sh;
		this.gamePanel = gp;
	}

	// flag to hold game state
	private boolean running;
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		Log.d(TAG, "Starting game loop");
		while (!running);
		this.gamePanel.init();
		while (running) {
			this.gamePanel.update();
			
			Canvas canvas;
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas();
				this.gamePanel.render(canvas);
			} catch (NullPointerException e) {
				if (running)
					throw e;
			}
			finally {
				if (canvas != null)
					surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		Log.d(TAG, "Ending game loop");
	}

	public boolean isRunning() {
		return running;
	}
}