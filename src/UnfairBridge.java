
public class UnfairBridge extends Bridge{
	
	/*
	 * this is the case of UNFAIR BRIDGE:
	 * The cars are crossing the bridge no matter their color but they have to do it one by one. 
	 * Only one car passes the bridge every time. 
	 * There are no crashes but it is not a fair bridge.
	 */
	
	//the only variable it is needed is a boolean to know if a car is passing the bridge
	public boolean carCrossing = false;

	@Override
	public void redCarArrives(int id) {
		System.out.println("red: " + id + " car is here!");
	}

	@Override
	public void blueCarArrives(int id) {
		System.out.println("blue: " + id + " car is here!");
	}

	@Override
	public boolean redCarEnters(int id) {
		//if a car is crossing the other thread has to wait 
		while(carCrossing) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//the bridge is empty
		carCrossing = true;
		System.out.println("red: " + id + " car is entering");
		
		//if it passes it can exits
		return carCrossing;
		
	}

	@Override
	public boolean blueCarEnters(int id) {
		//if a car is crossing the other thread has to wait 
		while(carCrossing) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		///the bridge is empty
		carCrossing = true;
		System.out.println("blue: " + id + " car is entering");
		
		//if it passes it can exits
		return carCrossing;
	}

	@Override
	public void redCarExits(int id) {
		System.out.println("red: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false;
		//notify the waiting threads
		synchronized (this) {
			notifyAll();
		}
		
	}

	@Override
	public void blueCarExits(int id) {
		System.out.println("blue: " + id + " car exits!");
		//the bridge is empty
		carCrossing = false;
		//notify the waiting threads
		synchronized (this) {
		    notifyAll();
		}
	}
	
	

}
