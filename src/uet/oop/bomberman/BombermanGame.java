package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    // bomber
    public Animal bomber;


    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        // xu li yeu cau tu ban phim, di chuyen bomber
        scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP:
                        Move.up(bomber);
                        break;
                    case DOWN:
                        Move.down(bomber);
                        break;
                    case RIGHT:
                        Move.right(bomber);
                        break;
                    case LEFT:
                        Move.left(bomber);
                        break;
                }
        });
        // init bomber
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomber);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        createMap();


        //Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        //entities.add(bomberman);

        // load image ballom
        Entity ballomleft1 = new Ballom(1, 10, Sprite.balloom_left1.getFxImage());
        entities.add(ballomleft1);
        Entity ballomleft2 = new Ballom(1, 5, Sprite.balloom_left2.getFxImage());
        entities.add(ballomleft2);
//      Entity ballom = new Ballom(1, 10, Sprite.balloom_left1.getFxImage());
//      entities.add(ballom);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }


    public void update() {
        entities.forEach(Entity::update);

        // update bomber
        bomber.update();
        bomber.setCountToRun(bomber.getCountToRun() + 1);
        if (bomber.getCountToRun() == 4) {
            Move.checkRun(bomber);
            bomber.setCountToRun(0);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

        // load bomber
        bomber.render(gc);
    }
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
}
