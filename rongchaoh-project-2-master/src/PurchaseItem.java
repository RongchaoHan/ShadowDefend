import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;


public class PurchaseItem extends Panel {

    private Rectangle rect;
    private final int stringSize = 25;
    private int price;
    private String fileName;
    private Image image;
    private Point location;
    private StringFont stringFont;
    private DrawOptions drawOptions = new DrawOptions();

    /**
     * Constructor for Panel
     *
     * */
    public PurchaseItem(String fileName, int price, Point location){
        this.price = price;
        this.fileName = fileName;
        this.image = new Image(fileName);
        this.location = location;
        this.rect = image.getBoundingBoxAt(location);
        if(ShadowDefend.money>=price){
            this.stringFont = new StringFont("$"+price, stringSize, Colour.GREEN);
        }else{
            this.stringFont = new StringFont("$"+price, stringSize, Colour.RED);
        }
    }

    @Override
    public void draw() {
        image.draw(location.x,location.y,drawOptions);
        if(ShadowDefend.money>=price){
            stringFont.draw(image.getBoundingBoxAt(location).bottomLeft().x,
                    image.getBoundingBoxAt(location).bottomLeft().y,Colour.GREEN);
        }else{
            stringFont.draw(image.getBoundingBoxAt(location).bottomLeft().x,
                    image.getBoundingBoxAt(location).bottomLeft().y,Colour.RED);
        }

    }

    @Override
    public void move() { }

    @Override
    public boolean interact() {
        return false;
    }

    public PurchaseItem ifClicked(Point point){
        if(ShadowDefend.money>=price){
            if(this.rect.intersects(point)){
                return this;
            }
        }
        return null;
    }

    public CopyImage copyCreate(Point point){
        CopyImage copyImage = new CopyImage(fileName, point);
        return copyImage;
    }


}
