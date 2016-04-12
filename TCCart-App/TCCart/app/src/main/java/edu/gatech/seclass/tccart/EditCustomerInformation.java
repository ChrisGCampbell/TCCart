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

import java.util.Scanner;

import edu.gatech.seclass.services.PrintingService;

public class EditCustomerInformation extends AppCompatActivity {

    // TextView fields
    TextView hex_field;
    TextView name_field;
    TextView email_field;

    // Dialog
    AlertDialog succ_dialog;
    AlertDialog err_dialog;

    // scanner for name
    Scanner name_scanner;

    // Holds first name and last name
    String[] name = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_information);

        final TCCart_System tcc_handle = (TCCart_System)getApplicationContext();

        Button back = (Button) findViewById(R.id.buttonBack);
        Button submit = (Button) findViewById(R.id.buttonSubmit);

        // Attach them to the resources in the app
        hex_field = (TextView) findViewById(R.id.editTextHexAmt);
        name_field = (TextView) findViewById(R.id.editTextName);
        email_field = (TextView) findViewById(R.id.editTextEmail);

        // Error dialog
        err_dialog = new AlertDialog.Builder(EditCustomerInformation.this).create();
        err_dialog.setTitle("Error");
        err_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // Success dialog
        succ_dialog = new AlertDialog.Builder(EditCustomerInformation.this).create();
        succ_dialog.setCancelable(false);
        succ_dialog.setTitle("Current Customer");
        succ_dialog.setMessage("The customer's information was edited successfully.");

        // Disables hex field for editing
        hex_field.setFocusable(false);
        hex_field.setEnabled(false);

        // Existing customer information
        name_field.setText(tcc_handle.getCurrentCustomer().getCustomerName());
        email_field.setText(tcc_handle.getCurrentCustomer().getEmailAddress());
        hex_field.setText("0x"+Long.toHexString(tcc_handle.getCurrentCustomer().getHexID()));

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
                name_scanner = new Scanner(name_field.getText().toString());
                name_scanner.useDelimiter(" ");

                for (int i = 0; i < name.length; i++) {
                    if (!name_scanner.hasNext() || (name[i] = name_scanner.next()).toString().isEmpty()) {
                        err_dialog.setMessage("A name is required to have at least 1 or more characters and must include a first and last name");
                        err_dialog.show();
                        return;
                    }
                }

                if(email_field.getText().toString().isEmpty())
                {
                    err_dialog.setMessage("The email address set is required to have at least 1 or more characters");
                    err_dialog.show();
                    return;
                }

                tcc_handle.editCustomerInfo(hex_field.getText().toString(), name_field.getText().toString(), email_field.getText().toString());

                succ_dialog.show();

                // Leave Gracefully
                ActivityUtility au = new ActivityUtility(EditCustomerInformation.this);
                au.getHandler().postDelayed(au.finishActivity, 2000);
            }

        });
    }
}
