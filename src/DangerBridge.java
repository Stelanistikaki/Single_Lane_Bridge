
public class DangerBridge extends Bridge{
	
	/*
	 * this is the case of DANGER BRIDGE: 
	 * In this case cars pass with collisions and
	 * without safety. When a car is here it crosses the bridge.
	 */
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
		System.out.println("red: " + id + " car is entering"); 
		//it passes the bridge every time
		return true;
	}

	@Override
	public boolean blueCarEnters(int id) {
		System.out.println("blue: " + id + " car is entering"); 
		return true;
	}

	@Override
	public void redCarExits(int id) {
		System.out.println("red: " + id + " car exits!");
	}

	@Override
	public void blueCarExits(int id) {
		System.out.println("blue: " + id + " car exits!");
	}


}
