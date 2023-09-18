package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.GameObject;
import invaders.builders.EnemyBuilder;
import invaders.entities.Player;
import invaders.entities.Enemy;
import invaders.entities.projectiles.Bullet;
import invaders.entities.projectiles.PlayerBullet;
import invaders.entities.projectiles.EnemyBullet;
import invaders.entities.Bunker;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import invaders.rendering.EndImage;

import invaders.builders.BuildObject;
import invaders.builders.BunkerBuilder;
import invaders.builders.EnemyBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Random;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	// private List<GameObject> gameobjects;
	private List<Enemy> enemies;
	private List<Bunker> bunkers;
	private List<Bullet> bullets;
	private List<Bullet> playerBullets;
	private List<Renderable> renderables;
	private Player player;

	private boolean end = false;
	private boolean endadded = false;

	private Bullet playerBullet;

	private boolean left;
	private boolean right;

	private Random random = new Random();

	public GameEngine(String config){

		// gameobjects = new ArrayList<GameObject>();
		enemies = new ArrayList<Enemy>();
		bunkers = new ArrayList<Bunker>();
		bullets = new ArrayList<Bullet>();
		playerBullets = new ArrayList<Bullet>();
		renderables = new ArrayList<Renderable>();

		// read the config here
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(config));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				
				// the enemy position is a double
				long enemyX = (long) ((JSONObject) jsonEnemy.get("position")).get("x");
				long enemyY = (long) ((JSONObject) jsonEnemy.get("position")).get("y");

				String projectileStrategy = (String) jsonEnemy.get("projectile");

				EnemyBuilder embuilder = new EnemyBuilder();
				embuilder.buildHealth();
				embuilder.buildImage();
				embuilder.buildPosition(new Vector2D(enemyX, enemyY));
				embuilder.buildType(projectileStrategy);
				Enemy enemy = embuilder.getObject();
				renderables.add(enemy);
				// gameobjects.add(enemy);
				enemies.add(enemy);
			}

			JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");
			for (Object obj : jsonBunkers) {
				JSONObject jsonBunker = (JSONObject) obj;
				
				// the enemy position is a double
				long bunkerX = (long) ((JSONObject) jsonBunker.get("position")).get("x");
				long bunkerY = (long) ((JSONObject) jsonBunker.get("position")).get("y");

				long bunkerW = (long) ((JSONObject) jsonBunker.get("size")).get("x");
				long bunkerH = (long) ((JSONObject) jsonBunker.get("size")).get("y");

				BunkerBuilder bkBuilder = new BunkerBuilder();
				bkBuilder.buildHealth();
				bkBuilder.buildPosition(new Vector2D(bunkerX, bunkerY));
				bkBuilder.buildWidth(bunkerW);
				bkBuilder.buildHeight(bunkerH);
				Bunker bunker = bkBuilder.getObject();
				// Bunker bunker = new Bunker(new Vector2D(bunkerX, bunkerY), bunkerW, bunkerH);
				renderables.add(bunker);
				// gameobjects.add(bunker);
				bunkers.add(bunker);

				// TODO: delete me, this is just a demonstration:
				// System.out.println("Enemey x: " + positionX + ", projectile: " + projectileStrategy);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		player = new Player(new Vector2D(200, 380));
		renderables.add(player);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		if(end){
			if(!endadded){
				renderables.add(new EndImage());
				this.endadded = true;
				return;
			}
			return;
		}
		movePlayer();
		for(Enemy em: enemies){
			em.update();
			if(this.bullets.size() < 3){
				if(random.nextInt(600) == 1){
					Bullet bullet = em.shoot();
					bullets.add(bullet);
					renderables.add(bullet);
				}
			}
		}
		for(Bunker bk: bunkers){
			bk.update();
		}

		for(Bullet bullet: bullets){
			bullet.update();
		}

		for(Bullet bullet: playerBullets){
			bullet.update();
			for(Enemy em: enemies){
				if(bullet.isColliding(em)){
					// System.out.print("collide\n");
					em.takeDamage(1);
					bullet.takeDamage(1);
					for(Enemy enemy: enemies){
						enemy.speedUp();
					}
					break;
				}
			}
			for(Bullet emBullet: bullets){
				if(bullet.isColliding(emBullet)){
					emBullet.takeDamage(1);
					bullet.takeDamage(1);
					break;
				}
			}
			for(Bunker bk: bunkers){
				if(bullet.isColliding(bk)){
					// System.out.print("collide\n");
					bullet.takeDamage(1);
					bk.takeDamage(1);
					break;
				}
			}
		}

		for(Enemy em: enemies){
			for(Bunker bk: bunkers){
				if(em.isColliding(bk)){
					// System.out.print("collide\n");
					bk.takeDamage(1000);
					break;
				}
			}
			if(em.isColliding(player)){
				this.end = true;
				break;
			}
			if(em.getPosition().getY() >= 399-em.getHeight()){
				this.end = true;
				break;
			}
		}

		for(Bullet bullet: bullets){
			for(Bunker bk: bunkers){
				if(bullet.isColliding(bk)){
					// System.out.print("collide\n");
					bk.takeDamage(1);
					bullet.takeDamage(1);
					break;
				}
			}
			if(bullet.isColliding(player)){
				player.takeDamage(1);
				bullet.takeDamage(1);
				if(player.getHealth() <= 0){
					this.end = true;
					break;
				}
				break;
			}
		}
		
		bunkers.removeIf(Bunker::isDelete);
		enemies.removeIf(Enemy::isDelete);
		bullets.removeIf(Bullet::isDelete);
		playerBullets.removeIf(Bullet::isDelete);
		// renderables.removeIf(Renderable::isDelete);

		if(enemies.size() == 0){
			this.end = true;
		}
		
		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= 640) {
				ro.getPosition().setX(639-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= 400) {
				ro.getPosition().setY(399-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}

	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		if(!playerBullets.contains(playerBullet)){
			playerBullet = player.shoot();
			playerBullets.add(playerBullet);
			renderables.add(playerBullet);
		}
		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}
}
