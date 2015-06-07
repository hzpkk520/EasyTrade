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
    private static final int SELECT_PICTURE =0;
    private Bitmap anImage;
    EditText title ;
    EditText title2;
    EditText title3 ;

     AutoCompleteTextView title5;
    EditText title6;
    private String selectedImagePath;
    public static Item currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.substitude);
        //     Toast.makeText(this, "newActivity", Toast.LENGTH_SHORT).show();
        //  super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity1_main);
        Intent intent = getIntent();


     //   String value = intent.getStringExtra("path");

        //  Drawable myDrawable = getResources().getDrawable(R.drawable.ucsbmap);
       // anImage = BitmapFactory.decodeFile(value);


     /*    customImageView = (CustomZoomView) findViewById(R.id.customZoomView);


        Bitmap anImage      = BitmapFactory.decodeFile(value);
        customImageView.setBitmap(anImage);*/

         title = (EditText) findViewById(R.id.edit_title);
         title2 = (EditText) findViewById(R.id.sellerPhone);
         title3 = (EditText) findViewById(R.id.editText3);

       title5 = (AutoCompleteTextView) findViewById(R.id.sellerAddress1);
        title6=(EditText) findViewById(R.id.category1);
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
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, category);

        title5.setAdapter(adapter);

    }
    public Item getCurrentItem (){
  return currentItem;
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
        public void next_step(View view) {

             //   float x = Integer.parseInt(title4.getText().toString().split(",")[0]);
             //   float y = Integer.parseInt(title4.getText().toString().split(",")[1]);

                if (title6.getText().toString().length()==0||title.getText().toString().length() == 0 || title2.getText().toString().length() == 0 || title3.getText().toString().length() == 0  || title5.getText().toString().length() == 0) {// || title.getText().toString() == "ie. chair"){

                    final CharSequence[] items = {"OK"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity.this);
                    builder.setTitle("You Must Fill All Required Areas");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (items[item].equals("OK")) {
                                dialog.dismiss();
                                //      Intent intent= new Intent(SellingActivity2.this,MainActivity.class);
                                //  intent.putExtra("path", image.getAbsolutePath());
                                //   SellingActivity2.this.startActivity(intent);
                            }
                        }
                    });
                    builder.show();
                }

            else {
                    ArrayList<Item>  post = new ArrayList<Item>();
                    User   user_new = MainActivity.CurrentUser;

                    String name =user_new.getUsername();
                    String itemtitle= title.getText().toString();
                    String itemprice ="$"+title2.getText().toString();
                    String itemcondition=title3.getText().toString();
                    String itemcategory=title5.getText().toString();
                    String itemdescription=title6.getText().toString();
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH)+1;

                    currentItem = new Item(MainActivity.EasyTradeDataBase.getITEMID_LOWER_BOUND()+1,name,false,itemtitle,itemcategory,itemprice,itemcondition,
                            itemdescription,
                            String.valueOf(day),String.valueOf(month),String.valueOf(year),new ArrayList<File>());

                    //  Editable editable = "";
                    //  if(title.getText()=="")
                    Toast.makeText(this, "aa" + title.getText().toString().length() + "aa", Toast.LENGTH_LONG).show();
                    ///////////////////get text here and save to database
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

        //noinspection SimplifiableIfStatement
        //  if (id == R.id.action_settings) {
        //        return true;
        //    }
        //    if(id==R.id.home){
        //      Intent intent= new Intent(this,MainActivity.class);
        // intent.putExtra("path", image.getAbsolutePath());
        //       this.startActivity(intent);
        //       return true;
        //        this.finish();
        //          return true;
        //     }
     //   if(id==R.id.select){

            // Bitmap anImage      = BitmapFactory.decodeFile(selectedImagePath);
            //  customImageView.setBitmap(anImage);

            //   return true;
       // }
        //      if(id==R.id.back){
        //  Toast.makeText(this, "back clicked", Toast.LENGTH_SHORT).show();
        //        Intent intent= new Intent(this,MainActivity.class);
        // intent.putExtra("path", image.getAbsolutePath());
        //      this.startActivity(intent);
        //   return true;
        // }

        return super.onOptionsItemSelected(item);
    }
   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Multitouch img1 = new Multitouch(this);
                //img1.setImageURI(data.getData());
                //setContentView(img1);
                anImage      = BitmapFactory.decodeFile(selectedImagePath);
                img1.setBitmap(anImage);

              //  setContentView(img1);
            }
        }
    }*/

    /**
     * helper to retrieve the path of an image URI
     */
  /*  public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }*/


}
