package models;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
     
    String username ;
    ArrayList<String> listItem; 

    public User() {
        username ="";
        listItem = new ArrayList(); 
    }

    public User(String username, ArrayList<String>  listItem) {
        this.username = username;
        this.listItem = listItem;
    }

    public  ArrayList<String> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<String> listItem) {
        this.listItem = listItem;
    }

    public String getUsername() {
        return username;
    }    

    public void setUsername(String username) {
        this.username = username;
    }
    
}
