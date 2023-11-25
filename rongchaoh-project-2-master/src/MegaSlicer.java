import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Subclass of Slicer
 *
 * The MegaSlicer class describe the type of mega slicers
 *
 *  Method: draw() abstract draw objects in subclass
 *  // method with its functional properties;
 */
public class MegaSlicer extends Slicer{

    private final String FILENAME = "res/images/megaslicer.png";
    private final int HEALTH = 8;
    private final double SPEED = 2*3.0/4;
    private final int REWAED = 10;

    /**
     * Constructor of MegaSlicer
     *
     * @param location  The current location of slicer
     * @param next      The target point slicer is moving to
     * @param nextIndex The index of target point in the map polyline
     */
    public MegaSlicer(Point location, Point next, int nextIndex) {
        super(location, next, nextIndex);

        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(location));
        super.health = HEALTH;
        super.speed = SPEED;
        super.reward = REWAED;
    }
}
