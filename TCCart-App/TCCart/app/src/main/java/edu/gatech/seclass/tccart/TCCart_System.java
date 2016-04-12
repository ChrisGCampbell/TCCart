package edu.gatech.seclass.tccart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.content.Context;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import edu.gatech.seclass.services.*;

/**
 * Created by Kevin Hall on 3/23/2016.
 * External classes all use static functions and thus do not need to be created.
 */
public class TCCart_System extends Application {
    private Context global_context;
    private File cust_file;
    private Customer current_customer;

    // List
    private Map<Long, Customer> customer_list = new HashMap<>();

    // Set default values
    public static final double discount_rate = 0.10;
    public static final double credit_earn = 3.00;
    public static final double credit_gain = 30.00;
    public static final double vip_gain = 300.00;

    public TCCart_System()
    {
        // Reserved for future use
        // Should setup singleton code for this class
    }

    public void Initialize(Context c)
    {
        Initialize(c, false);
    }

    public void Initialize(Context c, boolean force_reset)
    {
        // Refresh file if force reset = true
        if(cust_file != null && force_reset)cust_file.delete();
        else if(global_context != null)
        {
            current_customer = null;
            return;
        }

        global_context = c;
        // Load Customer List
        //super(c);
        File dir = global_context.getFilesDir().getAbsoluteFile();
        cust_file = new File(dir, "TCCartCustData.obj");

        // If the customer file does not exist, create the file
        if(!cust_file.exists())
        {
            //Object output stream
            ObjectOutputStream out;
            try {
                //cust_file.delete();
                out = new ObjectOutputStream(new FileOutputStream(cust_file, true));
                //new File(getFilesDir(),"")+File.separator+filename)
                // Create three test customers and write them to the file
                out.writeObject(new Customer("Ralph Hapschatt", "Ralph@hotmail.com", Long.decode("0x7c86ffee")));
                out.writeObject(new Customer("Betty Monroe", "Betty@hotmail.com", Long.decode("0xb59441af")));
                out.writeObject(new Customer("Everett Scott", "Everett@hotmail.com", Long.decode("0xcd0f0e05")));

                out.flush();
                out.close();
            } catch (IOException e) {
                System.err.println("ERROR: Could not create customer file!");
            }
            //return;
        }

        loadCustomerFile();
    }

    public boolean loadCustomerFile()
    {
        return loadCustomerFile(cust_file);
    }

    public boolean loadCustomerFile(File cf)
    {
        // If the customer file does exist, read the file
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(cf));

            // Load in customers
            Customer customer_in_file;
            while((customer_in_file = (Customer) in.readObject()) != null) {
                customer_list.put(customer_in_file.getHexID(), customer_in_file);
                // DEBUG INFO ------------------------
                System.out.println("Customer: " + customer_in_file.getCustomerName() + ", " + customer_in_file.getEmailAddress());
                // END DEBUG -------------------------
            }

