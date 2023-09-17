package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.EntityView;
import invaders.GameObject;
import invaders.builders.BuildObject;
import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements Moveable, Damagable, Renderable, GameObject, BuildObject {

    private Vector2D position;
    private final Animator anim = null;
    private double health;

    private double width;
    private double height;

    private Image image;
    private final Image greenImage;
    private final Image yellowImage;
    private final Image redImage;
    private boolean isDelete = false;

    public Bunker(){
        this.greenImage =  new Image(new File("src/main/resources/green_bunker.png").toURI().toString(), width, height, true, true);
        this.yellowImage = new Image(new File("src/main/resources/yellow_bunker.png").toURI().toString(), width, height, true, true);
        this.redImage = new Image(new File("src/main/resources/red_bunker.png").toURI().toString(), width, height, true, true);
        this.image = this.greenImage;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
        if(this.health <= 0){
            this.setImageToNull();
            this.isDelete = true;
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
    public void up() {}

    @Override
    public void down() {}

    @Override
    public void left() {}

    @Override
    public void right() {}

    @Override
    public void speedUp(){}

    public void shoot(){}

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
    public void start(){}

    @Override
    public void update(){}

    @Override
    public boolean isDelete(){
        return this.isDelete;
    }

    @Override
    public void setImageToNull(){
        this.image = new Image(new File("src/main/resources/null.png").toURI().toString(), width, height, true, true);;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setHeight(double height){
        this.height = height;
    }

    @Override
    public void setPosition(Vector2D position){
        this.position = position;
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