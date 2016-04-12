package edu.gatech.seclass.tccart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TransactionHistory extends Activity
{
    ExpandableListAdapter list_adapter;
    ExpandableListView exp_list_view;
    List<String> list_data_header;
    HashMap<String, List<String>> list_data_child;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        // List View
        exp_list_view = (ExpandableListView) findViewById(R.id.ExListView);

        // Populates list data
        populateListView();

        list_adapter = new ExpandableListAdapter(this, list_data_header, list_data_child);

        exp_list_view.setAdapter(list_adapter);

        // Experimental scroll bar
        exp_list_view.setTranscriptMode(ExpandableListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        btn_back = (Button) findViewById(R.id.buttonBack);

        // Set the back button up
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        }

        private void populateListView()
        {
            final TCCart_System tcc_handle = (TCCart_System)getApplicationContext();

            list_data_header = new ArrayList<>();
            list_data_child = new HashMap<String, List<String>>();
            int index = 0;

            Iterator it = tcc_handle.getCurrentCustomer().getPurchaseHistory().entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry purchase_entry = (Map.Entry)it.next();

                // Get String Vector
                final Vector<String> str = tcc_handle.getCurrentCustomer().getPurchaseHistory().get(purchase_entry.getKey());

                // Add date as list header
                list_data_header.add(str.firstElement());

                // A mental note: The str gets the exact object in the purchase history
                // It is NOT A COPY, ONLY BAD THINGS CAN HAPPEN IF YOU REMOVE/ALTER THEM.
                // Lesson Learned, nasty bug fixed [NOW FINAL AS WELL].

                // Add the rest of the elements at the child
                list_data_child.put(list_data_header.get(index), str.subList(1, str.size()));
                index++;
            }

            // If customer has no purchases
            if(index == 0)
            {
                list_data_header.add("No purchases");
                list_data_child.put(list_data_header.get(0), new Vector<String>());
            }

        }

}
