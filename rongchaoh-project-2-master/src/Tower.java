import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

import java.util.ArrayList;

public abstract class Tower extends Sprite{

    private Image image;
    private Point location;
    private Slicer slicer;
    private DrawOptions drawOptions;
    private ArrayList<Projectile> projectiles;

    private int effectRadius;
    private int damage;
    private int coolDown;
    private int price;
    private int time = 0;
    /**
     * Constructor for Tower
     *
     * @param location The initial position of slicer when it is initialised.
     * */
    public Tower(Point location){
        super(location);
        this.drawOptions = new DrawOptions();
        this.projectiles = new ArrayList<>();
    }

    @Override
    public void draw() {
        this.image.draw(location.x,location.y,drawOptions);
        for (Projectile projectile: projectiles) {
            projectile.draw();
            projectile.move();
            if(projectile.interact()){
                time = projectile.getTimeCreated();
                projectile.getSlicer().setHealth(projectile.getSlicer().getHealth() - damage);
                projectiles.remove(projectile);
                break;
            }
        }
    }

    public void attack(Slicer slicer){
        if (this instanceof GeneralTank) {
            if (!projectiles.isEmpty()){
                if(ShadowDefend.time>=coolDown+time) {
                    projectiles.add(new GeneralTankProjectile(this, slicer, ShadowDefend.time));
                    this.slicer = slicer;
                    time = ShadowDefend.time;
                }
            }else if(projectiles.isEmpty()&&time>0){
                if(ShadowDefend.time>=coolDown+time) {
                    projectiles.add(new GeneralTankProjectile(this, slicer, ShadowDefend.time));
                    this.slicer = slicer;
                    time = ShadowDefend.time;
                }
            }else if(projectiles.isEmpty()&&time ==0 ){
                projectiles.add(new GeneralTankProjectile(this, slicer, ShadowDefend.time));
                this.slicer = slicer;
            }

        }
    }
    @Override
    public void move() {
        Point target = null;
        if(slicer!=null){
            target = slicer.getLocation();
        }
        if (target != null) {
            Vector2 movement = new Vector2(target.x - getLocation().x,
                    target.y - getLocation().y);
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
        if(target!=null&&target.distanceTo(location)>=effectRadius){
            this.slicer = null;
        }
    }

    @Override
    public boolean interact() {
        return false;
    }


    // The method set the draw option with the rotation radians
    private void rotate(double radians){
        this.drawOptions = drawOptions.setRotation(radians);
    }

    protected void setImage(String fileName){
        this.image = new Image(fileName);
    }
    protected void setLocation(Point point) {
        this.location = new Point(point.x, point.y);
    }
    public Point getLocation(){
        return location;
    }
    protected void setEffectRadius(int effectRadius){
        this.effectRadius = effectRadius;
    }
    public int getEffectRadius(){
        return this.effectRadius;
    }
    protected void setCoolDown(int coolDown){
        this.coolDown = coolDown;
    }
    public int getCoolDown(){
        return coolDown;
    }
    protected void setDamage(int damage){
        this.damage = damage;
    }
    protected void setPrice(int price){
        this.price = price;
    }
    public ArrayList<Projectile> getProjectiles(){
        return projectiles;
    }

}
