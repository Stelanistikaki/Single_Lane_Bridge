
public class RedCar extends Car{
	

	public RedCar(int id, Bridge aBridge) {
		super(id, aBridge);	
	}

	@Override
	public void run() {
		aBridge.redCarArrives(id);
		while(true) {
			if(aBridge.redCarEnters(id)){
				aBridge.redCarExits(id);
				break;
			}
		}
		//super.run();
		 
	}
	
	
	
	
}
