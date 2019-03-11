
public class SmartBridge extends Bridge{
	
	/*
	 * this is the case of SMART BRIDGE: 
	 * It is really similar to fair bridge but it also checks if there are many cars from one side. 
	 * By saying many cars I mean that the number of, let's say blue cars, is twice or higher than the number
	 * of red cars. But this is working with the cars that has already arrive, have
	 * been counted and it also works as the cars are being removed.
	 * When the difference of the two numbers is small it works like the fair bridge
	 */
	
	//i need 3 boolean and 2 integer variables for smart bridge
	private boolean redCarsTurn = false;
	private boolean blueCarsTurn = false;
	private boolean carCrossing = false;
	private int redCars = 0;
	private int blueCars = 0;

	//the method that counts the red cars that come
	@Override
	public void redCarArrives(int id) {
		System.out.println("red: " + id + " car is here!");
		redCars++;
	}

	//the method that counts the blue cars that come
	@Override
	public void blueCarArrives(int id) {
		System.out.println("blue: " + id + " car is here!");
		blueCars++;
	}

	@Override
	public boolean redCarEnters(int id) {
		//when there is a car passing the bridge or is the blue car's turn it waits
		while(carCrossing || blueCarsTurn) {
			try {
				//it has to be synchronized to work properly
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//the bridge is empty or it is red car's turn and it can cross the bridge
		carCrossing = true;
		System.out.println("red: " + id + " car is entering");
		
		//return true when the car passes the bridge
		return carCrossing;
	}

	@Override
	public boolean blueCarEnters(int id) {
		//when there is a car passing the bridge or is the red car's turn it waits
		while(carCrossing || redCarsTurn) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//the bridge is empty or it is blue car's turn and it can cross the bridge
		carCrossing = true;
		System.out.println("blue: " + id + " car is entering");
		
		return carCrossing;
	}

	@Override
	public void redCarExits(int id) {
		System.out.println("red: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false;
		//one red car is removed
		redCars--;
		//the expression to check if the red cars are "more"
		//also it is the red car's turn if there are no blue cars left
		if(redCars - blueCars >= blueCars || blueCars == 0) {
			redCarsTurn = true;
			blueCarsTurn = false;
		}
		//else it is the blue car's turn
		else {
			blueCarsTurn = true;
			redCarsTurn = false;
		}
		//notify the waiting cars
		synchronized (this) {
			notifyAll();
		}
	}

	@Override
	public void blueCarExits(int id) {
		System.out.println("blue: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false; 
		//one blue car is removed
        blueCars--;
        //the expression to check if the blue cars are "more"
      	//also it is the blue car's turn if there are no red cars left
        if(blueCars - redCars >= redCars || redCars == 0) {
			redCarsTurn = false;
			blueCarsTurn = true;
		}else {
			//else it is the red car's turn
			redCarsTurn = true;
			blueCarsTurn = false;
		}
        //notify the waiting cars
		synchronized (this) {
			notifyAll();
		}
	}
	
	

}
