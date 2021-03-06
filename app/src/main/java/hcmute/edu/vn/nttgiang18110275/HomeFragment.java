package hcmute.edu.vn.nttgiang18110275;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private Database db;
    private SQLiteDatabase sqLiteDatabase;
    ListView lvAddress;
    ArrayList<Address> arrayAddress;
    AddressAdapter addressAdapter;

    //TextView textViewAddress;
    int dem = 0;
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        lvAddress = (ListView)rootView.findViewById(R.id.listAddress);

        arrayAddress = new ArrayList<>();
        db = new Database(getContext());
        sqLiteDatabase = db.getReadableDatabase();
        Cursor dataAddressStore = db.GetData("SELECT * FROM ADDRESS");

        while (dataAddressStore.moveToNext()){
            arrayAddress.add(new Address(dataAddressStore.getInt(0),
                    dataAddressStore.getString(1),
                    dataAddressStore.getInt(2)
            ));
        }
        addressAdapter = new AddressAdapter(getContext(), R.layout.layout_custome, arrayAddress);

        lvAddress.setAdapter(addressAdapter);
        //addressAdapter.notifyDataSetChanged();
        //lvAddress.setTextFilterEnabled(true);

        lvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = arrayAddress.get(position);
                Toast.makeText(rootView.getContext(), address.getDescription(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }


}