package hcmute.edu.vn.nttgiang18110275;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RateActivity extends AppCompatActivity {

    Button btnSubmit;
    RatingBar ratingBar;
    float myRating;
    EditText txtCmt;
    private SQLiteDatabase sqLiteDatabase;
    private Database db;
    SharedPreferences sharedPreferencesUser;
    int id;
    int positionCate;
    int positionPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        Intent intent = getIntent();
        id = getIntent().getIntExtra("Id", -1);
        positionCate = getIntent().getIntExtra("positionCate", -1);
        positionPro = getIntent().getIntExtra("positionPro", -1);
        db = new Database(this);
        sqLiteDatabase = db.getWritableDatabase();
        btnSubmit = (Button)findViewById(R.id.submit);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        txtCmt = (EditText)findViewById(R.id.comment);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String cmt = txtCmt.getText().toString();
                sharedPreferencesUser = getSharedPreferences("dataLogin", MODE_PRIVATE);
                String name = sharedPreferencesUser.getString("name", "");
                LocalDateTime lt = LocalDateTime.now();
                DateTimeFormatter formatToday = DateTimeFormatter.ofPattern("MM/dd/yy:hh:mm:ss");
                String datenow = lt.format(formatToday);
                float rating = ratingBar.getRating();
                db.insertRate(new Rate(id, name, datenow, rating, cmt));
                finish();
                Intent intentBack = new Intent(getApplicationContext(), FoodDetailActivity.class);
                intentBack.putExtra("positionCate", positionCate);
                intentBack.putExtra("positionPro", positionPro);
                startActivity(intentBack);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int ratingNow = (int)rating;
                String message = null;

                myRating = ratingBar.getRating();

                switch (ratingNow){
                    case 1:
                        message = "T??i r???t ti???c v?? ??i???u n??y :(";
                        break;
                    case 2:
                        message = "Ch??ng t??i lu??n mu???n ph???c v??? b???n!";
                        break;
                    case 3:
                        message = "C???m ??n b???n ???? ????ng g??p.";
                        break;
                    case 4:
                        message = "Tuy???t v???i c???m ??n b???n r???t nhi???u!";
                        break;
                    case 5:
                        message = "C???m ??n b???n, b???n th???t tuy???t v???i!";
                        break;
                    default:
                        message = "C???m ??n b???n ???!";
                        break;
                }

                Toast.makeText(RateActivity.this, "" + message, Toast.LENGTH_LONG).show();
            }
        });

    }
}