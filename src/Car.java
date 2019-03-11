
public class Car extends Thread{
	
	//the variables for every car object  
	private int id;
	private Bridge theBridge;
	private String aType;
	

	//the constructor
	public Car(int aId, Bridge theBridge, String type) {
		this.id = aId; 
		this.theBridge = theBridge;
		this.aType = type;
	}
	
	@Override
	//the method run is necessary for Threads 
	public void run() {
		//choosing between red and blue cars
		if(aType.equals("red")) {
			//the object arrives no matter what
			theBridge.redCarArrives(id);
			//but it has to cross the bridge to exit it
			if(theBridge.redCarEnters(id)) 
				theBridge.redCarExits(id);
		}else {
			//i send the id in every method so i can print it to know which car is doing what
			theBridge.blueCarArrives(id);
			if(theBridge.blueCarEnters(id))
				theBridge.blueCarExits(id);
		}
	}
	
}
