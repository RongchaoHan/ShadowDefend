import bagel.util.Point;
import bagel.util.Rectangle;

public class GeneralSlicer extends Slicer {

    private final String fileName = "res/images/slicer.png";
    private final int reward = 2;
    private final int speed = 2;
    private final int health = 1;


    /**
     * Constructor for Slicer
     *
     * @param location  The initial position of slicer when it is initialised.
     * @param next      The target point for the current slicer to move to.
     */
    public GeneralSlicer(Point location, Point next) {
        super(location, next);
        setImage(fileName);
        setLocation(location);
        setNext(next);
        setHealth(health);
        setReward(reward);
        setSpeed(speed);
    }

    @Override
    public boolean interact() {
        return false;
    }

}
