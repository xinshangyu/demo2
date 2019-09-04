package com.example.administrator.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;

import java.util.List;

/**
 * 文件名：充值  RechargeAdapter
 */
public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.Re> {

    private List<String> list;
    private Context context;
    private int selectPosition = 0;
//    private boolean ischeck = false;

    public int getSelectPosition() {
        return Integer.parseInt(list.get(selectPosition));
    }

    public RechargeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Re onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge, parent, false);
        Re holder = new Re(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RechargeAdapter.Re re, int i) {
        re.mTvMoney.setText(list.get(i)+context.getString(R.string.jindou));
        re.mTvMoney2.setText(list.get(i));
        re.mll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = re.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        if (selectPosition == re.getAdapterPosition()) {
            re.mll_check.setBackgroundResource(R.drawable.shape_pay_rect_solid);
            re.mTvMoney.setTextColor(context.getResources().getColor(R.color.colorWhite));
            re.mTvMoney2.setTextColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            re.mll_check.setBackgroundResource(R.drawable.shape_pay_rect);
            re.mTvMoney.setTextColor(context.getResources().getColor(R.color.colorBlack0));
            re.mTvMoney2.setTextColor(context.getResources().getColor(R.color.colorText_back));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Re extends RecyclerView.ViewHolder {

        private TextView mTvMoney;
        private TextView mTvMoney2;
        private LinearLayout mll_check;

        public Re(View itemView) {
            super(itemView);
            mll_check = itemView.findViewById(R.id.ll_check);
            mTvMoney = itemView.findViewById(R.id.tv_money);
            mTvMoney2 = itemView.findViewById(R.id.tv_money2);
        }
    }
}
