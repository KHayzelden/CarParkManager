package carparkcoursework;

import static carparkcoursework.CarParkManager.listVehiclesByDate;
import static carparkcoursework.CarParkManager.parkingCharges;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterCarParkManager implements CarParkManager
{
    public static Scanner keyboard = new Scanner(System.in);
    public static int numberOfSpaces = 20;
        
    public static void main(String[] args){

        CarParkManager westminster = new WestminsterCarParkManager();

        ArrayList<Vehicle> carPark = new ArrayList<>();

        boolean running = true ; //used to loop the menu until the user wishes to exit
        char menuChoice ;
        
        printMenu(); 
        
        while (running) //will loop the menu, keeping the program running until the user quits the program manually
        {
            menuChoice = getMenuChoice();
            
            switch (menuChoice) 
            {
                case 'A' : // add vehicle
                    addVehicle(carPark);
                    break;
                case 'R' : // remove vehicle
                    removeVehicle(carPark);
                    break;
                case 'L' : // list of parked vehicles
                    listVehicles(carPark);
                    break;    
                case 'S' : // statistics of parked vehicles
                    statistics(carPark);
                    break;
                case 'D' : // shows vechiles added on given date
                    listVehiclesByDate(carPark);
                    break;
                case 'C' : // shows parking costs
                    parkingCharges(carPark);
                    break;
                case 'M' : // shows the menu again
                    printMenu();
                    break;
                case 'Q' : // quit program
                    running = false ;
                    break;
            }
        }
    }  

    public static void printMenu(){ //only printed on request to save space
    
        System.out.println();
        System.out.println("-*-*-*-*- Menu -*-*-*-*-\n");
        System.out.println("A: Add a new vehicle");
        System.out.println("R: Remove a vehicle ");
        System.out.println("L: List currently parked vehicles");
        System.out.println("S: Show statistics about the vehicles currently parked");
        System.out.println("D: Show vehicles parked on a specified date");
        System.out.println("C: Show parking cost of all currently parked vehicles");
        System.out.println("Q: Quit program");
        
    }

    public static char getMenuChoice () { //gets the menu choice or asks for a new one if not valid
        
        String input;
        char acceptedChar = 'X';
        boolean acceptableInput = false;
        
        System.out.println();
        System.out.print("Please enter your choice from the menu or enter "
                + "\"M\" to show the menu again: ");
        input = keyboard.nextLine().toUpperCase();
        
        while (!acceptableInput)
        {
            try 
            {
                acceptedChar = input.charAt(0);
                acceptedChar = Character.toUpperCase(acceptedChar);
                
                if (acceptedChar == 'A' || acceptedChar == 'R' || acceptedChar == 'L' || 
                    acceptedChar == 'S' || acceptedChar == 'D' || acceptedChar == 'C' ||
                    acceptedChar == 'M' || acceptedChar == 'Q') 
                {    
                    acceptableInput = true ;
                }
                else
                {
                    System.out.println("That is an invalid option.");
                    System.out.println();
                    System.out.print("Please enter one of the menu options as listed above: ");
                    input = keyboard.nextLine().toUpperCase();
                }

            }
            catch (NumberFormatException e)
            {
                System.out.println("That is an invalid option.");
                System.out.println();
                System.out.print("Please enter one of the menu options as listed above: ");
                input = keyboard.nextLine().toUpperCase();
            }
        }
       
        return acceptedChar ;      
    }

    public static void addVehicle(ArrayList<Vehicle> carPark){
            
        if (carPark.size() == numberOfSpaces) //will only allow the user into the diaglouge below if there are spaces available
        {
            System.out.println("\nThe car park is currently full.");
        }
        else 
        {       
            String input;

            System.out.println("\n*-*- Vehicle Types -*-*\n");
            System.out.println("C: Car");
            System.out.println("V: Van");
            System.out.println("M: Motorbike");

            System.out.print("\nPlease Enter the vehicle type: ");
            input = keyboard.nextLine().toUpperCase();

            char acceptedChar = 'X';
            boolean acceptableInput = false; //starts a loop that will run until the user enters an acceptable choice

            while (!acceptableInput)
            {
                try 
                {
                    acceptedChar = input.charAt(0);
                    acceptedChar = Character.toUpperCase(acceptedChar);

                    if (acceptedChar == 'C' || acceptedChar == 'V' || acceptedChar == 'M')

                        acceptableInput = true ;

                    else
                    {
                        System.out.println("\nThat is an invalid option.");
                        System.out.println();
                        System.out.print("Please enter one of the vehicle types as listed above: ");
                        input = keyboard.nextLine().toUpperCase();
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("\nThat is an invalid option.");
                    System.out.println();
                    System.out.print("Please enter one of the vehilce types as listed above: ");
                    input = keyboard.nextLine().toUpperCase();
                }
            }
            
            if (acceptedChar == 'V' && carPark.size() > (numberOfSpaces-2)) //as vans take 2 spaces, the user is kicked out if they want to park a van when there is only one space left
            { 
                System.out.println("There is not enough space for a Van.");
            }
            else
            {
                String dateInput;

                System.out.print("\nEnter the date and time (DD/MM/YYYY HH:MM:SS): ");
                dateInput = keyboard.nextLine().toUpperCase();

                int day = -1, month = -1, year = -1, hours = -1, minutes = -1, seconds = -1; //set to impossible values for validation
                acceptableInput = false; //used for a validation loop again

                while (!acceptableInput)
                {
                    if(dateInput.length() < 19) //if formatted correctly this should be the length of the string
                    {
                        System.out.println("\nThat is an invalid date.");
                        System.out.println();
                        System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                        dateInput = keyboard.nextLine().toUpperCase();
                    }
                    else
                    {    
                        try 
                        {
                            day = Integer.parseInt(dateInput.substring(0, 2)); //location in string if it's correctly formatted
                            month = Integer.parseInt(dateInput.substring(3, 5)); //second is +1 as substring final is not inclusive
                            year = Integer.parseInt(dateInput.substring(6, 10));
                            hours = Integer.parseInt(dateInput.substring(11, 13));
                            minutes = Integer.parseInt(dateInput.substring(14, 16));
                            seconds = Integer.parseInt(dateInput.substring(17));

                            if (day >= 1 && day <= 31 && month >= 1 && month <= 12 &&
                                hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 &&
                                seconds >= 0 && seconds <= 59)
                            {
                                acceptableInput = true ;
                            }
                            else
                            {
                                System.out.println("\nThat is an invalid date.");
                                System.out.println();
                                System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                                dateInput = keyboard.nextLine().toUpperCase();
                            }

                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nThat is an invalid date.");
                            System.out.println();
                            System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                            dateInput = keyboard.nextLine().toUpperCase();
                        }
                    }
                }

                String tempPlateNumber, tempBrand;

                System.out.print("\nEnter the plate number: ");
                tempPlateNumber = keyboard.nextLine().toUpperCase();

                Iterator<Vehicle> it = carPark.iterator(); //creates an iterator to run through the carPark array
                boolean isAlreadyParked = false; 

                while (it.hasNext()) 
                {
                    Vehicle x = it.next();
                    
                    if (x.getPlateNumber().equals(tempPlateNumber)) //if the plate number is already in the arraylist then it will not allow the user to enter another as this should be unique
                    {
                        System.out.println("\nThis vehicle is already parked.");
                        isAlreadyParked = true;
                        break;
                    }
                }

                if (!isAlreadyParked) //otherwise they may continue
                {
                    System.out.print("Enter the brand: ");
                    tempBrand = keyboard.nextLine().toUpperCase();
                    
                    switch (acceptedChar) //will go through the subclass specific elements needed, and where applicable validate
                    {                       //then creates the objects
                        case 'C':
                        {
                            String tempColor, tempDoorsString;
                            int tempDoors = 0; //initalized to an invalid option for validation later

                            System.out.print("Enter the color of the car: ");
                            tempColor = keyboard.nextLine().toUpperCase();

                            System.out.print("Enter the number of doors on the car: ");
                            tempDoorsString = keyboard.nextLine().toUpperCase();

                            acceptableInput = false;

                            while (!acceptableInput)
                            {
                                try 
                                {
                                    tempDoors = Integer.parseInt(tempDoorsString);

                                    if (tempDoors >= 1 && tempDoors <= 5)

                                        acceptableInput = true ;

                                    else
                                    {
                                        System.out.println("\nThat is an invalid option.");
                                        System.out.println();
                                        System.out.print("Please enter a valid number: ");
                                        tempDoorsString = keyboard.nextLine().toUpperCase();
                                    }

                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("\nThat is an invalid option.");
                                    System.out.println();
                                    System.out.print("Please enter a valid number: ");
                                    tempDoorsString = keyboard.nextLine().toUpperCase();
                                }
                            }

                            carPark.add(new Car(tempPlateNumber, tempBrand, tempColor, tempDoors,
                                        day, month, year, hours, minutes, seconds));

                        }
                        break;

                        case 'V':
                        {
                            String tempCargo, tempVolumeString;
                            int tempVolume = 0; //initalized to an invalid option for validation later

                            System.out.print("Enter the type of cargo being carried: ");
                            tempCargo = keyboard.nextLine().toUpperCase();

                            System.out.print("Enter the number volume of the van: ");
                            tempVolumeString = keyboard.nextLine().toUpperCase();

                            acceptableInput = false;

                            while (!acceptableInput)
                            {
                                try 
                                {
                                    tempVolume = Integer.parseInt(tempVolumeString);

                                    if (tempVolume >= 1)

                                        acceptableInput = true ;

                                    else
                                    {
                                        System.out.println("\nThat is an invalid option.");
                                        System.out.println();
                                        System.out.print("Please enter a valid number: ");
                                        tempVolumeString = keyboard.nextLine().toUpperCase();
                                    }

                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("\nThat is an invalid option.");
                                    System.out.println();
                                    System.out.print("Please enter a valid number: ");
                                    tempVolumeString = keyboard.nextLine().toUpperCase();
                                }
                            }

                            carPark.add(new Van(tempPlateNumber, tempBrand, tempCargo, tempVolume,
                                        day, month, year, hours, minutes, seconds));
                            carPark.add(new Van(tempPlateNumber, tempBrand, tempCargo, tempVolume,
                                        day, month, year, hours, minutes, seconds));
                        }
                        break;
                        
                        case 'M':
                        {
                            String tempEngineSize;

                            System.out.print("Enter the motorbike's engine size: ");
                            tempEngineSize = keyboard.nextLine().toUpperCase();

                            carPark.add(new Motorbike(tempPlateNumber, tempBrand, tempEngineSize,
                                        day, month, year, hours, minutes, seconds));
                        }
                        break;
                    }
                    System.out.println("\nYou have successfully added the vehicle.");

                    if ((numberOfSpaces - carPark.size()) > 0) //calcualtes the reamining spaces and prints
                        System.out.println("\nThere are " + (numberOfSpaces - carPark.size()) + " empty lots remaining.");
                    else
                        System.out.println("\nThere are no empty lots remaining.");
                }
            }
        }
    }

    public static void removeVehicle(ArrayList<Vehicle> carPark){
        
        String input;
        
        if (carPark.isEmpty()) //will not let the user into the dialogue if there are no vehicles to remove
        {
            System.out.println("\nThe car park is currently empty.");
        }
        else
        {
            System.out.print("\nEnter the plate number of the vehicle you would like to remove: ");
            input = keyboard.nextLine().toUpperCase();

            Iterator<Vehicle> it = carPark.iterator();
            Boolean noneDeleted = true; //used to kepe track of message if no vehicles match the plate no.
            int counter = 0; //for deleting "both" instances of van

            while (it.hasNext()) 
            {
                Vehicle x = it.next();

                if (x.getPlateNumber().equals(input))
                {   
                    it.remove(); //deletes any that match the entered plate number
                    noneDeleted = false;

                    if (counter == 0)
                    {
                        String typeString = "";

                        if      (x instanceof Car)          {typeString = "CAR";} //checks the type of subclass to print
                        else if (x instanceof Motorbike)    {typeString = "MOTORBIKE";}
                        else if (x instanceof Van)          {typeString = "VAN";} 

                        System.out.println("\nMatched Vehicle");
                        System.out.println(x);
                        System.out.println("Type: " + typeString);

                        counter++; //avoids printing the second van instance but will still delete it above this if
                    }  
                }  
            }
                
            if (noneDeleted) 
            {
                System.out.println("\nNo vehicles currently parked matched that plate number.");
            }
            else
            {
                System.out.println("\nYou have successfully removed this vehicle.");
                System.out.println("\nThere are now " + (numberOfSpaces - carPark.size()) + " empty lots.");
            }
        }
    
    }

    public static void listVehicles(ArrayList<Vehicle> carPark){

        SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
        
        if (carPark.isEmpty())
            System.out.println("\nThe car park is currently empty.");
        else
        {
           
            if(!carPark.isEmpty())
            {
                
                SimpleDateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
                
                try
                {
                    for(int i = 0; i < carPark.size(); i++)
                    {
                        for(int j = 1; j < (carPark.size()-i); j++)
                        {
                            Date checkDate1 = dateformat.parse(carPark.get(j).entryDate.getDate());
                            Date checkDate2 = dateformat.parse(carPark.get(j-1).entryDate.getDate());

                            if(checkDate1.after(checkDate2) == true)
                            {
                                Vehicle temporary1 = carPark.get(j-1);
                                Vehicle temporary2 = carPark.get(j);
                                carPark.set(j,temporary1);
                                carPark.set(j-1, temporary2);   
                            }
                        } 
                    }  
                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                }
            }
            
            Iterator<Vehicle> it = carPark.iterator();
            int counter = 0; //used to track last car listed to avoid reprinting vans
            
            while (it.hasNext()) 
            {
                Vehicle x = it.next();

                if (counter == 0) //if the vehicle 
                {
                    String typeString = "";

                    if      (x instanceof Car)        {typeString = "CAR";}
                    else if (x instanceof Motorbike)  {typeString = "MOTORBIKE";}
                    else if (x instanceof Van)        {typeString = "VAN"; counter ++;} //sets the string and adds one to the counter for next time to not reprint the van

                    System.out.println(x);
                    System.out.println("Type: " + typeString);
                }
                else
                {
                    counter = 0; //resets the counter on the second of the same van so it will print anything after
                }
            }
        }
    }
    
    public static void statistics(ArrayList<Vehicle> carPark){
        
        String dateInput;

        if (carPark.isEmpty())
            System.out.println("\nThe car park is currently empty. No statistics may be given.");
        else
        {
            System.out.print("\nEnter the date and time (DD/MM/YYYY HH:MM:SS): ");
            dateInput = keyboard.nextLine().toUpperCase();

            int day = -1, month = -1, year = -1, hours = -1, minutes = -1, seconds = -1; //set to impossible values for validation
            Boolean acceptableInput = false;

            while (!acceptableInput)
            {
                if(dateInput.length() < 19)
                {
                    System.out.println("\nThat is an invalid date.");
                    System.out.println();
                    System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                    dateInput = keyboard.nextLine().toUpperCase();
                }
                else
                {    
                    try 
                    {
                        day = Integer.parseInt(dateInput.substring(0, 2)); //location in string if it's correctly formatted
                        month = Integer.parseInt(dateInput.substring(3, 5)); //second is +1 as substring final is not inclusive
                        year = Integer.parseInt(dateInput.substring(6, 10));
                        hours = Integer.parseInt(dateInput.substring(11, 13));
                        minutes = Integer.parseInt(dateInput.substring(14, 16));
                        seconds = Integer.parseInt(dateInput.substring(17));

                        if (day >= 1 && day <= 31 && month >= 1 && month <= 12 &&
                            hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 &&
                            seconds >= 0 && seconds <= 59)

                            acceptableInput = true ;

                        else
                        {
                            System.out.println("\nThat is an invalid date.");
                            System.out.println();
                            System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                            dateInput = keyboard.nextLine().toUpperCase();
                        }

                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nThat is an invalid date.");
                        System.out.println();
                        System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                        dateInput = keyboard.nextLine().toUpperCase();
                    }
                }
            }
        
            
            Iterator<Vehicle> it = carPark.iterator();
            double numberOfCars = 0, numberOfVans = 0, numberOfMotorbikes = 0, totalVehicles = 0, 
                   percentCars, percentVans, percentMotorbikes;
            
            Vehicle longestParked = carPark.get(0);
            Vehicle mostRecentlyParked = carPark.get(0); //get placeholder vehicles until calcualted in the following loop
            
            while (it.hasNext()) 
            {
                Vehicle x = it.next();
                
                if      (x instanceof Car)          {numberOfCars++;}
                else if (x instanceof Van)          {numberOfVans++;}
                else if (x instanceof Motorbike)    {numberOfMotorbikes++;}
                
                if (x.entryDate.getYear() < longestParked.entryDate.getYear() &&
                    x.entryDate.getMonth() < longestParked.entryDate.getMonth() && 
                    x.entryDate.getDay() < longestParked.entryDate.getDay() &&
                    x.entryDate.getHour() < longestParked.entryDate.getHour() &&
                    x.entryDate.getMinute() < longestParked.entryDate.getMinute() &&
                    x.entryDate.getSecond() < longestParked.entryDate.getSecond()) //calcualtes which is older by sections of the date
                {
                    longestParked = x;
                }
                
                if (x.entryDate.getYear() > mostRecentlyParked.entryDate.getYear() &&
                    x.entryDate.getMonth() > mostRecentlyParked.entryDate.getMonth() && 
                    x.entryDate.getDay() > mostRecentlyParked.entryDate.getDay() &&
                    x.entryDate.getHour() > mostRecentlyParked.entryDate.getHour() &&
                    x.entryDate.getMinute() > mostRecentlyParked.entryDate.getMinute() &&
                    x.entryDate.getSecond() > mostRecentlyParked.entryDate.getSecond()) //calculates which is newer by sections of the date
                {
                    mostRecentlyParked = x;
                }
            }
            
            numberOfVans /= 2; //halfed because there are two listings for each van
            totalVehicles = numberOfCars + numberOfVans + numberOfMotorbikes;
            
            percentCars = (numberOfCars / totalVehicles) * 100;
            percentVans = (numberOfVans / totalVehicles) * 100;
            percentMotorbikes = (numberOfMotorbikes / totalVehicles) * 100;
            
            System.out.println("\nStatistics\n");
            System.out.println("Percent of currently parked cars by type");
            System.out.println("Cars: %" + percentCars);
            System.out.println("Vans: %" + percentVans);
            System.out.println("Motorbikes: %" + percentMotorbikes);
            
            System.out.println("\nThe most recently parked vehicle");
            
            String typeString = "";

            if      (mostRecentlyParked instanceof Car)        {typeString = "CAR";}
            else if (mostRecentlyParked instanceof Motorbike)  {typeString = "MOTORBIKE";}
            else if (mostRecentlyParked instanceof Van)        {typeString = "VAN";}

            System.out.println(mostRecentlyParked);
            System.out.println("Type: " + typeString);
            
            System.out.println("\nThe longest parked vehicle");
            
            if      (mostRecentlyParked instanceof Car)        {typeString = "CAR";}
            else if (mostRecentlyParked instanceof Motorbike)  {typeString = "MOTORBIKE";}
            else if (mostRecentlyParked instanceof Van)        {typeString = "VAN";}

            System.out.println(mostRecentlyParked);
            System.out.println("Type: " + typeString);            
        }      
    }

    public static void listVehiclesByDate(ArrayList<Vehicle> carPark){
        
        if (carPark.isEmpty()) //skips if the carpark is empty
            System.out.println("\nThe car park is currently empty.");
        else
        {        
            int day = -1, month = -1, year = -1; //set to impossible values for validation
            boolean acceptableInput = false;
            String dateInput;

            System.out.print("\nEnter the date (DD/MM/YYYY): "); //asks for the date to list, and then validates it in a loop below
            dateInput = keyboard.nextLine().toUpperCase();

                while (!acceptableInput)
                {
                    if(dateInput.length() < 10)
                    {
                        System.out.println("\nThat is an invalid date.");
                        System.out.println();
                        System.out.print("Please enter a valid date in the format of DD/MM/YYYY: ");
                        dateInput = keyboard.nextLine().toUpperCase();
                    }
                    else
                    {
                        try 
                        {
                            day = Integer.parseInt(dateInput.substring(0, 2)); //location in string if it's correctly formatted
                            month = Integer.parseInt(dateInput.substring(3, 5)); //second is +1 as substring final is not inclusive
                            year = Integer.parseInt(dateInput.substring(6, 10));

                            if (day >= 1 && day <= 31 && month >= 1 && month <= 12)

                                acceptableInput = true ;

                            else
                            {
                                System.out.println("\nThat is an invalid date.");
                                System.out.println();
                                System.out.print("Please enter a valid date in the format of DD/MM/YYYY: ");
                                dateInput = keyboard.nextLine().toUpperCase();
                            }

                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nThat is an invalid date.");
                            System.out.println();
                            System.out.print("Please enter a valid date in the format of DD/MM/YYYY: ");
                            dateInput = keyboard.nextLine().toUpperCase();
                        }
                    }
                }

            Iterator<Vehicle> it = carPark.iterator();
            Boolean noVehiclesOnDate = true; //to keep track of if there were any vehicles that day
            int counter = 0; //keeps track of vans to not print twice

            while (it.hasNext()) 
            {
                Vehicle x = it.next();

                if ((x.entryDate.getDay() == day) && (x.entryDate.getMonth() == month) &&
                     (x.entryDate.getYear() == year) && (counter == 0))
                {
                    String typeString = "";

                    if      (x instanceof Car)        {typeString = "CAR";}
                    else if (x instanceof Motorbike)  {typeString = "MOTORBIKE";}
                    else if (x instanceof Van)        {typeString = "VAN"; counter ++;} //sets the string and adds one to the counter for next time to not reprint the van

                    System.out.println(x);
                    System.out.println("Type: " + typeString);
                    
                    noVehiclesOnDate = false;
                }
                else
                {
                    counter = 0; //resets the counter on the second of the same van so it will print anything after
                }
            }

            if (noVehiclesOnDate) System.out.println("There were no vehicles parked on that date.");
        }
    }
    
    public static void parkingCharges(ArrayList<Vehicle> carPark){
        
        if (carPark.isEmpty())
            System.out.println("\nThe car park is currently empty. No statistics may be given.");
        else
        {
            String dateInput;
            
            System.out.print("\nEnter the date and time (DD/MM/YYYY HH:MM:SS): ");
            dateInput = keyboard.nextLine().toUpperCase();

            int day = -1, month = -1, year = -1, hours = -1, minutes = -1, seconds = -1; //set to impossible values for validation
            Boolean acceptableInput = false;

            while (!acceptableInput)
            {
                if(dateInput.length() < 19)
                {
                    System.out.println("\nThat is an invalid date.");
                    System.out.println();
                    System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                    dateInput = keyboard.nextLine().toUpperCase();
                }
                else
                {    
                    try 
                    {
                        day = Integer.parseInt(dateInput.substring(0, 2)); //location in string if it's correctly formatted
                        month = Integer.parseInt(dateInput.substring(3, 5)); //second is +1 as substring final is not inclusive
                        year = Integer.parseInt(dateInput.substring(6, 10));
                        hours = Integer.parseInt(dateInput.substring(11, 13));
                        minutes = Integer.parseInt(dateInput.substring(14, 16));
                        seconds = Integer.parseInt(dateInput.substring(17));

                        if (day >= 1 && day <= 31 && month >= 1 && month <= 12 &&
                            hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 &&
                            seconds >= 0 && seconds <= 59)

                            acceptableInput = true ;

                        else
                        {
                            System.out.println("\nThat is an invalid date.");
                            System.out.println();
                            System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                            dateInput = keyboard.nextLine().toUpperCase();
                        }

                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nThat is an invalid date.");
                        System.out.println();
                        System.out.print("Please enter a valid date and time in the format of DD/MM/YYYY HH:MM:SS : ");
                        dateInput = keyboard.nextLine().toUpperCase();
                    }
                }
            }
            
            Iterator<Vehicle> it = carPark.iterator();
            int counter = 0; //keeps track of vans to not print twice
            int cost;

            while (it.hasNext()) 
            {
                cost = 0;
                Vehicle x = it.next();
                int tempYear = year, tempMonth = month, tempDay = day, tempHour = hours;
                int hoursCounter = 0;

                if (counter == 0) //all the whiles following this add the hours equivalent until the dates match
                {                   //the hours are the used to calcuate the cost of the parking
                    while (x.entryDate.getYear() != tempYear)
                    {
                        tempYear--; 
                        hoursCounter += 8760; //hours in a year
                    }
                    
                    while (x.entryDate.getMonth() != tempMonth)
                    {
                        tempMonth--; 
                        hoursCounter += 720; //hours in a month
                    }
                    
                    while (x.entryDate.getDay() != tempDay)
                    {
                        tempDay--; 
                        hoursCounter += 24; 
                    }
                    
                    while (x.entryDate.getHour() != tempHour)
                    {
                        tempHour--; 
                        hoursCounter++;
                    }

                    if (hoursCounter <= 3)
                    {
                        cost = hoursCounter * 3;
                    }
                    else
                    {
                        cost = (hoursCounter/24) * 30;
                        hoursCounter %= 24; //changes the counter to the remaining hours outside of whole days
                        
                        if (hoursCounter <=3)
                        {
                            cost += (hoursCounter * 3); //£3 for first 3 hours
                        }
                        else
                        {
                            cost += ((hoursCounter-3) + 9); //9 for the inital 3 hour @ £3 charge, hence removing 3 hours from the counter
                        }
                        
                    }
                                            
                    if (x instanceof Van) {counter ++;} //adds one to the counter for next time to not reprint the van's cost
                    
                    System.out.println(x);
                    
                    if (cost < 0) //avoids user error of inputting a older date, resulting in a negative cost
                        { System.out.println("The date you entered is before the arrival date of this vehicle."); }  
                    else
                        { System.out.println("Cost: " + cost); }
                    
                }
                else
                {
                    counter = 0; //resets the counter on the second of the same van so it will print anything after
                }
            }
            
            
        }
        
    }
}  