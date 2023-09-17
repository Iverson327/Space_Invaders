package invaders.builders;

import invaders.physics.Vector2D;
import javafx.scene.image.Image;

public interface BuildObject {
    public void setPosition(Vector2D position);
    public void setHealth(double health);
    public void setImage(Image image);
}
