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

public class Game {

    private enum GameState {
        START, PAUSED, RUNNING, LOST
    }

    private Context context;
    private SurfaceHolder holder;
    private Rect screen;
    private Resources resources;
    private GameState state = GameState.START;

    private Player player;
    private ScrollableBackground highway;
    private ScrollableBackground skyline_close;
    private ScrollableBackground skyline_mid;
    private ScrollableBackground skyline_far;
    private Vehicle testCar;
    private Sprite loseText;

    Paint borderPaint = new Paint();

    public Game(Context context, Rect screen, SurfaceHolder holder, Resources resources) {
        this.context = context;
        this.screen = screen;
        this.holder = holder;
        this.resources = resources;
        restartGame();
    }

    public void onTouchEvent(MotionEvent event) {
        if (state == GameState.RUNNING) {
            player.jump();
        } else if(state == GameState.LOST){
            restartGame();
        } else if(state == GameState.START) {
            state = GameState.RUNNING;
        } else if(state == GameState.PAUSED) {
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

            if(Rect.intersects(testCar.getHitbox(), player.getHitbox())) loseGame();
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
                case RUNNING:
                    drawGame(canvas);
                    break;
                case LOST:
                    drawGame(canvas);
                    loseText.draw(canvas, 0);
                    break;
                case START:
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

    private void restartGame() {
        player = new Player(null, context, new Rect(
                400,
                screen.height()/2,
                520,
                screen.height()/2 + 220),
                screen);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        highway = new ScrollableBackground( BitmapFactory.decodeResource(resources, R.drawable.highway, options),
                context, new Rect( 0, screen.height() - screen.width() / 10, screen.width(), screen.height()), screen, 12);

        skyline_close = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_close, options),
                context, new Rect( 0, screen.height() / 2, screen.height() * 3, screen.height()), screen, 8);

        skyline_mid = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_mid, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 4);

        skyline_far = new ScrollableBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.skyline_far, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 2);

        testCar = new Vehicle(null, context, Vehicle.generate(screen), screen, screen.height() - screen.width() / 10);

        loseText = new Sprite(BitmapFactory.decodeResource(resources, R.drawable.lose_text, options),
                context, new Rect(screen.width() / 2 - 600, screen.height() / 2 - 180, screen.width() / 2 + 600, screen.height() / 2 + 180), screen);

        borderPaint.setStrokeWidth(24);
        borderPaint.setColor(Color.GREEN);
        borderPaint.setStyle(Paint.Style.STROKE);
        state = GameState.RUNNING;
    }

    private void loseGame() {
        state = GameState.LOST;
    }
}
