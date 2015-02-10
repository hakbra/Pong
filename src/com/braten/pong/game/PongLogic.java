package com.braten.pong.game;

import com.braten.pong.engine.Data;
import com.braten.pong.engine.GameObject;
import com.braten.pong.engine.Logic;
import com.braten.pong.engine.State;
import com.braten.pong.engine.msg.Event;
import com.braten.pong.engine.msg.EventHandler;
import com.braten.pong.engine.msg.EventListener;
import com.braten.pong.game.states.PlayAgainState;
import com.braten.pong.game.states.TextState;

public class PongLogic extends Logic implements EventListener {

	public PongLogic(State s) {
		super(s);
	}

	@Override
	public void init() {
		Data d = state.getData();
		d.set("WinScore", 21);
		d.set("CpuScore", 0);
		d.set("HumanScore", 0);

		EventHandler.get().sendEvent(Event.UPDATE_SCOREBOARD, null, null);

		EventHandler.get().addObserver(Event.SCORE_HUMAN, this);
		EventHandler.get().addObserver(Event.SCORE_CPU, this);
	}

	@Override
	public void handleEvent(Event e, GameObject go1, GameObject go2) {
		Data d = state.getData();
		if (e == Event.SCORE_HUMAN) {
			d.inc("HumanScore");
			checkWin();
		}

		if (e == Event.SCORE_CPU) {
			d.inc("CpuScore");
			checkWin();
		}
	}

	private void checkWin() {
		Data d = state.getData();
		if (d.get("CpuScore") == d.get("WinScore")) {
			state.getGame().popState();
			state.getGame().pushState(new PlayAgainState());
			state.getGame().pushState(new TextState("You lost"));
			return;
		}

		if (d.get("HumanScore") == d.get("WinScore")) {
			state.getGame().popState();
			state.getGame().pushState(new PlayAgainState());
			state.getGame().pushState(new TextState("You Win"));
			return;
		}
	}

}
