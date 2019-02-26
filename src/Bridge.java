
public class Bridge {
	
	int redCars = 0;
	int blueCars = 0;
	boolean carCrossing = false;
	
	public void redCarArrives(int id) {
		System.out.println(id + " car is here!");
		redCars++;
	}
	
	public void blueCarArrives(int id) {
		System.out.println(id + " car is here!");
		blueCars++;
		
	}
	
	synchronized boolean redCarEnters(int id) {
		if(blueCars > 0 && carCrossing)
			try {
				System.out.println(id + " car is waiting");
				//redCarWaiting = true;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else {
			System.out.println(id + " car is entering!");
			//redCarWaiting = false;
			carCrossing = true;
		}
		
		return carCrossing;
	}
	
	synchronized boolean blueCarEnters(int id) {
		if(redCars > 0 && carCrossing)
			try {
				System.out.println(id + " car is waiting");
				//blueCarWaiting = true;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			System.out.println(id + " car is entering!");
			//blueCarWaiting = false;
			carCrossing = true;
		}
		
		return carCrossing;
	}
	
	public void redCarExits(int id) {
		System.out.println( id + " car exits!");
		carCrossing = false;
		redCars --;
	}
	
	public void blueCarExits(int id) {
		System.out.println( id + " car exits!");
		carCrossing = false;
		blueCars --;
	}

}
