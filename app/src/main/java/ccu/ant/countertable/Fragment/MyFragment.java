package ccu.ant.countertable.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.os.UserManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ccu.ant.countertable.Item.DummyItem;
import ccu.ant.countertable.R;

public class MyFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "HOME_PageFragment";
    Context mContext;

    //物件
    private TextView mIndexText;
    //傳遞過來的值
    private int Frag_Number;
    private String Frag_Name ;
    private String Frag_Orders ;
    private String Frag_Price ;
    private String Frag_Request ;
    private String Frag_Place ;
    private String Frag_Take_Number ;
    //與Activity溝通用
    OnHeadlineSelectedListener mCallback;


    //初始化時傳進來的參數
    public static final MyFragment newInstance(int num, String name, String orders, String price, String request, String place, String take_number){
        MyFragment f = new MyFragment();

        Bundle bd = new Bundle();

        bd.putInt("User_Number", num);
        bd.putString("User_Name", name);
        bd.putString("Orders", orders);
        bd.putString("Price", price);
        bd.putString("Request", request);
        bd.putString("Place", place);
        bd.putString("Take_Number", take_number);
        f.setArguments(bd);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    // This makes sure that the container activity has implemented
    // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            e.getMessage();
        }
    }

    //在 onCreate的時候把傳入的參數取出來
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Frag_Number = getArguments().getInt("User_Number");
        Frag_Name = getArguments().getString("User_Name");
        Frag_Orders = getArguments().getString("Order");
        Frag_Price = getArguments().getString("Price");
        Frag_Request = getArguments().getString("Request");
        Frag_Place = getArguments().getString("Place");
        Frag_Take_Number = getArguments().getString("Take_Number");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    //這邊是View Created的時候，可以處理內部的物件了
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        //---畫面物件初始化---
        mIndexText = (TextView) view.findViewById(R.id.Fragment_text);
        mIndexText.setText(""+ Frag_Number+": "+Frag_Name);

        FloatingActionButton delbut = (FloatingActionButton) getView().findViewById(R.id.delbut);
        delbut.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e("test: ", "button success!");
                if(!DummyItem.fragments.isEmpty()) {
                    //Log.e("number: ", DummyItem.fragments.get(User_Number-1).toString());
                    mCallback.onaitemSelected(Frag_Number-1);
                }
            }
        });
        FloatingActionButton master_add = (FloatingActionButton) getView().findViewById(R.id.master_add);
        master_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }


    //點擊事件
    @Override
    public void onClick(View v) {
    }

}