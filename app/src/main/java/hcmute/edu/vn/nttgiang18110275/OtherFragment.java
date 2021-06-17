package hcmute.edu.vn.nttgiang18110275;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvOrder;
    ArrayList<Booked> orderArrayList;
    private Database db;
    OrderAdapter orderAdapter;
    private SQLiteDatabase sqLiteDatabase;

    public OtherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    TextView order;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_other, container, false);

        lvOrder = (ListView) rootView.findViewById(R.id.lvOrder);
//        imageButtonBack = (ImageButton) rootView.findViewById(R.id.btnback);
        //
        db = new Database(inflater.getContext());
        sqLiteDatabase = db.getReadableDatabase();
//        Intent intent = getIntent();
        //name = getIntent().getStringExtra("name");
        orderArrayList = new ArrayList<>();

        String Query = "SELECT * FROM BOOKED";
        Cursor cursor = db.GetData(Query);

        while (cursor.moveToNext()){
            orderArrayList.add(new Booked(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3), cursor.getFloat(4)));
        }

        orderAdapter = new OrderAdapter(inflater.getContext(), R.layout.layout_custom_order, orderArrayList);

        lvOrder.setAdapter(orderAdapter);
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(inflater.getContext(), OrderDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        return rootView;
    }
}