package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.balloom;
import static uet.oop.bomberman.entities.Bomb.map_flame;

public class Oneal extends Animal {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }

    public int swap_img = 1;
    public int delay_swap = 0;
    private void Oneal_dead(Animal animal) {
        if (delay_swap % 25 == 0) {
            if (swap_img == 1) {
                animal.setImg(Sprite.oneal_dead.getFxImage());
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
                //entity.remove(oneal);
                //swap_img = 1;
            }
        }
    }
    private void checkBomb() {
        int ax = oneal.getX() / 32;
        int ay = oneal.getY() / 32;
        if (map_flame[ax][ay] == 5) {
            oneal.life = false;
        }
    }
    public void update() {
        if (oneal.life) {
            if (bomber.getX() < this.x) {
                Move.left(this);
            }
            if (bomber.getX() > this.x) {
                Move.right(this);
            }
            if (bomber.getY() > this.y) {
                Move.down(this);
            }
            if (bomber.getY() < this.y) {
                Move.up(this);
            }
        }

        checkBomb();
        delay_swap++;
        if (!oneal.life) {
            Oneal_dead(oneal);
        }
    }
}
