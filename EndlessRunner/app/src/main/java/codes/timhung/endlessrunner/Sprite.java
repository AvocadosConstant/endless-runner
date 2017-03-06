package codes.timhung.endlessrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by Tim Hung on 3/7/2017.
 */


/**
 * Parent class of any sprite
 */
public class Sprite {
    public enum SpriteState {
        IDLE, JUMP, FLY, LAND
    }
    public Bitmap image;
    public Context context;
    private Rect hitbox;
    public Rect screen;
    private SpriteState spriteState;

    private int width;
    private int height;
    private double x;
    private double y;

    public double vx;
    public double vy;
    public double ax;
    public double ay;

    public final double FRIC = 3;
    public final double GRAV = 2;
    public boolean affectedByGrav = false;

    Paint noAliasPaint = new Paint();
    Paint borderPaint = new Paint();
    Paint vectorPaint = new Paint();

    public Sprite(Bitmap image, Context context, Rect hitbox, Rect screen) {
        this.image = image;
        this.context = context;
        this.hitbox = hitbox;
        this.screen = screen;
        spriteState = SpriteState.IDLE;

        this.width = hitbox.width();
        this.height = hitbox.height();
        this.x = hitbox.left;
        this.y = hitbox.top;

        this.vx = 0;
        this.vy = 0;
        this.ax = 0;
        this.ay = 0;

        noAliasPaint.setAntiAlias(false);
        noAliasPaint.setFilterBitmap(false);
        noAliasPaint.setDither(false);
        noAliasPaint.setColor(Color.GREEN);

        borderPaint.setStrokeWidth(10);
        borderPaint.setStyle(Paint.Style.STROKE);

        vectorPaint.setStyle(Paint.Style.STROKE);
        vectorPaint.setStrokeWidth(8);
        vectorPaint.setColor(Color.GREEN);
    }

    public void update(long elapsed) {
        // Update velocities
        vx += ax;
        vy += ay;

        // Update positions
        if(this.affectedByGrav) vy += GRAV;
        setX(this.getX() + vx);
        setY(this.getY() + vy);
    }

    public void draw(Canvas canvas, long elevation) {
        //Log.d("SPRITE", "Drawing sprite at (" + hitbox.left + ", " + hitbox.top + ")");
        if(image != null) {
            // Draw image
            this.setY(this.getY());
            canvas.drawBitmap(image, null, getHitbox(), noAliasPaint);
        } else {
            drawHitbox(canvas, elevation, Color.MAGENTA);
        }
        //drawVecs(canvas, elevation, 15);
    }

    public void drawHitbox(Canvas canvas, long elevation, int color) {
        borderPaint.setColor(color);
        this.setY(this.getY());// + elevation);
        canvas.drawRect(hitbox, borderPaint);
    }

    public void drawVecs(Canvas canvas, long elevation, int scalar) {
        // Draw velocities
        Path path = new Path();
        path.moveTo(this.getHitbox().centerX(), this.getHitbox().centerY());
        path.lineTo(this.getHitbox().centerX() + (int) vx * scalar,
                this.getHitbox().centerY() + (int) vy * scalar);
        path.close();
        canvas.drawPath(path, vectorPaint);
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public int getHeight() {return this.height;}

    public int getWidth() {return this.width;}

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public double getRight() {return this.x + this.getWidth();}

    public double getBottom() {return this.y + this.getHeight();}

    public void setX(double x) {
        this.x = x;
        //this.hitbox.offsetTo((int) this.x, (int) this.y);
        this.getHitbox().set(
                (int) x,
                (int) y,
                (int) x + this.getWidth(),
                (int) y + this.getHeight()
        );
    }

    public void setY(double y) {
        this.y = y;
        //this.hitbox.offsetTo((int) this.x, (int) this.y);
        this.getHitbox().set(
                (int) x,
                (int) y,
                (int) x + this.getWidth(),
                (int) y + this.getHeight()
        );
    }
}
