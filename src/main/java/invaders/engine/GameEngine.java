package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.GameObject;
import invaders.entities.Player;
import invaders.entities.Enemy;
import invaders.entities.Bullet;
import invaders.entities.PlayerBullet;
import invaders.entities.EnemyBullet;
import invaders.entities.Bunker;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	// private List<GameObject> gameobjects;
	private List<Enemy> enemies;
	private List<Bunker> bunkers;
	private List<Bullet> bullets;
	private List<Renderable> renderables;
	private Player player;

	private boolean left;
	private boolean right;

	public GameEngine(String config){

		// gameobjects = new ArrayList<GameObject>();
		enemies = new ArrayList<Enemy>();
		bunkers = new ArrayList<Bunker>();
		bullets = new ArrayList<Bullet>();
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

				Enemy enemy = new Enemy(new Vector2D(enemyX, enemyY));
				renderables.add(enemy);
				// gameobjects.add(enemy);
				enemies.add(enemy);

				

				// TODO: delete me, this is just a demonstration:
				// System.out.println("Enemey x: " + positionX + ", projectile: " + projectileStrategy);
			}
			JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");
			for (Object obj : jsonBunkers) {
				JSONObject jsonBunker = (JSONObject) obj;
				
				// the enemy position is a double
				long bunkerX = (long) ((JSONObject) jsonBunker.get("position")).get("x");
				long bunkerY = (long) ((JSONObject) jsonBunker.get("position")).get("y");

				long bunkerW = (long) ((JSONObject) jsonBunker.get("size")).get("x");
				long bunkerH = (long) ((JSONObject) jsonBunker.get("size")).get("y");

				Bunker bunker = new Bunker(new Vector2D(bunkerX, bunkerY), bunkerW, bunkerH);
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
		movePlayer();
		for(Enemy em: enemies){
			em.update();
		}
		for(Bunker bk: bunkers){
			bk.update();
		}

		for(Bullet bl: bullets){
			bl.update();
		}
		
		
		for(int i = 0; i < bunkers.size(); i++){
			if(bunkers.get(i).isDelete()){
				renderables.remove(bunkers.get(i));
				bunkers.remove(bunkers.get(i));
			}
		}

		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isDelete()){
				renderables.remove(enemies.get(i));
				enemies.remove(enemies.get(i));
			}
		}
		
		for(int i = 0; i < bullets.size(); i++){
			if(bullets.get(i).isDelete()){
				renderables.remove(bullets.get(i));
				bullets.remove(bullets.get(i));
			}
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
		player.shoot();
		PlayerBullet bullet = new PlayerBullet(new Vector2D(player.getPosition().getX(), player.getPosition().getY()));
		bullets.add(bullet);
		renderables.add(bullet);
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
