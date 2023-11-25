import bagel.util.Point;
import bagel.util.Rectangle;

public class GeneralTankProjectile extends Projectile {

    public static final String generalTankProjectileFileName = "res/images/tank_projectile.png";

    /**
     * Constructor for Projectile
     *
     * @param tower  The bullet is fired by specific tower
     * @param slicer The bullet is fired towards specific slicer
     */


    public GeneralTankProjectile(Tower tower, Slicer slicer, int timeCreated) {
        super(tower, slicer, timeCreated);
        setImage(generalTankProjectileFileName);
        setTower(tower);
        setSlicer(slicer);
        setLocation(tower.getLocation());
        setTimeCreated(timeCreated);
    }

}
