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

public class Enemy implements Moveable, Damagable, Renderable, GameObject {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 1;
    private final double initX;
    private final double initY;

    private final double width = 25;
    private final double height = 30;
    private Image image;
    private double speedh = 0.2;
    private double speedv = 0.015;
    private boolean left = true;
    private boolean right = false;

    private boolean isDelete = false;

    public Enemy(Vector2D position){
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), width, height, true, true);
        this.position = position;
        this.initX = position.getX();
        this.initY = position.getY();
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
    public void left() {
        this.position.setX(this.position.getX() - this.speedh);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + this.speedh);
    }

    public void shoot(){
        // todo
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
    public void speedUp(){
        this.speedv += 0.01;
    }

    @Override
    public void start(){}

    @Override
    public void update(){
        if(right){
            right();
        }else if(left){
            left();
        }
        if (this.position.getX() <= this.initX - 75 && !right){
            this.position.setX(this.initX - 75);
            left = false;
            right = true;
        }else if(this.position.getX() >= this.initX + 75 && !left){
            this.position.setX(this.initX + 75);
            left = true;
            right = false;
        }
        down();
    }

    // @Override
    public boolean isDelete(){
        return this.isDelete;
    }

    @Override
    public void setImageToNull(){
        this.image = new Image(new File("src/main/resources/null.png").toURI().toString(), width, height, true, true);;
    }
}