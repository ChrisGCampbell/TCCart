package edu.gatech.seclass.tccart;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by equinn on 3/23/16.
 * Customer class
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private String customerName = "UNDEFINED";
    private String emailAddress = "UNDEFINED";
    private long hexID;
    private VIP vip_status = new VIP();
    private Credit cred = new Credit();

    Map<Date, Vector<String>> purchaseHistoryDict = new LinkedHashMap<>();

    public Customer(String nameString, String emailString, long hexLong) {
        setCustomerName(nameString);
        setEmailAddress(emailString);
        setHexID(hexLong);
    }

    public void setCustomerName(String nameString) {
        customerName = nameString;
    }

    public void setEmailAddress(String emailString) {
        emailAddress = emailString;
    }

    public void setHexID(long hexLong) {
        hexID = hexLong;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public long getHexID() {
        return hexID;
    }

    public VIP getVipInfo(){return vip_status;}

    public Credit getCredInfo(){return cred;}

    public void editData(String cname, String email) {
        setCustomerName(cname);
        setEmailAddress(email);
    }

    public Vector<String> getPurchase(Date key) {
        return purchaseHistoryDict.get(key);
    }

    public Map<Date, Vector<String>> getPurchaseHistory(){
        return purchaseHistoryDict;
    }

    public void savePurchase(Purchase p)
    {
        purchaseHistoryDict.put(new Date(), p.printPurchase());
    }

}
