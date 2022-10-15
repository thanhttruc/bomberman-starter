package uet.oop.bomberman.entities.explosion;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Explosion extends Entity {

    int animate = 0;
    int time = 34;


    public Explosion(int x, int y, Image img) {
        super(x,y,img);
    }

    @Override
    public void update() {
        animate++;
    }

    public int getAnimate() {
        return animate;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
