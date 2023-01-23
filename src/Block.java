import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
/**
 * @author Tal Ishon.
 * Block class.
 * this class represents block.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * construct a block from a rectangle.
     * @param rect the rectangle that defines the block.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
        int myColor = new Random().nextInt();
        this.color = new Color(myColor);
    }

    /**
     * construct a block from a rectangle and color.
     * @param rect the rectangle that defines the block.
     * @param color the rectangle color.
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        // collision point collides with horizontal line
        if (this.rectangle.getUpperLine().isInRange(collisionPoint)
            || this.rectangle.getBottomLine().isInRange(collisionPoint)) {
            v = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        // collision point collides with vertical line
        if (this.rectangle.getLeftLine().isInRange(collisionPoint)
            || this.rectangle.getRightLine().isInRange(collisionPoint)) {
            v = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }

        return v;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame method.
     * Adds the ball to the game's sprite and Collidable list.
     * @param g the game that played.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
