import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class SuperTank extends Tank {

    public static final String FILENAME = "res/images/supertank.png";
    public static final int PRICE = 600;
    public static final int DAMAGE = 3;
    private final int RADIUS = 150;
    private final double COOLDOWN = (500*1.0/1000)*60;

    /**
     * Constructor of SuperTank
     * Store the common information of a SuperTank
     *
     * @param location The current location of slicer
     */
    public SuperTank(Point location) {
        super(location);
        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(location));
        super.damage = DAMAGE;
        super.radius = RADIUS;
        super.coolDown = COOLDOWN;
    }
}
