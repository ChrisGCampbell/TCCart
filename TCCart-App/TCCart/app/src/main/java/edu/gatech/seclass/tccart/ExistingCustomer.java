package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExistingCustomer extends AppCompatActivity {
    TextView txtCustomerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_customer);

        final TCCart_System tcc_handle = (TCCart_System)getApplicationContext();

        txtCustomerName = (TextView) findViewById(R.id.text_cust_name);
        txtCustomerName.setText(tcc_handle.getCurrentCustomer().getCustomerName());

        Button makePurchase = (Button) findViewById(R.id.buttonMakePurchase);
        assert makePurchase != null;
        makePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExistingCustomer.this, Order.class);
                startActivity(i);
            }
        });

        Button purchaseHistory = (Button) findViewById(R.id.buttonPurchaseHistory);
        assert purchaseHistory != null;
        purchaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExistingCustomer.this, TransactionHistory.class);
                startActivity(i);
            }
        });


        Button back = (Button) findViewById(R.id.buttonBack);
        assert back != null;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button editCustomerInfo = (Button) findViewById(R.id.buttonEditCustomerInfo);
        assert editCustomerInfo  != null;
        editCustomerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExistingCustomer.this, EditCustomerInformation.class);
                startActivity(i);
            }
        });
    }

}
