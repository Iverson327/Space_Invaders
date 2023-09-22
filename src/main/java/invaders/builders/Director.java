package invaders.builders;

import invaders.builders.EnemyBuilder;
import invaders.builders.BunkerBuilder;
import invaders.entities.Enemy;
import invaders.entities.Bunker;

import invaders.physics.Vector2D;

public class Director {
    public static Enemy makeEnemy(double enemyX, double enemyY, String projectileStrategy, double gameY, double mindis, double maxdis){
        EnemyBuilder embuilder = new EnemyBuilder();
        embuilder.buildHealth();
        embuilder.buildImage();
        embuilder.buildPosition(new Vector2D(enemyX, enemyY));
        embuilder.buildType(projectileStrategy, gameY);
        embuilder.buildEdge(mindis, maxdis);
        return embuilder.getObject();
    }
    public static Bunker makeBunker(double bunkerX, double bunkerY, double bunkerW, double bunkerH){
        BunkerBuilder bkBuilder = new BunkerBuilder();
        bkBuilder.buildHealth();
        bkBuilder.buildPosition(new Vector2D(bunkerX, bunkerY));
        bkBuilder.buildWidth(bunkerW);
        bkBuilder.buildHeight(bunkerH);
        bkBuilder.buildImage();
        return bkBuilder.getObject();
    }
}
