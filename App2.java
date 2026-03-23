public class App2 {
    public static void main(String[] args) throws Exception {
               Electricar myCar = new Electricar("Honda", "V1", "black", 100);
        
        System.out.println(myCar.getSpeed());
        myCar.startEngine(); 
        myCar.payToll(10000000); 
    }
}

abstract class Car {
    protected String brand;
    protected String type;
    protected String color;
    protected int speed;
    protected CashPayment cashPayment = new CashPayment();

    public Car(String brand, String type, String color, int speed) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.speed = speed;
    }

    abstract void startEngine();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speedNew) {
        this.speed = speedNew;
    }
}

class Electricar extends Car implements TollPayment {
    public Electricar(String brand, String type, String color, int speed) {
        super(brand, type, color, speed);
    }

    @Override
    void startEngine() {
        System.out.println("mantap");
    }

    @Override
    public void payToll(int number) {
        cashPayment.payToll(number);
    }
}

interface TollPayment {
    void payToll(int number);
}

class CashPayment implements TollPayment {
    @Override
    public void payToll(int number) { 
        System.out.println("bayar: " + number); 
    }
}