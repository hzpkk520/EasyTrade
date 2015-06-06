package edu.ucsb.cs.cs185.easytrade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private static final String TAG = "Debug";


    public static Database EasyTradeDataBase;

    public static User CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Debug","MAINACTIVITY ONCREATE CALLED");

        //Read saved Database from local storage
        try
        {
            File fileIn = new File(Environment.getExternalStorageDirectory(), "EasyTradeDataBase.ser");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileIn));
            EasyTradeDataBase = (Database) in.readObject();
            in.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c) {

            EasyTradeDataBase = new Database();

            Log.d("Debug","Employee class not found");
//            c.printStackTrace();
        }

        Log.d("Debug", "Database may be found, Continue on");

        if (EasyTradeDataBase == null){
            Log.d("Debug", "Database not found :( ");
            EasyTradeDataBase = new Database();

            loadInDefaultData();

        }
        else if (EasyTradeDataBase != null)
            Log.d("Debug", "Database FOUND!! ( ⊙o⊙ ) ");


        if (EasyTradeDataBase.isEmpty()){
            Log.d(TAG,"The Read Database is Empty");

            loadInDefaultData();
        }


        sortData();

        CurrentUser = new User("zixiaweng","123456","8058864549","02","07","1995","6521 Cordoba Rd., #30",0,0,
                new ArrayList<Item>(),new ArrayList<Item>());

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }


    }



    @Override
    public void onStop() {
        super.onStop();
        //Write Database to disk
        sortData();
        EasyTradeDataBase.setITEMID_LOWER_BOUND(EasyTradeDataBase.getITEMID_LOWER_BOUND()+1);
        try {
            File outFile = new File(Environment.getExternalStorageDirectory(), "EasyTradeDataBase.ser");
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(EasyTradeDataBase);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0){
                Log.d(TAG,"Ready to call GridFragment");
                return GridFragment.newInstance(position +1);
            }

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
//                case 2:
//                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }



    /**
     * A StaggeredGrid fragment containing a grid view.
     */
    public static class GridFragment extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String TAG = "Debug";
        public static String USERNAME_TO_PASS;

        private GridView mGridView;
        public static GridAdapter mAdapter;
        public ArrayList<User> usersShownInGrid;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GridFragment newInstance(int sectionNumber) {
            GridFragment fragment = new GridFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public GridFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG,"calling onCreateView in StaggeredFragment");

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView theTextView = (TextView)rootView.findViewById(R.id.section_label);
            theTextView.setVisibility(View.GONE);
//            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
//            theTextView.setText("Section "+sectionNumber);
            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setRetainInstance(true);

            Log.d(TAG, "calling onCreate in StaggeredFragment");

        }

        @Override
        public void onActivityCreated(final Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);



            Log.d(TAG, "calling onActivityCreated in StaggeredFragment");
            mGridView = (GridView) getActivity().findViewById(R.id.grid_view);

            usersShownInGrid = getUsersShownInGrid();
            Log.d(TAG,"SIZE of usersShownInGrid is: "+usersShownInGrid.size());

//            if (mAdapter == null) {
//                mAdapter = new GridAdapter(getActivity(),usersShownInGrid);
//            }
            mAdapter = new GridAdapter(getActivity(),usersShownInGrid);

            Log.d(TAG,"setting Adapter for mGridView in StaggeredFragment");
            Log.d(TAG, "the size of the data base is " + EasyTradeDataBase.size());

            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    USERNAME_TO_PASS = usersShownInGrid.get(position).getUsername();
                    Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
                    intent.putExtra("userName", USERNAME_TO_PASS);
//                    intent.putExtra("parentActivity",0);
                    Toast.makeText(getActivity(), "Item "+ position + " clicked", Toast.LENGTH_SHORT).show();

                    getActivity().startActivity(intent);
                }
            });

        }



        public ArrayList<User> getUsersShownInGrid(){
            return EasyTradeDataBase.getCurrentUsers();
        }
