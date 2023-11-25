import bagel.Image;
import bagel.util.Rectangle;

/**
 * Subclass of Projectile
 *
 * The SuperProjectile class describe the type of projectile
 *  for super tanks
 *
 *  Method: draw() draw objects
 *  // method with its functional properties;
 */
public class SuperProjectile extends Projectile {

    private final String FILENAME = "res/images/supertank_projectile.png";
    private final int DAMAGE = SuperTank.DAMAGE;
    private final int SPEED = 10;

    /**
     * Constructor of SuperProjectile
     * Store the common information of the type of projectile
     *
     * @param tank     The tank that the projectile belongs to
     */
    public SuperProjectile(Tank tank) {
        super(tank);

        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(tank.getLocation()));
        super.damage = DAMAGE;
        super.speed = SPEED;
    }

}

