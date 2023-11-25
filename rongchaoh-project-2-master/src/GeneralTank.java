import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class GeneralTank extends Tower {

    public static final String fileName = "res/images/tank.png";
    private final int effectRadius = 100;
    private final int coolDown = 60;
    private final int damage = 1;
    public final static  int price = 250;
    /**
     * Constructor for Tower
     *
     * @param location The initial position of slicer when it is initialised.
     */
    public GeneralTank(Point location) {
        super(location);
        setImage(fileName);
        setLocation(location);
        setEffectRadius(effectRadius);
        setCoolDown(coolDown);
        setDamage(damage);
        setPrice(price);
    }

    @Override
    public boolean interact() {
        return false;
    }
}
