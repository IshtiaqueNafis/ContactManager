package Classes;
import javafx.beans.property.SimpleIntegerProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class MyDate {
    private SimpleIntegerProperty day;
    private SimpleIntegerProperty month;
    private SimpleIntegerProperty year;



    public MyDate(int month,int day,int year) {
        this.month = new SimpleIntegerProperty(month);
        this.day = new SimpleIntegerProperty(day);
        this.year = new SimpleIntegerProperty(year);

    }



    public int getDay() {
        return day.get();
    }

    public SimpleIntegerProperty dayProperty() {
        return day;
    }

    public void setDay(int day) {
        this.day.set(day);
    }

    public int getMonth() {
        return month.get();
    }

    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    @Override
    public String toString() {
        return getMonth()+"-"+getDay()+"-"+getYear();
    }

    public String getMonthShortForm(){
    LocalDate ld = LocalDate.of(getYear(),getMonth(),getDay());
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return format.format(ld);
}
public String getLongForm() throws ParseException {
    SimpleDateFormat full_format = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    String actualDate ="";
    actualDate +=Integer.toString(getYear());
    actualDate+="-";
    actualDate+=Integer.toString(getMonth());
    actualDate+="-";
    actualDate+=Integer.toString(getYear());

    Date date = sdf.parse(actualDate);

    return full_format.format(date);
}


}
