import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.ArrayList;

public abstract class Slicer extends Sprite{

    private Image image;
    private Point location;
    private DrawOptions drawOptions;
    private ArrayList<Point> next;

    private int health;
    private int reward;
    private int speed;
    /**
     * Constructor for Slicer
     *
     * @param location  The initial position of slicer when it is initialised.
     * @param next      The target point for the current slicer to move to.
     */
    public Slicer(Point location, Point next) {
        super(location, next);
        this.next = new ArrayList<>();
        this.drawOptions = new DrawOptions();
    }

    @Override
    public void draw() {
        this.image.draw(location.x,location.y,drawOptions);
    }

    @Override
    public void move(){
        Point target = next.get(next.size()-1);
        if (next != null && location != null) {
            Vector2 movement = new Vector2(target.x - location.x, target.y - location.y)
                    .normalised().mul(speed * ShadowDefend.timeScale);
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
        return false;
    }

    // The method set the draw option with the rotation radians
    private void rotate(double radians){
        this.drawOptions = drawOptions.setRotation(radians);
    }

    public boolean arriveToNext(){
        Point point = next.get(next.size()-1);
        double distance = point.distanceTo(location);
        return distance <= ShadowDefend.timeScale*speed;
    }


    protected void setImage(String fileName){
        this.image = new Image(fileName);
    }
    public Image getImage(){
        return image;
    }
    protected void setLocation(Point point) {
        this.location = new Point(point.x, point.y);
        this.next.add(point);
    }
    public Point getLocation(){return location;}
    protected void setNext(Point point){
        this.next.add(point);
    }
    public ArrayList<Point> getNext(){
        return next;
    }
    protected void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return health;
    }
    protected void setReward(int reward) {
        this.reward = reward;
    }
    protected  void setSpeed(int speed){
        this.speed = speed;
    }
}
