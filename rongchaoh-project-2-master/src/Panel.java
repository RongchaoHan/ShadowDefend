import bagel.util.Point;
import bagel.util.Rectangle;

public abstract class Panel extends Sprite{

    private final String statusPanelFileName = "res/images/statuspanel.png";
    /**
     * Constructor for Panel
     *
     * */
    public Panel(){

    }


    public abstract void draw();

    public abstract void move();

    @Override
    public boolean interact() {
        return false;
    }


    public abstract PurchaseItem ifClicked(Point point);
}
