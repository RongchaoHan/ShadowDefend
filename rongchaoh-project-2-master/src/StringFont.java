import bagel.DrawOptions;
import bagel.Font;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class StringFont extends Sprite {

    private final String fontFileName = "res/fonts/DejaVuSans-Bold.ttf";
    private DrawOptions drawOptions = new DrawOptions();
    private Font font;
    private String target;
    private int size;

    /**
     * Constructor for StringFont
     *
     * @param target The string will be put in the font.
     * */
    public StringFont(String target, int size, Colour colour){
        this.target = target;
        this.font = new Font(fontFileName, size);
        this.drawOptions = this.drawOptions.setBlendColour(colour);
    }

    public void draw(double x, double y,Colour colour) {
        this.drawOptions = drawOptions.setBlendColour(colour);
        font.drawString(target,x,y+25/2,drawOptions);
    }

    @Override
    public void draw() { }

    @Override
    public void move() { }

    @Override
    public boolean interact() {
        return false;
    }

}