package com.braten.pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.braten.pong.engine.Game;
import com.braten.pong.game.states.PongState;
import com.braten.pong.game.states.TextState;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Game game = new Game(this);
        game.pushState(new PongState());
        game.pushState(new TextState("PLAY!"));
        game.run();
        
        setContentView(game);
    }


    @Override
    protected void onPause() {
      super.onPause();
      finish();
    }
}