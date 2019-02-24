
public class Car extends Thread {
	
	int id;
	Bridge aBridge;

	public Car(int id, Bridge aBridge) {
		this.id = id;
		this.aBridge = aBridge;
	}
	
	public void run() {
		
		 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	

}
