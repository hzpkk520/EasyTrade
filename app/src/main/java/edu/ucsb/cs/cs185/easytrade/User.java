package edu.ucsb.cs.cs185.easytrade;

import android.util.FloatMath;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable{

    private String username;
    private String password;
    private String phoneNumber;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String address;
    private float locationX;
    private float locationY;
    private ArrayList<Item> postedItems;
    private ArrayList<Item> boughtItems;

    public User(){
        username = "NULL";
        password = "000000";
        phoneNumber = "0000000000";
        dayOfBirth = "00";
        monthOfBirth = "00";
        yearOfBirth = "0000";
        address = "NULL";
        locationX = 0;
        locationY = 0;
        postedItems = new ArrayList<Item>();
        boughtItems = new ArrayList<Item>();
    }

    public User(String username, String password, String phoneNumber, String dayOfBirth, String monthOfBirth, String yearOfBirth, String address,
                float locationX, float locationY, ArrayList<Item> postedItems, ArrayList<Item> boughtItems) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
        this.locationX = locationX;
        this.locationY = locationY;
        this.postedItems = postedItems;
        this.boughtItems = boughtItems;
    }

    //----------------------------Getters and Setters------------------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public ArrayList<Item> getPostedItems() {
        return postedItems;
    }

    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }

    public ArrayList<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(ArrayList<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }
    //----------------------------End of Getters and Setters------------------------------------------



    //----------------------------Methods for postedItems arrayList------------------------------------------

    public int indexOfPost(Item element){
        return postedItems.indexOf(element);
    }

    public Item getItemPost(int position){
        return postedItems.get(position);
    }

    public void addItemPost(Item element){
        postedItems.add(element);
    }

    public void addItemPost(int position, Item element){
        postedItems.add(position,element);
    }

    public boolean addAllPost(ArrayList<Item> c){
        return postedItems.addAll(c);
    }

    public boolean addAllPost(int index, ArrayList<Item> c){
        return postedItems.addAll(index,c);
    }

    public Item deleteItemPost(int position){
        return postedItems.remove(position);
    }

    public boolean deleteItemPost(Item element){
        return postedItems.remove(element);
    }

    public void clearPost(){
        postedItems.clear();
    }

    public boolean containsPost(Item item){
        return postedItems.contains(item);
    }

    public boolean isEmptyPost(){
        return postedItems.isEmpty();
    }

    public int getNumberOfPost(){
        return postedItems.size();
    }

    //----------------------------Methods for boughtItems arrayList------------------------------------------


    public int indexOfBought(Item element){
        return boughtItems.indexOf(element);
    }

    public Item getItemBought(int position){
        return boughtItems.get(position);
    }

    public void addItemBought(Item element){
        boughtItems.add(element);
    }

    public void addItemBought(int position, Item element){
        boughtItems.add(position,element);
    }

    public boolean addAll(ArrayList<Item> c){
        return boughtItems.addAll(c);
    }

    public boolean addAll(int index, ArrayList<Item> c){
        return boughtItems.addAll(index,c);
    }

    public Item deleteItemBought(int position){
        return boughtItems.remove(position);
    }

    public boolean deleteItemBought(Item element){
        return boughtItems.remove(element);
    }

    public void clearBought(){
        boughtItems.clear();
    }

    public boolean contains(Item item){
        return boughtItems.contains(item);
    }

    public boolean isEmptyBought(){
        return boughtItems.isEmpty();
    }

    public int getNumberOfBought(){
        return boughtItems.size();
    }


    //Helper function

    //Origin is the other user who is looking at this users item
    public float getDistToOrigin(){
        float x = this.getLocationX();
        float y = this.getLocationY();
        return FloatMath.sqrt(x * x + y * y);
    }

    //----------------------------Overriding .equals for comparing two User objects------------------------------------------

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        User other = (User)obj;
        if (other.getUsername() == this.getUsername())
            return  true;
        return false;
    }

}
