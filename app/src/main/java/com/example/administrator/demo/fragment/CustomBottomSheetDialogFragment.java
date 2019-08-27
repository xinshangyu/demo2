package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.administrator.demo.R;


public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetListener bottomSheetListener;
    private Button mBt1;
    private Button mBt2;

    private int flag;


    public static CustomBottomSheetDialogFragment newInstance(int flag){
        CustomBottomSheetDialogFragment fragment = new CustomBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("flag",flag);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_btmsheet, container, false);
        Bundle args = getArguments();

        mBt1 = v.findViewById(R.id.bt_all_delete);
        mBt2 = v.findViewById(R.id.bt_delete);


        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetListener != null){
                    bottomSheetListener.bottom1();
                    dismiss();
                }
            }
        });

        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetListener != null) {
                    bottomSheetListener.bottom2();
                    dismiss();
                }
            }
        });
        return v;
    }

    public void show(FragmentManager manager, String tag, BottomSheetListener bottomSheetListener) {
        super.show(manager, tag);
        this.bottomSheetListener = bottomSheetListener;

    }

    public interface BottomSheetListener{
        void bottom1();
        void bottom2();
    }
}
