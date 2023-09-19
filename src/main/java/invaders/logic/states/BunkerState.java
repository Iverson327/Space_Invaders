package invaders.logic.states;

import java.io.File;

public interface BunkerState {
    public File getColour();
    public BunkerState next();
}
