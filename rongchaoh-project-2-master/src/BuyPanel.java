import bagel.DrawOptions;
import bagel.Image;
import bagel.Window;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;


public class BuyPanel extends Panel {

    private final String buyPanelFileName = "res/images/buypanel.png";
    private final String keyBindString = "Key binds:\n\nS - Start Wave\nL - Increase Timescale\nK - Decrease Timescale";
    private final int moneySize = 48;
    private final int keyBindsSize = 16;
    private final int NUM_ITEMS = 3;

    private Image image = new Image(buyPanelFileName);
    private DrawOptions drawOptions = new DrawOptions();

    private PurchaseItem[] purchaseItems;
    private StringFont money;
    private StringFont keyBinds;

    /**
     * Constructor for Panel
     *
     * */
    public BuyPanel(){
        purchaseItems = new PurchaseItem[NUM_ITEMS];
        int i = 0;
        purchaseItems[0] = new PurchaseItem("res/images/tank.png",GeneralTank.price, new Point(64+(i++)*120,image.getBoundingBox().centre().y-10));
        purchaseItems[1] = new PurchaseItem("res/images/supertank.png",600,new Point(64+(i++)*120,image.getBoundingBox().centre().y-10));
        purchaseItems[2] = new PurchaseItem("res/images/airsupport.png",500,new Point(64+(i++)*120,image.getBoundingBox().centre().y-10));
        this.money = new StringFont("$"+Integer.toString(ShadowDefend.money)
                ,moneySize, Colour.WHITE);
        this.keyBinds = new StringFont(keyBindString,keyBindsSize,Colour.WHITE);
    }

    @Override
    public void draw() {
        image.draw(0,0,drawOptions.setScale(2,2));
        this.money = new StringFont("$"+Integer.toString(ShadowDefend.money)
                ,moneySize, Colour.WHITE);
        int i = 0;
        for (PurchaseItem purchaseItem: purchaseItems) {
            purchaseItem.draw();
            i++;
        }
        money.draw(Window.getWidth()-200, 50,Colour.WHITE);
        keyBinds.draw(64+i*120+50,10, Colour.WHITE);
    }

    @Override
    public void move() { }

    @Override
    public boolean interact() {
        return false;
    }

    public PurchaseItem ifClicked(Point point){
        for(int i = 0; i<NUM_ITEMS;i++){
            if(purchaseItems[i].ifClicked(point)!=null){
                return purchaseItems[i];
            }
        }
        return null;
    }

}
