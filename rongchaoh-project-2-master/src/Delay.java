public class Delay extends Event {

    private String event;
    private int delaySeconds;
    /**
     * Constructor for Wave when event is delay
     *
     * @param event        The index of Wave for each Wave;
     * @param delaySeconds The delay in milliseconds for this delay;
     */
    public Delay(String event, int delaySeconds) {
        super(event, delaySeconds);
        this.event = event;
        this.delaySeconds = delaySeconds*60/1000;
    }

    @Override
    public void draw() {

    }

    @Override
    public int getDelaySeconds() {
        return delaySeconds;
    }

    @Override
    public int getNumSpawn() {
        return 2;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public String getSlicerType() {
        return null;
    }

    @Override
    public void slicerInitial() {

    }
}
