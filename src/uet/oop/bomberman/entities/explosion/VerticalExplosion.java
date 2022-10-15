package uet.oop.bomberman.entities.explosion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class VerticalExplosion extends Explosion {

    boolean isLast = true;
    boolean isTop = true;

    public VerticalExplosion(int x, int y, Image img, boolean isLast, boolean isTop) {
        super(x,y,img);
        this.isLast = isLast;
        this.isTop = isTop;
    }


    @Override
    public void render(GraphicsContext gc) {
        if (isLast)
            if (isTop)
                setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2,animate , time).getFxImage());
            else
                setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2,animate , time).getFxImage());
        else
            setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2,animate , time).getFxImage());

        super.render(gc);
    }
}
