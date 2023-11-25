import bagel.Image;
import bagel.util.Rectangle;

/**
 * Subclass of Projectile
 *
 * The GeneralProjectile class describe the type of projectile
 *  for general tanks
 *
 *  Method: draw() draw objects
 *  // method with its functional properties;
 */
public class GeneralProjectile extends Projectile {

    private final String FILENAME = "res/images/tank_projectile.png";
    private final int DAMAGE = GeneralTank.DAMAGE;
    private final int SPEED = 10;

    /**
     * Constructor of GeneralProjectile
     * Store the common information of the type of projectile
     *
     * @param tank     The tank that the projectile belongs to
     */
    public GeneralProjectile(Tank tank) {
        super(tank);

        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(tank.getLocation()));
        super.damage = DAMAGE;
        super.speed = SPEED;
    }

}
