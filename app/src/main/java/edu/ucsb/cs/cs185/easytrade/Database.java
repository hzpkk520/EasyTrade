package edu.ucsb.cs.cs185.easytrade;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hzpkk520 on 15/5/29.
 */
public class Database implements Serializable {

    private ArrayList<User> myDatabase;

    private int ITEMID_LOWER_BOUND;

    //Default constructor
    public Database(){
        myDatabase = new ArrayList<User>();
        ITEMID_LOWER_BOUND = 0;
    }

    public Database(ArrayList<User> myDatabase) {
        this.myDatabase = myDatabase;
        ITEMID_LOWER_BOUND = 0;
    }

    //Getter for ArrayList
    public ArrayList<User> getMyDatabase(){
        return myDatabase;
    }

    //Setter for ArrayList
    public void setMyDatabase(ArrayList<User> value){
        myDatabase = value;
    }

    public int getITEMID_LOWER_BOUND() {
        return ITEMID_LOWER_BOUND;
    }

    public void setITEMID_LOWER_BOUND(int ITEMID_LOWER_BOUND) {
        this.ITEMID_LOWER_BOUND = ITEMID_LOWER_BOUND;
    }

    //----------------------------End of Getters and Setters------------------------------------------

    //----------------------------Methods for operating database------------------------------------------

    public int indexOf(User tmpUser){
        return myDatabase.indexOf(tmpUser);
    }

    public User get(int position){
        return myDatabase.get(position);
    }

    public User get(String userName){
        for (User tmpUser: myDatabase){
            Log.d("Debug","Checking this user: "+tmpUser.getUsername());
            if (tmpUser.getUsername().equals(userName)){
                return tmpUser;
            }
        }
        return null;
    }

    public void add(User tmpUser){
        Log.d("Debug","Before checking if user already existed");
        if (!contains(tmpUser)){
            Log.d("Debug","The User being checked is not in the Database, we should add it");
            myDatabase.add(tmpUser);
            sortData();
        }
    }

    public void add(int index, User element){
        if (!contains(element)){
            myDatabase.add(index,element);
            sortData();
        }
    }

    public boolean addAll(ArrayList<User> c){
        boolean tmp = myDatabase.addAll(c);
        sortData();
        return tmp;
    }

    public boolean addAll(int index, ArrayList<User> c){
        boolean tmp = myDatabase.addAll(index, c);
        sortData();
        return tmp;
    }

    public boolean delete(String userName){
        User tmp = get(userName);
        boolean tmp1 = myDatabase.remove(tmp);
        sortData();
        return tmp1;
    }

    public User delete(int position){
        User tmpUser = myDatabase.remove(position);
        sortData();
        return tmpUser;
    }

    public boolean delete(User element){
        boolean tmp = myDatabase.remove(element);
        sortData();
        return tmp;
    }

    public void clear(){
        myDatabase.clear();
        sortData();
    }

    public boolean contains(User user){
        return myDatabase.contains(user);
    }

    public boolean isEmpty(){
        return myDatabase.isEmpty();
    }

    public int size(){
        return myDatabase.size();
    }

    public ArrayList<User> getCurrentUsers(){
        ArrayList<User> tmpList = new ArrayList<User>();
        for (User user:myDatabase){
            Log.d("Debug", "for ADAPTER DATASET, checking this user: "+user.getUsername());
            Log.d("Debug", "the Size of this user's postedItems is: "+user.getPostedItems().size());
            if (user.getPostedItems().size()!=0) {
                Item tmpItem = user.getItemPost(user.getPostedItems().size()-1);
                if (!tmpItem.isSold()){
                    tmpList.add(user);
                    Log.d("Debug", user.getUsername()+" Added to the Adapter dataset");
                }
            }
        }
        return tmpList;
    }

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
        Collections.sort(myDatabase, my_compare);
        if (MainActivity.GridFragment.mAdapter != null)
            MainActivity.GridFragment.mAdapter.notifyDataSetChanged();
    }

    @Override
    public String toString() {
        String tmpString="All the users currently in DATABASE: \n";
        for (User user:myDatabase){
            tmpString = tmpString.concat("-"+user.getUsername()+"--");
        }
        return tmpString;
    }

}
