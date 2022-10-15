package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.block;
import static uet.oop.bomberman.BombermanGame.bomber;

public class Bomb extends Entity {

    int animate = 0;
    int time = 20; // 4s

    Thread thread;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    time --;
                    System.out.println(time);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void update() {
        if (thread.getState().equals(Thread.State.NEW)) thread.start();
        animate++;
    }

    @Override
    public void render(GraphicsContext gc) {
        setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,animate , 34).getFxImage());
        super.render(gc);
    }

    public static void set_Bomb() {
        Bomb bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
        block.add(bomb);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isAlive() {
        return time > 0;
    }
}
