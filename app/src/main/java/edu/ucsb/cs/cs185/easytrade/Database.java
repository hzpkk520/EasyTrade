package edu.ucsb.cs.cs185.easytrade;

import java.util.ArrayList;

/**
 * Created by hzpkk520 on 15/5/29.
 */
public class Database {

    private ArrayList<User> myDatabase;

    public static int ITEMID_LOWER_BOUND;

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

    //----------------------------End of Getters and Setters------------------------------------------

    //----------------------------Methods for operating database------------------------------------------

    public int indexOf(User tmpUser){
        return myDatabase.indexOf(tmpUser);
    }

    public User get(int position){
        return myDatabase.get(position);
    }

    public void add(User tmpUser){
        myDatabase.add(tmpUser);
    }

    public void add(int index, User element){
        myDatabase.add(index,element);
    }

    public boolean addAll(ArrayList<User> c){
        return myDatabase.addAll(c);
    }

    public boolean addAll(int index, ArrayList<User> c){
        return myDatabase.addAll(index,c);
    }

    public User delete(int position){
        return myDatabase.remove(position);
    }

    public boolean delete(User element){
        return myDatabase.remove(element);
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


}
