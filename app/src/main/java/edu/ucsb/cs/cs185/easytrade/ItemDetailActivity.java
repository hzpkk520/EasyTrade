package edu.ucsb.cs.cs185.easytrade;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class ItemDetailActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    public static int FROM_MAINACTIVITY;
    //0: from other activity; 1: from main activity

    //new variables added

    //variables
    private TextView itemTitle;
    private ImageView sellerImage;
    private TextView sellerId;
    private TextView postTime;
    private TextView description;
    private TextView designer;
    private TextView size;
    private TextView color;
    private TextView condition;
    private Button buyButton;
    private TextView price;
    private TextView category;
    private ArrayList<Integer> drawableArray;
    private ArrayList<File> fileArray;
    private int arraySize;
    private int fileArraySize;
    private Item item;
    private String sellerName;
    private String titleName;
    private String postedTime;
    private String itemDescription;
    private String itemCondition;
    private String itemPrice;
    private String itemCategory;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        Log.d("Debug", "pass in user name is " + userName);
        Log.d("Debug", "Current databasesize is " + MainActivity.EasyTradeDataBase.size());

        User user = MainActivity.EasyTradeDataBase.get(userName);
        Log.d("Debug", "current user name is " + user.getUsername());
        item = user.getItemPost(user.getPostedItems().size()-1);
        itemID = item.getItemID();
        drawableArray = item.getDrawablesForSampleUsers();
        arraySize = drawableArray.size();
        fileArray = item.getItemImages();
        fileArraySize = fileArray.size();
        Log.d("Debug", "Sample Drawable size is  " + arraySize);
        sellerName = user.getUsername();
        titleName = item.getItemTitle();
        postedTime = item.getDayOfPost()+"/"+item.getMonthOfPost()+"/"+item.getYearOfPost();
        itemDescription = item.getDescription();
        itemCondition = item.getCondition();
        itemPrice = item.getPrice().substring(1);
        itemCategory = item.getCategory();


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        itemTitle = (TextView) findViewById(R.id.itemTitle);
        price = (TextView) findViewById(R.id.price);
        sellerImage = (ImageView) findViewById(R.id.sellerImage);
        sellerId = (TextView) findViewById(R.id.sellerId);
        postTime = (TextView) findViewById(R.id.postTime);
        description = (TextView) findViewById(R.id.description);
        category = (TextView)findViewById(R.id.categoryDescription);
//        designer = (TextView) findViewById(R.id.designerName);
//        size = (TextView) findViewById(R.id.sizeNumber);
//        color = (TextView) findViewById(R.id.colorName);
        condition = (TextView) findViewById(R.id.conditionDescription);
        buyButton = (Button) findViewById(R.id.buyButton);

        sellerId.setText(sellerName);
        itemTitle.setText(titleName);
        postTime.setText(postedTime);
        description.setText(itemDescription);
        condition.setText(itemCondition);
        double thePrice = Double.parseDouble(itemPrice);
        DecimalFormat decim = new DecimalFormat("0.00");
        price.setText("$"+decim.format(thePrice));
        category.setText(itemCategory);


//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


        //if not the sample users
        if ((sellerName.equals("JackBacon") ) || (sellerName .equals("ScottCesar")) || (sellerName .equals("JenniChou") ) || (sellerName .equals("KobeHollerer") ) || (sellerName .equals("CoryFeitelson") )) {

            HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

            for (int i = 0; i < arraySize; i++) {
                file_maps.put(" " + i, drawableArray.get(i));
            }
            for (String name : file_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", name);

                mDemoSlider.addSlider(textSliderView);
                Log.d("Debug", "Read in file path is  ------------------" );

            }
        }
        else{

            HashMap<String, File> file_maps = new HashMap<String, File>();

            for (int i = 0; i < fileArraySize; i++) {
                User theCurrentUserInDataBase = MainActivity.EasyTradeDataBase.get(MainActivity.CurrentUser.getUsername());
                file_maps.put(" " + i, theCurrentUserInDataBase.getPostedItems().get(theCurrentUserInDataBase.getPostedItems().size()-1).getImage(i));
            }
            for (String name : file_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(this);
//                Log.d("Debug", "Read in file path is " + MainActivity.CurrentUser.getPostedItems().get(0).getImage(0).getAbsolutePath());
                // initialize a SliderLayout
                textSliderView
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", "1");

                mDemoSlider.addSlider(textSliderView);
            }
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


    }

    public void openConfirmationActivity(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, confirmationActivity.class);
        intent.putExtra("id", itemID);
        intent.putExtra("title", itemTitle.getText().toString());
        intent.putExtra("price", price.getText().toString());
        intent.putExtra("sellerName", sellerId.getText().toString());
        intent.putExtra("itemCondition", condition.getText().toString());
        if (drawableArray.size() != 0) {
            intent.putExtra("itemPicture", drawableArray.get(0));
        }
        this.startActivity(intent);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}