package carparkcoursework;

public abstract class Vehicle {
    
    String plateNumber, brand;
    
    public Vehicle(){}
    
    DateTime entryDate = new DateTime() {};
    
    public Vehicle(String plateNumber, String brand, int day, int month,
            int year, int hours, int minutes, int seconds){
        
        this.plateNumber = plateNumber;
        this.brand = brand;
        
        entryDate.setDateTime(day, month, year, hours, minutes, seconds);
        
    }
        
    public String getPlateNumber () {return this.plateNumber;}
    
    public String getBrand () {return this.brand;}
    
    public String getDate () 
    {
        return entryDate.getDate();
    }
    
    @Override
    public String toString () {
         return ("\nPlate Number: " + this.getPlateNumber() +
                 "\nEntry Time: " + entryDate.getDate() + " " + entryDate.getTime());
    }
}