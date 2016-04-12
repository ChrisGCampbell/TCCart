package edu.gatech.seclass.tccart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by equinn on 3/24/16.
 * Purchase class
 */
public class Purchase {
    private ArrayList<Item> item_purchase = new ArrayList<>();
    private double item_discount; /* discount is the amount removed from the item cost by the percentage of the discount */
    private double item_credit;
    private Date p_date;

    public Purchase() {p_date = new Date();}
    public void addItem(Item item)
    {
        if(item.getQuantity() > 0)item_purchase.add(item);
    }

    public Vector<String> printPurchase(){
        DecimalFormat df = new DecimalFormat("0.00");
        Vector<String> str = new Vector<>();
        double total = 0;

        // Date is first string
        str.add(p_date.toString());

        // Items are second string
        String item_string = new String();
        for(int i = 0; i < item_purchase.size(); i++)
            item_string += item_purchase.get(i).getName()+" x "+item_purchase.get(i).getQuantity()+", ";

        str.add("\tItems: "+item_string);
        str.set(str.size() - 1, str.lastElement().substring(0, str.lastElement().length() - 2));

        str.add("\tUnmodified Total: $" + df.format(getUnmodifiedTotal()));          // Total is string 3
        str.add("\tCredit Applied: $" + df.format(item_credit));        // Credit used is string 4
        str.add("\tDiscount: $"+df.format(item_discount));            // Discount is string 5
        str.add("\tFinal Total: $"+df.format(getModifiedTotal()));     // final Total

        //System.out.println(str);
        return str;
    }

    public double getUnmodifiedTotal(){
        double total_cost = 0;

        for(int i = 0; i < item_purchase.size(); i++)
            total_cost += item_purchase.get(i).getCost();

        return total_cost;
    }

    public double getItem_credit(){
        return item_credit;
    }

    public double getModifiedTotal(){
        double m_total = getUnmodifiedTotal()-item_discount-item_credit;

        // Safety check for total
        return ((m_total > 0) ? m_total:0);
    }

    // Amount to discount from the item
    public void applyDiscount(double discount_amount){
        item_discount = discount_amount;
    }

    public void applyCredit(double credit_amount){
        item_credit = credit_amount;
    }

}
