package invaders.logic.states;

import invaders.logic.states.BunkerState;

import java.io.File;

public class NullState implements BunkerState {
    @Override
    public File getColour(){
        return new File("src/main/resources/null.png");
    }

    @Override
    public BunkerState next(){
        return this;
    }
}
