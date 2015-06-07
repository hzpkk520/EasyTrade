package edu.ucsb.cs.cs185.easytrade;

/**
 * Created by wengzixia on 6/5/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by wengzixia on 5/30/15.
 */
public class confirmationActivity extends ActionBarActivity {

    private Button confirmButton;
    private int itemID;
    private TextView itemTitle;
    private TextView itemPrice;
    private TextView itemPrice2;
    private TextView seller;
    private TextView itemCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_main);


        itemTitle = (TextView)findViewById(R.id.itemTitle);
        itemPrice = (TextView)findViewById(R.id.itemPrice);
        itemPrice2 = (TextView)findViewById(R.id.itemPrice2);
        seller = (TextView)findViewById(R.id.seller);
        itemCondition = (TextView)findViewById(R.id.itemCondition);
        confirmButton = (Button)findViewById(R.id.confirmButton);


        Intent intent = getIntent();
        itemID = intent.getIntExtra("id",0);
        String str1 = intent.getStringExtra("title");
        itemTitle.setText(str1);
        String str2 = intent.getStringExtra("price");
        itemPrice.setText(str2);
        itemPrice2.setText(str2);
        String str3 = intent.getStringExtra("sellerName");
        seller.setText(str3);
        String str4 = intent.getStringExtra("itemCondition");
        itemCondition.setText(str4);


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openSuccessActivity(View view) {
        // Do something in response to button
        String userName = seller.getText().toString();
        User itemOwner =  MainActivity.EasyTradeDataBase.get(userName);
        Item thisSellingItem = itemOwner.getItemPost(itemOwner.getPostedItems().size()-1);
        Log.d("Debug","the confirmed Item Title is : "+thisSellingItem.getItemTitle());
        thisSellingItem.setSold(true);
        MainActivity.CurrentUser.addItemBought(thisSellingItem);
        Log.d("Debug","THE OWNER OF THIS selling item is :"+thisSellingItem.getOwner());
        Log.d("Debug","The current number of posted item before delete is: ----- "+itemOwner.getPostedItems().size());
        boolean check = itemOwner.deleteItemPost(thisSellingItem);
        if (MainActivity.GridFragment.mAdapter != null)
            MainActivity.GridFragment.mAdapter.notifyDataSetChanged();
        Log.d("Debug","Is this selling item successfully deleted from the database? ----- "+check);
        Log.d("Debug","The current number of posted item after delete is: ----- "+itemOwner.getPostedItems().size());


        //Write Database to disk
        MainActivity.EasyTradeDataBase.sortData();
        MainActivity.EasyTradeDataBase.setITEMID_LOWER_BOUND(MainActivity.EasyTradeDataBase.getITEMID_LOWER_BOUND() + 1);
        try {
            File outFile = new File(Environment.getExternalStorageDirectory(), "EasyTradeDataBase.ser");
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(MainActivity.EasyTradeDataBase);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, tradeSuccess.class);
        intent.putExtra("sellerName", seller.getText().toString());
        this.startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();


    }

}