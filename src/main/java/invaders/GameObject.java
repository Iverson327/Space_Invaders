package invaders;

import invaders.physics.Collider;
import invaders.physics.Vector2D;

// contains basic methods that all GameObjects must implement
public interface GameObject extends Collider {

    public void start();
    public void update();
    public double getWidth();
    public double getHeight();
    public Vector2D getPosition();
    // public boolean isBunker();

}
