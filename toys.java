abstract class Toy{
    private final String toyId;
    private static int counter = 1000;
    public Toy() {
        counter++;
        toyId = "TOY-" + counter;
    }

    public abstract String makeSound();
    public String getToyId() {
        return toyId;
    }
}

class ToyCar extends Toy {
    private String name;
    public ToyCar(String name) {
        this.name = name;
    }

    public String makeSound(){
        return name + ": Vroom vroom!";
    }
}


class ToyRobot extends Toy {
    private String name;
    public ToyRobot(String name) {
        this.name = name;
    }

    public String makeSound() {
        return name + ": Beep boop!";
    }
}

public class toys {
    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        ToyRobot r = new ToyRobot("Bolt");

        System.out.println(c.makeSound());
        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}