package com.example.http2.counter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.http2.R;
import com.example.http2.counter.Counter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CounterAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Counter> countersList;

    public CounterAdapter(Context context, ArrayList<Counter> countersList) {
        this.context = context;
        this.countersList = countersList;
    }

    @Override
    public int getCount() {
        return countersList.size();
    }

    @Override
    public Object getItem(int position) {
        return countersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Counter> getcounters() {
        return this.countersList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = null;
        // inflate the layout for each list row
        if (convertView != null) {
            linearLayout = (LinearLayout) convertView;
            System.out.println("!!!!! convertView not null !!!!!" + position);
        }
        else {
            linearLayout = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.row_counter, parent, false);
            System.out.println("!!!!! convertView NULL " + position);

        }
        // get current item to be displayed
        final Counter currentcounter = (Counter) getItem(position);

        TextView txtCounterName = (TextView)
                linearLayout.findViewById(R.id.txtCounterName);

        TextView txtCounterCount = (TextView) linearLayout.findViewById(R.id.txtCounterCount);
        //TextView txtCounterUrl = (TextView)
                //linearLayout.findViewById(R.id.txtCounterUrl);
        //ImageView img = (ImageView) linearLayout.findViewById(R.id.imageView);

        Button button = linearLayout.findViewById(R.id.button);
        txtCounterName.setText(currentcounter.getTitle());
        txtCounterCount.setText(currentcounter.getCountString());

        final TextView finalCounterCount = txtCounterCount;
        //LinearLayout finalCounterRow = (LinearLayout)convertView;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("!!!!CLICK ON BUTTON + !!!!!");
                //HERE GET ON http://192.168.1.20:8080/counter/web/increment?id=currentcounter.getId()
                //notifyDataSetChanged();
                new CounterTaskIncrement(currentcounter).execute("increment"); // decrement, reset
                int newval = currentcounter.getCount();
                currentcounter.setCount(newval + 1); // -1, 0
                finalCounterCount.setText(currentcounter.getCountString());
                notifyDataSetChanged();
            }
        });


        //sets the text for item name and item description from the current item object
        // txtcounterId.setText(currentcounter.getId());

        //txtcounterUrl.setText(currentcounter.getUrl());
        //Picasso.with(context).load(currentcounter.thumbnailUrl).into(img);
        // retourne la ligne
        return linearLayout;
    }
}
