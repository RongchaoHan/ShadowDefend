import bagel.util.Point;
import bagel.util.Rectangle;

public abstract class Sprite {

    protected static final double pi = 3.1415926;

    /**
     * Constructor for Wave
     *
     * @param waveNumber The index of level for each game;
     */
    public Sprite(int waveNumber) { }

    /**
     * Constructor for Slicer
     *
     * @param location The initial position of slicer when it is initialised.
     * @param next     The target point for the current slicer to move to.
     * */
    public Sprite(Point location, Point next){ }

    /**
     * Constructor for Tower
     *
     * @param location The initial position of slicer when it is initialised.
     * */
    public Sprite(Point location){ }

    /**
     * Constructor for Panel
     *
     * */
    public Sprite(){}

    /**
     * Constructor for Projectile
     *
     * @param tower The bullet is fired by specific tower
     * @param slicer The bullet is fired towards specific slicer
     * @param timeCreated The time of which projectile is created
     * */
    public Sprite(Tower tower, Slicer slicer, int timeCreated){}




    public abstract void draw();

    public abstract void move();

    public abstract boolean interact();
}
