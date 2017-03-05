package codes.timhung.endlessrunner;

/**
 * Created by Tim Hung on 3/7/2017.
 */

public class GameThread extends Thread {
    private Game game;
    private volatile boolean running = true;
    private final int FRAME_RATE = 100;

    public GameThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();

        // Game loop
        while (running) {
            long now = System.currentTimeMillis();
            long elapsed = now - lastTime;

            if (elapsed < FRAME_RATE) {
                game.update(elapsed);
                game.draw();
            }
            lastTime = now;
        }
    }

    public void shutdown() {
        running = false;
    }
}
