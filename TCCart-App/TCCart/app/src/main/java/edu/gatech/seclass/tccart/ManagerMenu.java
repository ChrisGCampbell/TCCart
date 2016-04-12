package edu.gatech.seclass.tccart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

import edu.gatech.seclass.services.PrintingService;

public class ManagerMenu extends AppCompatActivity {

    AlertDialog data_warn_dialog;
    AlertDialog printer_err_dialog;

    ActivityUtility au;
    TCCart_System tcc_handle;

    TextView customerText;
    TextView emailEditText;
    Button getEmail;
    Button btn_printCustomerCard;
    Button editCustomerInfo;
    Button resetData;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);

        au = new ActivityUtility(ManagerMenu.this);

        customerText = (TextView) findViewById(R.id.customerText);
        emailEditText = (TextView) findViewById(R.id.emailEditText);
        getEmail = (Button) findViewById(R.id.buttonGet);
        btn_printCustomerCard = (Button) findViewById(R.id.buttonPrintCustomerCard);
        editCustomerInfo= (Button) findViewById(R.id.buttonEditCustomerInfo);
        resetData = (Button) findViewById(R.id.buttonResetData);
        back = (Button) findViewById(R.id.buttonBack);

        tcc_handle = (TCCart_System)getApplicationContext();

        // Data Warning dialog
        data_warn_dialog = new AlertDialog.Builder(ManagerMenu.this).create();
        data_warn_dialog.setTitle("System Data");
        data_warn_dialog.setMessage("This action will reset the customer database.\nAre your really sure you wish to reset?");
        data_warn_dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Reset",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the data
                        tcc_handle.Initialize(getApplicationContext(), true);
                    }
                });
        data_warn_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        getEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer cust = tcc_handle.getCustomer(emailEditText.getText().toString());
                if(cust != null)
                {
                    tcc_handle.setCurrentCustomer(cust);
                    customerText.setText(tcc_handle.getCurrentCustomer().getCustomerName());
                }else
                {
                    au.emulateRunningActivity("Customer Retrieval", "No customer exists with that email address.", 2000);
                }
            }
        });

        assert btn_printCustomerCard != null;
        btn_printCustomerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tcc_handle.getCurrentCustomer() == null)
                {
                    au.emulateRunningActivity("Customer Required", "A customer must be selected for this operation.", 2000);
                    return;
                }

                // Split name into two parts for card printer
                String [] name = new String[2];
                Scanner name_scanner = new Scanner(customerText.getText().toString());
                name_scanner.useDelimiter(" ");

                for (int i = 0; i < 2; i++) {
                    if (!name_scanner.hasNext() || (name[i] = name_scanner.next()).toString().isEmpty()) {
                        // Should never occur
                        au.emulateRunningActivity("PARSING ERROR", "A string parsing error has occured.", 2000);
                        return;
                    }
                }

                // Call customer card printer
                printCustomerCard(name);
            }
        });


        assert editCustomerInfo  != null;
        editCustomerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tcc_handle.getCurrentCustomer() == null)
                {
                    au.emulateRunningActivity("Customer Required", "A customer must be selected for this operation.", 2000);
                    return;
                }

                Intent i = new Intent(ManagerMenu.this, EditCustomerInformation.class);
                startActivity(i);
            }
        });


        assert resetData != null;
        resetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_warn_dialog.show();
            }
        });


        assert back != null;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void printCustomerCard(final String [] name)
    {
        // Printer fail dialog
        printer_err_dialog = new AlertDialog.Builder(ManagerMenu.this).create();
        printer_err_dialog.setCancelable(false);
        printer_err_dialog.setTitle("Card Printer");
        printer_err_dialog.setMessage("Failed to print Customer Card.\nTry Again?");
        printer_err_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        printer_err_dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!PrintingService.printCard(name[0], name[1], "0x" + Long.toHexString(tcc_handle.getCurrentCustomer().getHexID()))) {
                            // This will go into a dialog loop until the user either successfully prints the card or cancels
                            Random r = new Random();
                            au.emulateRunningActivity("Card Printer", "Printing Customer Card...", r.nextInt(3000) + 1, printer_err_dialog);
                            return;
                        }

                        au.emulateRunningActivity("Card Printer", "Printing Customer Card...", 3000);
                        au.emulateRunningActivity("Card Printer", "Successfully printed card.", 2000);
                    }
                });

        if (!PrintingService.printCard(name[0], name[1], "0x" + Long.toHexString(tcc_handle.getCurrentCustomer().getHexID()))) {
            // This will go into a dialog loop until the user either successfully prints the card or cancels
            Random r = new Random();
            au.emulateRunningActivity("Card Printer", "Printing Customer Card...", r.nextInt(3000)+1, printer_err_dialog);
            return;
        }

        au.emulateRunningActivity("Card Printer", "Printing Customer Card...", 3000);
        au.emulateRunningActivity("Card Printer", "Successfully printed card.", 2000);
    }

}
