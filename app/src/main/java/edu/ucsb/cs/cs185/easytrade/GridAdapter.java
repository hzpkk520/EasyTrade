package edu.ucsb.cs.cs185.easytrade;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hzpkk520 on 15/5/30.
 */
public class GridAdapter extends BaseAdapter {
    private static final String TAG = "Debug";
    private Context mContext;

    static class ViewHolder {
        DynamicHeightImageView myGridImage;
        TextView titleText;
        TextView distanceText;
        TextView priceText;
    }

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private Database adapterDatabase;
//    private final ArrayList<Integer> mBackgroundColors;

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public GridAdapter(Context context, Database theDatabase) {
//        super(context, imageViewResourceId);
        adapterDatabase = theDatabase;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();
    }


    public void add(User user){
        adapterDatabase.add(user);
    }

    public int getCount() {
        return MainActivity.EasyTradeDataBase.size();
    }

    public long getItemId(int position) {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.single_grid, parent, false);
            vh = new ViewHolder();
            vh.myGridImage = (DynamicHeightImageView) convertView.findViewById(R.id.gridImage);
            vh.titleText = (TextView) convertView.findViewById(R.id.title);
            vh.distanceText = (TextView) convertView.findViewById(R.id.itemDistance);
            vh.priceText = (TextView) convertView.findViewById(R.id.itemPrice);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);


        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);


        Item currentItem = MainActivity.EasyTradeDataBase.get(position).getItemPost(0);
        vh.myGridImage.setHeightRatio(positionHeight);
        Picasso.with(convertView.getContext()).load(currentItem.getImage(0))
                .resize(convertView.getWidth(),convertView.getHeight()-38).centerCrop().into(vh.myGridImage);
//        vh.myGridImage.setImageBitmap();
        vh.titleText.setText(currentItem.getItemTitle());
        vh.distanceText.setText(String.valueOf(Math.round(MainActivity.EasyTradeDataBase.get(position).getDistToOrigin())));
        vh.priceText.setText(currentItem.getPrice());

        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }
}
