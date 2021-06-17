package hcmute.edu.vn.nttgiang18110275;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    ListView lvFood;
    ArrayList<Product> productArrayList;
    private Database db;
    CartAdapter cartAdapter;
    private SQLiteDatabase sqLiteDatabase;

    int pos;
    int dem = 0;
    int idOrder;
    TextView txtTotal;

    float total;
    int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);


        Intent intent = getIntent();
        pos = getIntent().getIntExtra("position", -1);
        txtTotal = (TextView)findViewById(R.id.total);
        lvFood = (ListView)findViewById(R.id.lvFood);

        productArrayList = new ArrayList<>();
        db = new Database(this);
        sqLiteDatabase = db.getReadableDatabase();

        String query = "SELECT * FROM BOOKED";
        Cursor cursor = db.GetData(query);
        while (cursor.moveToNext()){
            if(dem == pos){
                idOrder = cursor.getInt(0);
                total = cursor.getFloat(4);
                status = cursor.getInt(2);
                break;
            }
            dem++;
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText( decimalFormat.format(total) + " VNĐ");

        Cursor dataOrder = db.getOrderByIdBooked(idOrder);
        while (dataOrder.moveToNext()){
            int id = dataOrder.getInt(1);
            Cursor dataFood = db.getProductById(id);
            while(dataFood.moveToNext())
            {
                productArrayList.add(new Product(dataFood.getInt(0), dataFood.getString(1), dataOrder.getFloat(2),
                        dataFood.getString(3), dataOrder.getInt(3), dataFood.getInt(5)));
            }
        }

        cartAdapter = new CartAdapter(this, R.layout.layout_custom_cart, productArrayList);

        lvFood.setAdapter(cartAdapter);


    }
}