import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

import java.util.ArrayList;

/**
 * Subclass of Sprite
 *
 * The Tank class describe a abstract type of towers
 *
 *  Method: draw() abstract draw objects in subclass
 *  // method with its functional properties;
 */
public abstract class Tank extends Sprite{

    private Point location;

    private DrawOptions drawOptions = new DrawOptions();

    protected Rectangle rect;
    protected Image image;
    protected int radius;
    protected int damage;
    protected double coolDown;
    protected int shootTime = 0;
    protected Slicer slicer  = null;

    protected ArrayList<Projectile> projectiles = new ArrayList<>();

    /**
     * Constructor of Tank
     *  Store the common information of all subclass of Tank
     *
     * @param location The current location of slicer
     */
    public Tank(Point location){
        this.location = location;
    }

    @Override
    public void draw(){
        this.image.draw(location.x,location.y,drawOptions);
        for (int i=0;i<projectiles.size();i++) {
            Projectile projectile = projectiles.get(i);
            projectile.draw();
            projectile.move();
            if (projectile.isRemoved()) {
                projectiles.remove(projectile);
                i--;
            }
        }
    }

    public void move(){
        shootTime+=ShadowDefend.timeScale;
        if(slicer != null){
            if(slicer.isRemoved()){
                this.slicer = null;
            }
        }
        Point target = null;
        if(slicer!=null){
            target = slicer.getLocation();
        }

        if (target != null) {
            Vector2 movement = new Vector2(target.x - location.x,
                    target.y - location.y);
            movement = movement.normalised();
            double radians = Math.abs(Math.atan(movement.y / movement.x));



            if (movement.x > 0) {
                if (movement.y > 0) {
                    radians += pi/2;
                    rotate(radians);
                } else {
                    radians -= pi/2;
                    rotate(-radians);
                }
            } else {
                if (movement.y > 0) {
                    radians -= pi/2;
                    rotate(pi - radians);
                } else {
                    radians += pi/2;
                    rotate(pi + radians);
                }

            }
        }

    }

    // The method set the draw option with the rotation radians
    private void rotate(double radians){
        this.drawOptions = drawOptions.setRotation(radians);
    }

    public void attack(Slicer slicer){
        if(slicer.ifEnterRadius(this)){

            if(shootTime>=coolDown) {
                this.slicer = slicer;
                if (this instanceof GeneralTank) {
                    projectiles.add(new GeneralProjectile(this));
                    for (Projectile projectile : projectiles) {
                        projectile.attack(slicer);
                    }
                    shootTime = 0;
                }else if(this instanceof SuperTank){
                    projectiles.add(new SuperProjectile(this));
                    for (Projectile projectile : projectiles) {
                        projectile.attack(slicer);
                    }
                    shootTime = 0;
                }
            }
        }
    }

    public Point getLocation(){
        return new Point(location.x,location.y);
    }
    protected void setLocation(Point point){
        this.location = point;
    }
    protected void setCoolDown(double coolDown){
        this.coolDown = coolDown;
    }
    protected void setDrawOptions(double rotation){
        this.drawOptions = drawOptions.setRotation(rotation);
    }

}
