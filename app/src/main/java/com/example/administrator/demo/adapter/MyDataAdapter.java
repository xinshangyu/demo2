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
            imageView.setImageResource(R.color.white);
            helper.setGone(R.id.select, "2".equals(item.getType()) ? false : isShow);//2代表文件夹
            /* 取得扩展名 */
            String end = item.getName()
                    .substring(item.getName().lastIndexOf(".") + 1,
                            item.getName().length()).toLowerCase();
            if("2".equals(item.getType())){
                helper.setText(R.id.tv_name, "");
                helper.setGone(R.id.ll_folder, true);
                if(item.getFolderBeans().size() == 0){
                    helper.setVisible(R.id.tv1, false);
                    helper.setVisible(R.id.tv2, false);
                    helper.setVisible(R.id.tv3, false);
                    helper.setVisible(R.id.tv4, false);
                    helper.setText(R.id.tv1, "");
                    helper.setText(R.id.tv2, "");
                    helper.setText(R.id.tv3, "");
                    helper.setText(R.id.tv4, "");

                }else if(item.getFolderBeans().size() == 1){
                    helper.setVisible(R.id.tv1, true);
                    helper.setVisible(R.id.tv2, false);
                    helper.setVisible(R.id.tv3, false);
                    helper.setVisible(R.id.tv4, false);
                    helper.setText(R.id.tv1, item.getFolderBeans().get(0).getName());
                    helper.setText(R.id.tv2, "");
                    helper.setText(R.id.tv3, "");
                    helper.setText(R.id.tv4, "");

                }else if(item.getFolderBeans().size() == 2){
                    helper.setVisible(R.id.tv1, true);
                    helper.setVisible(R.id.tv2, true);
                    helper.setVisible(R.id.tv3, false);
                    helper.setVisible(R.id.tv4, false);
                    helper.setText(R.id.tv1, item.getFolderBeans().get(0).getName());
                    helper.setText(R.id.tv2, item.getFolderBeans().get(1).getName());
                    helper.setText(R.id.tv3, "");
                    helper.setText(R.id.tv4, "");
                }else if(item.getFolderBeans().size() == 3){
                    helper.setVisible(R.id.tv1, true);
                    helper.setVisible(R.id.tv2, true);
                    helper.setVisible(R.id.tv3, true);
                    helper.setVisible(R.id.tv4, false);
                    helper.setText(R.id.tv1, item.getFolderBeans().get(0).getName());
                    helper.setText(R.id.tv2, item.getFolderBeans().get(1).getName());
                    helper.setText(R.id.tv3, item.getFolderBeans().get(2).getName());
                    helper.setText(R.id.tv4, "");
                }else if(item.getFolderBeans().size() >= 4){
                    helper.setVisible(R.id.tv1, true);
                    helper.setVisible(R.id.tv2, true);
                    helper.setVisible(R.id.tv3, true);
                    helper.setVisible(R.id.tv4, true);
                    helper.setText(R.id.tv1, item.getFolderBeans().get(0).getName());
                    helper.setText(R.id.tv2, item.getFolderBeans().get(1).getName());
                    helper.setText(R.id.tv3, item.getFolderBeans().get(2).getName());
                    helper.setText(R.id.tv4, item.getFolderBeans().get(3).getName());
                }
            }else{
                helper.setText(R.id.tv_name, end);
                helper.setGone(R.id.ll_folder, false);
            }

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
