package edu.gatech.seclass.tccart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class MainScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Initializes data on start up
        final TCCart_System tcc_handle = (TCCart_System)getApplicationContext();
        tcc_handle.Initialize(getApplicationContext());
        final ActivityUtility au = new ActivityUtility(MainScreen.this);

        Button newCustomer = (Button) findViewById(R.id.buttonNewCustomer);
        assert newCustomer != null;
        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainScreen.this, AddCustomer.class);
                startActivity(i);
            }
        });

        Button existingCustomer = (Button) findViewById(R.id.buttonExistingCustomer);
        assert existingCustomer != null;
        existingCustomer .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scans a customer card
                // Will add messages confirming successful/unsuccessful scan later.
                String QRcode = edu.gatech.seclass.services.QRCodeService.scanQRCode();
                if (!QRcode.contentEquals("ERR")) {
                    tcc_handle.setCurrentCustomer(tcc_handle.getCustomer(Long.decode("0x"+QRcode)));
                    Intent i = new Intent(MainScreen.this, ExistingCustomer.class);
                    startActivity(i);
                }else
                {
                    au.emulateRunningActivity("Card Scanner","Customer code could not be read.", 2000);
                }
            }
        });

        Button managerMenu = (Button) findViewById(R.id.buttonManagerMenu);
        assert managerMenu != null;
        managerMenu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcc_handle.clearCurrentCustomer();
                Intent i = new Intent(MainScreen.this, ManagerMenu.class);
                startActivity(i);
            }
        });

    }

}
