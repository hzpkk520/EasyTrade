package edu.ucsb.cs.cs185.easytrade;

/**
 * Created by wjy on 2015/5/31.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by wjy on 2015/5/31.
 */
public class SellingActivity2 extends ActionBarActivity {
    private static final int SELECT_PICTURE =0;
    private Bitmap anImage;
private int REQUEST_CAMERA =0;
    private int SELECT_FILE =1;
    private String selectedImagePath;
    private int next_free_imageView=0;
    private ArrayList<ImageView> ImageList  = new ArrayList<ImageView>();
    private ArrayList<ImageView> ButtonList = new ArrayList<ImageView>();
    private ArrayList<TextView> TextList =new ArrayList<TextView>();
    private ImageView ImageView1;
    private ImageView ImageView2 ;
    private ImageView ImageView3;
    private ImageView ImageView4;
    private ImageView  Button1;
    private ImageView  Button2;
    private ImageView  Button3;
    private ImageView  Button4;
    private TextView Text1;
    private TextView Text2;
    private TextView Text3;
    private TextView Text4;
    private TextView Text5;
    private File destination;
    private ArrayList<File> File_list =new ArrayList<File>();
 private boolean isTaken=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_activity2);

        Intent intent = getIntent();
        Button1=(ImageView) findViewById(R.id.delete_one);
        Button2=(ImageView) findViewById(R.id.delete_two);
        Button3=(ImageView) findViewById(R.id.delete_three);
        Button4=(ImageView) findViewById(R.id.delete_four);
        Button1.setVisibility(View.INVISIBLE);
        Button2.setVisibility(View.INVISIBLE);
        Button3.setVisibility(View.INVISIBLE);
        Button4.setVisibility(View.INVISIBLE);
        ButtonList.add(Button1);
        ButtonList.add(Button2);
        ButtonList.add(Button3);
        ButtonList.add(Button4);

        ImageView1 = (ImageView) findViewById(R.id.ImageView1);
        ImageView2 = (ImageView) findViewById(R.id.ImageView2);
          ImageView3 = (ImageView) findViewById(R.id.ImageView3);
         ImageView4 = (ImageView) findViewById(R.id.ImageView4);
        ImageList.add(ImageView1);
        ImageList.add(ImageView2);
        ImageList.add(ImageView3);
        ImageList.add(ImageView4);

        Text1= (TextView)findViewById(R.id.photo4);
        Text2= (TextView)findViewById(R.id.photo3);
        Text3= (TextView)findViewById(R.id.photo2);
        Text4= (TextView)findViewById(R.id.photo1);
        Text5= (TextView)findViewById(R.id.photo0);
        TextList.add(Text1);
        TextList.add(Text2);
        TextList.add(Text3);
        TextList.add(Text4);
        TextList.add(Text5);

        TextList.get(0).setVisibility(View.VISIBLE);
        TextList.get(1).setVisibility(View.INVISIBLE);
        TextList.get(2).setVisibility(View.INVISIBLE);
        TextList.get(3).setVisibility(View.INVISIBLE);
        TextList.get(4).setVisibility(View.INVISIBLE);



    }

    public void delete_one(View view){
        ImageView1.setVisibility(View.INVISIBLE);
        Button1.setVisibility(View.INVISIBLE);
        Text1.setVisibility(View.VISIBLE);
        Text2.setVisibility(View.INVISIBLE);
        Text3.setVisibility(View.INVISIBLE);
        Text4.setVisibility(View.INVISIBLE);
        Text5.setVisibility(View.INVISIBLE);
        File delete = File_list.get(0);

        SellingActivity.currentItem.deleteImage(delete);
        File_list.remove(0);
       if(isTaken==true){
        delete.delete();}

        next_free_imageView--;

    }
    public void delete_two(View view){
        ImageView2.setVisibility(View.INVISIBLE);
        Button2.setVisibility(View.INVISIBLE);
        Button1.setVisibility(View.VISIBLE);
        Text1.setVisibility(View.INVISIBLE);
        Text2.setVisibility(View.VISIBLE);
        Text3.setVisibility(View.INVISIBLE);
        Text4.setVisibility(View.INVISIBLE);
        Text5.setVisibility(View.INVISIBLE);
        File delete = File_list.get(1);

        SellingActivity.currentItem.deleteImage(delete);
        File_list.remove(1);
        if(isTaken==true){
        delete.delete();}

        next_free_imageView--;
    }
    public void delete_three(View view){
        ImageView3.setVisibility(View.INVISIBLE);
        Button3.setVisibility(View.INVISIBLE);
        Button2.setVisibility(View.VISIBLE);
        Text1.setVisibility(View.INVISIBLE);
        Text2.setVisibility(View.INVISIBLE);
        Text3.setVisibility(View.VISIBLE);
        Text4.setVisibility(View.INVISIBLE);
        Text5.setVisibility(View.INVISIBLE);
        File delete = File_list.get(2);

        SellingActivity.currentItem.deleteImage(delete);
        File_list.remove(2);
        if(isTaken==true){
        delete.delete();}


        next_free_imageView--;
    }
    public void delete_four(View view){
        ImageView4.setVisibility(View.INVISIBLE);
        Button4.setVisibility(View.INVISIBLE);
        Button3.setVisibility(View.VISIBLE);
        Text1.setVisibility(View.INVISIBLE);
        Text2.setVisibility(View.INVISIBLE);
        Text3.setVisibility(View.INVISIBLE);
        Text4.setVisibility(View.VISIBLE);
        Text5.setVisibility(View.INVISIBLE);

        File delete = File_list.get(3);

        SellingActivity.currentItem.deleteImage(delete);
        File_list.remove(3);
        if(isTaken==true) {
            delete.delete();

        }
        next_free_imageView--;
    }




            public void confirm (View view){
        if(next_free_imageView!=0){
            MainActivity.CurrentUser.addItemPost(SellingActivity.currentItem);
            MainActivity.EasyTradeDataBase.get(MainActivity.CurrentUser.getUsername()).addItemPost(SellingActivity.currentItem);
            if (MainActivity.GridFragment.mAdapter != null)
                MainActivity.GridFragment.mAdapter.notifyDataSetChanged();
            Log.d("Debug","New Item Added to CurrentUser");

            MainActivity.EasyTradeDataBase.sortData();
            MainActivity.EasyTradeDataBase.setITEMID_LOWER_BOUND(MainActivity.EasyTradeDataBase.getITEMID_LOWER_BOUND()+1);
            try {
                File outFile = new File(Environment.getExternalStorageDirectory(), "EasyTradeDataBase.ser");
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream(outFile));
                out.writeObject(MainActivity.EasyTradeDataBase);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        final CharSequence[] items = { "Done" };
        AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity2.this);
        builder.setTitle("Item Successfully Posted");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
            if (items[item].equals("Done")) {
                Intent intent= new Intent(SellingActivity2.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SellingActivity2.this.startActivity(intent);
                }
            }
        });
        builder.show();}
        else {
            final CharSequence[] items = { "OK" };
            AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity2.this);
            builder.setTitle("Add At Least One Photo");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("OK")) {
                          dialog.dismiss();

                    }
                }
            });
            builder.show();}
        }



    public void select_photo_from_file(View view) {

        if (next_free_imageView >= 4) {
            final CharSequence[] items = { "OK" };
            AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity2.this);
            builder.setTitle("You Can Take Up to Four Photos");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("OK")) {
                        dialog.dismiss();

                    }
                }
            });
            builder.show();}
         else {
            final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                    "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(SellingActivity2.this);
            builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("Take Photo")) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                    } else if (items[item].equals("Choose from Gallery")) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(
                                Intent.createChooser(intent, "Select File"),
                                SELECT_FILE);
                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                File storageDir= new File(Environment.getExternalStorageDirectory()+"/EasyTrade");
                if(!storageDir.exists()){
                    File storageDir1= new File(Environment.getExternalStorageDirectory()+"/EasyTrade/");
                    storageDir1.mkdir();
                }
                String imageFileName =  String.valueOf( System.currentTimeMillis());
                destination = new File(storageDir,imageFileName+".jpg");

                File_list.add(destination);

                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageList.get(next_free_imageView).setVisibility(View.VISIBLE);
                TextList.get(next_free_imageView).setVisibility(View.INVISIBLE);
                TextList.get(next_free_imageView+1).setVisibility(View.VISIBLE);
                Picasso.with(this).load(destination).centerCrop().resize(ImageList.get(next_free_imageView).getWidth()-20, ImageList.get(next_free_imageView).getHeight()).into(ImageList.get(next_free_imageView));
                for(int i=0;i<4;i++) {
                    if(i==next_free_imageView)
                        ButtonList.get(i).setVisibility(View.VISIBLE);
                    else {
                        ButtonList.get(i).setVisibility(View.INVISIBLE);
                    }
                        }
                isTaken=true;
                next_free_imageView++;
                SellingActivity.currentItem.addImage(destination);

            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
             /*   String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                        null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();

                String selectedImagePath = cursor.getString(column_index);*/
                selectedImagePath = getPath(selectedImageUri);
                    destination =  new File (selectedImagePath);


                ImageList.get(next_free_imageView).setVisibility(View.VISIBLE);
                TextList.get(next_free_imageView).setVisibility(View.INVISIBLE);
                TextList.get(next_free_imageView+1).setVisibility(View.VISIBLE);

                Picasso.with(this).load(selectedImageUri).resize(ImageList.get(next_free_imageView).getWidth()-20, ImageList.get(next_free_imageView).getHeight()).into(ImageList.get(next_free_imageView));

                for(int i=0;i<4;i++) {
                    if(i==next_free_imageView)
                        ButtonList.get(i).setVisibility(View.VISIBLE);
                    else {
                        ButtonList.get(i).setVisibility(View.INVISIBLE);
                    }
                }

                isTaken=false;
                next_free_imageView++;
                SellingActivity.currentItem.addImage(destination);
                File_list.add(destination);
            }
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

    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
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
    }

}
