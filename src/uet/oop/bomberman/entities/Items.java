package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Items extends Entity {

    protected boolean used = false;

    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Items() {
    }

    public Items(boolean used) {

    }

    public boolean is_used() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public void update() {

    }
}

