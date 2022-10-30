package uet.oop.bomberman.entities;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomb.map_flame;
import static uet.oop.bomberman.entities.Menu.*;

public class Bomber extends Animal {
    public static ImageView play_Again;
    public static ImageView exit_Game;

    public static int swap_img = 1;
    public static int delay_swap = 0;

    public Bomber(int is_move, int swap, String direction, int count, int count_to_run) {
        super(8, 1, "down", 0, 0);
    }
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void Bomber_dead(Animal animal) {
        if (delay_swap % 10 == 0) {
            if (swap_img == 1) {
                animal.setImg(Sprite.player_dead1.getFxImage());
                swap_img = 2;
            } else if (swap_img == 2) {
                animal.setImg(Sprite.player_dead2.getFxImage());
                swap_img = 3;
            } else if (swap_img == 3) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swap_img = 4;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
                is_running = false;
                Image gameOver = new Image("levels/GameOver.png");
                menu_game.setImage(gameOver);
                createGameover(BombermanGame.root);
            }
        }
    }
    public void createGameover(Group root) {
        //add 2 button play again and exit
        Image playAgain = new Image("levels/PlayAgain.png");
        Image exitGame = new Image("levels/ExitGame.png");
        play_Again = new ImageView(playAgain);
        exit_Game = new ImageView(exitGame);
        play_Again.setX(0);
        play_Again.setY(50);
        play_Again.setScaleX(0.5);
        play_Again.setScaleY(0.5);

        exit_Game.setX(0);
        exit_Game.setY(140);
        exit_Game.setScaleX(0.5);
        exit_Game.setScaleY(0.5);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        play_Again.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            play_Again.setEffect(colorAdjust);
        });
        play_Again.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            play_Again.setEffect(null);
        });

        exit_Game.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            exit_Game.setEffect(colorAdjust);
        });
        exit_Game.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            exit_Game.setEffect(null);
        });
        Pane pane = new Pane();
        pane.getChildren().addAll(play_Again, exit_Game);
        pane.setMinSize(992, 200);
        root.getChildren().add(pane);

        play_Again.setOnMouseClicked(event -> {
            is_playAgain = true;
            Image transparent = new Image("levels/transparent.png");
            menu_game.setImage(transparent);
            play_Again.setImage(transparent);
            exit_Game.setImage(transparent);

            level_rank = 1;
            entity.clear();
            block.clear();
            //for (Entity ett: BombermanGame.getStillObjects()) {
                for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                    BombermanGame.getStillObjects().get(i).setImg(Sprite.grass.getFxImage());
                    BombermanGame.getStillObjects().get(i).render(BombermanGame.getGc());
                }
            for (int i = 0; i < BombermanGame.getStillObjects().size(); i++) {
                if (BombermanGame.getStillObjects().get(i).getX()/32 == posx_flameItem && BombermanGame.getStillObjects().get(i).getY()/32 == posy_flameItem) {
                    BombermanGame.getStillObjects().get(i).setImg(transparent);
                    new FlameItem(posx_flameItem, posy_flameItem,Sprite.powerup_flames.getFxImage()).render(BombermanGame.getGc());
                }
                if (BombermanGame.getStillObjects().get(i).getX()/32 == posx_bombItem && BombermanGame.getStillObjects().get(i).getY()/32 == posy_bombItem) {
                    BombermanGame.getStillObjects().get(i).setImg(transparent);
                    new BombItem(posx_bombItem, posy_bombItem,Sprite.powerup_bombs.getFxImage()).render(BombermanGame.getGc());
                }
                if (BombermanGame.getStillObjects().get(i).getX()/32 == posx_speedItem && BombermanGame.getStillObjects().get(i).getY()/32 == posy_speedItem) {
                    BombermanGame.getStillObjects().get(i).setImg(transparent);
                    new SpeedItem(posx_speedItem, posy_speedItem,Sprite.powerup_speed.getFxImage()).render(BombermanGame.getGc());
                }
                if (BombermanGame.getStillObjects().get(i).getX()/32 == posx_portalItem && BombermanGame.getStillObjects().get(i).getY()/32 == posy_portalItem) {
                    BombermanGame.getStillObjects().get(i).setImg(transparent);
                    new Portal(posx_portalItem, posy_portalItem,Sprite.portal.getFxImage()).render(BombermanGame.getGc());
                }
                BombermanGame.getStillObjects().get(i).render(BombermanGame.getGc());
            }

            list_item.clear();
            BombermanGame.getStillObjects().clear();

            FlameItem.damageLevel = 1;
            SpeedItem.speed = 1;
            BombItem.can_add_bomb = false;
            is_running = true;
            new Sound("levels/SoundMainGame.wav","main_game");

            // back to level 1
            Score = 0;
            time_game = 200;
            bomb_game = 50;

            swap_img = 1;
            bomber.setLife(true);
            for (Animal animal : entity) {
                animal.setLife(true);
            }
            init_Entity_Level1();
            new BombermanGame();
            updateMenu();
        });

        exit_Game.setOnMouseClicked(event -> {
                Platform.exit();
                System.exit(0);
        });
    }

        private void checkBomb() {
        int ax = bomber.getX() / 32;
        int ay = bomber.getY() / 32;
        if (map_flame[ax][ay] == 5) {
            bomber.life = false;
        }
    }
    private void checkEnemy() {
        int ax = bomber.getX() / 32;
        int ay = bomber.getY() / 32;
        for (Animal animal : entity) {
            int bx = animal.getX() / 32;
            int by = animal.getY() / 32;
            if (ax == bx && by - 0.5 <= ay && by + 0.5 >= ay && animal.life
                    || ay == by && bx - 0.5 <= ax && bx + 0.5 >= ax && animal.life) {
                bomber.life = false;
                break;
            }
        }
    }
    public static void init_Entity_Level1() {
        //init entity to play again
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());

        balloom = new Balloom(17, 1, Sprite.balloom_right1.getFxImage());
        balloom1 = new Balloom(24, 3, Sprite.balloom_right1.getFxImage());
        oneal = new Oneal(10, 10, Sprite.oneal_right1.getFxImage());
        oneal1 = new Oneal(24, 5, Sprite.oneal_right1.getFxImage());
        oneal2 = new Oneal(18, 3, Sprite.oneal_right1.getFxImage());
        kondoria = new Kondoria(4,1, Sprite.kondoria_right1.getFxImage());
        doll = new Doll(28, 11, Sprite.doll_right1.getFxImage());
        minvo = new Minvo(28, 11, Sprite.minvo_right1.getFxImage());

        // init items to play again
        speedItem = new SpeedItem(29, 11, Sprite.grass.getFxImage());
        flameItem = new FlameItem(29, 11, Sprite.grass.getFxImage());
        bombItem = new BombItem(29, 11, Sprite.grass.getFxImage());
        portal = new Portal(29, 11, Sprite.grass.getFxImage());
        cur_grass = new Grass(29, 11, Sprite.grass.getFxImage());
        entity.add(balloom);
        entity.add(balloom1);
        entity.add(oneal);
        entity.add(oneal1);
        entity.add(oneal2);
        entity.add(kondoria);
        entity.add(doll);
        entity.add(minvo);
    }

    public static void init_Entity_Level2() {
        //init entity to play new level
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());

        balloom = new Balloom(17, 1, Sprite.balloom_right1.getFxImage());
        balloom1 = new Balloom(24, 3, Sprite.balloom_right1.getFxImage());
        oneal = new Oneal(10, 10, Sprite.oneal_right1.getFxImage());
        oneal1 = new Oneal(24, 5, Sprite.oneal_right1.getFxImage());
        ovape2 = new Ovape(18, 3, Sprite.ovape_right1.getFxImage());
        kondoria = new Kondoria(4,1, Sprite.kondoria_right1.getFxImage());
        doll = new Doll(28, 11, Sprite.doll_right1.getFxImage());
        minvo = new Minvo(28, 11, Sprite.minvo_right1.getFxImage());

        // init items
        speedItem = new SpeedItem(29, 11, Sprite.grass.getFxImage());
        flameItem = new FlameItem(29, 11, Sprite.grass.getFxImage());
        bombItem = new BombItem(29, 11, Sprite.grass.getFxImage());
        portal = new Portal(29, 11, Sprite.grass.getFxImage());
        cur_grass = new Grass(29, 11, Sprite.grass.getFxImage());

        // new entity
        ovape = new Ovape(4, 5, Sprite.ovape_right1.getFxImage());
        pass = new Pass(4,6, Sprite.pass_right1.getFxImage());

        entity.add(balloom);
        entity.add(balloom1);
        entity.add(oneal);
        entity.add(oneal1);
        entity.add(ovape2);
        entity.add(kondoria);
        entity.add(doll);
        entity.add(minvo);
        entity.add(ovape);
        entity.add(pass);
    }
    @Override
    public void update() {
        checkEnemy();
        checkBomb();
        delay_swap++;
        if (!bomber.life) {
            Bomber_dead(bomber);
        }
    }
}
