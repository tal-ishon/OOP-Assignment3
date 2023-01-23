import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The type Game.
 *
 * @author Tal Ishon. Game class. This class creates a game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private int screenWidth = 800;
    private int screenHeight = 600;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Game method.
     * This method constructs a game object.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }
    /**
     * addCollidable Method.
     * This method adds a given collidable object to game.
     * @param c a Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite method.
     * this method adds a given sprite object to game.
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize method.
     * Initialize a new game: create the Blocks and Ball (and Paddle),
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new biuoop.GUI("Hey Look Ma I Made It", this.screenWidth, this.screenHeight);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.sleeper = new biuoop.Sleeper();
        //create paddle
        Rectangle r = new Rectangle(new Point(300, 560), 100, 20);
        Paddle paddle = new Paddle(r, Color.orange, this.screenWidth, this.screenHeight, keyboard);
        paddle.addToGame(this);
        // create ball
        Ball ball = new Ball(new Point(150, 200), 5, Color.magenta);
        Ball ball2 = new Ball(new Point(100, 130), 5, Color.white);
        ball.setVelocity(5, 6);
        ball2.setVelocity(7, 4);
        ball.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        ball.setPaddle(paddle);
        ball2.setPaddle(paddle);
        ball.addToGame(this);
        ball2.addToGame(this);
        // create frame's blocks
        Block[] frameBlocks = new Block[4];
        frameBlocks[0] = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.lightGray);
        frameBlocks[1] = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.lightGray);
        frameBlocks[2] = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.lightGray);
        frameBlocks[3] = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.lightGray);
        for (Block block : frameBlocks) {
            block.addToGame(this);
        }

        // create other blocks
        double width = 55;
        double height = 20;
        Point point = new Point(780 - width, 120);
        Block[] firstRow = new Block[12];
        for (Block block : firstRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.magenta);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        point = new Point(780 - width, 140);
        Block[] secondRow = new Block[11];
        for (Block block : secondRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.orange);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        point = new Point(780 - width, 160);
        Block[] thirdRow = new Block[10];
        for (Block block : thirdRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.pink);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        point = new Point(780 - width, 180);
        Block[] forthRow = new Block[9];
        for (Block block : forthRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.yellow);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        point = new Point(780 - width, 200);
        Block[] fifthRow = new Block[8];
        for (Block block : fifthRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.white);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        point = new Point(780 - width, 220);
        Block[] sixthRow = new Block[7];
        for (Block block : sixthRow) {
            block = new Block(new Rectangle(new Point(point.getX(), point.getY()), width, height), Color.orange);
            point = new Point(point.getX() - width, point.getY());
            block.addToGame(this);
            ball.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
    }

    /**
     * Run method.
     *  Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            // background.
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, screenWidth, screenHeight);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(millisecondsPerFrame);
            }
        }
    }
}
