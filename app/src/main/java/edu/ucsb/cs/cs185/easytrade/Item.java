package edu.ucsb.cs.cs185.easytrade;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by hzpkk520 on 15/5/29.
 */
public class Item {

    private int itemID;
    private String itemTitle;
    private String category;
    private String price;
    private float locationX;
    private float locationY;
    private String description;
    private String dayOfPost;
    private String monthOfPost;
    private String yearOfPost;
    private ArrayList<File> itemImages;

    public Item(){
        itemID = 0;
        itemTitle = "NULL";
        category = "NULL";
        price = "00.00";
        locationX = 0;
        locationY = 0;
        description = "NULL";
        dayOfPost = "00";
        monthOfPost = "00";
        yearOfPost = "00";
        itemImages = new ArrayList<File>();

    }

    public Item(int itemID, String itemTitle, String category, String price, float locationX, float locationY, String description, String dayOfPost, String monthOfPost, String yearOfPost, ArrayList<File> itemImages) {
        this.itemID = itemID;
        this.itemTitle = itemTitle;
        this.category = category;
        this.price = price;
        this.locationX = locationX;
        this.locationY = locationY;
        this.description = description;
        this.dayOfPost = dayOfPost;
        this.monthOfPost = monthOfPost;
        this.yearOfPost = yearOfPost;
        this.itemImages = itemImages;
    }

    //----------------------------Getters and Setters------------------------------------------

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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
        return itemImages.addAll(index,c);
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
