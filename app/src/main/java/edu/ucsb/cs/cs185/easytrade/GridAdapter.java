package edu.ucsb.cs.cs185.easytrade;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hzpkk520 on 15/6/3.
 */
public class GridAdapter extends BaseAdapter  {
    private Context context;
    Item currentItem;
    ImageView myGridImage;
    TextView titleText;
    TextView distanceText;
    TextView priceText;
    private ArrayList<User> mGridUsers;


    public GridAdapter(Context context, ArrayList<User> mGridUsers) {
        this.context = context;
        this.mGridUsers = mGridUsers;
        Log.d("Debug","GridAdapter Constructor called");
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

//            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.single_grid, null);

            myGridImage = (ImageView) gridView.findViewById(R.id.gridImage);
            titleText = (TextView) gridView.findViewById(R.id.itemTitle);
            distanceText = (TextView) gridView.findViewById(R.id.itemDistance);
            priceText = (TextView) gridView.findViewById(R.id.itemPrice);

            User currentUser = mGridUsers.get(position);
            Log.d("Debug","the size of the user post item is "+ currentUser.getPostedItems().size());

            currentItem = currentUser.getItemPost(0);
            Log.d("Debug","Current Item ID: "+ currentItem.getItemID());


            String tmpTitleText = currentItem.getItemTitle();
            titleText.setText(tmpTitleText);
            distanceText.setText(String.valueOf(Math.round(currentUser.getDistToOrigin()))+"m");
            priceText.setText(currentItem.getPrice());


        } else {
            gridView = (View) convertView;
            currentItem = mGridUsers.get(position).getItemPost(0);
        }


        switch (currentItem.getItemID()){
            case 999900:
                Picasso.with(parent.getContext()).load(R.drawable.bookshelf1).
                        resize(150,200).centerCrop().into(myGridImage);
                return gridView;
            case 999901:
                Picasso.with(parent.getContext()).load(R.drawable.comfortablemattress1).
                        resize(150,200).centerCrop().into(myGridImage);
                return gridView;
            case 999902:
                Picasso.with(parent.getContext()).load(R.drawable.bikinis1).
                        resize(150,200).centerCrop().into(myGridImage);
                return gridView;
            case 999903:
                Picasso.with(parent.getContext()).load(R.drawable.randomclothes1).
                        resize(150,200).centerCrop().into(myGridImage);
                return gridView;
            case 999904:
                Picasso.with(parent.getContext()).load(R.drawable.minifridge1).
                        resize(150,200).centerCrop().into(myGridImage);
                return gridView;
        }

        Picasso.with(parent.getContext()).load(currentItem.getImage(0)).
                resize(150,200).centerCrop().into(myGridImage);
        return gridView;
    }

    @Override
    public int getCount() {
        return mGridUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
