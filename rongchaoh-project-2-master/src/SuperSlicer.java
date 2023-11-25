import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Subclass of Slicer
 *
 * The SuperSlicer class describe the type of super slicers
 *
 *  Method: draw() abstract draw objects in subclass
 *  // method with its functional properties;
 */
public class SuperSlicer extends Slicer{

    private final String FILENAME = "res/images/superslicer.png";
    private final int HEALTH = 2;
    private final double SPEED = 2*3.0/4;
    private final int REWAED = 15;

    /**
     * Constructor of SuperSlicer
     * Store the common information of all subclass of Slicer
     *
     * @param location  The current location of slicer
     * @param next      The target point slicer is moving to
     * @param nextIndex The index of target point in the map polyline
     */
    public SuperSlicer(Point location, Point next, int nextIndex) {
        super(location, next, nextIndex);

        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(location));
        super.health = HEALTH;
        super.speed = SPEED;
        super.reward = REWAED;
    }
}
