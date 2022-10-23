package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.explosion.Explosion;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.FileUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static uet.oop.bomberman.entities.Menu.statusGame;
import static uet.oop.bomberman.entities.Portal.is_portal;
import static uet.oop.bomberman.entities.Sound.updateSound;

public class BombermanGame extends Application {
    
//    public static final int WIDTH = 20;
//    public static final int HEIGHT = 15;
    public static final int WIDTH = 31;  // 832x1984
    public static final int HEIGHT = 13;

    public static boolean is_running = false;

    // position of items from file config map
    public static final int posx_flameItem = 1;
    public static final int posy_flameItem = 5;
    public static final int posx_speedItem = 3;
    public static final int posy_speedItem = 3;
    public static final int posx_portalItem = 19;
    public static final int posy_portalItem = 11;

    public static ImageView menu_game;
    public static Image menu;

    public static Animal bomber;
    public static Animal balloom;
    public static Animal balloom1;

    public static Animal oneal;
    public static Animal oneal1;
    public static Animal oneal2;

    public static Animal kondoria;
    public static Animal doll;

    public static SpeedItem speedItem;
    public static FlameItem flameItem;
    public static BombItem bombItem;
    public static Portal portal;

    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Animal> entity = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static int [][] checkWall = new int[WIDTH][HEIGHT];
    public static final List<Entity> block = new ArrayList<>(); // chứa bomb
    static BombermanGame instance;

    public static BombermanGame getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        
        instance = this;
        //Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        canvas.setTranslateY(32);
        // Tao root container
        Group root = new Group();
        Menu.createMenu(root);
        root.getChildren().add(canvas);

        root.getChildren().add(menu_game);
        root.getChildren().add(statusGame);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

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
                }
            }
        });
        // init bomber
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());

        balloom = new Balloom(17, 1, Sprite.balloom_right1.getFxImage());
        balloom1 = new Balloom(24, 3, Sprite.balloom_right1.getFxImage());

        oneal = new Oneal(10, 10, Sprite.oneal_right1.getFxImage());
        oneal1 = new Oneal(24, 5, Sprite.oneal_right1.getFxImage());
        oneal2 = new Oneal(18, 3, Sprite.oneal_right1.getFxImage());

        kondoria = new Kondoria(2,1, Sprite.kondoria_right1.getFxImage());
        doll = new Doll(28, 11, Sprite.doll_right1.getFxImage());

        // init items are grass
        speedItem = new SpeedItem(29, 11, Sprite.grass.getFxImage());
        flameItem = new FlameItem(29, 11, Sprite.grass.getFxImage());
        bombItem = new BombItem(29, 11, Sprite.grass.getFxImage());
        portal = new Portal(29, 11, Sprite.grass.getFxImage());


        entity.add(balloom);
        entity.add(balloom1);
        entity.add(oneal);
        entity.add(oneal1);
        entity.add(oneal2);
        entity.add(kondoria);
        entity.add(doll);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (is_running) {
                    render();
                    update();
                }
            }
        };

        timer.start();

        createMap();
    }

    public void createMap() {

        String path = this.getClass().getResource("/map.txt").getPath();
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
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                    stillObjects.add(entity);
                }
                System.out.println();
            }
        }
    }

    public void update() {
        for (Entity ett : entities) {
            ett.update();
        }
        for (Entity ett: block) {
            ett.update();
        }
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
                                    stillObjects.remove(e);
                                    //flameitem
                                    if (e.getX()/32 == posx_flameItem && e.getY()/32 == posy_flameItem) {
                                        flameItem = new FlameItem(posx_flameItem, posy_flameItem, Sprite.powerup_flames.getFxImage());
                                    }
                                    //speeditem
                                    if (e.getX()/32 == posx_speedItem && e.getY()/32 == posy_speedItem) {
                                        speedItem = new SpeedItem(posx_speedItem, posy_speedItem, Sprite.powerup_speed.getFxImage());
                                    }
                                    //portal item
                                    if (e.getX()/32 == posx_portalItem && e.getY()/32 == posy_portalItem) {
                                        portal = new Portal(posx_portalItem, posy_portalItem, Sprite.portal.getFxImage());
                                    }
                                    checkWall[e.getX()/32][e.getY()/32] = 1;
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

        //update items
        speedItem.update();
        flameItem.update();
        bombItem.update();
        portal.update();

        //update bomber
        bomber.update();
//        balloom.update();
//        oneal.update();

        for (int i = 0; i < entity.size(); i++) {
            entity.get(i).update();
        }
//        for (Entity ett: entity) {
//            ett.update();
//        }
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
        }

        // load Portal, chưa xử lí qua level & kiêm tra bomb nổ.
        if (!is_portal /*&& entity.size() == 0 */) {
//            Entity portal = new Portal(posx_portalItem, posy_portalItem, Sprite.portal.getFxImage());
            //block.add(portal);
            if (bomber.getX()/32 == posx_portalItem && bomber.getY()/32 == posy_portalItem) {
                portal.setImg(Sprite.grass.getFxImage());
                //block.add(portal);
                is_portal = true;
            }
        }
    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        //render items
        speedItem.render(gc);
        flameItem.render(gc);
        bombItem.render(gc);
        portal.render(gc);

        entities.forEach(g -> g.render(gc));
        block.forEach(g -> g.render(gc));
        entity.forEach(g -> g.render(gc));

//        balloom.render(gc);
//        oneal.render(gc);

        //render bomber
        bomber.render(gc);
    }
    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getStillObjects() {
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
}
