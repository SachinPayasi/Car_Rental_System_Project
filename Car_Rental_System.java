

import java.util.ArrayList;
import java.util.Scanner;

//car class

class Car{
	
	  private String carId;
	  private String brand;
	  private String carModel;
	  private double basePricePerDay;
	  private boolean isAvailable;
	
	
	//Constructor to set the values for Important Data Member Of Class;
	  
	public Car(String carId, String brand, String carModel, double basePricePerDay) {
		
		this.carId = carId;
		this.brand = brand;
		this.carModel = carModel;
		this.basePricePerDay = basePricePerDay;
		this.isAvailable = true;
		
	}
	
	
    //getters method to get the values of the important data members of class   
	
	
	public String getCarId() {
		return carId;
	}
	
	
	
	public String getBrand() {
		return brand;
	}
	
	
	public String getCarModel() {
		return carModel;
	}
	
	
	//method to calculate the total rent of customer
	
	public double calculateRent(int rentalDays) {
		return basePricePerDay * rentalDays;             //price per day ,multiplied with number of rental days  
	}
	
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	
	//method to show the car is booked or not available. i.e its is booked
	
	public void rent() {
		
		isAvailable = false;
	}
	
	
	// method to show the car is Again available for rent
	
	public void returnCar() {
		isAvailable= true;
	}
	
	
}


// customer class
 
class Customer{
	
	private String customerId;
	private String name;
	
	
	public Customer(String customerId , String name) {

		this.customerId = customerId;
		this.name = name;
		
		
	}
	
	public String getCustomerId() {
		
		return customerId;
	}
	
	
	public String getCustomerName() {
		
		return name;
	}
	
	
}



// class rental 

class Rental {
	
	private Car car ;
	private Customer customer;
	private int days;
	
	//constructor 
	
	public Rental(Car car, Customer customer, int days) {
		
		this.car = car;
		this.customer = customer;
		this.days = days;
	}
	
	//getter's
	
	public Car getCar() {
		return car;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getDay() {
		return days;
	}

}


class CarRentalSystem{
	
	// ArrayList to store Car Information
	private ArrayList<Car> cars;     
	
	
	// ArrayList To Store The Customer Information
	private ArrayList<Customer> customers; 
	
	
	//ArrayList To Store The Rental Car Information
	private ArrayList<Rental> rentals;
	
	
	
	//Constructor
	
	public CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();
	}
	
	
   // Storing the Data(information) inside ArrayList
	
	
	//Car Detail's
	public void addCar(Car car) {
		
		cars.add(car);
	}
	
	
	//Customer Detail's
	
	public void addCustomer(Customer customer) {
		
		customers.add(customer);
	}
	
	
	//RENTED CAR AND CUSTOMERS DETAIL'S
	
	// Car Rented
	
