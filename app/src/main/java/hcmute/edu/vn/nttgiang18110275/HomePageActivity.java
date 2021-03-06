package hcmute.edu.vn.nttgiang18110275;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

    TextView textView;
    //Toolbar toolbar;
    ImageButton info;
    ImageButton cart;
    ImageButton notification;
    BottomNavigationView bottomNavigationView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompage);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);
        //
        textView = (TextView)findViewById(R.id.titleFrag);
        textView.setText("Home");
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment,"");
        fragmentTransaction.commit();
        //
        Intent intent = getIntent();
        String name = getIntent().getStringExtra("name");
        if(name != null){
            if(name.equals("product"))
            {
                bottomNavigationView.setSelectedItemId(R.id.navigation_products);
                //bottomNavigationView.setOnNavigationItemSelectedListener(R.id.navigation_products);
            }
            else if(name.equals("home")){
                bottomNavigationView.setSelectedItemId(R.id.navigation_store);
            }

            else if(name.equals("other") || name.equals("order")){
                bottomNavigationView.setSelectedItemId(R.id.navigation_others);
            }
        }

        //
        cart = (ImageButton)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), CartActivity.class);
                if(textView.getText().equals("Home"))
                {
                    intent.putExtra("name", "home");
                }
                else if(textView.getText().equals("Products"))
                {
                    intent.putExtra("name", "product");
                }
                else if(textView.getText().equals("Promotion"))
                {
                    intent.putExtra("name", "promotion");
                }
                else if(textView.getText().equals("Points"))
                {
                    intent.putExtra("name", "point");
                }
                else
                {
                    intent.putExtra("name", "other");
                }
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
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.navigation_store:
                    textView.setText("Ministop");
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment, "");
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_products:
                    textView.setText("S???n ph???m");
                    ProductFragment fragment1 = new ProductFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.container, fragment1);
                    fragmentTransaction1.commit();
                    return true;



                case R.id.navigation_others:
                    textView.setText("????n h??ng");
                    OtherFragment fragment4 = new OtherFragment();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.container, fragment4);
                    fragmentTransaction4.commit();
                    return true;
            }
            return false;
        }
    };


}
