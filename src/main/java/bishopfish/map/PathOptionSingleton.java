package bishopfish.map;

public class PathOptionSingleton {
    private static PathOptionSingleton optionPicker;

    //pathfinding context?

    //10 pixels per 3 feet
    //3.3 pixels per 1 foot
    //1 pixel = 0.3 feet
    public final double metersPerPixel = 0.099;//meters per pixel
    public final double feetPerPixel = 0.3;//feet per pixel
    public final double walkSpeedMeters = 1.4;///walk speed in m/s
    public final double walkSpeedFeet = 4.6;//walk speed in ft/sec

    private PathOptionSingleton(){

    }

    private static class SingletonHelper{
        private static final PathOptionSingleton optionPicker = new PathOptionSingleton();
    }

    public static PathOptionSingleton getOptionPicker(){
        return SingletonHelper.optionPicker;
    }
}
