package uet.oop.bomberman.entities;

import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;

import javafx.scene.input.MouseEvent;

import static uet.oop.bomberman.BombermanGame.*;


public class Menu {
    public static ImageView statusGame;
    public static Text level;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        level.setFill(Color.BLACK);
        level.setX(480);
        level.setY(20);

        //start Button
        Image newGame = new Image("levels/start_Button.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(200);
        statusGame.setY(0);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);
        // Background Menu
        menu = new Image("levels/MenuGame.png");
        menu_game = new ImageView(menu);
        menu_game.setX(-496);
        menu_game.setY(-176);
        menu_game.setScaleX(0.5);
        menu_game.setScaleY(0.5);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        statusGame.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            statusGame.setEffect(colorAdjust);
        });
        statusGame.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            statusGame.setEffect(null);
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(level,statusGame);
        pane.setMinSize(992, 200);

        pane.setStyle("-fx-background-color: rgb(0,255,255)");
        root.getChildren().add(pane);

        // Click Button
        statusGame.setOnMouseClicked(event -> {
            Image transparent = new Image("levels/transparent.png");
            menu_game.setImage(transparent);
            is_running = true;
            new Sound("levels/SoundMainGame.wav","main_game");
            new BombermanGame();
            updateMenu();
            statusGame.setImage(transparent);
        });
    }

    public static void updateMenu() {
        }
    }

