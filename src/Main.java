
public class Main {

	public static void main(String[] args) {
		Bridge theBridge = new Bridge();
		RedCar aCar1 = new RedCar(1, theBridge);
		BlueCar aCar2 = new BlueCar(2, theBridge);
		RedCar aCar3 = new RedCar(3, theBridge);
		BlueCar aCar4 = new BlueCar(4, theBridge);
		BlueCar aCar6 = new BlueCar(6, theBridge);
		RedCar aCar5 = new RedCar(5, theBridge);
		
		aCar1.start();
		aCar2.start();
		aCar3.start(); 
		//aCar4.start(); aCar5.start(); aCar6.start();

	}

}
