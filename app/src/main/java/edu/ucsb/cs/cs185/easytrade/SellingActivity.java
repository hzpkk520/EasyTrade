package edu.ucsb.cs.cs185.easytrade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by wjy on 2015/5/31.
 */
public class SellingActivity extends ActionBarActivity {
    private static final int SELECT_PICTURE = 0;
    private Bitmap anImage;
    EditText title;
    EditText title2;
    EditText title3;

    AutoCompleteTextView title5;
    EditText title6;
    private String selectedImagePath;
    public static Item currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.substitude);

        Intent intent = getIntent();


        title = (EditText) findViewById(R.id.edit_title);
        title2 = (EditText) findViewById(R.id.sellerPhone);
        title3 = (EditText) findViewById(R.id.editText3);

        title5 = (AutoCompleteTextView) findViewById(R.id.sellerAddress1);
        title6 = (EditText) findViewById(R.id.category1);
        title2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        title3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        title5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        title6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        String[] category = getResources().getStringArray(R.array.category_array);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category);

        title5.setAdapter(adapter);

    }


    public Item getCurrentItem() {
        return currentItem;
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void next_step(View view) {



        if (title6.getText().toString().length() == 0 || title.getText().toString().length() == 0 || title2.getText().toString().length() == 0 || title3.getText().toString().length() == 0 || title5.getText().toString().length() == 0) {// || title.getText().toString() == "ie. chair"){

            final CharSequence[] items = {"OK"};
            AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity.this);
            builder.setTitle("You Must Fill All Required Areas");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("OK")) {
                        dialog.dismiss();

                    }
                }
            });
            builder.show();
        } else {
            ArrayList<Item> post = new ArrayList<Item>();
            User user_new = MainActivity.CurrentUser;

            String name = user_new.getUsername();
            String itemtitle = title.getText().toString();
            String itemprice = "$" + title2.getText().toString();
            String itemcondition = title3.getText().toString();
            String itemcategory = title5.getText().toString();
            String itemdescription = title6.getText().toString();
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;

            currentItem = new Item(MainActivity.EasyTradeDataBase.getITEMID_LOWER_BOUND() + 1, name, false, itemtitle, itemcategory, itemprice, itemcondition,
                    itemdescription,
                    String.valueOf(day), String.valueOf(month), String.valueOf(year), new ArrayList<File>());

            Intent intent = new Intent(SellingActivity.this, SellingActivity2.class);
            //  intent.putExtra("path", image.getAbsolutePath());
            SellingActivity.this.startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


}
