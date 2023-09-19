package invaders.builders;

import invaders.physics.Vector2D;
import invaders.builders.BuildObject;

import java.io.File;
import javafx.scene.image.Image;

import invaders.entities.Enemy;

public class EnemyBuilder implements ObjectBuilder {
    private Enemy enemy;

    public EnemyBuilder(){
        this.enemy = new Enemy();
    }

    @Override
    public void buildHealth(){
        this.enemy.setHealth(1);
    }

    @Override
    public void buildImage(){
        this.enemy.setImage(new Image(new File("src/main/resources/enemy.png").toURI().toString(), this.enemy.getWidth(), this.enemy.getHeight(), true, true));
    }

    @Override
    public void buildPosition(Vector2D position){
        this.enemy.setPosition(position);
    }

    public void buildType(String type, double gameY){
        this.enemy.setType(type, gameY);
    }

    public void buildEdge(double edgeL, double edgeR){
        this.enemy.setEdge(edgeL, edgeR);
    }

    public Enemy getObject(){
        return this.enemy;
    }
}
