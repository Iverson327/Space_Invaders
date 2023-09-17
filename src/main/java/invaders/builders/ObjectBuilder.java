package invaders.builders;

import invaders.physics.Vector2D;

public interface ObjectBuilder{
    public void buildImage();
    public void buildPosition(Vector2D position);
    public void buildHealth();
}