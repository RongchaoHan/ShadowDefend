import bagel.Image;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class Airplane extends Tank {

    public static final String FILENAME = "res/images/airsupport.png";
    public static final int PRICE = 500;
    public static final int DAMAGE = 500;
    private final double COOL_DOWN = 2*60;
    private final double SPEED = 3;

    private Point target;
    private int shootTime = 0;
    private boolean ifRemove = false;

    /**
     * Constructor of AirSupport
     * Store the common information of a AirSupport
     *
     * @param location The current location of slicer
     */
    public Airplane(Point location) {
        super(location);
        super.setLocation(iniLocation(location));
        this.target = iniTarget(location);
        super.setDrawOptions(iniRotation(location));
        super.image = new Image(FILENAME);
        super.rect = new Rectangle(image.getBoundingBoxAt(location));
        super.damage = DAMAGE;
        super.coolDown = Math.random()*COOL_DOWN;
    }

    private boolean ifHorizontal(Point location) {
        if ((location.x / Window.getWidth()) > (location.y / Window.getHeight())) {
            return true;
        }
        return false;
    }
    private Point iniLocation(Point location){
        if(ifHorizontal(location)){
            Point point = new Point(-70,location.y);
            return point;
        }
        Point point = new Point(location.x,100);
        return point;
    }
    private Point iniTarget(Point location){
        if(ifHorizontal(location)){
            Point point = new Point(Window.getWidth()+70,getLocation().y);
            return point;
        }
        Point point = new Point(getLocation().x,Window.getHeight()+70);
        return point;
    }
    private double iniRotation(Point location){
        if(ifHorizontal(location)){
            return pi/2;
        }
        return pi;
    }

    @Override
    public void move(){
        shootTime++;
        Vector2 movement = target.asVector().sub(getLocation().asVector()).normalised().mul(SPEED).mul(ShadowDefend.timeScale);
        setLocation(getLocation().asVector().add(movement).asPoint());
        rect = image.getBoundingBoxAt(getLocation());
        if(rect.centre().distanceTo(target)<=INTERACT_LIMITATION*ShadowDefend.timeScale){
            this.ifRemove = true;
        }
        if(shootTime>=coolDown){
            explosiveDropping();
            shootTime=0;
        }

    }
    @Override
    public void attack(Slicer slicer){
        for (Projectile projectile: projectiles) {
            projectile.attack(slicer);
        }
    }

    public void explosiveDropping(){
        if(projectiles.size()>0){
            projectiles.clear();
        }
        projectiles.add(new Explosive(this));
        super.setCoolDown(Math.random()*COOL_DOWN);
    }

    public boolean isIfRemove(){
        return this.ifRemove;
    }
}