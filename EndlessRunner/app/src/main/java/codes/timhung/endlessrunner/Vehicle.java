package codes.timhung.endlessrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Vehicle extends Sprite {

    public Rect hitbox;
    public int roadHeight;

    public Vehicle(Bitmap image, Context context, Rect hitbox, Rect screen, int roadHeight) {
        super(image, context, hitbox, screen);
        this.roadHeight = roadHeight;
        this.vx = -30;
        this.setX(screen.right);
        this.setY(roadHeight - this.getHeight());
    }

    public static Rect generate(Rect screen) {
        return new Rect(0, 0, 300, 140);
    }

    public boolean isOffScreen() {
        return this.getRight() < screen.left;
    }
}
