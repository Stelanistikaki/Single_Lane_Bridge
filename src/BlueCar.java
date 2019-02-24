
public class BlueCar extends Car{
	
	
	public BlueCar(int id, Bridge aBridge) {
		super(id, aBridge);	
	}
	
	@Override
	public void run() {
		aBridge.blueCarArrives(id);
		while(true) {
			if(aBridge.blueCarEnters(id)){
				aBridge.blueCarExits(id);
				break;
			}
		}
		//super.run();	
	}
	
	
	

}
