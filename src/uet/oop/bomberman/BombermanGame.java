package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.explosion.Explosion;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;


import static uet.oop.bomberman.entities.Bomber.init_Entity_Level1;
import static uet.oop.bomberman.entities.Bomber.init_Entity_Level2;
import static uet.oop.bomberman.entities.Menu.*;
import static uet.oop.bomberman.entities.Portal.is_portal;
import static uet.oop.bomberman.entities.Sound.main_game;
import static uet.oop.bomberman.entities.Sound.updateSound;

public class BombermanGame extends Application {

    // WIDTH AND HEIGHT FORMAT FROM FILE CONST MAP
    public static final int WIDTH = 31;  // 832x1984
    public static final int HEIGHT = 13;

    public static boolean is_running = false;
    public static boolean is_playAgain = false;
    public static boolean is_pause = true;

    // position of items from file config map
    public static final int posx_flameItem = 1;
    public static final int posy_flameItem = 5;
    public static final int posx_speedItem = 3;
    public static final int posy_speedItem = 3;
    public static final int posx_portalItem = 19;
    public static final int posy_portalItem = 11;
    public static final int posx_bombItem = 7;
    public static final int posy_bombItem = 1;

    public static ImageView menu_game;
    public static ImageView next_level;
    public static Image menu;

    public static Animal bomber;
    public static Animal balloom;
    public static Animal balloom1;

    public static Animal oneal;
    public static Animal oneal1;
    public static Animal oneal2;

    public static Animal kondoria;
    public static Animal doll;
    public static Animal minvo;
    public static Animal ovape;
    public static Animal pass;
    public static Animal ovape2;

    public static SpeedItem speedItem;
    public static FlameItem flameItem;
    public static BombItem bombItem;
    public static Portal portal;
    public static Grass cur_grass;

    private static GraphicsContext gc;
    private Canvas canvas;

    public static List<Animal> entity = new ArrayList<>();
    public static List<Items> list_item = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    public static int [][] checkWall = new int[WIDTH][HEIGHT];
    public static final List<Entity> block = new ArrayList<>(); // chứa bomb
    static BombermanGame instance;

    public static Stage temp_Stage;
    private int fps = 1;
    private long last;
    public static int level_rank = 1;

    public static BombermanGame getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    // TAO CONTAINER
    public static Group root = new Group();

