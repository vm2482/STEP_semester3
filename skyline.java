abstract class Drone
{
    public Drone()
    {
    }

    public abstract String fly();
}


interface Trackable
{
    String getLocation();
}


class DeliveryDrone extends Drone implements Trackable
{
    private String id;

    public DeliveryDrone(String id)
    {
        this.id = id;
    }

    public String fly()
    {
        return "Delivery drone " + id + " flying";
    }

    public String getLocation()
    {
        return id + " at Sector 4";
    }
}


class ScoutDrone extends Drone
{
    private String id;

    public ScoutDrone(String id)
    {
        this.id = id;
    }

    public String fly()
    {
        return "Scout drone " + id + " flying";
    }
}


class GroundRobot implements Trackable
{
    private String id;

    public GroundRobot(String id)
    {
        this.id = id;
    }

    public String getLocation()
    {
        return id + " at Sector 4";
    }
}


public class skyline
{
    static String getLocationIfTrackable(Object o)
    {
        if (o instanceof Trackable)
        {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        else
        {
            return "Tracking not available";
        }
    }

    public static void main(String[] args)
    {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");

        System.out.println(getLocationIfTrackable(d));
        System.out.println(getLocationIfTrackable(s));
        System.out.println(getLocationIfTrackable(g));
    }
}
