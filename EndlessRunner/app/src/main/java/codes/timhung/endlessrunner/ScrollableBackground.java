package codes.timhung.endlessrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class ScrollableBackground {

    public Sprite[] sprites;
    public int speed;

    public ScrollableBackground(Bitmap image, Context context, Rect hitbox, Rect screen, int speed) {
        sprites = new Sprite[2];
        this.speed = speed;

        sprites[0] = new Sprite(image, context, hitbox, screen);
        sprites[1] = new Sprite(image, context, hitbox, screen);
        sprites[0].setX(0);
        sprites[1].setX(sprites[0].getRight());
    }

    public void update(long elapsed) {
        for(Sprite s : sprites) {
            //Log.d("SCROLLABLE_BACKGROUND", "Updating with speed " + speed);
            s.setX(s.getX() - speed);

            // Move sprite to right of other one if it's past the left side of screen
            if(s.getRight() < 0) s.setX(
                    (s == sprites[0]) ? sprites[1].getRight() - speed: sprites[0].getRight() - speed);
        }
    }

    public void draw(Canvas canvas) {
        for(Sprite s : sprites) {
            //Log.d("SCROLLABLE_BACKGROUND", "Drawing at x = " + s.getX() + "!");
            s.draw(canvas, 0);
        }
    }
}
