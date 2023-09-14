package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.EntityView;
import invaders.GameObject;

import javafx.scene.image.Image;

import java.io.File;

public class PlayerBullet implements Bullet, Moveable, Damagable, Renderable, GameObject {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 1;

    private final double width = 15;
    private final double height = 20;
    private final Image image;
    private double speedv = 1;
    private boolean isDelete = false;


    public PlayerBullet(Vector2D position){
        this.image = new Image(new File("src/main/resources/fastBullet.png").toURI().toString(), width, height, true, true);
        this.position = position;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() - this.speedv);
    }

    @Override
    public void down() {
        this.position.setY(this.position.getY() + this.speedv);
    }

    @Override
    public void left() {}

    @Override
    public void right() {}

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.EFFECT;
    }

    @Override
    public void speedUp(){}

    @Override
    public void start(){}

    @Override
    public void update(){
        up();
        if(this.position.getY() <= 0){
            this.isDelete = true;
        }
    }

    // @Override
    public boolean isDelete(){
        return this.isDelete;
    }
}