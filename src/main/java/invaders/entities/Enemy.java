package invaders.entities;

import invaders.logic.Damagable;
import invaders.logic.Shootable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.EntityView;
import invaders.GameObject;
import invaders.builders.BuildObject;
import invaders.entities.projectiles.BulletFactory;
import invaders.entities.projectiles.EnemyBullet;
import invaders.entities.projectiles.Bullet;

import javafx.scene.image.Image;

import java.io.File;

public class Enemy implements Moveable, Damagable, Renderable, GameObject, Shootable, BuildObject {

    private Vector2D position;
    private final Animator anim = null;
    private double health = 1;
    private double initX;
    private double initY;

    private final double width = 25;
    private final double height = 30;
    private Image image;
    private double speedh = 0.2;
    private double speedv = 10;
    private boolean left = true;
    private boolean right = false;

    private double edgeL;
    private double edgeR;

    private boolean isDelete = false;

    private Bullet bullet;

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
        if(this.health <= 0){
            this.isDelete = true;
            this.setImageToNull();
        }
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

    @Override
    public Bullet shoot(){
        // todo
        if(this.bullet.isDelete()){
            this.bullet.getPosition().setX(this.getPosition().getX());
            this.bullet.getPosition().setY(this.getPosition().getY());
            this.bullet.start();
        }
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
    public void speedUp(){
        this.speedv += 2;
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
        if (this.position.getX() <= this.initX - (this.edgeL - 3) && !right){
            this.position.setX(this.initX - (this.edgeL - 3));
            down();
            left = false;
            right = true;
        }else if(this.position.getX() >= this.initX + (this.edgeR - 3) && !left){
            this.position.setX(this.initX + (this.edgeR - 3));
            down();
            left = true;
            right = false;
        }
        // down();
    }

    // @Override
    public boolean isDelete(){
        return this.isDelete;
    }

    @Override
    public void setImageToNull(){
        this.image = new Image(new File("src/main/resources/null.png").toURI().toString(), width, height, true, true);;
    }

    @Override
    public void setPosition(Vector2D position){
        this.position = position;
        this.initX = position.getX();
        this.initY = position.getY();
    }

    public void setType(String type, double gameY){
        this.bullet = BulletFactory.makeBullet(type, new Vector2D(this.getPosition().getX(), this.getPosition().getY()), gameY);
    }

    public void setEdge(double edgeL, double edgeR){
        this.edgeL = edgeL;
        this.edgeR = edgeR;
    }

    @Override
    public void setHealth(double health){
        this.health = health;
    }

    @Override
    public void setImage(Image image){
        this.image = image;
    }
}