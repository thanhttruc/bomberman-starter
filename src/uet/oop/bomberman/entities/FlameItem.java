package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomber;

public class FlameItem extends Items {

    public static int damageLevel = 1;
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public FlameItem(boolean used) {
        super(used);
    }

    public FlameItem() {

    }

    @Override
    public void update() {
        if (!this.used)
            if (bomber.getX() == this.x && bomber.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.used = true;
                damageLevel = 2;
            }
    }
}
