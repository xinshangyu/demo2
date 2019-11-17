package com.example.administrator.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.vincent.filepicker.filter.entity.MyDataBean;
import com.example.baselibrary.zh.utils.BaseUtils;

import java.util.List;

public class MyDataAdapter extends BaseQuickAdapter<MyDataBean, BaseViewHolder> {

    private boolean isShow;
    private boolean isSelect;
    private List<MyDataBean> datas;

    public MyDataAdapter( @Nullable List<MyDataBean> data) {
        super(R.layout.item_mydata, data);
        datas = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDataBean item) {
        ImageView imageView = helper.getView(R.id.iv);

        helper.setText(R.id.tv, item.getName())
                .setImageResource(R.id.select, item.isSelect() ? R.mipmap.yxz : R.mipmap.wxz)
                .addOnClickListener(R.id.select);
        if("-1".equals(item.getId())){//-1代表添加按钮
            helper.setGone(R.id.select, false);
            imageView.setImageResource(R.drawable.icon_mydata_add);
            int size = BaseUtils.dip2px(mContext, 40);
            imageView.setPadding(size, size, size, size);
            helper.setText(R.id.tv_name, "");
        }else{
            imageView.setPadding(0, 0, 0, 0);
            imageView.setImageResource(R.color.color_f2);
            helper.setGone(R.id.select, "2".equals(item.getType()) ? false : isShow);//2代表文件夹
            /* 取得扩展名 */
            String end = item.getName()
                    .substring(item.getName().lastIndexOf(".") + 1,
                            item.getName().length()).toLowerCase();
            helper.setText(R.id.tv_name, "2".equals(item.getType()) ? "文件夹" : end);
        }

    }

    public void setShow(boolean isShow){
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    public boolean getShow(){
        return isShow;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
