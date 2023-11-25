import bagel.Window;
import bagel.map.TiledMap;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.ArrayList;

/**
 * Subclass of Sprite
 *
 * The Level class describe a type of tiled map
 *  and updates the changes on each map
 *
 *  // method with its functional properties;
 */
public class Level extends Sprite {

    public static final String firstLevelMap = "res/levels/1.tmx";
    public static final String secondLevelMap = "res/levels/2.tmx";

    private TiledMap map;
    private final double BOUND_LIMITATION = 50;
    public static ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Wave> waves = new ArrayList<>();
    private BuyPanel buyPanel = new BuyPanel();
    private StatusPanel statusPanel = new StatusPanel();

    /**
     * Subclass constructor for level
     *
     * Level defines a kind of object
     *      which describe the map
     *      and update the changes on each map
     *
     * @param fileName String of static map file name defined in Level
     */
    public Level(String fileName){
        this.map = new TiledMap(fileName);
        this.points.addAll(map.getAllPolylines().get(0));
    }

    @Override
    public void draw() {
        map.draw(0,0,0,0, Window.getWidth(),Window.getHeight());
        buyPanel.draw();
        statusPanel.draw();
        if(ShadowDefend.time>0){
            waves.get(ShadowDefend.waveIndex).draw();
        }
    }

    public PurchaseItem ifClicked(Point point){
        for (PurchaseItem purchaseItem: buyPanel.getPurchaseItems()) {
            if(purchaseItem.interact(point)){
                return purchaseItem;
            }
        }
        return null;
    }

    public boolean ifValidLocation(Point point){
        double minDistance = Double.MAX_VALUE;
        for (Point polyLine: points) {
            if(points.indexOf(polyLine)!=0){
                int index = points.indexOf(polyLine);
                double distance = getDistance(point, points.get(index-1), polyLine);
                if(distance<minDistance){
                    minDistance = distance;
                }
            }
        }
        for (Tank tank: ShadowDefend.tanks) {
            if(tank.rect.centre().distanceTo(point)<=BOUND_LIMITATION){
                return false;
            }
        }
        if(point.x>0 && point.x<Window.getWidth()){
            if(point.y>buyPanel.image.getHeight()+BOUND_LIMITATION/2){
                if(minDistance>BOUND_LIMITATION){
                    return true;
                }

                //这里还有status的判定条件
            }
        }
        return false;

    }


    private double getDistance(Point P, Point A, Point B) {
        Vector2 AP, AB, BP, AC;
        double distance;
        AP = new Vector2(P.x - A.x, P.y- A.y);
        BP = new Vector2(P.x - B.x, P.y- B.y);
        AB = new Vector2(B.x - A.x,
                B.y-A.y);
        if(AP.dot(AB)<=0){
            distance = AP.length();
        }else if(BP.dot(new Vector2(-AB.x,-AB.y))<=0){
            distance = BP.length();
        }else{
            AC = AB.normalised().mul((AP.dot(AB))/AB.length());
            distance = Math.sqrt(AP.lengthSquared()-AC.lengthSquared());
        }
        return distance;
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }
}
