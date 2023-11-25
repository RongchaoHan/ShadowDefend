import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Subclass of Slicer
 *
 * The ApexSlicer class describe the type of apex slicers
 *
 *  Method: draw() abstract draw objects in subclass
 *  // method with its functional properties;
 */
public class ApexSlicer extends Slicer{

    private final String FILENAME = "res/images/apexslicer.png";
    private final int HEALTH = 25;
    private final double SPEED = 2*3.0/8;
    private final int REWAED = 150;

    /**
     * Constructor of ApexSlicer
     * Store the common information of all subclass of Slicer
     *
     * @param location  The current location of slicer
     * @param next      The target point slicer is moving to
     * @param nextIndex The index of target point in the map polyline
     */
    public ApexSlicer(Point location, Point next, int nextIndex) {
        super(location, next, nextIndex);

        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(location));
        super.health = HEALTH;
        super.speed = SPEED;
        super.reward = REWAED;
    }
}
