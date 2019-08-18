package carparkcoursework;

public abstract class DateTime { 
    
    protected int year;
    protected int month;
    protected int day;
    protected int hour;
    protected int minute;
    protected int second;
    
    public DateTime() {}
    
    public void setDateTime(int tempDay, int tempMonth, int tempYear, int tempHours,
                     int tempMinutes, int tempSeconds)
    {
        this.year = tempYear;
        this.month = tempMonth;
        this.day = tempDay;
        this.hour = tempHours;
        this.minute = tempMinutes;
        this.second = tempSeconds;
    }

    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getDay(){return day;}
    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public int getSecond(){return second;} 
    
    public String getDate() {
        
        String dateString = String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hour, minute );
        return dateString;
        
    }
    
    public String getTime() {
        
        return "" + this.hour + ":" + this.minute + ":" + this.second;
    }
    
}