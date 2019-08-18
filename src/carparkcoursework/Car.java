package carparkcoursework;

public class Car extends Vehicle{

    protected String color;
    protected int doors;
    
    public Car(){super();};
    
    public Car(String tempPlateNumber, String tempBrand, String tempColor, int tempDoors, 
                int tempDay, int tempMonth, int tempYear, int tempHours, int tempMinutes, 
                int tempSeconds)
    {
        
        super(tempPlateNumber, tempBrand, tempDay, tempMonth, tempYear, tempHours,
                tempMinutes, tempSeconds);
        
        this.color = tempColor;
        this.doors = tempDoors;

    }
}