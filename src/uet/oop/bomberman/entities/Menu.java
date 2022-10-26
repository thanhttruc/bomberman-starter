package uet.oop.bomberman.entities;

import javafx.application.Platform;
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
    public static ImageView statusGame, des_game, exit_game;
    public static Text level, score, menu_Text, bomb, time;

    public static int Score = 0;
    public static int bomb_game = 50; // bomb in levels
    public static int time_game = 200; // bomb in levels

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        level.setFill(Color.BLACK);
        level.setX(220);
        level.setY(20);

        time = new Text("Time: ");
        time.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        time.setFill(Color.BLACK);
        time.setX(700);
        time.setY(20);

        bomb = new Text("Bombs: 0");
        bomb.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        bomb.setFill(Color.BLACK);
        bomb.setX(350);
        bomb.setY(20);

        menu_Text = new Text("Bomberman Game");
        menu_Text.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        menu_Text.setFill(Color.BLACK);
        menu_Text.setX(430);
        menu_Text.setY(20);

        score = new Text("Score: 0");
        score.setFont(Font.font("Cooper Black", FontWeight.BOLD, 18));
        score.setFill(Color.BLACK);
        score.setX(600);
        score.setY(20);

        //start Button
        Image newGame = new Image("levels/StartButton.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(200);
        statusGame.setY(-150);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        //Desciption Button
        Image desGame = new Image("levels/DescribeButton.png");
        des_game = new ImageView(desGame);
        des_game.setX(200);
        des_game.setY(-60);
        des_game.setScaleX(0.5);
        des_game.setScaleY(0.5);

        //exit Button
        Image exitGame = new Image("levels/ExitGame.png");
        exit_game = new ImageView(exitGame);
        exit_game.setX(200);
        exit_game.setY(30);
        exit_game.setScaleX(0.5);
        exit_game.setScaleY(0.5);
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
        exit_game.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            exit_game.setEffect(colorAdjust);
        });
        exit_game.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            exit_game.setEffect(null);
        });
        des_game.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            des_game.setEffect(colorAdjust);
        });
        des_game.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            des_game.setEffect(null);
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(des_game,exit_game,menu_Text, statusGame);
        pane.setMinSize(992, 200);
//
        pane.setStyle("-fx-background-color: rgb(192,192,192)");
        root.getChildren().add(pane);

        // Click Button
        statusGame.setOnMouseClicked(event -> {
            Image transparent = new Image("levels/transparent.png");
            menu_game.setImage(transparent);
            des_game.setImage(transparent);
            exit_game.setImage(transparent);
            menu_Text.setText("");

            is_running = true;
            new Sound("levels/SoundMainGame.wav","main_game");
            new BombermanGame();
            statusGame.setImage(transparent);
            // test
            pane.getChildren().addAll(level, score, bomb, time);
            pane.setMinSize(992, 200);
            pane.setStyle("-fx-background-color: rgb(192,192,192)");
            //root.getChildren().add(pane);
            updateMenu();
        });
        //click exit button
        exit_game.setOnMouseClicked(event -> {
            Platform.exit();
            System.exit(0);
        });
        //click des button
        des_game.setOnMouseClicked(event -> {
            Image transparent = new Image("levels/transparent.png");
            statusGame.setImage(transparent);
            des_game.setImage(transparent);
            exit_game.setImage(transparent);
            Image info = new Image("levels/DescriptionText.png");
            menu_game.setImage(info);
            menu_game.setOnMouseClicked(event_back -> {
                menu_game.setImage(menu);
                des_game.setImage(desGame);
                exit_game.setImage(exitGame);
                statusGame.setImage(newGame);
            });
        });
    }

    public static void updateMenu() {
            level.setText("Levels: " + level_rank);
            score.setText("Score: " + Score);
            bomb.setText("Bombs: " + bomb_game);
            time.setText("Times: " + time_game);
        }
    }

