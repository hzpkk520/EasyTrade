package edu.ucsb.cs.cs185.easytrade;

/**
 * Created by wengzixia on 6/5/15.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wengzixia on 5/31/15.
 */
public class tradeSuccess extends ActionBarActivity {

    private TextView seller;
    private TextView phoneNumber;
    private TextView Address;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_success);

        seller = (TextView)findViewById(R.id.sellerId);
        phoneNumber = (TextView)findViewById(R.id.phoneNumber);
        Address = (TextView)findViewById(R.id.address);
        backButton = (Button)findViewById(R.id.backButton);

        Intent intent = getIntent();
        String str = intent.getStringExtra("sellerName");
        Log.d("Debug", "user is "+ str);

        User user = MainActivity.EasyTradeDataBase.get(str);

        Log.d("Debug", "user is "+ user.getUsername());
        String address = user.getAddress();

        String phone = user.getPhoneNumber();
        seller.setText(str);
        phoneNumber.setText(phone);
        Address.setText(address);
    }

    public void openMainActivity(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}