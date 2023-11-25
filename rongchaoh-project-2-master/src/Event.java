

public abstract class Event {


    /**
     * Constructor for Wave when event is delay
     *
     * @param event The index of Wave for each Wave;
     * @param numSpawn The number of slicers for this wave;
     * @param slicerType The type of slicer for this wave;
     * @param delaySeconds The delay in milliseconds for this spawn;
     */
    public Event(String event, int numSpawn, String slicerType, int delaySeconds) { }

    /**
     * Constructor for Wave when event is delay
     *
     * @param event The index of Wave for each Wave;
     * @param delaySeconds The delay in milliseconds for this delay;
     */
    public Event(String event, int delaySeconds){ }

    public abstract void draw();

    public abstract int getDelaySeconds();

    public abstract int getNumSpawn();

    public abstract String getEvent();

    public abstract String getSlicerType();

    public abstract void slicerInitial();

}
