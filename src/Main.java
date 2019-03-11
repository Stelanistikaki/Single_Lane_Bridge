import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//the scanner variable to scan the numbers i need
		Scanner sc = new Scanner(System.in);
		//a Bridge object - it will be assigned later
		Bridge theBridge = null;
		
		System.out.println("------------ BRIDGE ------------");
		
		System.out.println("How many blue cars are coming? ");
		//scanning the number of blue cars
		int blueCarsCount = sc.nextInt();
		
		System.out.println("How many red cars are coming? ");
		//scanning the numbers of red cars 
		int redCarsCount = sc.nextInt();
		
		while(true) {
			//the menu for choice
			System.out.println("OK! Type: ");
			System.out.println("1 for Dangerous Bridge ");
			System.out.println("2 for Unfair Bridge ");
			System.out.println("3 for Fair Bridge ");
			System.out.println("4 for Smart Bridge ");
			System.out.println("0 for Exit ");
		
			//the case the user chooses
			int bridgeType = sc.nextInt();
		
			//cases for what bridge to create and 0 for exiting and finishing the program
			switch(bridgeType) {
				case 1: {
					theBridge = new DangerBridge();
					break;     
				}
				case 2: {
					theBridge = new UnfairBridge();
					break;
				}
				case 3: { 
					theBridge = new FairBridge(); 
					break; 
				} 
				case 4:{ 
					theBridge = new SmartBridge(); 
					break; 
				}
				case 0: {
					break;
				}
			}
			
			//if the user chooses 0 the while loop will end
			//if the if is not placed here i will have a null pointer exception from the null Bridge object
			if(bridgeType == 0) break;
			
			/*
			 * this also could be done with 2 for loops but it didn't work with very big
			 * numbers, because the first for loop's objects - let's say red cars - always
			 * get to the bridge first and there was not conflict between the blue and red
			 * cars
			 */
			int id=1, redLeft=redCarsCount, blueLeft = blueCarsCount;
			for(int i=1; i<= redCarsCount + blueCarsCount; i++) {
				if(redLeft != 0) {
					new Car(id, theBridge, "red").start();
					//the id is unique for every car
					id++;
					redLeft--;
				}
				if(blueLeft != 0) {
					new Car(id, theBridge, "blue").start();
					id++;
					blueLeft--;
				}
			}
			
//			this is done because it has to wait for the cars to finish their crossing
//			and then print the menu again for the user to choose
//			It can be also done in 2 ways:
//				1. know the number of cars and join the threads here
//				2. finish the program after one pick by the user. And then he has to start it again
//				for another pick of the case
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		//i have to close the scanner after the scanning is done
		sc.close();

	}
}
