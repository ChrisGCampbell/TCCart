package edu.gatech.seclass.tccart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.text.DecimalFormat;

import edu.gatech.seclass.services.CreditCardService;

public class Order extends AppCompatActivity {
    // Prices of items
    private Item Coffee = new Item("Coffee", 2, 0);
    private Item Tea = new Item("Tea", 5, 0);

    // Decimal format
    DecimalFormat df = new DecimalFormat("0.00");

    //Coffee Amounts
    TextView textViewCurrentCoffee;
    TextView textViewCoffeeTotal;
    TextView textViewCoffeePrice;

    //Tea Amounts
    TextView textViewTeaCurrent;
    TextView textViewTeaTotal;
    TextView textViewTeaPrice;

    //need to connect Vip Amount
    TextView textViewVIPCurrentAmount;

    //Need to connect credit amount
    TextView textViewCreditAmount;

    //Total Amount After Credit and Vip
    TextView textViewTotalAmount;

    Button buttonAddOneCoffee;
    Button buttonSubOneCoffee;
    Button buttonAddOneTea;
    Button buttonSubOneTea;
    Button submit;
    Button back;

    AlertDialog trans_err_dialog;
    ActivityUtility au;

    TCCart_System tcc_handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Grab handle to TCCart system
        tcc_handle = (TCCart_System)getApplicationContext();
        au = new ActivityUtility(Order.this);

        // Attach buttons to references
        buttonAddOneCoffee = (Button) findViewById(R.id.buttonAddCoffee);
        buttonSubOneCoffee = (Button) findViewById(R.id.buttonSubCoffee);
        buttonAddOneTea = (Button)findViewById(R.id.buttonAddTea);
        buttonSubOneTea = (Button)findViewById(R.id.buttonSubTea);

        submit = (Button) findViewById(R.id.buttonSubmit);
        back = (Button) findViewById(R.id.buttonBack);

        //Setting Text Fields
        textViewCurrentCoffee = (TextView)findViewById(R.id.textViewCoffeeCurrent);
        textViewCoffeeTotal = (TextView)findViewById(R.id.textViewCoffeeTotal);
        textViewCoffeePrice = (TextView) findViewById(R.id.coffee_price_textView);
        textViewTeaCurrent = (TextView)findViewById(R.id.textViewTeaCurrent);
        textViewTeaTotal = (TextView)findViewById(R.id.textViewTeaTotal);
        textViewTeaPrice = (TextView)findViewById(R.id.tea_price_textView);
        textViewVIPCurrentAmount = (TextView) findViewById(R.id.textViewVIPCurrentAmount);
        textViewCreditAmount = (TextView)findViewById(R.id.textViewCreditAmount);
        textViewTotalAmount = (TextView)findViewById(R.id.textViewTotalAmount);

        textViewCoffeePrice.setText("$"+df.format(Coffee.getCost(1)));
        textViewTeaPrice.setText("$"+df.format(Tea.getCost(1)));

        // Remove negative prefix from zero
        df.setNegativePrefix("");