            in.close();
        }catch(FileNotFoundException e)
        {
            System.err.println("ERROR: Could not find customer file!");
            return false;
        } catch(StreamCorruptedException e)
        {
            e.printStackTrace();
        }catch(IOException e) {
            // This occurs if we have run out of data to read
            // Technically a valid state.
        }catch(ClassNotFoundException e) {
            System.err.println("ERROR: Could not find customer class!");
            return false;
        }
        return true;
    }

    public Customer getCustomer(Long cHexId)
    {
        return customer_list.get(cHexId);
    }

    public Customer getCustomer(String email)
    {
        Iterator it = customer_list.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry this_customer = (Map.Entry) it.next();
            if(customer_list.get(this_customer.getKey()).getEmailAddress().contentEquals(email))
                return customer_list.get(this_customer.getKey());
        }

        // No customer exists with that email
        return null;
    }

    boolean loadItemList(String itemfile)
    {
        // Loads item data to program on startup
        return true;
    }

    public boolean addCustomer(String QRcode, String name, String email)
    {
        // Check if someone has same email.
        Iterator it = customer_list.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry this_customer = (Map.Entry)it.next();
            if(customer_list.get(this_customer.getKey()).getEmailAddress().contentEquals(email))
                return false;
        }
        customer_list.put(Long.decode(QRcode), new Customer(name, email, Long.decode(QRcode)));
        if(!updateCustomerData())System.err.println("WARNING: Customer File could not be updated.");
        debugCustomerFile();
        return true;
    }

    public boolean editCustomerInfo(String QRcode, String name, String email)
    {
        Customer this_customer = customer_list.get(Long.decode(QRcode));
        if(this_customer != null) {
            this_customer.editData(name, email);
            customer_list.put(Long.decode(QRcode), this_customer); // Finish updating customer
            if(!updateCustomerData())System.err.println("WARNING: Customer File could not be updated.");
            return true;
        }


        return false;
    }

    boolean updateCustomerData()
    {
        return updateCustomerData(cust_file);
    }

    boolean updateCustomerData(File cf)
    {
        // Updates customerfile with new information
        //Object output stream
        ObjectOutputStream out;
        try {

            // Clean customer file
            if(cf.exists())cf.delete();
            out = new ObjectOutputStream(new FileOutputStream(cf, true));

            // Iterate through hashtable and update file.
            Iterator it = customer_list.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry this_customer = (Map.Entry)it.next();
                out.writeObject(customer_list.get(this_customer.getKey()));
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            System.err.println("ERROR: Could not create customer file!");
            return false;
        }

        return true;
    }

    public void debugCustomerFile()
    {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(cust_file));

            // Load in customers
            System.out.println(cust_file.getName());
            Customer customer_in_file;
            while((customer_in_file = (Customer) in.readObject()) != null)
                System.out.println("Customer: " + customer_in_file.getCustomerName() + ", " + customer_in_file.getEmailAddress());

            in.close();
        }catch(FileNotFoundException e)
        {
            System.err.println("ERROR: Could not find customer file!");
        } catch(StreamCorruptedException e)
        {
            e.printStackTrace();
        }catch(IOException e) {
            // This occurs if we have run out of data to read
            // Technically a valid state.
        }catch(ClassNotFoundException e) {
            System.err.println("ERROR: Could not find customer class!");
        }
    }

    public int doTransaction(Purchase cp, String cc_card, ActivityUtility au, int start_state)
    {
        /* This is the first part of the transaction */
        //ActivityUtility au = new ActivityUtility(app);

        if(start_state <= 0) {
            // Does customer have VIP benefits
            if (current_customer.getVipInfo().getStatus())
                cp.applyDiscount(cp.getUnmodifiedTotal()*discount_rate);

            // Does customer have credit?
            if (current_customer.getCredInfo().isValid()) {
                // Calculate credit due
                if (cp.getModifiedTotal() < current_customer.getCredInfo().getCredit())
                    cp.applyCredit(cp.getModifiedTotal());
                else
                    cp.applyCredit(current_customer.getCredInfo().getCredit());
            }

            // Skip swiping cards and processing payments if total = 0
            if(cp.getModifiedTotal() == 0)start_state = 3;
        }

        if(start_state <= 1) {
            // Read credit card
            au.emulateRunningActivity("Credit Card", "Waiting for customer to swipe credit card...", 2000);

            if (cc_card.contentEquals("ERR")) {
                System.err.println("ERROR: Could not read credit card");
                return -1;
            }

            au.emulateRunningActivity("Credit Card", "Credit card scanned successfully", 2000);
        }

        // Start state 2 or higher
        if(start_state <= 2) {
            Scanner cc_parser = new Scanner(cc_card);
            cc_parser.useDelimiter("#");
            String [] parsed_data = new String[5];

            for(int i = 0; cc_parser.hasNext(); i++)parsed_data[i] = cc_parser.next();

            au.emulateRunningActivity("Payment Processor", "Processing payment...", 3000);

            try {
                if (!PaymentService.processPayment(parsed_data[0], parsed_data[1], parsed_data[2], new SimpleDateFormat("MMddyyyy").parse(parsed_data[3]), parsed_data[4], cp.getModifiedTotal())) {
                    System.err.println("ERROR: Payment Processor refused transaction");
                    return -2;
                }
            } catch (ParseException e)
            {
                System.err.println("ERROR: Parse Exception thrown");
                return -2;
            }
            au.emulateRunningActivity("Payment Processor", "Payment Processed successfully", 2000);
        }

        // Start state 3 or higher
        // Use credit if payment was successful
        current_customer.getCredInfo().useCredit(cp.getItem_credit());

            // Send email if they qualify for benefits
        if(cp.getModifiedTotal() >= credit_gain)
        {
            current_customer.getCredInfo().addCredit(credit_earn);
            if(!EmailService.sendEMail(current_customer.getEmailAddress(), "You earned $"+credit_earn+" Credit!", "You can earn another $"+credit_earn+" in credits every time you spend $"+credit_gain+".\nThank you for shopping at TCCart."))
                System.err.println("ERROR: A problem occurred sending the email.");

        }

        if(cp.getModifiedTotal() >= vip_gain && current_customer.getVipInfo().renew())
        {
            if(!EmailService.sendEMail(current_customer.getEmailAddress(), "You Qualify for VIP Benefits", "Next year discount rate:"+discount_rate+"\nThank you for shopping at TCCart."))
                System.err.println("ERROR: A problem occurred sending the email.");
        }

        if(!EmailService.sendEMail(current_customer.getEmailAddress(), "Thank you for your purchase", "Your total is $"+cp.getModifiedTotal()+".\nThank you for shopping at TCCart."))
            System.err.println("ERROR: A problem occurred sending the email.");

        // Save the purchase to customer history
        current_customer.savePurchase(cp);

        customer_list.put(current_customer.getHexID(), current_customer);
        updateCustomerData();
        au.emulateRunningActivity("Customer Transaction", "Transaction Complete", 2000);
        return 1;
    }

    public String generate8DigitHexString()
    {
        // Generate random seeded values
        Random r = new Random();

        // Return new hex string
        return ("0x"+String.valueOf(Integer.toHexString(r.nextInt())));
    }

    public void setCurrentCustomer(Customer c)
    {
        current_customer = c;
    }

    public Customer getCurrentCustomer()
    {
        return current_customer;
    }

    public void clearCurrentCustomer()
    {
        current_customer = null;
    }
}
