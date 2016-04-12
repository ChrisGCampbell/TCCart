package edu.gatech.seclass.tccart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

import edu.gatech.seclass.services.PrintingService;

public class AddCustomer extends AppCompatActivity {

    // TextView fields
    TextView hex_field;
    TextView name_field;
    TextView email_field;

    // Dialog
    AlertDialog succ_dialog;
    AlertDialog err_dialog;
    AlertDialog printer_err_dialog;

    // Button
    Button back;
    Button submit;

    Scanner name_scanner;

    // Holds first name and last name
    String[] name = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        final TCCart_System tcc_handle = (TCCart_System)getApplicationContext();

        back = (Button) findViewById(R.id.buttonBack);
        submit = (Button) findViewById(R.id.buttonSubmit);

        // Attach them to the resources in the app
        hex_field = (TextView) findViewById(R.id.editTextHexAmt);
        name_field = (TextView) findViewById(R.id.editTextName);
        email_field = (TextView) findViewById(R.id.editTextEmail);

        // Disables hex field for editing
        hex_field.setFocusable(false);
        hex_field.setEnabled(false);

        // Error dialog
        err_dialog = new AlertDialog.Builder(AddCustomer.this).create();
        err_dialog.setTitle("Error");
        err_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // Success dialog
        succ_dialog = new AlertDialog.Builder(AddCustomer.this).create();
        succ_dialog.setCancelable(false);
        succ_dialog.setTitle("New Customer");
        succ_dialog.setMessage("The customer was added successfully.");

        // Printer fail dialog
        printer_err_dialog = new AlertDialog.Builder(AddCustomer.this).create();
        printer_err_dialog.setCancelable(false);
        printer_err_dialog.setTitle("Card Printer");
        printer_err_dialog.setMessage("Failed to auto-print Customer Card.\nTry Again?");
        printer_err_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        succ_dialog.show();

                        // Leave Gracefully after 2 seconds
                        ActivityUtility au = new ActivityUtility(AddCustomer.this);
                        au.getHandler().postDelayed(au.finishActivity, au.getCurrentMillisecondTime());
                    }
                });
        printer_err_dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityUtility au = new ActivityUtility(AddCustomer.this);
                        if (!PrintingService.printCard(name[0], name[1], hex_field.getText().toString())) {
                            // This will go into a dialog loop until the user either successfully prints the card or cancels
                            Random r = new Random();
                            au.emulateRunningActivity("Card Printer", "Printing Customer Card...", r.nextInt(3000) + 1, printer_err_dialog);
                            return;
                        }

                        au.emulateRunningActivity("Card Printer", "Printing Customer Card...", 3000);
                        au.emulateRunningActivity("Card Printer", "Successfully printed card.", 2000);
                        au.getHandler().postDelayed(au.finishActivity, au.getCurrentMillisecondTime());
                    }
                });



        // I'm going to replace this with the appropriate function from the utility library
        hex_field.setText(tcc_handle.generate8DigitHexString());

        assert back != null;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility au = new ActivityUtility(AddCustomer.this);
                name_scanner = new Scanner(name_field.getText().toString());
                name_scanner.useDelimiter(" ");

                for (int i = 0; i < name.length; i++) {
                    if (!name_scanner.hasNext() || (name[i] = name_scanner.next()).toString().isEmpty()) {
                        err_dialog.setMessage("A name is required to have at least 1 or more characters and must include a first and last name");
                        err_dialog.show();
                        return;
                    }
                }

                if (email_field.getText().toString().isEmpty()) {
                    err_dialog.setMessage("The email address set is required to have at least 1 or more characters");
                    err_dialog.show();
                    return;
                }

                if (!tcc_handle.addCustomer(hex_field.getText().toString(), name_field.getText().toString(), email_field.getText().toString())) {
                    err_dialog.setMessage("This email address [" + email_field.getText() + "] already exists, please enter another.");
                    err_dialog.show();
                    return;
                }

                au.emulateRunningActivity("New Customer", "The customer was added successfully.", 2000);

                if (!PrintingService.printCard(name[0], name[1], hex_field.getText().toString())) {
                    // This will go into a dialog loop until the user either successfully prints the card or cancels
                    Random r = new Random();
                    au.emulateRunningActivity("Card Printer", "Printing Customer Card...", r.nextInt(3000)+1, printer_err_dialog);
                    return;
                }

                au.emulateRunningActivity("Card Printer", "Printing Customer Card...", 3000);
                au.emulateRunningActivity("Card Printer", "Successfully printed card.", 2000);
                au.getHandler().postDelayed(au.finishActivity, au.getCurrentMillisecondTime());
            }

        });
    }

}
