package com.vincent.filepicker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baselibrary.SharedPreferencesHelper;
import com.vincent.filepicker.R;
import com.vincent.filepicker.ToastUtil;
import com.vincent.filepicker.Util;
import com.vincent.filepicker.filter.entity.Directory;
import com.vincent.filepicker.filter.entity.MyDataBean;
import com.vincent.filepicker.filter.entity.NormalFile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Woo
 * Date: 2016/10/26
 * Time: 10:23
 */

public class NormalFilePickAdapter extends BaseAdapter<NormalFile, NormalFilePickAdapter.NormalFilePickViewHolder> {
    private int mMaxNumber;
    private int mCurrentNumber = 0;
    private List<MyDataBean> datas = new ArrayList<>();
    private boolean isStop;//停止循环

    public NormalFilePickAdapter(Context ctx, int max) {
        this(ctx, new ArrayList<NormalFile>(), max);
    }

    public NormalFilePickAdapter(Context ctx, ArrayList<NormalFile> list, int max) {
        super(ctx, list);
        mMaxNumber = max;
    }

    @Override
    public NormalFilePickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.vw_layout_item_normal_file_pick, parent, false);
        return new NormalFilePickViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NormalFilePickViewHolder holder, final int position) {
        final NormalFile file = mList.get(position);

        holder.mTvTitle.setText(Util.extractFileNameWithSuffix(file.getPath()));
        holder.mTvTitle.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        if (holder.mTvTitle.getMeasuredWidth() >
                Util.getScreenWidth(mContext) - Util.dip2px(mContext, 10 + 32 + 10 + 48 + 10 * 2)) {
            holder.mTvTitle.setLines(2);
        } else {
            holder.mTvTitle.setLines(1);
        }

        if (file.isSelected()) {
            holder.mCbx.setSelected(true);
        } else {
            holder.mCbx.setSelected(false);
        }

        //是否已在书架
        for (MyDataBean bean:
             datas) {
            if("2".equals(bean.getType())){
                List<MyDataBean.FolderBean> folderBeans = bean.getFolderBeans();
                for (MyDataBean.FolderBean folderBean : folderBeans) {
                    if((!TextUtils.isEmpty(folderBean.getName()) && !TextUtils.isEmpty(file.getName()))
                            || (!TextUtils.isEmpty(folderBean.getName()) && !TextUtils.isEmpty(file.getPath()))){
                        if(folderBean.getName().startsWith(file.getName() != null ? file.getName() : getName(file.getPath()))){//如果name为空就取path
                            holder.mTvRight.setVisibility(View.VISIBLE);
                            holder.mCbx.setVisibility(View.GONE);
                            isStop = true;
                            break;
                        }else{
                            holder.mTvRight.setVisibility(View.GONE);
                            holder.mCbx.setVisibility(View.VISIBLE);
                        }
                    }else{
                        holder.mTvRight.setVisibility(View.GONE);
                        holder.mCbx.setVisibility(View.VISIBLE);
                    }
                }
                if(isStop){
                    isStop = !isStop;
                    break;
                }
            }else{
                if((!TextUtils.isEmpty(bean.getName()) && !TextUtils.isEmpty(file.getName()))
                        || (!TextUtils.isEmpty(bean.getName()) && !TextUtils.isEmpty(file.getPath()))){
                    if(bean.getName().startsWith(file.getName() != null ? file.getName() : getName(file.getPath()))){//如果name为空就取path
                        holder.mTvRight.setVisibility(View.VISIBLE);
                        holder.mCbx.setVisibility(View.GONE);
                        break;
                    }else{
                        holder.mTvRight.setVisibility(View.GONE);
                        holder.mCbx.setVisibility(View.VISIBLE);
                    }
                }else{
                    holder.mTvRight.setVisibility(View.GONE);
                    holder.mCbx.setVisibility(View.VISIBLE);
                }
            }

        }

        if (file.getPath().endsWith("xls") || file.getPath().endsWith("xlsx")) {
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_excel);
        } else if (file.getPath().endsWith("doc") || file.getPath().endsWith("docx")){
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_word);
        } else if (file.getPath().endsWith("ppt") || file.getPath().endsWith("pptx")){
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_ppt);
        } else if (file.getPath().endsWith("pdf")){
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_pdf);
        } else if (file.getPath().endsWith("txt")){
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_txt);
        } else {
            holder.mIvIcon.setImageResource(R.drawable.vw_ic_file);
        }

        holder.mCbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!v.isSelected() && isUpToMax()) {
                    ToastUtil.getInstance(mContext).showToast(R.string.vw_up_to_max);
                    return;
                }

                if (v.isSelected()) {
                    holder.mCbx.setSelected(false);
                    mCurrentNumber--;
                } else {
                    holder.mCbx.setSelected(true);
                    mCurrentNumber++;
                }

                mList.get(holder.getAdapterPosition()).setSelected(holder.mCbx.isSelected());

                if (mListener != null) {
                    mListener.OnSelectStateChanged(holder.mCbx.isSelected(), mList.get(holder.getAdapterPosition()));
                }
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("file://" + file.getPath());
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(uri, "audio/mp3");
//                if (Util.detectIntent(mContext, intent)) {
//                    mContext.startActivity(intent);
//                } else {
//                    Toast.makeText(mContext, "No Application exists for audio!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class NormalFilePickViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvIcon;
        private TextView mTvTitle;
        private ImageView mCbx;
        private TextView mTvRight;

        public NormalFilePickViewHolder(View itemView) {
            super(itemView);
            mIvIcon = (ImageView) itemView.findViewById(R.id.ic_file);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_file_title);
            mCbx = (ImageView) itemView.findViewById(R.id.cbx);
            mTvRight = (TextView) itemView.findViewById(R.id.tv_right);
        }
    }

    private boolean isUpToMax() {
        return mCurrentNumber >= mMaxNumber;
    }

    public void refresh(List<NormalFile> list, List<MyDataBean> datas) {
        this.datas = datas;
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    private String getName(String name){
        String end = name.substring(name.lastIndexOf("/") + 1, name.length()).toLowerCase();
        return end;
    }
}
