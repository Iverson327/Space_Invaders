package invaders.logic.states;

import invaders.logic.states.BunkerState;

import java.io.File;

public class GreenState implements BunkerState {
    @Override
    public File getColour(){
        return new File("src/main/resources/green_bunker.png");
    }

    @Override
    public BunkerState next(){
        return new YellowState();
    }
}
