abstract class GardenTool
{
    public GardenTool()
    {
    }

    public String use()
    {
        return "Using the tool in the garden";
    }
}


class CuttingTool extends GardenTool
{
    public CuttingTool()
    {
        super();
    }

    public String use()
    {
        return super.use() + ",\nblade sharpened first";
    }
}


class Pruner extends CuttingTool
{
    public Pruner()
    {
        super();
    }

    public String use()
    {
        return super.use() + ", then\ntrimming branches precisely";
    }
}


public class backyard
{
    public static void main(String[] args)
    {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}
