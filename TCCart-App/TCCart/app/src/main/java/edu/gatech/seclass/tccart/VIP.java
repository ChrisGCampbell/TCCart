package edu.gatech.seclass.tccart;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by equinn on 3/23/16.
 * VIP class
 */

public class VIP implements Serializable{
    private static final long serialVersionUID = 1L;
    private Calendar start_date;
    private Calendar end_date;

    private void setStart_date(Calendar date_start){
        start_date = date_start;
    }

    private void setEnd_date(Calendar date_end){
        end_date = date_end;
    }

    private void setVipDate(Calendar sd, Calendar ed)
    {
        setStart_date(sd);
        setEnd_date(ed);
    }

    public Calendar getStart_date(){
        return start_date;
    }

    public Calendar getEnd_date(){
        return end_date;
    }

    // Returns whether customer is a VIP or not.
    public boolean getStatus(){
        final Calendar today = Calendar.getInstance();

        if(start_date == null || end_date == null)return false;
        return(start_date.before(today) && end_date.after(today));
    }

    public boolean renew(final int new_year){

        // Today's date
        final Calendar today = Calendar.getInstance();

        // The customer does not have current VIP status or future VIP status
        if(start_date == null || end_date == null || end_date.before(today))
        {
            /* VIP status starts NEXT January */
            setVipDate(new GregorianCalendar(new_year, 0, 1), new GregorianCalendar(new_year, 11, 31));
            return true;
        }

        // The customer has VIP benefits this year, and qualifies for VIP benefits next year
        if(getStatus() && (today.get(Calendar.YEAR) == end_date.get(Calendar.YEAR)))
        {
            // Extend benefits another year
            end_date.set(Calendar.YEAR, end_date.get(Calendar.YEAR)+1);
            return true;
        }

        // If the code gets here, this means that the customer already has VIP status already for
        // this year and next year.
        return false;
    }

    // Normal renew
    public boolean renew()
    {
        return renew(Calendar.getInstance().get(Calendar.YEAR)+1);
    }

    // Debug renew
    public boolean debug_renew()
    {
        return renew(Calendar.getInstance().get(Calendar.YEAR));
    }
}
