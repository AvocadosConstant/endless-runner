package codes.timhung.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class Player extends Sprite {

    public Player(Bitmap image, Rect hitbox, Rect screen) {
        super(image, hitbox, screen);
        this.affectedByGrav = true;
    }

    public void update(long elapsed) {
        // Check for player hitting bottom
        if(this.getHitbox().bottom >= screen.bottom) {
            this.setY(screen.bottom - this.getHeight());
            // Don't let player fall through
            this.vy = 0;
            // Apply friction in the x axis
            //this.applyFrictionX();
        }
        Log.d("PLAYER", "y: " + (this.getY() - getWidth()));
        //Log.d("PLAYER", "vx: " + vx + " | ax: " + ax);
        //Log.d("PLAYER", "vy: " + vy + " | ay: " + ay);
        //Log.d("PLAYER", "height: " + this.getHeight() + " | width: " + this.getWidth());
        //Log.d("PLAYER", "HBheight: " + this.getHitbox().height() + " | HBwidth: " + this.getHitbox().width());
        //Log.d("PLAYER", "Screen height: " + screen.height() + " | Screen width: " + screen.width());

        super.update(elapsed);

        this.ax = this.ay = 0;
    }

    public void jump() {
        Log.d("PLAYER", "Jump");
        if(Math.abs(this.getBottom() - screen.bottom) < 5) this.applyForce(0, -50);
    }

    public void applyForce(double fax, double fay) {
        this.ax = fax;
        this.ay = fay;
    }

    public void applyFrictionX() {
        if(this.vx > 1) {
            this.ax = (this.vx > FRIC) ? -FRIC : this.vx;
        } else if(this.vx < -1) {
            this.ax = (this.vx < -FRIC) ? FRIC : this.vx;
        } else {
            this.ax = 0;
            this.vx = 0;
        }
    }
}
