package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.checkWall;

public class Brick extends Entity {

    boolean broken = false;
    int animate = -1;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        //animate++;
        if (broken) {
            animate ++;
        }
        if (animate > 15) {
            List<Entity> stillObjects = BombermanGame.getInstance().getStillObjects();
            if (stillObjects.contains(this)) {
                stillObjects.remove(this);
                checkWall[this.getX()/32][this.getY()/32] = Sprite.CODE_ID_GRASS;
            }
            List<Entity> entities = BombermanGame.getInstance().getEntities();
            if (entities.contains(this)) {
                entities.remove(this);
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (animate >= 0) {
            setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 5).getFxImage());
        }
        super.render(gc);
    }

    public int getAnimate() {
        return animate;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public void remove() {
        broken = true;
        animate = 0;
    }
}
