package invaders.rendering;

import invaders.GameObject;
import invaders.physics.Vector2D;
import invaders.engine.GameEngine;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.io.File;

public class EndImage implements Renderable{
    private Image image;
    public EndImage(){
        this.image = new Image(new File("src/main/resources/gameover.png").toURI().toString(), 200, 205, true, true);
    }

    @Override
    public Image getImage(){
        return this.image;
    }

    @Override
    public double getWidth(){
        return 100;
    };

    @Override
    public double getHeight(){
        return 110;
    }

    @Override
    public boolean isDelete(){
        return false;
    }

    @Override
    public void setImageToNull(){}

    @Override
    public Vector2D getPosition(){
        return new Vector2D(100, 100);
    }

    @Override
    public Renderable.Layer getLayer(){
        return Layer.BACKGROUND;
    }
}
