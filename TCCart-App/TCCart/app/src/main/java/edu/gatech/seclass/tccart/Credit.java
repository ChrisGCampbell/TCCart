package edu.gatech.seclass.tccart;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by equinn on 3/24/16.
 * Credit Class
 */

public class Credit implements Serializable{
    private static final long serialVersionUID = 1L;
    private double amount = 0.00;
    private Calendar valid_until;

    public void addCredit(double cred_amount){
        if(!isValid())amount = 0;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        valid_until = new GregorianCalendar(year, month + 1, day);
        amount += cred_amount;
    }

    public double getCredit(){
        return (isValid() ? amount : 0);
    }

    public Calendar getValid_until(){
        return valid_until;
    }

    public void useCredit(double creditUsed)
    {
        if(isValid())amount -= creditUsed;
    }

    public boolean isValid()
    {
        if(valid_until == null)return false;

        Calendar today = Calendar.getInstance();
        return valid_until.after(today);
    }
}
