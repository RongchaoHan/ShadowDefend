import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public abstract class Projectile extends Sprite{

    private final int speed = 10;

    private Image image;
    private Tower tower;
    private Slicer slicer;
    private Point location;
    private int timeCreated;
    private DrawOptions drawOptions;
    /**
     * Constructor for Projectile
     *
     * @param tower The bullet is fired by specific tower
     * @param slicer The bullet is fired towards specific slicer
     * @param timeCreated The time of which projectile is created
     * */
    public Projectile(Tower tower, Slicer slicer, int timeCreated){
        super(tower, slicer, timeCreated);
        this.drawOptions = new DrawOptions();

    }

    public void draw(){this.image.draw(location.x,location.y,drawOptions);}

    @Override
    public void move() {
        Point target = slicer.getLocation();
        if (target != null) {
            Vector2 movement = new Vector2(target.x - location.x,
                    target.y - location.y);
            movement = movement.normalised();
            movement = movement.mul(speed*ShadowDefend.timeScale);
            location = new Point(location.x + movement.x, location.y + movement.y);

            double radians = Math.abs(Math.atan(movement.y / movement.x));
            if (movement.x > 0) {
                if (movement.y > 0) {
                    rotate(radians);
                } else {
                    rotate(-radians);
                }
            } else {
                if (movement.y > 0) {
                    rotate(pi - radians);
                } else {
                    rotate(pi + radians);
                }

            }
        }
    }

    @Override
    public boolean interact() {
        return slicer.getImage().getBoundingBoxAt(slicer.getLocation()).intersects(location);
    }


    private void rotate(double radians){
        this.drawOptions = drawOptions.setRotation(radians);
    }


    public void interact(Point point){

    }

    protected void setImage(String fileName){
        this.image = new Image(fileName);
    }
    protected void setTower(Tower tower){
        this.tower = tower;
    }
    protected void setSlicer(Slicer slicer){
        this.slicer = slicer;
    }
    public Slicer getSlicer(){
        return slicer;
    }
    protected void setLocation(Point point) {
        this.location = point;
    }
    protected void setTimeCreated(int timeCreated){
        this.timeCreated = timeCreated;
    }
    public int getTimeCreated(){
        return timeCreated;
    }
}
