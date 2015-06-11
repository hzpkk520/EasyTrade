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
public class listViewAdapter extends BaseAdapter {

    MainActivity.PlaceholderFragment context;
    String[] data;
    private static LayoutInflater inflater = null;

    public listViewAdapter(MainActivity.PlaceholderFragment fragment, String[] data) {
        this.context = fragment;
        this.data = data;
        inflater = (LayoutInflater) fragment.getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(data[position]);
        return vi;
    }
}