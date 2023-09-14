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

public class Bunker implements Moveable, Damagable, Renderable, GameObject {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 100;

    private final double width;
    private final double height;

    private final Image image;
    private final Image greenImage;
    private final Image yellowImage;
    private final Image redImage;
    private boolean isDelete = true;

    public Bunker(Vector2D position, double width, double height){
        this.greenImage =  new Image(new File("src/main/resources/green_bunker.png").toURI().toString(), width, height, true, true);
        this.yellowImage = new Image(new File("src/main/resources/yellow_bunker.png").toURI().toString(), width, height, true, true);
        this.redImage = new Image(new File("src/main/resources/red_bunker.png").toURI().toString(), width, height, true, true);
        this.image = this.greenImage;
        this.position = position;
        this.width = width;
        this.height = height;
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

    // @Override
    public boolean isDelete(){
        return this.isDelete;
    }
}