import bagel.Image;
import bagel.util.Rectangle;

import java.util.ArrayList;

/**
 * Subclass of Projectile
 *
 * The Explosive class describe the type of projectile
 *  for air support
 *
 *  Method: draw() draw objects
 *  // method with its functional properties;
 */
public class Explosive extends Projectile {

    private final String FILENAME = "res/images/explosive.png";
    private final int DAMAGE = Airplane.DAMAGE;
    private final int RADIUS = 200;
    private final int SPEED = 10;
    private final int REMOVE = 2*60;

    private ArrayList<Slicer> slicers = new ArrayList<>();
    private boolean ifRemoved = false;
    private int time = 0;

    /**
     * Constructor of Explosive
     * Store the common information of the type of projectile
     *
     * @param tank     The tank that the projectile belongs to
     */
    public Explosive(Tank tank) {
        super(tank);
        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(tank.getLocation()));
        super.damage = DAMAGE;
        super.speed = SPEED;
    }
    @Override
    public void move(){
        time+=ShadowDefend.timeScale;
        if(!ifRemoved) {
            for (Slicer slicer : slicers) {
                slicer.health -= (damage / slicers.size());
                if (slicer.health <= 0) {
                    slicer.setIfRemoved(true);
                    ShadowDefend.money += slicer.reward;
                }
            }
        }
        if(time>=REMOVE){
            ifRemoved = true;
        }

    }

    @Override
    public void attack(Slicer slicer){
        if(slicer.getLocation().distanceTo(rect.centre())<=RADIUS){
            if(slicer != null) {
                slicers.add(slicer);
            }
        }
    }



}