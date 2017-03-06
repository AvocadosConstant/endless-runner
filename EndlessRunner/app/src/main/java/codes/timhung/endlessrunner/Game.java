package codes.timhung.endlessrunner;

import android.content.Context;
        import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;

/**
 * Created by Tim Hung on 3/7/2017.
 */

public class Game {

    private enum GameState {
        PAUSED, RUNNING
    }

    private Context context;
    private SurfaceHolder holder;
    private Rect screen;
    private Resources resources;
    private GameState state = GameState.PAUSED;

    private Player player;
    private ScrollableBackground highway;
    private ScrollableBackground skyline_close;
    private ScrollableBackground skyline_mid;
    private ScrollableBackground skyline_far;
    private Vehicle testCar;

    Paint borderPaint = new Paint();

    public Game(Context context, Rect screen, SurfaceHolder holder, Resources resources) {
        this.context = context;
        this.screen = screen;
        this.holder = holder;
        this.resources = resources;
        player = new Player(null, context, new Rect(
                400,
                screen.height()/2,
                520,
                screen.height()/2 + 220),
                screen);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        highway = new ScrollableBackground( BitmapFactory.decodeResource(context.getResources(), R.drawable.highway, options),
                context, new Rect( 0, screen.height() - screen.width() / 10, screen.width(), screen.height()), screen, 12);

        skyline_close = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_close, options),
                context, new Rect( 0, screen.height() / 2, screen.height() * 3, screen.height()), screen, 8);

        skyline_mid = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_mid, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 4);

        skyline_far = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_far, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 2);

        testCar = new Vehicle(null, context, Vehicle.generate(screen), screen, screen.height() - screen.width() / 10);

        borderPaint.setStrokeWidth(24);
        borderPaint.setColor(Color.GREEN);
        borderPaint.setStyle(Paint.Style.STROKE);
    }

    public void onTouchEvent(MotionEvent event) {
        if (state == GameState.RUNNING) {
            player.jump();
        } else {
            state = GameState.RUNNING;
        }
    }


    /**
     * Game logic is checked here! Hitboxes, movement, etc.
     * @param elapsed Time since game started
     */
    public void update(Long elapsed) {
        if(state == GameState.RUNNING){
            // Do stuff
            player.update(elapsed);
            highway.update(elapsed);
            skyline_close.update(elapsed);
            skyline_mid.update(elapsed);
            skyline_far.update(elapsed);
            testCar.update(elapsed);

            if(testCar.isOffScreen()) testCar = new Vehicle(null, context, Vehicle.generate(screen), screen, screen.height() - screen.width() / 10);
        }
    }


    /**
     * Decides whether to draw
     */
    public void draw() {
        //Log.d("GAME_DRAW", "Locking canvas");
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            switch (state) {
                case PAUSED:
                    break;
                case RUNNING:
                    drawGame(canvas);
                    break;
            }
            //Log.d("GAME_DRAW", "Unlocking canvas");
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Draws game resources
     * @param canvas Canvas to be drawn on
     */
    private void drawGame(Canvas canvas) {
        //Log.d("GAME_DRAWGAME", "Trying to draw everything in the game!");
        //canvas.drawRect(screen, borderPaint);
        skyline_far.draw(canvas);
        skyline_mid.draw(canvas);
        skyline_close.draw(canvas);
        highway.draw(canvas);
        testCar.draw(canvas, 0);
        player.draw(canvas, 0);
    }
}
