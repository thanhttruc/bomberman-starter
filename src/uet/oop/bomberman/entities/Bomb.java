package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.explosion.Explosion;
import uet.oop.bomberman.entities.explosion.HorizontalExplosion;
import uet.oop.bomberman.entities.explosion.VerticalExplosion;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.BombItem.can_add_bomb;
import static uet.oop.bomberman.entities.FlameItem.damageLevel;
import static uet.oop.bomberman.entities.Menu.*;


public class Bomb extends Entity {

    public static int[][] map_flame = new int[WIDTH][HEIGHT];
    public static int x;
    public static int y;
    static int bomb_number = 0;         // number of bomb in map
    int animate = 0;
    int time = 20;
    static int tempx = -1;
    static int tempy = -1;

    Thread thread;
    int renderTime = 34;

    List<Explosion> explosionList = new ArrayList<>();

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        //TEST
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                map_flame[i][j] = checkWall[i][j];
            }
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    if (!can_add_bomb) bomb_number = 1;
                    time --;
                    System.out.println(time);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                new Sound("levels/SoundExplosion.wav", "explosion");
                if (!can_add_bomb)  bomb_number = 0;
                checkWall[tempx][tempy] = Sprite.CODE_ID_GRASS;
                map_flame[tempx][tempy] = Sprite.CODE_ID_GRASS;
                if (can_add_bomb) bomb_number--;
                initializeExplosion();
                block.addAll(explosionList);
            }
        });
    }

    public void initializeExplosion() {
        int xBlock = getXBlock();
        int yBlock = getYBlock();
        int index = 0;
        while (index < damageLevel) {
            Explosion topExplosion = new VerticalExplosion(xBlock, yBlock - index - 1
                    , Sprite.explosion_vertical_top_last.getFxImage(), true, true);
            Explosion downExplosion = new VerticalExplosion(xBlock, yBlock + index + 1
                    , Sprite.explosion_vertical_down_last.getFxImage(), true, false);
            Explosion rightExplosion = new HorizontalExplosion(xBlock + index + 1, yBlock
                    , Sprite.explosion_horizontal_right_last.getFxImage(), true, true);
            Explosion leftExplosion= new HorizontalExplosion(xBlock - index - 1, yBlock
                    , Sprite.explosion_horizontal_left_last.getFxImage(), true, false);
            if (index == damageLevel - 1) {
                if (yBlock > 1) {
                    topExplosion = new VerticalExplosion(xBlock, yBlock - index - 1
                            , Sprite.explosion_vertical_top_last.getFxImage(), true, true);
                }
                if (yBlock < 11) {
                    downExplosion = new VerticalExplosion(xBlock, yBlock + index + 1
                            , Sprite.explosion_vertical_down_last.getFxImage(), true, false);
                }
                if (xBlock < 11) {
                    rightExplosion = new HorizontalExplosion(xBlock + index + 1, yBlock
                            , Sprite.explosion_horizontal_right_last.getFxImage(), true, true);
                }
                if (xBlock > 1) {
                    leftExplosion = new HorizontalExplosion(xBlock - index - 1, yBlock
                            , Sprite.explosion_horizontal_left_last.getFxImage(), true, false);
                }
            }
            else {
                if (yBlock > 0) {
                    topExplosion = new VerticalExplosion(xBlock, yBlock - index - 1
                            , Sprite.explosion_vertical_top_last.getFxImage(), false, true);
                }
                if (yBlock < 11) {
                    downExplosion = new VerticalExplosion(xBlock, yBlock + index + 1
                            , Sprite.explosion_vertical_down_last.getFxImage(), false, false);
                }
                if (xBlock < 11) {
                    rightExplosion = new HorizontalExplosion(xBlock + index + 1, yBlock
                            , Sprite.explosion_horizontal_right_last.getFxImage(), false, true);
                }
                if (xBlock > 0) {
                    leftExplosion = new HorizontalExplosion(xBlock - index - 1, yBlock
                            , Sprite.explosion_horizontal_left_last.getFxImage(), false, false);
                }
            }

            topExplosion.setTime(renderTime);
            downExplosion.setTime(renderTime);
            rightExplosion.setTime(renderTime);
            leftExplosion.setTime(renderTime);

            topExplosion.setAnimate(animate % renderTime);
            downExplosion.setAnimate(animate % renderTime);
            rightExplosion.setAnimate(animate % renderTime);
            leftExplosion.setAnimate(animate%renderTime);

            x = topExplosion.getXBlock();
            y = topExplosion.getYBlock();
            if (y < 0) y = 0;
            if (map_flame[x][y] != Sprite.CODE_ID_WALL) {
                explosionList.add(topExplosion);
                int temp = 0;
                while (temp < 1) {
                    map_flame[x][y] = 5;
                    System.out.println(tempx);
                    map_flame[tempx][tempy] = 5;
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp++;
                }
                map_flame[x][y] = Sprite.CODE_ID_GRASS;
                map_flame[tempx][tempy] = Sprite.CODE_ID_GRASS;
                System.out.println(map_flame[tempx][tempy]);
            }

            x = downExplosion.getXBlock();
            y = downExplosion.getYBlock();
            if (y > 12) y = 12;
            if (map_flame[x][y] != Sprite.CODE_ID_WALL) {
                explosionList.add(downExplosion);
                int temp = 0;
                while (temp < 1) {
                    map_flame[x][y] = 5;
                    map_flame[tempx][tempy] = 5;
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp++;
                }
                map_flame[x][y] = Sprite.CODE_ID_GRASS;
                map_flame[tempx][tempy] = Sprite.CODE_ID_GRASS;
            }

            x = rightExplosion.getXBlock();
            y = rightExplosion.getYBlock();
            if (x > 30) x = 30;
            if (map_flame[x][y] != Sprite.CODE_ID_WALL) {
                explosionList.add(rightExplosion);
                int temp = 0;
                while (temp < 1) {
                    map_flame[x][y] = 5;
                    map_flame[tempx][tempy] = 5;
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp++;
                }
                map_flame[x][y] = Sprite.CODE_ID_GRASS;
                map_flame[tempx][tempy] = Sprite.CODE_ID_GRASS;
            }

            x = leftExplosion.getXBlock();
            y = leftExplosion.getYBlock();
            if (x < 0) x = 0;
            if (map_flame[x][y] != Sprite.CODE_ID_WALL) {
                explosionList.add(leftExplosion);
                int temp = 0;
                while (temp < 1) {
                    map_flame[x][y] = 5;
                    map_flame[tempx][tempy] = 5;
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp++;
                }
                map_flame[x][y] = Sprite.CODE_ID_GRASS;
                map_flame[tempx][tempy] = Sprite.CODE_ID_GRASS;
            }
            index++;
        }
    }

    @Override
    public void update() {
        if (thread.getState().equals(Thread.State.NEW)) thread.start();
        animate++;
    }
    public void render(GraphicsContext gc) {
        if (time > 0)
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,animate , renderTime).getFxImage());
        else
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2,animate , renderTime).getFxImage());
        super.render(gc);
    }

    public static void set_Bomb() {
        if (bomb_number == 0 || can_add_bomb && bomb_number < 2) {
            new Sound("levels/SetBomb.wav", "setBomb");
            if (can_add_bomb) {
                bomb_number++;
            }
            if (tempx != -1 && tempy != -1) {
                checkWall[tempx][tempy] = Sprite.CODE_ID_GRASS;
            }
            tempx = bomber.getX()/32;
            tempy = bomber.getY()/32;
            Bomb bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
            checkWall[tempx][tempy] = Sprite.CODE_ID_BOOM;
            //map_flame[tempx][tempy] = 5;
            bomb_game--;
            //updateMenu();
            block.add(bomb);
        }
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public List<Explosion> getExplosionList() {
        return explosionList;
    }

    public void setExplosionList(List<Explosion> explosionList) {
        this.explosionList = explosionList;
    }

    public boolean isAlive() {
        if (time > 0) return true;
        for (Explosion explosion : explosionList) {
            if (explosion.getAnimate() > 50) return false;
        }
        return true;
    }

}
