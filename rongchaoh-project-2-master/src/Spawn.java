import java.util.ArrayList;

public class Spawn extends Event {

    private String event;
    private String slicerType;
    private int numSpawn;
    private int delaySeconds;

    private ArrayList<Slicer> slicers = new ArrayList<>();
    /**
     * Constructor for Wave when event is delay
     *
     * @param event        The index of Wave for each Wave;
     * @param numSpawn   The number of slicers for this wave;
     * @param slicerType   The type of slicer for this wave;
     * @param delaySeconds The initial money for this wave;
     */
    public Spawn(String event, int numSpawn, String slicerType, int delaySeconds) {
        super(event, numSpawn, slicerType, delaySeconds);
        this.event = event;
        this.numSpawn = numSpawn;
        this.slicerType = slicerType;
        this.delaySeconds = delaySeconds;
        slicerInitial();
    }

    public void slicerInitial(){
        while(slicers.size()<numSpawn){
            if(slicerType.equals("slicer")){
                Slicer slicer = (new GeneralSlicer(Wave.points.get(0),Wave.points.get(1)));
                slicers.add(slicer);
            }else{
                break;
            }
            //这里没有补充其他slicer, 一会儿在写

        }
    }

    @Override
    public void draw() {
        for (Slicer slicer:slicers) {
            if(ShadowDefend.time>=slicers.indexOf(slicer)*delaySeconds){
                slicer.move();
                slicer.draw();
                if(slicer.arriveToNext()&&(slicer.getNext().size()<Wave.points.size())){
                    slicer.getNext().add(Wave.points.get(slicer.getNext().size()));
                }else if(slicer.getNext().size()>=Wave.points.size()){
                    slicers.remove(slicer);
                    break;
                }else if(slicer.getHealth()<=0){
                    slicers.remove(slicer);
                    break;
                }


            }

        }
    }


    @Override
    public int getDelaySeconds() {
        return delaySeconds;
    }

    @Override
    public int getNumSpawn() {
        return numSpawn;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public String getSlicerType() {
        return slicerType;
    }

    public ArrayList<Slicer> getSlicers() {
        return slicers;
    }
}
