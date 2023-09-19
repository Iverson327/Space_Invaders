package invaders.entities.projectiles;

import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.physics.Collider;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.EntityView;
import invaders.entities.projectiles.Bullet;
import invaders.entities.projectiles.PlayerBullet;
import invaders.entities.projectiles.EnemyBullet;
import invaders.GameObject;

import javafx.scene.image.Image;

public class BulletFactory{
    public static Bullet makeBullet(String type, Vector2D pos, double gameY){
        if(type.equals("Player")){
            return new PlayerBullet(pos);
        }
        if(type.equals("slow_straight")){
            return new EnemyBullet(pos, type, gameY);
        }
        if(type.equals("fast_straight")){
            return new EnemyBullet(pos, type, gameY);
        }
        return new PlayerBullet(pos);
    }
}