//        @Override
//        public void onScrollStateChanged(final AbsListView view, final int scrollState) {
//            Log.d(TAG, "onScrollStateChanged:" + scrollState);
//        }
//
//        @Override
//        public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
//            Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
//                    " visibleItemCount:" + visibleItemCount +
//                    " totalItemCount:" + totalItemCount);
//            if(firstVisibleItem + visibleItemCount >= totalItemCount){
//                // End has been reached
//            }
////            // our handling
////            if (!mHasRequestedMore) {
////                int lastInScreen = firstVisibleItem + visibleItemCount;
////                if (lastInScreen >= totalItemCount) {
////                    Log.d(TAG, "onScroll lastInScreen - so load more");
////                    mHasRequestedMore = true;
////                    onLoadMoreItems();
////                }
////            }
//        }
//
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//            myView = view;
//            Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
//        }


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ListView listView;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sellerinfo, container, false);

            listView = (ListView) rootView.findViewById(R.id.listview);
            listView.setAdapter(new listViewAdapter(this, new String[] { "Bought items",
                    "Sold items" }));
            return rootView;
        }
    }



    //Helper Functions

    //a Comparator interface for ordering the files when loaded to myDataSet
    public class fileCompare implements Comparator<User> {
        @Override
        public int compare(User o1, User o2){
            /*By default:
            returns <0 if o1 <o2
            returns ==0 if o1==o2
            returns >0 if o1>o2
             */

            if (o1.getDistToOrigin()>o2.getDistToOrigin())
                return 1;
            else if(o1.getDistToOrigin()<o2.getDistToOrigin())
                return -1;
            else
                return 0;
        }
    }

    public void sortData(){
        fileCompare my_compare = new fileCompare();
        Collections.sort(EasyTradeDataBase.getMyDatabase(), my_compare);
        if (GridFragment.mAdapter != null)
            GridFragment.mAdapter.notifyDataSetChanged();
    }




    public void loadInDefaultData(){
        User user0 = new User("JackBacon","000000","(805)878-3652","21","02","1991","6636 Trigo Rd., #10",30,4,new ArrayList<Item>(),new ArrayList<Item>());
        User user1 = new User("ScottCesar","000000","(805)708-6878","25","03","1992","6675 Sabado Rd., #2",50,70,new ArrayList<Item>(),new ArrayList<Item>());
        User user2 = new User("JenniChou","000000","(805)252-0086","21","05","1993","6722 Del Playa Rd., #30",95,100,new ArrayList<Item>(),new ArrayList<Item>());
        User user3 = new User("KobeHollerer","000000","(805)356-4332","11","11","1981","6621 Abrego Rd., #9",22,35,new ArrayList<Item>(),new ArrayList<Item>());
        User user4 = new User("CoryFeitelson","000000","(805)708-0991","10","03","1992","6654 Picasso  Rd., #4",800,255,new ArrayList<Item>(),new ArrayList<Item>());

        Item item0 = new Item(999900,"JackBacon",false,"Book Shelf for Sale","Furniture","$55","Little Damaged",
                "This bookshelf is bought one year ago. It is pretty much still in good shape. If you are interested, please don't hesitate to contact me!",
                "28","05","2015",new ArrayList<File>());
        Item item1 = new Item(999901,"ScottCesar",false,"Selling this VERY comfortable mattress","Furniture","$125","Like New",
                "I bought this EXTREMELY comfortable mattress when I entered university. Now since I'm graduating, it should be taken over by a kind person. If you are viewing this, I hope you could be the one. Please contact me!",
                "26","05","2015",new ArrayList<File>());
        Item item2 = new Item(999902,"JenniChou",false,"ALL NEW Bikinis $5 each","Clothes","$5","All New",
                "These bikinis were bought a few month ago when I went to Hawaii for vacation. But I came back before I even get a chance to wear these. So, if you want these beautiful bikinis just contact me.",
                "29","05","2015",new ArrayList<File>());
        Item item3 = new Item(999903,"KobeHollerer",false,"RANDOM CLOTHES $10 EACH!","Clothes","$10","Almost New",
                "Our family is moving to another city so we are trying to sell these random clothes we bought some time ago. These clothes were only worn at most twice. I PROMISE.",
                "10","04","2015",new ArrayList<File>());
        Item item4 = new Item(999904,"CoryFeitelson",false,"Modern Super Cool Minifridge","Appliance","$35","Like New",
                "Living in this modern and crowded world, don't you want to buy a minifridge to save space and store your favorite food? This fridge was bought a year ago. It is still functional and in very good shape. Contact me for details!",
                "28","05","2015",new ArrayList<File>());

        Log.d("Debug","Built-in User and Item initialization done, start adding images to Database");
        convertAndAdd(R.drawable.bookshelf1, user0, item0);
        convertAndAdd(R.drawable.bookshelf2, user0, item0);
        convertAndAdd(R.drawable.bookshelf3,user0,item0);
        Log.d("Debug", "Adding first Users item image done");


        convertAndAdd(R.drawable.comfortablemattress1, user1, item1);
        convertAndAdd(R.drawable.comfortablemattress2,user1,item1);
        convertAndAdd(R.drawable.comfortablemattress3,user1,item1);
        Log.d("Debug", "Adding second Users item image done");


        convertAndAdd(R.drawable.bikinis1, user2, item2);
        convertAndAdd(R.drawable.bikinis2,user2,item2);
        convertAndAdd(R.drawable.bikinis3,user2,item2);
        Log.d("Debug", "Adding third Users item image done");

        convertAndAdd(R.drawable.randomclothes1, user3, item3);
        convertAndAdd(R.drawable.randomclothes2,user3,item3);
        convertAndAdd(R.drawable.randomclothes3,user3,item3);
        convertAndAdd(R.drawable.randomclothes4,user3,item3);
        convertAndAdd(R.drawable.randomclothes5,user3,item3);
        convertAndAdd(R.drawable.randomclothes6,user3,item3);
        convertAndAdd(R.drawable.randomclothes7,user3,item3);
        convertAndAdd(R.drawable.randomclothes8,user3,item3);
        Log.d("Debug", "Adding fourth Users item image done");

        convertAndAdd(R.drawable.minifridge1, user4, item4);
        convertAndAdd(R.drawable.minifridge2,user4,item4);
        convertAndAdd(R.drawable.minifridge3,user4,item4);
        Log.d("Debug", "Adding fifth Users item image done");
        Log.d("Debug", "Finished adding all the images");


    }


    public void convertAndAdd(int drawable, User user, Item item){
//        String path = "android.resource://" + getPackageName() + "/" + drawable;
//        File tmpFile = new File(URI.create(path));
//        File file1User0 = convertDrawableToFile(drawable);

        item.getDrawablesForSampleUsers().add(drawable);
        user.getPostedItems().add(item);
        EasyTradeDataBase.add(user);
    }


//    public File convertDrawableToFile(int drawable) {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
//        File f = new File(getCacheDir(), String.valueOf(drawable));
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(f);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
//            // PNG is a lossless format, the compression factor (100) is ignored
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return f;
//    }

}
