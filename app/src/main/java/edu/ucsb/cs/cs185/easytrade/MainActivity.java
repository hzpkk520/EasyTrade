package edu.ucsb.cs.cs185.easytrade;

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

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;


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


    public static Database EasyTradeDataBase;

    public static User CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }
        else if (EasyTradeDataBase != null)
            Log.d("Debug", "Database FOUND!! ( ⊙o⊙ ) ");


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
                return StaggeredGridFragment.newInstance(position +1);
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
     * A placeholder fragment containing a simple view.
     */
    public static class StaggeredGridFragment extends Fragment implements View.OnTouchListener, AbsListView.OnItemClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String TAG = "Debug";
        private static double TENSION = 80;
        private static double DAMPER = 8; //friction
        private SpringSystem mSpringSystem;
        private Spring mSpring;
        private View myView;

        private StaggeredGridView mGridView;
        private GridAdapter mAdapter;
        private Database mDatabase;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static StaggeredGridFragment newInstance(int sectionNumber) {
            StaggeredGridFragment fragment = new StaggeredGridFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mSpring.setEndValue(1f);
                    return true;
                case MotionEvent.ACTION_UP:
                    mSpring.setEndValue(0f);
                    return true;
            }

            return false;
        }

        public StaggeredGridFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
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

            mSpringSystem = SpringSystem.create();

            mSpring = mSpringSystem.createSpring();
            mSpring.addListener(new SimpleSpringListener() {

                @Override
                public void onSpringUpdate(Spring spring) {
                    // You can observe the updates in the spring
                    // state by asking its current value in onSpringUpdate.
                    float value = (float) spring.getCurrentValue();
                    float scale = 1f - (value * 0.5f);
                    myView.setScaleX(scale);
                    myView.setScaleY(scale);
                }
            });

            SpringConfig config = new SpringConfig(TENSION, DAMPER);
            mSpring.setSpringConfig(config);
        }

        @Override
        public void onActivityCreated(final Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);

            if (mDatabase == null) {
                mDatabase = EasyTradeDataBase;
            }

            if (mAdapter == null) {
                mAdapter = new GridAdapter(getActivity(),EasyTradeDataBase);
            }


            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(this);

        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            myView = view;
            Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
        }


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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            StaggeredGridView staggeredGridView = (StaggeredGridView)rootView.findViewById(R.id.grid_view);
            staggeredGridView.setVisibility(View.GONE);

            TextView theTextView = (TextView)rootView.findViewById(R.id.section_label);
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            theTextView.setText("Section " + sectionNumber);

            return rootView;
        }
    }



    //Helper Functions

    //a Comparator interface for ordering the files when loaded to myDataSet
    private class fileCompare implements Comparator<User> {
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
    }



}
