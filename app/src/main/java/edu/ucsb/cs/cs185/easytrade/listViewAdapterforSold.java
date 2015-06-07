package edu.ucsb.cs.cs185.easytrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by wengzixia on 6/3/15.
 */
public class listViewAdapterforSold extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public listViewAdapterforSold(listViewSoldItems fragment, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = fragment;
        this.data = data;
        inflater = (LayoutInflater) fragment
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(data[position]);
        return vi;
    }
}