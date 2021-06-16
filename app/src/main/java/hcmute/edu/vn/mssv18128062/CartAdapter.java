package hcmute.edu.vn.mssv18128062;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends ArrayAdapter {

    List<Product> foodList;

    public CartAdapter(@NonNull Context context, int resource, ArrayList<Product> productArrayList) {
        super(context, resource);

        foodList = productArrayList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNameFood;
        ImageView imageViewFood;
        TextView txtPrice;
        EditText amount;



    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartAdapter.ViewHolder holder;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        if(convertView == null)
        {
            holder = new CartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_custom_cart, null);

            holder.txtNameFood = (TextView) convertView.findViewById(R.id.nameFood);
            holder.imageViewFood = (ImageView) convertView.findViewById(R.id.imageFoodDetail);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.priceFood);
            //holder.imageButtonDelete = (ImageButton)convertView.findViewById(R.id.delete);

            holder.amount = (EditText) convertView.findViewById(R.id.amount);

            convertView.setTag(holder);
        }else{
            holder = (CartAdapter.ViewHolder) convertView.getTag();
        }


        Product addressStore = (Product) this.getItem(position);
        holder.imageViewFood.setImageResource(addressStore.get_picture());
        holder.txtNameFood.setText(addressStore.getName());
        holder.txtPrice.setText( decimalFormat.format(addressStore.get_price())+ " VNĐ" );
        String totalAmount = "" + addressStore.getIdCategory();
        holder.amount.setText("x" + totalAmount);
        return convertView;
    }

}
