package invaders.entities;

import invaders.logic.Damagable;
import invaders.logic.Shootable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.physics.Collider;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;

import invaders.entities.PlayerBullet;

import javafx.scene.image.Image;

import java.io.File;

public class Player implements Moveable, Damagable, Renderable, Collider, Shootable {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 10;

    private final double width = 25;
    private final double height = 30;
    private final Image image;

    private PlayerBullet bullet;

    public Player(Vector2D position){
        this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
        this.position = position;
        this.bullet = new PlayerBullet(new Vector2D(this.getPosition().getX(), this.getPosition().getY()));
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
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - 1);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + 1);
    }

    @Override
    public void speedUp(){}

    @Override
    public PlayerBullet shoot(){
        // todo
        this.bullet.getPosition().setX(this.getPosition().getX());
        this.bullet.getPosition().setY(this.getPosition().getY());
        this.bullet.start();
        return this.bullet;
    }

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
        return Layer.FOREGROUND;
    }

    @Override
    public boolean isDelete(){
        return false;
    }

    @Override
	public void setImageToNull(){}

}
