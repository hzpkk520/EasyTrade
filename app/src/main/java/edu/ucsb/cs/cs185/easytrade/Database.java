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

    public void add(User tmpUser){
        if (indexOf(tmpUser)== -1)
            myDatabase.add(tmpUser);
        sortData();
    }

    public void add(int index, User element){
        if (indexOf(element)== -1)
            myDatabase.add(index,element);
        sortData();
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
            Item tmpItem = user.getItemPost(0);
            if (!tmpItem.isSold()){
                tmpList.add(user);
                Log.d("Debug", "Item Added to the Adapter itemList");
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
    }

}
