package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomb.map_flame;
import static uet.oop.bomberman.entities.Bomber.delay_swap;
import static uet.oop.bomberman.entities.Bomber.swap_img;

public class Balloom extends Animal {

    public Balloom(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    public int swap_img = 1;
    public int delay_swap = 0;
    private void Balloom_dead(Animal animal) {
        if (delay_swap % 25 == 0) {
            if (swap_img == 1) {
                animal.setImg(Sprite.balloom_dead.getFxImage());
                swap_img = 2;
            } else if (swap_img == 2) {
                animal.setImg(Sprite.mob_dead1.getFxImage());
                swap_img = 3;
            } else if (swap_img == 3) {
                animal.setImg(Sprite.mob_dead2.getFxImage());
                swap_img = 4;
            } else if (swap_img == 4) {
                animal.setImg(Sprite.mob_dead3.getFxImage());
                swap_img = 5;
            } else {
                animal.setImg(Sprite.transparent.getFxImage());
                entity.remove(balloom);
                //swap_img = 1;
            }
        }
    }
    private void checkBomb() {
        int ax = balloom.getX() / 32;
        int ay = balloom.getY() / 32;
        if (map_flame[ax][ay] == 5) {
            balloom.life = false;
        }
    }

    @Override
    public void update() {
        Random random = new Random();
        int direction = random.nextInt(4);
        if (balloom.life) {
            switch (direction) {
                case 0:
                    Move.down(this);
                    break;
                case 1:
                    Move.up(this);
                    break;
                case 2:
                    Move.left(this);
                    break;
                case 3:
                    Move.right(this);
                    break;
            }
        }
        checkBomb();
        delay_swap++;
        if (!balloom.life) {
            Balloom_dead(balloom);
        }
    }
}
