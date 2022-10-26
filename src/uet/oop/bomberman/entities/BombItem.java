package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomber;

public class BombItem extends Items {
    public static boolean can_add_bomb = false;

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public BombItem() {
    }

    public BombItem(boolean used) {
        super(used);
    }

    @Override
    public void update() {
        if (!this.used)
            if (bomber.getX() == this.x && bomber.getY() == this.y) {
                //Grass grass = new Grass(this.x/32, this.y/32, Sprite.grass.getFxImage());
                new Sound("levels/SoundItems.wav","receiveItem");
                this.setImg(Sprite.grass.getFxImage());
                this.used = true;
                can_add_bomb = true;
            }
    }
}