	public void rentCar(Car car , Customer customer, int days) {
		
		if(car.isAvailable()) {
			
			car.rent();    // to show status car is rented
			
			rentals.add(new Rental(car , customer , days)); // Storing all the detail of car , customer and no of day's to be rented in ArrayList 
		}
		else {
			System.out.println("Car Is Not Available For The Rent");
		}
		
	}
	
	
	// Car Returned 
	
	
	public void returnCar(Car car) {
		
		Rental rentalToRemove = null;  // To Track The Car Object in ArrayList Is present Or Not;
		
		
		//loop to iterate on the ArrayList
		
		for(Rental rental : rentals) {
			
		     	//if car is in my rented car list then remove that car i.e. car returned
			if(rental.getCar() == car) {
				rentalToRemove = rental;  // assigning that object to rentalToremove If it was in ArrayList
				break;	
			}
			
		}
		car.returnCar();
		// if car is found that it's rented then remove that from ArrayList
		if(rentalToRemove != null) {
			rentals.remove(rentalToRemove);
		}
		
		
		else {
			System.out.println("Car Was Not Rented");
		}
	} 
	
	
	public void menu() {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("=========== Car Rental System ===========");
			
			System.out.println("\n1:- Rent A Car ");
			System.out.println("2:- Return A Car ");
			System.out.println("3:- Exit");
			System.out.println("\nEnter Your Choice");
			
			int choice = scanner.nextInt();
			scanner .nextLine();
			
			if(choice ==1) {
				
				System.out.println("\n ========Rent A Car========");
				
				
				System.out.println("\nEnter Your Name");
				String costomerName = scanner.nextLine();
				
				System.out.println("\n Available Car's");
				
				// By using the ArrayList Object We Can Show The Available Cars;
				for(Car car : cars) {
					
					if(car.isAvailable()) {    // if cars is available
						System.out.println(car.getCarId()+" - "+car.getBrand()+" "+car.getCarModel()); // this will print the car all details
					}
				}
				// to get the car id from user
				System.out.println("\nEnter The Car Id You Want To Rent");
				String carId = scanner.nextLine();
				
				// to get number of the days,want to rent the car
				System.out.println("Enter The Number Of Day's, You Want To Rent ");
				int rentalDays = scanner.nextInt();
				scanner.nextLine();
				
				 Customer newCustomer = new Customer("CUS" + (customers.size() + 1),costomerName);
	             addCustomer(newCustomer);  // customer added to ArrayList
			 	
	             
	             Car selectedCar = null; // till now customer didn't select the car
	            		 
	             for(Car car : cars) {  // iterate to ArrayList to find the input carId is Available or not i.e car is available or not
	            	 if(car.getCarId().equals(carId) && car.isAvailable() ) {
	            		 selectedCar = car;
	            		 break;
	            	 }
	             }
	             
	             if(selectedCar != null ) {  // i.e. Chosen car by id is Available and selected for the rent
	            	 
	            	 //calculating total price
	            	 double totalPrice = selectedCar.calculateRent(rentalDays);
	            	 
	            	 
	            	 // Generating The Rental Details for User
	            	 System.out.println("\n~~~~~~~~~Rental Information~~~~~~~~~");
	            	 
	            	 System.out.println("Customer Id: "+newCustomer.getCustomerId());
	            	 System.out.println("Customer Name: "+newCustomer.getCustomerName());
	            	 System.out.println("Car: "+selectedCar.getBrand()+" "+selectedCar.getCarModel());
	            	 System.out.println("Rental Days: "+ rentalDays);
	            	 System.out.printf("Total Price: ₹%.2f%n", totalPrice);
	            	 
	            	 // to get the confirmation , want to rent or not
	            	 System.out.print("\n Confirm Rental (Yes/No): ");
	            	 String confirm = scanner.nextLine();
	            
	            	 
	            	 // rental Confirm
	            	 if(confirm.equalsIgnoreCase("Yes")) {
	            		 rentCar(selectedCar, newCustomer , rentalDays);
	            		 System.out.println("\nCar Rented Successfully!");
	            	}
	            	 else {
	            		System.out.println("\nRental Canceled");
	            	 }
	             }
	             else {
	                    System.out.println("\nInvalid Car Selection (Car id) Or Car Not Available For Rent.");
	                } 
	            // break;// car rented successfully ;
			}
			
			// Return A Car
			
			else if(choice==2) {
				
				System.out.println("\n~~~~~~~~Return A Car~~~~~~~~");
				 
				System.out.println("Enter The CarId You Wnat To Return");
			    String carId = scanner.nextLine();
			
			    // To Store the car After Checking from The ArrayList if It's Matches 
			    Car carToReturn = null ;
			    for(Car car : cars) {
			    	if(car.getCarId().equals(carId)&& !car.isAvailable()) {
			    		carToReturn = car;
			    		break;
			    	}
			    }
			    
			    if(carToReturn != null) {
			    	Customer customer = null ;  // to remove the customer from the rental ArrayList Because they returned the car 
			    	for(Rental rental : rentals) {
			    		if(rental.getCar()==carToReturn) {
			    			customer = rental.getCustomer(); 
			    		    break;
			    	}
			    }
			    	
				if (customer!= null) {
			    	returnCar(carToReturn);
			    	System.out.println("Car Returned Successfully By "+customer.getCustomerName());
			    	
			    }
				else {   // if carId Not Matches With Any Object of ArrayList Rental
					System.out.println("Car Was Not Rented Or Information Is Missing. ");
				}
				
			}
			    else {  // if that type of car is 
			    	System.out.println("Inavlid Car Id Or Car Is Not Rented");
			    }
		}
			else if(choice == 3){
				
				break; 
			}
			
			else {
				 System.out.println("Invalid Choice. Please Enter Valid Option ");
			}
				
		}
		System.out.println("\n Thank You!");
	}		
}


public class Main {

	public static void main(String[] args) {
		
		  CarRentalSystem rentalSystem = new CarRentalSystem();

	        Car car1 = new Car("C001", "Toyota", "Camry", 600.0); // Different base price per day for each car
	        Car car2 = new Car("C002", "Honda", "Accord", 700.0);
	        Car car3 = new Car("C003", "Mahindra", "Thar", 2500.0);
	        Car car4 = new Car("C004","Tata","Punch",900.0);
	        Car car5 = new Car("C005","Mahindra","Scorpio",1400.0);
	        Car car6 = new Car("C006","Kia","Sonet",900.0);
	        Car car7 = new Car("C007", "Maruti", "Swift", 600.0);
	        Car car8 = new Car("C008", "Hyundai", "Creta",800.0);
	        Car car9 = new Car("C009","Toyota","Fortuner",2000.0);
	        Car car10 = new Car("C010", "Tata", "Nexon",1300.0);
	       // Car car11 = new Car("C011", null, null, 0)
	        		
	       //Adding Car Object in Car Rental System;
	        rentalSystem.addCar(car1);
	        rentalSystem.addCar(car2);
	        rentalSystem.addCar(car3);
	        rentalSystem.addCar(car4);
	        rentalSystem.addCar(car5);
	        rentalSystem.addCar(car6);
	        rentalSystem.addCar(car7);
	        rentalSystem.addCar(car8);
	        rentalSystem.addCar(car9);
	        rentalSystem.addCar(car10);
	        

	        rentalSystem.menu();
	}

}
