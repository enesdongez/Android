package com.example.neekilir;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adabter_list3 extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<String> recordList;
    private ArrayList<String> recordList2;

    //private Activity activity;

    public adabter_list3(Activity activity, ArrayList<String> records, ArrayList<String> records2) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        recordList = records;
        recordList2 = records2;
        // this.activity = activity;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_detail_row2, null); // create layout from

        TextView ay = vi.findViewById(R.id.textView4);
        TextView urun = vi.findViewById(R.id.textView3);





        final String record = recordList.get(position);
        final String record2 = recordList2.get(position);


        ay.setText(record+"");
        urun.setText(record2+"");


        return vi;
    }

}