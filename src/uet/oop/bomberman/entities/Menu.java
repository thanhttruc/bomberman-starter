package uet.oop.bomberman.entities;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.BombermanGame;

import static uet.oop.bomberman.BombermanGame.*;


public class Menu {
    public static ImageView statusGame;
    //public static Text level, bomb, time;

    public static void createMenu(Group root) {

        //start Button
        Image newGame = new Image("levels/start_Button.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(60);
        statusGame.setY(150);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);
        // Background Menu
        menu = new Image("levels/BomberMenu.png");
        menu_game = new ImageView(menu);
        menu_game.setX(-325);
        menu_game.setY(-208);
        menu_game.setScaleX(0.5);
        menu_game.setScaleY(0.5);

        Pane pane = new Pane();
        pane.getChildren().addAll(statusGame);
        root.getChildren().add(pane);

        // Click Button
        statusGame.setOnMouseClicked(event -> {
            Image transparent = new Image("levels/transparent.png");
            menu_game.setImage(transparent);
            new Sound("levels/SoundMainGame.wav","main_game");
            new BombermanGame();
            updateMenu();
            statusGame.setImage(transparent);
        });
    }

    public static void updateMenu() {

        }
    }

