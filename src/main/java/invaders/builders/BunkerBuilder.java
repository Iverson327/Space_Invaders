package invaders.builders;

import invaders.physics.Vector2D;
import invaders.builders.BuildObject;

import java.io.File;
import javafx.scene.image.Image;

import invaders.entities.Bunker;

public class BunkerBuilder implements ObjectBuilder {
    private Bunker bunker;

    public BunkerBuilder(){
        this.bunker = new Bunker();
    }

    @Override
    public void buildHealth(){
        this.bunker.setHealth(3);
    }

    @Override
    public void buildImage(){}

    @Override
    public void buildPosition(Vector2D position){
        this.bunker.setPosition(position);
    }

    public void buildWidth(double width){
        this.bunker.setWidth(width);
    }

    public void buildHeight(double height){
        this.bunker.setHeight(height);
    }

    public Bunker getObject(){
        return this.bunker;
    }
}