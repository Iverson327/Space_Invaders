package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.physics.Collider;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.EntityView;
import invaders.GameObject;

import javafx.scene.image.Image;

public interface Bullet extends Damagable, Moveable, Renderable, GameObject, Collider {
    public boolean isDelete();
    public void takeDamage(double amount);
    public double getHealth();
    public boolean isAlive();
    public void up();
    public void down();
    public void left();
    public void right();
    public void speedUp();
    public Image getImage();
    public double getWidth();
    public double getHeight();
    public Vector2D getPosition();
    public Layer getLayer();
    public void start();
    public void update();
}