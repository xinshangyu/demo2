package com.example.administrator.demo.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.demo.R;

import butterknife.BindView;

class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.ViewHolder> {


    private Onclike onclike;
    private Context context;

    public MyDataAdapter(MyDataActivity myDataActivity) {
        context = myDataActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_mydata, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclike.selectclike(i);
            }
        });

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclike.imageclike(i);
            }
        });
    }

    public interface Onclike{
        void selectclike(int i);

        void imageclike(int i);
    }

    public void setOncItemClike(Onclike onclike){
        this.onclike = onclike;
    }


    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView image;
        ImageView select;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            select = itemView.findViewById(R.id.select);
        }
    }
}
