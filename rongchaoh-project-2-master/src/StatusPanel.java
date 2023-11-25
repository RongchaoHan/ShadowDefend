import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class StatusPanel extends Panel {

    private final String statusPanelFileName = "res/images/statuspanel.png";
    private final int waveNumberSize = 20;
    private Image image;
    private DrawOptions drawOptions = new DrawOptions();
    private StringFont waveNumber = new StringFont("Wave: "+Integer.toString(ShadowDefend.waveIndex)
            ,waveNumberSize, Colour.WHITE);;
    private StringFont timeScale = new StringFont("Time Scale: "+Double.toString(ShadowDefend.timeScale)
            , waveNumberSize,Colour.GREEN);;
    private StringFont status = new StringFont("Status: "+ShadowDefend.status, waveNumberSize, Colour.WHITE);
    private StringFont lives = new StringFont("Lives: "+ShadowDefend.lives, waveNumberSize, Colour.WHITE);
    /**
     * Constructor for Panel
     *
     * */
    public StatusPanel(){
        this.image = new Image(statusPanelFileName);
    }

    @Override
    public void draw() {
        image.draw(0,Window.getHeight(),drawOptions.setScale(2,2));
        int i = 0;

        waveNumber = new StringFont("Wave: "+Integer.toString(ShadowDefend.waveIndex)
                ,waveNumberSize, Colour.WHITE);;
        timeScale = new StringFont("Time Scale: "+Double.toString(ShadowDefend.timeScale)
                , waveNumberSize,Colour.GREEN);;
        StringFont status = new StringFont("Status: "+ShadowDefend.status, waveNumberSize, Colour.WHITE);
        StringFont lives = new StringFont("Lives: "+ShadowDefend.lives, waveNumberSize, Colour.WHITE);

        waveNumber.draw(i = 10, Window.getHeight()-18, Colour.WHITE);
        timeScale.draw(i+=250, Window.getHeight()-18,Colour.GREEN);
        status.draw(i+=250, Window.getHeight()-18,Colour.WHITE);
        lives.draw(Window.getWidth()-150, Window.getHeight()-18,Colour.WHITE);
    }

    @Override
    public void move() { }

    @Override
    public boolean interact() {
        return false;
    }

    @Override
    public PurchaseItem ifClicked(Point point) {
        return null;
    }

}