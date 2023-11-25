import bagel.Window;
import bagel.map.TiledMap;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Wave extends Sprite{

    public static final String waveFile = "res/levels/waves.txt";
    public final String firstMapFile = "res/levels/1.tmx";
    public final String secondMapFile = "res/levels/2.tmx";
    public static ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Point> polyLine = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();

    private int waveNumber;
    private TiledMap map;
    private Panel buyPanel;
    private Panel statusPanel;


    private ArrayList<Event> events = new ArrayList<>();
    /**
     * Constructor for Wave
     *
     * @param waveNumber The index of wave for each game;
     */
    public Wave(int waveNumber) {
        super(waveNumber);

        this.waveNumber = waveNumber;
        this.buyPanel = new BuyPanel();
        this.statusPanel = new StatusPanel();
        this.map = new TiledMap(firstMapFile);
        for (Point point: map.getAllPolylines().get(0)) {
            if(point != null){
                this.polyLine.add(point);
            }
        }
        points = polyLine;

    }



    private void addEvents(String[] splitText){
        if(splitText[1].compareTo("spawn")==0) {
            Spawn event = new Spawn(splitText[1], Integer.parseInt(splitText[2])
                    , splitText[3], Integer.parseInt(splitText[4]));
            events.add(event);
        }else if(splitText[1].compareTo("delay")==0){
            Delay event = new Delay(splitText[1], Integer.parseInt(splitText[2]));
            events.add(event);
        }else{
            System.out.println("Invalid event");
        }

    }
    public static ArrayList<Wave> readWaves(){
        ArrayList<Wave> waves = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(Wave.waveFile))) {
            String text;
            while ((text = br.readLine()) != null) {
                String[] arguments = text.split(",");
                if(waves.isEmpty()){
                    Wave wave = new Wave(Integer.parseInt(arguments[0]));
                    wave.addEvents(arguments);
                    waves.add(wave);
                }else{
                    boolean find = false;
                    for (Wave curWave : waves) {
                        if(curWave.getWaveNumber() == Integer.parseInt(arguments[0])){

                            curWave.addEvents(arguments);
                            find = true;
                        }
                    }
                    if(!find){
                        Wave newWave = new Wave(Integer.parseInt(arguments[0]));
                        newWave.addEvents(arguments);
                        waves.add(newWave);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return waves;
    }

    @Override
    public void draw(){
        map.draw(0,0,0,0,Window.getWidth(),Window.getHeight());
        buyPanel.draw();
        statusPanel.draw();
        for (Tower tower: towers) {
            tower.move();
            tower.draw();
            if(events.get(ShadowDefend.eventIndex) instanceof Spawn){
                for (Slicer slicer: ((Spawn) events.get(ShadowDefend.eventIndex)).getSlicers()) {
                    if(slicer.getLocation().distanceTo(tower.getLocation())<=tower.getEffectRadius()){
                        tower.attack(slicer);
                    }
                }
            }
        }
    }

    public int getWaveNumber(){
        return waveNumber;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }


    public void move() { }

    @Override
    public boolean interact() {
        return false;
    }


    public PurchaseItem ifClicked(Point point){
        return buyPanel.ifClicked(point);
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }
}
