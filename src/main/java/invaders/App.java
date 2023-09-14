package invaders;

import javafx.application.Application;
import javafx.stage.Stage;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        long gameX = 640;
        long gameY = 400;
        JSONParser parser = new JSONParser();
        try {
			Object object = parser.parse(new FileReader("src/main/resources/config.json"));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Game section:
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");

			// reading a coordinate from the nested section within the game
			// note that the game x and y are of type Long (i.e. they are integers)
			gameX = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			gameY = (Long) ((JSONObject) jsonGame.get("size")).get("y");
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Map<String, String> params = getParameters().getNamed();

        GameEngine model = new GameEngine("src/main/resources/config.json");
        GameWindow window = new GameWindow(model, gameX, gameY);
        window.run();

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
