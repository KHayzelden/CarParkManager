package carparkcoursework;

public class Van extends Vehicle{

    protected String cargo;
    protected int volume;
    
    public Van(){super();};
    
    public Van(String tempPlateNumber, 
               String tempBrand, 
               String tempCargo, 
               int tempVolume,
               int tempDay, 
               int tempMonth, 
               int tempYear, 
               int tempHours, 
               int tempMinutes, 
               int tempSeconds)
    {
        
        super(tempPlateNumber, tempBrand, tempDay, tempMonth, tempYear, tempHours,
                tempMinutes, tempSeconds);
        
        this.cargo = tempCargo;
        this.volume = tempVolume;

    }
}