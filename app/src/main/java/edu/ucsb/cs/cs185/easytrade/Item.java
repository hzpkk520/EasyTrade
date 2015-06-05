package edu.ucsb.cs.cs185.easytrade;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hzpkk520 on 15/5/29.
 */
public class Item implements Serializable{

    private int itemID;
    private String owner;
    private boolean sold;
    private String itemTitle;
    private String category;
    private String price;
    private String condition;
    private String description;
    private String dayOfPost;
    private String monthOfPost;
    private String yearOfPost;
    private ArrayList<File> itemImages;
    private ArrayList<Integer> drawablesForSampleUsers;

    public Item(){
        itemID = 0;
        owner = "NULL";
        sold = false;
        itemTitle = "NULL";
        category = "NULL";
        price = "00.00";
        condition = "NULL";
        description = "NULL";
        dayOfPost = "00";
        monthOfPost = "00";
        yearOfPost = "00";
        itemImages = new ArrayList<File>();
        drawablesForSampleUsers = new ArrayList<Integer>();


    }

    public Item(int itemID, String owner, boolean sold, String itemTitle, String category, String price, String condition, String description, String dayOfPost, String monthOfPost, String yearOfPost, ArrayList<File> itemImages) {
        this.itemID = itemID;
        this.owner = owner;
        this.sold = sold;
        this.itemTitle = itemTitle;
        this.category = category;
        this.price = price;
        this.condition = condition;
        this.description = description;
        this.dayOfPost = dayOfPost;
        this.monthOfPost = monthOfPost;
        this.yearOfPost = yearOfPost;
        this.itemImages = itemImages;
        drawablesForSampleUsers = new ArrayList<Integer>();
    }

    //----------------------------Getters and Setters------------------------------------------

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayOfPost() {
        return dayOfPost;
    }

    public void setDayOfPost(String dayOfPost) {
        this.dayOfPost = dayOfPost;
    }

    public String getMonthOfPost() {
        return monthOfPost;
    }

    public void setMonthOfPost(String monthOfPost) {
        this.monthOfPost = monthOfPost;
    }

    public String getYearOfPost() {
        return yearOfPost;
    }

    public void setYearOfPost(String yearOfPost) {
        this.yearOfPost = yearOfPost;
    }

    public ArrayList<File> getItemImages() {
        return itemImages;
    }

    public void setItemImages(ArrayList<File> itemImages) {
        this.itemImages = itemImages;
    }


    public ArrayList<Integer> getDrawablesForSampleUsers() {
        return drawablesForSampleUsers;
    }

    public void setDrawablesForSampleUsers(ArrayList<Integer> drawablesForSampleUsers) {
        this.drawablesForSampleUsers = drawablesForSampleUsers;
    }

    //----------------------------End of Getters and Setters------------------------------------------


    //----------------------------Methods for itemImages arrayList------------------------------------------


    public int indexOf(File element){
        return itemImages.indexOf(element);
    }

    public File getImage(int position){
        return itemImages.get(position);
    }

    public void addImage(File element){
        itemImages.add(element);
    }

    public void addImage(int position, File element){
        itemImages.add(position,element);
    }

    public boolean addAllImage(ArrayList<File> c){
        return itemImages.addAll(c);
    }

    public boolean addAllImage(int index, ArrayList<File> c){
        return itemImages.addAll(index, c);
    }

    public File deleteImage(int position){
        return itemImages.remove(position);
    }

    public boolean deleteImage(File element){
        return itemImages.remove(element);
    }

    public void clearImage(){
        itemImages.clear();
    }

    public boolean containsImage(File file){
        return itemImages.contains(file);
    }

    public boolean isImageEmpty(){
        return itemImages.isEmpty();
    }

    public int getNumberOfImage(){
        return itemImages.size();
    }


    //----------------------------Overriding .equals for comparing two Item objects------------------------------------------

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Item other = (Item)obj;
        if (other.getItemID() == this.getItemID())
            return  true;
        return false;
    }


}
