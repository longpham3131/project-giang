package hcmute.edu.vn.nttgiang18110275;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends ArrayAdapter {

    List<Booked> mlist;
    public OrderAdapter(@NonNull Context context, int resource, ArrayList<Booked> orderArrayList) {
        super(context, resource);
        mlist = orderArrayList;

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView order;
        ImageView imageView;
        ImageView status;
        TextView dateOrder;
        TextView total;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        OrderAdapter.ViewHolder holder;
        if(convertView == null)
        {
            holder = new OrderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_custom_order, parent, false);
            holder.order = (TextView) convertView.findViewById(R.id.order);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageViewCustome);

            holder.dateOrder = (TextView)convertView.findViewById(R.id.dateOrder);
            holder.total = (TextView)convertView.findViewById(R.id.total);
            convertView.setTag(holder);
        }else{
            holder = (OrderAdapter.ViewHolder) convertView.getTag();
        }

        Booked booked = (Booked) this.getItem(position);
        holder.dateOrder.setText(booked.get_dateOrder());
        //int imgId = addressStore.get_picture();
        holder.total.setText("T???ng: " + decimalFormat.format(booked.get_total())  + " VN??" );
        //byte[] image = addressStore.get_picture();
        ///Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        //holder.imageViewFood.setImageBitmap(bitmap);
        return convertView;
    }
}