                        assert buttonAddOneCoffee != null;
                        buttonAddOneCoffee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Coffee.addQuantity(1);
                                textViewCurrentCoffee.setText(Integer.toString(Coffee.getQuantity()));
                                textViewCoffeeTotal.setText(df.format(Coffee.getCost()));
                                textViewVIPCurrentAmount.setText(df.format(getVipAmount(tcc_handle.getCurrentCustomer().getVipInfo().getStatus())));
                                textViewCreditAmount.setText(df.format(getCreditUsed(tcc_handle.getCurrentCustomer().getVipInfo().getStatus(),
                                        Coffee.getCost() + Tea.getCost(), tcc_handle.getCurrentCustomer().getCredInfo().getCredit())));
                                textViewTotalAmount.setText(df.format(getTotalCost()));
                            }
                        });

                        assert buttonSubOneCoffee != null;
                        buttonSubOneCoffee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Coffee.addQuantity(-1);
                                textViewCurrentCoffee.setText(Integer.toString(Coffee.getQuantity()));
                                textViewCoffeeTotal.setText(df.format(Coffee.getCost()));
                                textViewVIPCurrentAmount.setText(df.format(getVipAmount(tcc_handle.getCurrentCustomer().getVipInfo().getStatus())));
                                textViewCreditAmount.setText(df.format(getCreditUsed(tcc_handle.getCurrentCustomer().getVipInfo().getStatus(),
                                        Coffee.getCost() + Tea.getCost(), tcc_handle.getCurrentCustomer().getCredInfo().getCredit())));
                                textViewTotalAmount.setText(df.format(getTotalCost()));
                            }
                        });
                        assert buttonAddOneTea != null;
                        buttonAddOneTea.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Tea.addQuantity(1);
                                textViewTeaCurrent.setText(Integer.toString(Tea.getQuantity()));
                                textViewTeaTotal.setText(df.format(Tea.getCost()));
                                textViewVIPCurrentAmount.setText(df.format(getVipAmount(tcc_handle.getCurrentCustomer().getVipInfo().getStatus())));
                                textViewCreditAmount.setText(df.format(getCreditUsed(tcc_handle.getCurrentCustomer().getVipInfo().getStatus(),
                                        Coffee.getCost() + Tea.getCost(), tcc_handle.getCurrentCustomer().getCredInfo().getCredit())));
                                textViewTotalAmount.setText(df.format(getTotalCost()));
                            }
                        });

                        assert buttonSubOneTea != null;
                        buttonSubOneTea.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Tea.addQuantity(-1);
                                textViewTeaCurrent.setText(Integer.toString(Tea.getQuantity()));
                                textViewTeaTotal.setText(df.format(Tea.getCost()));
                                textViewVIPCurrentAmount.setText(df.format(getVipAmount(tcc_handle.getCurrentCustomer().getVipInfo().getStatus())));
                                textViewCreditAmount.setText(df.format(getCreditUsed(tcc_handle.getCurrentCustomer().getVipInfo().getStatus(),
                                        Coffee.getCost() + Tea.getCost(), tcc_handle.getCurrentCustomer().getCredInfo().getCredit())));
                                textViewTotalAmount.setText(df.format(getTotalCost()));
                            }
                        });

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if(Coffee.getQuantity() == 0 && Tea.getQuantity() == 0)
                                {
                                    au.emulateRunningActivity("Purchase Error", "No items selected for purchase.", 2000);
                                    return;
                                }

                                // Adding Order Function goes here
                                Purchase customer_purchase = new Purchase();
                                customer_purchase.addItem(Coffee);
                                customer_purchase.addItem(Tea);

                                validateTransaction(customer_purchase, CreditCardService.readCreditCard(), 0);
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

                    private double getVipAmount(boolean isVip) {
                        return ((isVip) ? ((Coffee.getCost() + Tea.getCost()) * TCCart_System.discount_rate) : 0);
                    }

                    private double getCreditUsed(boolean isVIP, double total, double credit_value) {
                        return ((total-getVipAmount(isVIP) < credit_value) ? total-getVipAmount(isVIP) : credit_value);
                    }

                    private double getTotalCost() {
                        return Coffee.getCost()
                                + Tea.getCost()
                                - Double.parseDouble(textViewCreditAmount.getText().toString())
                                - Double.parseDouble(textViewVIPCurrentAmount.getText().toString());
                    }

                    private void validateTransaction(final Purchase cp, final String cc_card, final int start_state) {

                        // Run Transaction
                        final int state = tcc_handle.doTransaction(cp, cc_card, au, start_state);

                        // Alert Dialogs
                        trans_err_dialog = new AlertDialog.Builder(Order.this).create();
                        trans_err_dialog.setCancelable(false);


                        switch (state) {
                            // Credit card was not read successfully
                            case -1:
                                trans_err_dialog.setTitle("Credit Card Reader");
                                trans_err_dialog.setMessage("Credit card could not be read.\nTry Again?");
                                trans_err_dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                validateTransaction(cp, CreditCardService.readCreditCard(), 1);
                                            }
                                        });
                                trans_err_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel Transaction",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                return;
                                            }
                                        });
                                au.emulateRunningActivity("...","...", 0, trans_err_dialog);
                                break;

                            // Payment processor failed
                            case -2:
                                trans_err_dialog.setTitle("Payment Processor");
                                trans_err_dialog.setMessage("Transaction was refused by payment processor.\nTry Again?");
                                trans_err_dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                validateTransaction(cp, cc_card, 2);
                                            }
                                        });
                                trans_err_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel Transaction",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                return;
                                            }
                                        });


                                au.emulateRunningActivity("...", "...", 0, trans_err_dialog);
                                break;

                            case 1:
                                // Finish Transaction if successful
                                au.getHandler().postDelayed(au.finishActivity, au.getCurrentMillisecondTime());
                                break;
                        }
                    }

                }
