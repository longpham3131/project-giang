package hcmute.edu.vn.nttgiang18110275;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    ListView lvFood;
    ArrayList<Product> productArrayList;
    private Database db;
    FoodAdapter productAdapter;
    ImageButton info;
    ImageButton cart;
    ImageButton notification;
    Intent intentBack;
    Intent intentFood;
    SearchView searchViewFood;
    int positionCate;
    ImageButton imageButtonBack;
    private SQLiteDatabase sqLiteDatabase;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    int dem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent intent = getIntent();

        positionCate = getIntent().getIntExtra("positionCate", -1);
        imageButtonBack = (ImageButton)findViewById(R.id.btnback);
        lvFood = (ListView)findViewById(R.id.lvFood);
        productArrayList = new ArrayList<>();
        db = new Database(this);
        sqLiteDatabase = db.getReadableDatabase();
        productArrayList = db.getProductByCategory(positionCate + 1);
        productAdapter = new FoodAdapter(this, R.layout.layout_custom_food, productArrayList);

        lvFood.setAdapter(productAdapter);
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentFood = new Intent(getBaseContext(), FoodDetailActivity.class);
                intentFood.putExtra("positionPro", position);
                intentFood.putExtra("positionCate", positionCate);
                startActivity(intentFood);
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentBack = new Intent(getApplicationContext(),HomePageActivity.class);
                intentBack.putExtra("name", "product");
                startActivity(intentBack);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contant_main, new Home()).commit();
            }
        });

        cart = (ImageButton)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), CartActivity.class);
                intent.putExtra("name", "food");
                intent.putExtra("positionCate", positionCate);
                startActivity(intent);
            }
        });
        //



        //

        info = (ImageButton)findViewById(R.id.profile);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), MyInfoActivity.class);
                startActivity(intent);
            }
        });


        searchViewFood = (SearchView)findViewById(R.id.searchFood);
        searchViewFood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(dem != 0)
                {
                    productArrayList.clear();
                    productArrayList = db.getProductByCategory(positionCate + 1);
                    productArrayList.clear();
                    productAdapter = new FoodAdapter(getApplicationContext(), R.layout.layout_custom_food, productArrayList);
                }
                productAdapter.filter(newText);
                //lvAddress.setAdapter(addressAdapter);
                dem++;
                return false;
            }
        });

    }



}