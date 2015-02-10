package com.braten.pong.game.states;

import com.braten.pong.engine.State;
import com.braten.pong.game.PongLogic;
import com.braten.pong.game.objects.Background;
import com.braten.pong.game.objects.Ball;
import com.braten.pong.game.objects.BottomBorder;
import com.braten.pong.game.objects.CpuPaddle;
import com.braten.pong.game.objects.HumanPaddle;
import com.braten.pong.game.objects.ParticleEmitter;
import com.braten.pong.game.objects.ScoreBoard;
import com.braten.pong.game.objects.TopBorder;

public class PongState extends State {
	
	public PongState() {
		this.addGameObject(new Background(this));
		this.addGameObject(new BottomBorder(this));
		this.addGameObject(new TopBorder(this));
        this.addGameObject(new ScoreBoard(this));
        this.addGameObject(new ParticleEmitter(this));
        this.addGameObject(new Ball(this));
        this.addGameObject(new CpuPaddle(this));
        this.addGameObject(new HumanPaddle(this));
        
        this.setLogic(new PongLogic(this));
	}
}
