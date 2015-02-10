package com.braten.pong.game.states;

import android.graphics.Color;
import android.view.MotionEvent;

import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.Physics;
import com.braten.pong.engine.State;
import com.braten.pong.engine.render.RenderText;
import com.braten.pong.engine.utils.MathUtil;

public class PlayAgainState extends State {
	
	Physics no;
	Physics yes;
	int change = 0;

	public void init() {
		float sw = game.getWidth();
		float sh = game.getHeight();

		RenderText titleText = new RenderText();
		titleText.setColor(Color.WHITE);
		titleText.setSize(100);
		titleText.setText("PLAY AGAIN?");
		
		GameObject title = new GameObject(this, "title");
		title.getPhysics().setPosition(sw / 2, sh / 3);
		title.setDrawable(titleText);
		
		RenderText yesText = new RenderText();
		yesText.setColor(Color.WHITE);
		yesText.setSize(80);
		yesText.setText("Yes");
		
		GameObject yes = new GameObject(this, "yes");
		yes.getPhysics().setPosition(3 * sw / 4, 2*sh / 3);
		yes.setDrawable(yesText);
		
		RenderText noText = new RenderText();
		noText.setColor(Color.WHITE);
		noText.setSize(80);
		noText.setText("No");
		
		GameObject no = new GameObject(this, "no");
		no.getPhysics().setPosition(sw / 4, 2*sh / 3);
		no.setDrawable(noText);
		
		this.addGameObject(title);
		this.addGameObject(yes);
		this.addGameObject(no);
		
		this.yes = yes.getPhysics();
		this.no = no.getPhysics();
	}
	
	public void update() {
		if (change == 1) {
			game.popState();
			game.pushState(new PongState());
		} else if (change == 2) {
			game.popState();
		}
	}
	

	public boolean onTouch(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return false;
		
		if (MathUtil.dist(event.getX(), event.getY(), yes.getX(), yes.getY()) < 100) {
			change = 1;
			return true;
		}
		
		if (MathUtil.dist(event.getX(), event.getY(), no.getX(), no.getY()) < 100) {
			change = 2;
			return true;
		}
		
		return false;
	}
}
