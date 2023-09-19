package invaders.entities;

import invaders.logic.Damagable;
import invaders.logic.Shootable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.physics.Collider;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.projectiles.BulletFactory;
import invaders.entities.projectiles.PlayerBullet;
import invaders.entities.projectiles.Bullet;

import javafx.scene.image.Image;

import java.io.File;

public class Player implements Moveable, Damagable, Renderable, Collider, Shootable {

    private final Vector2D position;
    private final Animator anim = null;
    private double health;

    private final double width = 40;
    private final double height = 35;
    private final double speed;
    private Image image;

    private Bullet bullet;

    public Player(Vector2D position, double speed, double health, String colour, double gameY){
        // this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
        this.position = position;
        this.speed = speed;
        this.health = health;
        this.bullet = BulletFactory.makeBullet("Player", new Vector2D(this.getPosition().getX(), this.getPosition().getY()), gameY);
        setColour(colour);
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
        this.position.setX(this.position.getX() - this.speed);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + this.speed);
    }

    @Override
    public void speedUp(){}

    @Override
    public Bullet shoot(){
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

    public void setColour(String colour){
        colour = colour.toLowerCase();
        if(colour.equals("green")){
            this.image = new Image(new File("src/main/resources/greenplayer.png").toURI().toString(), this.width, this.height, true, true);
            return;
        }
        if(colour.equals("red")){
            this.image = new Image(new File("src/main/resources/redplayer.png").toURI().toString(), this.width, this.height, true, true);
            return;
        }
        if(colour.equals("blue")){
            this.image = new Image(new File("src/main/resources/blueplayer.png").toURI().toString(), this.width, this.height, true, true);
            return;
        }
        if(colour.equals("yellow")){
            this.image = new Image(new File("src/main/resources/yellowplayer.png").toURI().toString(), this.width, this.height, true, true);
            return;
        }
        this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), this.width, this.height, true, true);
        return;
    }

}
