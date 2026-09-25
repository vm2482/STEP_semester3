abstract class DeliveryNote
{
    public DeliveryNote()
    {
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature)
    {
        return confirmDelivery() + ", signed by\n" + signature;
    }
}


class ParcelNote extends DeliveryNote
{
    private String trackingId;

    public ParcelNote(String trackingId)
    {
        this.trackingId = trackingId;
    }

    public String confirmDelivery()
    {
        return "Parcel " + trackingId + " delivered";
    }
}


class LetterNote extends DeliveryNote
{
    private String trackingId;

    public LetterNote(String trackingId)
    {
        this.trackingId = trackingId;
    }

    public String confirmDelivery()
    {
        return "Letter " + trackingId + " delivered";
    }
}


public class delivery
{
    static void logAll(DeliveryNote[] notes)
    {
        for (int i = 0; i < notes.length; i++)
        {
            System.out.println(notes[i].confirmDelivery());
        }
    }

    public static void main(String[] args)
    {
        ParcelNote p = new ParcelNote("TRK-1");

        System.out.println(p.confirmDelivery());

        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;

        logAll(new DeliveryNote[]
                {
                        ref,
                        new LetterNote("TRK-2")
                });
    }
}