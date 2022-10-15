package uet.oop.bomberman.entities.explosion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class HorizontalExplosion extends Explosion {

    boolean isLast = true;
    boolean isRight = true;

    public HorizontalExplosion(int x, int y, Image img, boolean isLast, boolean isRight) {
        super(x,y,img);
        this.isLast = isLast;
        this.isRight = isRight;
    }


    @Override
    public void render(GraphicsContext gc) {
        if (isLast)
            if (isRight)
                setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        , Sprite.explosion_horizontal_right_last2,animate , time).getFxImage());
            else
                setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        , Sprite.explosion_horizontal_left_last2,animate , time).getFxImage());
        else
            setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                    , Sprite.explosion_horizontal2,animate , time).getFxImage());

        super.render(gc);
    }
}
