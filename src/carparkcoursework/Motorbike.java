package carparkcoursework;

public class Motorbike extends Vehicle{

    protected String engineSize;
    
    public Motorbike(){super();};
    
    public Motorbike(String tempPlateNumber, String tempBrand, String tempEngineSize,
                    int tempDay, int tempMonth, int tempYear, int tempHours, int tempMinutes, 
                    int tempSeconds)
    {
        
        super(tempPlateNumber, tempBrand, tempDay, tempMonth, tempYear, tempHours,
                tempMinutes, tempSeconds);
        
        this.engineSize = tempEngineSize;

    }
}