package hcmute.edu.vn.mssv18128062;

import android.widget.ImageButton;

public class Product {
    int _id;
    String _name;
    float _price;
    String _description;
    int _idcategory;
    int _picture;




    public Product(){   }
    public Product(int id, String _name, float _price, String _description, int _idcategory, int _picture){
        this._id = id;
        this._name = _name;
        this._picture = _picture;
        this._description = _description;
        this._idcategory = _idcategory;
        this._price = _price;
    }

    public Product(String _name, float _price, String _description, int _idcategory, int _picture){
        this._name = _name;
        this._picture = _picture;
        this._description = _description;
        this._idcategory = _idcategory;
        this._price = _price;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public String getDescription(){return this._description;}

    public void setDescription(String description){this._description = description;}

    public int get_picture() {
        return _picture;
    }

    public void set_picture(int _picture) {
        this._picture = _picture;
    }

    public int getIdCategory(){return this._idcategory;}

    public void setIdCategory(int idCategory){this._idcategory = idCategory; }


}
