import bagel.AbstractGame;
import bagel.Input;
import bagel.Keys;
import bagel.MouseButtons;
import bagel.util.Point;

import java.util.ArrayList;

public class ShadowDefend extends AbstractGame{

    private final int INITIAL_TIME = 0;
    private final int MAX_TIMESCALE = 100;
    private final int MIN_TIMESCALE = 1;

    public static int waveIndex = 0;
    public static int eventIndex = 0;
    public static double timeScale = 1;
    public static String status;
    public static Point mousePosition = null;
    private CopyImage copyImage = null;
    public static int time = 0;
    public static int money = 500;
    public static int lives = 25;

    private ArrayList<Wave> waves = new ArrayList<>();



    /**
     * Entry point for Bagel game
     *
     * Explore the capabilities of Bagel: https://people.eng.unimelb.edu.au/mcmurtrye/bagel-doc/
     */
    public static void main(String[] args) {
        // Create new instance of game and run it
        new ShadowDefend().run();
    }

    /**
     * Updates the game state approximately 60 times a second, potentially reading from input.
     * @param input The input instance which provides access to keyboard/mouse state information.
     */
    @Override
    protected void update(Input input) {
        Wave wave = waves.get(waveIndex);
        wave.draw();

        if(time>INITIAL_TIME){
            Event event = wave.getEvents().get(eventIndex);
            if(time <= event.getDelaySeconds()*(event.getNumSpawn()-1))
            {
                event.draw();
                time+=timeScale;
            }else{
                time = INITIAL_TIME+1;
                eventIndex+=1;
                if(eventIndex>=wave.getEvents().size()){
                    System.out.println("下一关");
                    ArrayList<Tower> towers = wave.getTowers();
                    ArrayList<Slicer> slicers = new ArrayList<>();
                    for (Event oldEvent: wave.getEvents()) {
                        if(oldEvent instanceof Spawn){
                            slicers.addAll(((Spawn) oldEvent).getSlicers());
                        }
                    }
                    eventIndex = 0;
                    waveIndex++;
                    Wave nextWave = waves.get(waveIndex);
                    nextWave.getTowers().addAll(towers);
                    towers = null;
                    time = INITIAL_TIME;
                }
            }

        }

        if(input.wasPressed(Keys.S)&&time==INITIAL_TIME){
            time = INITIAL_TIME+1;
        }else if(input.wasPressed(Keys.L)){
            if(timeScale<MAX_TIMESCALE){
                timeScale++;
            }
        }else if(input.wasPressed(Keys.K)){
            if(timeScale>MIN_TIMESCALE){
                timeScale--;
            }
        }else if(input.wasPressed(MouseButtons.LEFT)){
            if(mousePosition==null){
                PurchaseItem purchaseItem = null;
                if((purchaseItem = wave.ifClicked(input.getMousePosition()))!=null){
                    mousePosition = input.getMousePosition();
                    copyImage = purchaseItem.copyCreate(mousePosition);
                }
            }else{

                if(copyImage.getFileName().compareTo(GeneralTank.fileName)==0){
                    wave.getTowers().add(new GeneralTank(input.getMousePosition()));
                }

                //还有很多tower一会儿写
                copyImage = null;
                mousePosition = null;
            }
        }

        if(mousePosition != null){
            mousePosition = input.getMousePosition();
            copyImage.draw();
        }


    }



    /**
     * Setup the Game: Shadow Defend
     */



    public ShadowDefend(){
        setUpWaves();
    }

    private void setUpWaves(){

        // 目前只有第一关
        waves = Wave.readWaves();
    }

}