    @Override
    public void start(Stage stage) {
        
        instance = this;
        //Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setTranslateY(32);

        Menu.createMenu(root);
        root.getChildren().add(canvas);

        root.getChildren().add(menu_game);
        root.getChildren().add(statusGame);
        root.getChildren().add(des_game);
        root.getChildren().add(exit_game);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        temp_Stage = stage;

        // xu li yeu cau tu ban phim, di chuyen bomber
        scene.setOnKeyPressed(event -> {
            if (is_running) {
                switch (event.getCode()) {
                    case UP:
                        Move.up(bomber);
                        new Sound("levels/SoundMoveDownUp.wav", "up");
                        break;
                    case DOWN:
                        Move.down(bomber);
                        new Sound("levels/SoundMoveDownUp.wav", "down");
                        break;
                    case RIGHT:
                        Move.right(bomber);
                        new Sound("levels/SoundMoveLeftRight.wav", "right");
                        break;
                    case LEFT:
                        Move.left(bomber);
                        new Sound("levels/SoundMoveLeftRight.wav", "left");
                        break;
                    case SPACE:
                        Bomb.set_Bomb();
                        break;
                    case ENTER:
                        is_pause = !is_pause;
                        break;

                }
            }
        });

        init_Entity_Level1();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (is_running) {
                    render();
                    if (is_pause) {
                        time_fps();
                        update();
                    }
                }
            }
        };
        timer.start();
        createMap();
    }

    public void createMap() {
        String path = this.getClass().getResource("/map.txt").getPath();

        // next level
        if (level_rank == 2) {
            path = this.getClass().getResource("/mapLevel2.txt").getPath();
        }
        System.out.println(path);
        List<String> map = new ArrayList<>();
        try {
            map = FileUtils.readFileLineByLine(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map.isEmpty()) System.out.println("Load map fail!");
        else {
            for (int i = 0; i < map.size(); i++) {
                String line = map.get(i);
                for (int j = 0; j < line.length(); j++) {
                    int codeID = Integer.parseInt(String.valueOf(line.charAt(j))) ;

                    checkWall[j][i] = codeID;
                    System.out.print(checkWall[j][i]);
                    Entity entity = null;
                    switch (codeID) {
                        case Sprite.CODE_ID_WALL: {
                            entity = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        }
                        case Sprite.CODE_ID_GRASS: {
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        }
                        case Sprite.CODE_ID_BRICK: {
                            entity = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        }
                        case Sprite.CODE_ID_BOMBERMAN: {
                            entity = bomber;
                            break;
                        }
                    }
                    if (entity instanceof Bomber) {
                        Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        entities.add(entity);
                    } else if (entity instanceof Brick) {
                        entities.add(entity);
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                    stillObjects.add(entity);
                }
                System.out.println();
            }
        }
    }

    // set fps and name stage
    public void time_fps() {
        fps++;
        long now = System.currentTimeMillis();
        if (now - last > 1000) {
            last = System.currentTimeMillis();
            temp_Stage.setTitle("Bomberman Game  " + fps + " fps");
            fps = 0;
            updateMenu();
            if (is_running) {
                time_game--;
            }
            if (time_game < 0 || bomb_game == 0) {
                bomber.setLife(false);
            }
        }
    }
    public static void animation_Brick() {
        for (int i = 0; i < block.size(); i++) {
            Entity ett = block.get(i);
            if (ett instanceof Bomb) {
                if (((Bomb) ett).isAlive()) ett.update();
                else {
                    //check explosion
                    for (int j = ((Bomb) ett).getExplosionList().size() - 1; j >= 0; j--) {
                        Explosion explosion = ((Bomb) ett).getExplosionList().get(j);
                        //check brick
                        for (Entity e : stillObjects) {
                            if (e.getXBlock() == explosion.getXBlock() && e.getYBlock() == explosion.getYBlock()) {
                                if (e instanceof Brick) {
                                    //flameitem
                                    if (e.getX()/32 == posx_flameItem && e.getY()/32 == posy_flameItem) {
                                        flameItem = new FlameItem(posx_flameItem, posy_flameItem, Sprite.powerup_flames.getFxImage());
                                        list_item.add(flameItem);
                                    }
                                    //speeditem
                                    if (e.getX()/32 == posx_speedItem && e.getY()/32 == posy_speedItem) {
                                        speedItem = new SpeedItem(posx_speedItem, posy_speedItem, Sprite.powerup_speed.getFxImage());
                                        list_item.add(speedItem);
                                    }
                                    //portal item
                                    if (e.getX()/32 == posx_portalItem && e.getY()/32 == posy_portalItem) {
                                        portal = new Portal(posx_portalItem, posy_portalItem, Sprite.portal.getFxImage());
                                    }
                                    // bomb item
                                    if (e.getX()/32 == posx_bombItem && e.getY()/32 == posy_bombItem) {
                                        bombItem = new BombItem(posx_bombItem, posy_bombItem, Sprite.powerup_bombs.getFxImage());
                                        list_item.add(bombItem);
                                    }
                                    checkWall[e.getX()/32][e.getY()/32] = 1;
                                    ((Brick) e).remove();
                                    break;
                                }
                            }
                        }
                        block.remove(explosion);
                    }
                    block.remove(ett);
                    i--;
                }
            } else ett.update();
        }
    }

    public void update() {
        if (is_playAgain) {
            createMap();
            is_playAgain = false;
        }

        int n = entities.size();
        for (int i = 0; i < entities.size(); i++) {
            Entity ett = entities.get(i);
            ett.update();
            if (entities.size() < n) {
                i--;
                n = entities.size();
            }
            ett.update();
        }

        for (Entity ett: block) {
            ett.update();
        }
        animation_Brick();

        // load Portal & next level
        if (!is_portal && entity.size() == 0) {
            if (bomber.getX()/32 == posx_portalItem && bomber.getY()/32 == posy_portalItem) {
                portal.setImg(Sprite.grass.getFxImage());
                is_portal = true;
                is_running = false;
                Image levelup = new Image("levels/LevelUp.png");
                main_game.close();
                new Sound("levels/SoundNextLevel.wav", "win");
                menu_game.setImage(levelup);

                // button nextLevel
                Image nextLevel = new Image("levels/NextLevel.png");
                next_level = new ImageView(nextLevel);
                next_level.setX(0);
                next_level.setY(80);
                next_level.setScaleX(0.5);
                next_level.setScaleY(0.5);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.5);
                next_level.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
                    next_level.setEffect(colorAdjust);
                });
                next_level.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
                    next_level.setEffect(null);
                });
                Pane pane = new Pane();
                pane.getChildren().addAll(next_level);
                root.getChildren().add(pane);
                next_level.setOnMouseClicked(event -> {

                    FlameItem.damageLevel = 1;
                    SpeedItem.speed = 1;
                    BombItem.can_add_bomb = false;
                    Image transparent = new Image("levels/transparent.png");

                    entity.clear();
                    block.clear();
                    for (Entity ett: stillObjects) {
                        ett.setImg(Sprite.grass.getFxImage());
                        ett.render(gc);
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
                    stillObjects.clear();
                    level_rank = 2;

                    createMap();
                    next_level.setImage(transparent);
                    menu_game.setImage(transparent);
                    is_running = true;
                    new Sound("levels/SoundMainGame.wav","main_game");

                    for (Animal animal : entity) {
                        animal.setLife(true);
                    }
                    init_Entity_Level2();
                    bomb_game = 20;
                    time_game = 120;
                    updateMenu();
                    new BombermanGame();
                });
            }
        }

        //update items
        for (Items it: list_item) {
            it.update();
        }
        portal.update();

        //update bomber
        bomber.update();

        for (int i = 0; i < entity.size(); i++) {
            entity.get(i).update();
        }
        updateSound();

        bomber.setCountToRun(bomber.getCountToRun() + 1);
        if (bomber.getCountToRun() == 4) {
            Move.checkRun(bomber);
            bomber.setCountToRun(0);
        }

        for (Animal ett: entity) {
            if (ett instanceof Balloom) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Oneal) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Kondoria) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Doll) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Minvo) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Ovape) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
            if (ett instanceof Pass) {
                ett.setCountToRun(ett.getCountToRun() + 1);
                if (ett.getCountToRun() == 4) {
                    Move.checkRun(ett);
                    ett.setCountToRun(0);
                }
            }
        }
    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));

        //render items
        list_item.forEach(g -> g.render(gc));
        portal.render(gc);


        entities.forEach(g -> g.render(gc));
        block.forEach(g -> g.render(gc));
        entity.forEach(g -> g.render(gc));

        //render bomber
        bomber.render(gc);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public static List<Animal> getEntity() {
        return entity;
    }

    public static void setEntity(List<Animal> entity) {
        BombermanGame.entity = entity;
    }
    public static GraphicsContext getGc() {
        return gc;
    }

}
