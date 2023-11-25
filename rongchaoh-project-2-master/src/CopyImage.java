import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;


public class CopyImage extends Panel {

    private String fileName;
    private Image image;
    private Point location;
    private static Rectangle rect;
    private DrawOptions drawOptions = new DrawOptions();

    /**
     * Constructor for CopyImage
     */
    public CopyImage(String fileName, Point location) {
        this.fileName = fileName;
        this.image = new Image(fileName);
        this.location = location;
    }

    @Override
    public void draw() {
        location = ShadowDefend.mousePosition;
        image.draw(location.x,location.y);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean interact() {
        return false;
    }


    @Override
    public PurchaseItem ifClicked(Point point) {
        return null;
    }

    public String getFileName() {
        return fileName;
    }
}
