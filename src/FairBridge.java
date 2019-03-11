
public class FairBridge extends Bridge{
	
	/*
	 * this is the case of FAIR BRIDGE: 
	 * It is the case where the blue and red car's turn is strictly exchanged 
	 * between them until one the two types is done (for example red). 
	 * When this happens the other type keeps coming until it is done too (the blue cars).
	 */
	
	private boolean redCarsTurn = false;
	private boolean blueCarsTurn = false;
	private boolean carCrossing = false;
	//i need the number of cars to check if it is 0 so the same color of car is passing although it is a fair bridge
	private int redCars = 0;
	private int blueCars = 0;

	//car arrives and gets counted
	@Override
	public void redCarArrives(int id) {
		System.out.println("red: " + id + " car is here!");
		redCars++;
	}

	@Override
	public void blueCarArrives(int id) {
		System.out.println("blue: " + id + " car is here!");
		blueCars++;
	}

	@Override
	public boolean redCarEnters(int id) {
		//if a car is crossing or it is not red's turn it has to wait  
		while(carCrossing || blueCarsTurn) {
			try {
				//it has to be synchronized 
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//it is notified that it can pass so car crossing bool is true
		carCrossing = true;
		System.out.println("red: " + id + " car is entering");
		
		//this specific thread passed in the bridge so it can exits safely
		return carCrossing;
	}

	@Override
	public boolean blueCarEnters(int id) {
		//if a car is crossing or it is not blue's turn it has to wait  
		while(carCrossing || redCarsTurn) {
			try {
				//it has to be synchronized 
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//it is notified that it can pass so car crossing boolean is true
		carCrossing = true;
		System.out.println("blue: " + id + " car is entering");
		
		//this specific thread passed in the bridge so it can exits safely
		return carCrossing;
	}

	@Override
	public void redCarExits(int id) {
		System.out.println("red: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false;
		redCars--;
		//this is for the scenario where the blue cars are finished but there are some red cars
		if(blueCars != 0) {
			blueCarsTurn = true;
			redCarsTurn = false;
		}
		/*
		 * if the the if statement is wrong then the blue cars are finished and the
		 * variables blueCarsTurn is false and redCarsTurn is true so the red cars
		 * continue to pass the bridge notify the waiting threads that the bridge is
		 * empty
		 */
		synchronized (this) {
			notifyAll();
		}
	}

	@Override
	public void blueCarExits(int id) {
		System.out.println("blue: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false; 
		blueCars--;
		//this is for the scenario where the red cars are finished but there are some blue cars
		if(redCars != 0) {
			redCarsTurn = true;
			blueCarsTurn = false;
		}
		/*
		 * if the the if statement is wrong then the red cars are finished and the
		 * variables redCarsTurn is false and blueCarsTurn is true so the blue cars
		 * continue to pass the bridge notify the waiting threads that the bridge is
		 * empty
		 */
		synchronized (this) {
			notifyAll();
		}
	}
	
	

}
