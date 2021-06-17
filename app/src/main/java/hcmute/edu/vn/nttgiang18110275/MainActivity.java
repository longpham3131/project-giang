package hcmute.edu.vn.nttgiang18110275;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {


    Button login;
    TextView txtSignUp;
    TextView txtFogotPass;
    ImageView ch;
    public static Database db;
    SharedPreferences sharedPreferencesUser;
    SharedPreferences sharedPreferencesCart;
    private GoogleSignInClient mGoogleSignInClient;
    EditText usernameLogin;
    EditText passwordLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new Database(this);
        //Xóa database
//        deleteDatabase("manageStores");

        db.QueryData("CREATE TABLE IF NOT EXISTS USERS(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, " +
                "PASSWORD TEXT, EMAIL TEXT, NAME TEXT, PHONE TEXT, POINT INTEGER, PICTURE BLOB)");
        //db.QueryData("INSERT INTO USER VALUES(NULL, 'mint', '12345', '123456'," + 1 +")");
        db.QueryData("CREATE TABLE IF NOT EXISTS CATEGORY( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, IMAGE_ID INTEGER )");
        db.QueryData("CREATE TABLE IF NOT EXISTS ADDRESS( ID INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPTION TEXT, IMAGE_ID INTEGER)");
        db.QueryData("CREATE TABLE IF NOT EXISTS PRODUCT( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PRICE FLOAT, DESCRIPTION TEXT, " +
                "ID_CATEGORY INTEGER, IMAGE_ID INTEGER)");
        db.QueryData("CREATE TABLE IF NOT EXISTS RATE( ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_PRODUCT INTEGER, NAME TEXT, DATE_RATING TEXT, " +
                "RATING FLOAT, CMT TEXT)");
        db.QueryData("CREATE TABLE IF NOT EXISTS BOOKED( ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE_ORDER TEXT, STATUS INTEGER, ADDRESS TEXT, TOTAL FLOAT)");
        db.QueryData("CREATE TABLE IF NOT EXISTS ORDERS( ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_PRODUCT INTEGER, PRICE FLOAT, AMOUNT_PRODUCT INTEGER, " +
             "ID_BOOKED INTEGER)");

        db.QueryData("CREATE TABLE IF NOT EXISTS NOTIFICATION( ID INTEGER PRIMARY KEY AUTOINCREMENT, INFOMATION TEXT, IMAGE_ID INTEGER)");

        User userMain = new User();

        userMain.set_name("Nguyễn Trường Giang");
        userMain.set_phone_number("093281241");
        userMain.setEmail("nttgiang@gmail.com");
        userMain.setUsername("nttgiang18110275");
        userMain.setPassword("123");

        db.insertUser(userMain);


//        String des = "Chào mừng tình yêu đã đến đồng hành cùng mình nè. Love u <3 !!!";
//        db.insertNotification(des, idImg);
        db.insertAddress("28 Tân Lập 1, phường Hiệp Phú, quận 9, tp.Thủ Đức", R.drawable.store_1);
        db.insertAddress("275 Nguyễn Trường Giang, phường HCMUTE, quận 9, tp.Thủ Đức", R.drawable.store_2);
        db.insertAddress("19 Nguyễn Trãi, phường An His, quận Bình Thạnh, tp.Hồ Chí Minh", R.drawable.store_3);
        db.insertAddress("89 Phan Chu Trinh, phường Giao Thoa, quận 8, tp.Hồ Chí Minh", R.drawable.store_3);
        db.insertAddress("16 Phạm Thúc Kha, phường Sapa, quận Mười Sáu Triệu Màu, tp.Hồ Chí Minh", R.drawable.store_5);
//
        db.insertCategory("Cơm văn phòng", R.drawable.com_ga_sot_thai);
        db.insertProduct("Cơm gà cay sốt thái", 47000, "Cơm được lấy nguyên liệu từ loại gạo Nhật Bản mang độ dẽo cùng với hương vị thiên nhiên cùng sốt thái chua cay phủ lên đùi gà", 1, R.drawable.com_ga_sot_thai);
        db.insertProduct("Cơm gà miếng Shillin Yaki", 47000, "Cơm được lấy nguyên liệu từ loại gạo Nhật Bản mang độ dẽo cùng với hương vị thiên nhiên cùng miếng gà phủ bột mè chiên", 1, R.drawable.com_ga_mieng_yaki);
        db.insertProduct("Cơm đùi gà chiên nước mắm", 45000, "Cơm được lấy nguyên liệu từ loại gạo Nhật Bản mang độ dẽo cùng với hương vị thiên nhiên cùng đùi gà tẩm nước mắm sau khi chiên", 1, R.drawable.com_ga_chien_mam);
        db.insertProduct("Cơm thịt bò xào cần tây", 45000, "Sự kết hợp giữa hạt gạo ST25 thơm ngon cùng với thịt bò xào cần tây tạo ra món ăn đầy đủ dinh dưỡng sau giờ làm việc", 1, R.drawable.com_bo_xao_can_tay);
        db.insertProduct("Cơm thịt luộc cà pháo", 43000, "Sự kết hợp giữa hạt gạo ST25 thơm ngon cùng với hương vị thanh đạm của thịt heo và nồng cháy của cà pháo", 1, R.drawable.thit_luoc_ca_phao);
        db.insertProduct("Cơm sườn non kho khóm", 43000, "Sự kết hợp giữa hạt gạo ST25 thơm ngon cùng với những miếng sườn non được chọn lọc và khóm tươi giúp mang lại năng lượng sau giờ làm việc", 1, R.drawable.suon_non_kho_khom);

        db.insertCategory("Mì - Nui - Cháo", R.drawable.mi_xao_bo);
        db.insertProduct("Mì xào thịt bò", 25000, "", 2, R.drawable.mi_xao_bo);
        db.insertProduct("Mì tươi súp sườn bò hầm", 30000, "", 2, R.drawable.mi_tuoi_sup_bo);
        db.insertProduct("Nui xào trứng", 25000, "", 2, R.drawable.nui_xao_trung);
        db.insertProduct("Nui súp sườn bò hầm", 25000, "", 2, R.drawable.nui_sup_bo);
        db.insertProduct("Cháo sườn", 25000, "", 2, R.drawable.chao_suon);
        db.insertProduct("Cháo trắng dưa mắm thịt bằm", 25000, "", 2, R.drawable.chao_trang_dua_mam);
        db.insertProduct("Cháo đậu xanh thịt bằm", 25000, "", 2, R.drawable.chao_dau_xanh_thit_bam);

        db.insertCategory("Xôi 3 miền", R.drawable.xoi_thap_cam);
        db.insertProduct("Xôi thập cẩm", 25000, "", 3, R.drawable.xoi_thap_cam);
        db.insertProduct("Xôi đùi gà quay", 30000, "", 3, R.drawable.xoi_dui_ga);
        db.insertProduct("Xôi gấc đậu phộng mè", 20000, "", 3, R.drawable.xoi_gac);
        db.insertProduct("Xôi xéo", 25000, "", 3, R.drawable.xoi_xeo);

        db.insertCategory("Đồ uống", R.drawable.tra_dao_cam_xa);
        db.insertProduct("Trà đào cam xả", 30000, "Trà đào kết hợp cùng cam và xả", 4, R.drawable.tra_dao_cam_xa);
        db.insertProduct("Trà trái cây thạch thủy tinh", 30000, "Trà kết hợp cùng các loại nước ép trái cây cùng hạt thủy tinh giòn tan", 4, R.drawable.tra_trai_cay);
        db.insertProduct("Sữa tươi trân châu đường đen", 30000, "Sữa tươi cùng công thức pha chế trân châu đặc biệt ", 4, R.drawable.sua_tuoi_duong_den);
        db.insertProduct("Trà sữa truyền thống", 30000, "Thức uống trending của giới trẻ, trà sữa béo ngậy nguyên bản", 4, R.drawable.tra_sua);

        db.insertCategory("Ăn vặt", R.drawable.cha_chien);
        db.insertProduct("Đồ chiên thập cẩm", 30000, "10 loại đồ chiên được mix ngẫu nhiên", 5, R.drawable.cha_chien);
        db.insertProduct("Kimbap Hàn Quốc", 30000, "Cuộn Kimbap chắc chắn, bao no nhe", 5, R.drawable.kimbap);
        db.insertProduct("Salad trái cây", 25000, "Sốt mayonnaise béo ngậy phủ lên saled tươi ngon", 5, R.drawable.salad);
        db.insertProduct("Bánh tráng muối nhuyễn", 15000, "Món ăn gây nghiện cho người thử lần đầu", 5, R.drawable.banh_trang_muoi_nhuyen);
        db.insertProduct("Xoài lắc chua ngọt", 20000, "Kích thích vị giác cùng xoài lắc chua ngọt", 5, R.drawable.xoai_lac);

        db.insertCategory("Đồ sấy khô", R.drawable.thap_cam_say);
        db.insertProduct("Đồ sấy thập cẩm", 35000, "", 6, R.drawable.thap_cam_say);
        db.insertProduct("Mít sấy", 25000, "", 6, R.drawable.mit_say);
        db.insertProduct("Chuối sấy", 25000, "", 6, R.drawable.chuoi_say);
        db.insertProduct("Rau củ sấy", 25000, "", 6, R.drawable.rau_cu_say);
        db.insertProduct("Hạt dẻ", 35000, "", 6, R.drawable.hat_de_cuoi);
        db.insertProduct("Hạt bí", 15000, "", 6, R.drawable.hat_bi);




        sharedPreferencesCart = getSharedPreferences("dataCart", MODE_PRIVATE);
        String query = "SELECT * FROM PRODUCT";
        int count = 0;
        Cursor allProduct = db.GetData(query);


        txtSignUp = (TextView) findViewById(R.id.txtSingUp);
        login = (Button)findViewById(R.id.btnLogin);
        usernameLogin = (EditText)findViewById(R.id.username);
        passwordLogin = (EditText)findViewById(R.id.password);



        //Sign Up
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();
                if(username.equals("") || password.equals("")){
                    Toast.makeText(getBaseContext(), "Nhập tên đăng nhập và password luôn bạn iu <3 ơi!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String query = "SELECT * FROM USERS";
                    Cursor cursor = db.GetData(query);
                    while (cursor.moveToNext()){
                        if(cursor.getString(1).equals(username) && cursor.getString(2).equals(password)){
                            Toast.makeText(getBaseContext(), "Đăng nhập thành công rồi nè!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                            startActivity(intent);
                            sharedPreferencesUser = getSharedPreferences("dataLogin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferencesUser.edit();
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putString("name", cursor.getString(4));
                            editor.putString("email", cursor.getString(3));
                            editor.putString("phone", cursor.getString(5));
                            editor.putInt("id", cursor.getInt(0));
                            editor.commit();
                            break;
                        }
                    }
                }
            }
        });
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camera);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
            byte[] image = byteArray.toByteArray();
             sharedPreferencesUser = getSharedPreferences("dataLogin", MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPreferencesUser.edit();
             editor.putString("name", personName);
             editor.putString("email", personEmail);
            //editor.put
             editor.commit();
             int flag = 0;
            Cursor user = db.GetData("SELECT * FROM USERS");
            while(user.moveToNext()){
                if(user.getString(3).equals(personEmail))
                {
                   flag = 1;
                   break;

                }
            }
            if(flag == 0)
            {
                db.insertUser(new User("", "", personEmail, personName, "", 0, image));
            }

             Intent intent = new  Intent(getBaseContext(), HomePageActivity.class);
            //intent.putExtra("urip", personPhoto);
             startActivity(intent);
            //ch.setImageURI(personPhoto);
        }


    }


    public Bitmap getBitmap(String path) {
        Bitmap bitmap=null;
        try {
            File f= new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap ;
    }
}