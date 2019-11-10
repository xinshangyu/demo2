package com.example.administrator.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.MyDataBean;
import com.example.baselibrary.zh.utils.BaseUtils;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.List;

public class FolderAdapter extends BaseQuickAdapter<MyDataBean.FolderBean, BaseViewHolder> {

    private boolean isShow;
    private List<MyDataBean.FolderBean> datas;

    public FolderAdapter(@Nullable List<MyDataBean.FolderBean> data) {
        super(R.layout.item_mydata, data);
        datas = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDataBean.FolderBean item) {
        ImageView imageView = helper.getView(R.id.iv);

        helper.setText(R.id.tv, item.getName())
                .setImageResource(R.id.select, item.isSelect() ? R.mipmap.yxz : R.mipmap.wxz)
                .addOnClickListener(R.id.select);
        if ("-1".equals(item.getId())) {
            helper.setGone(R.id.select, false);
            imageView.setImageResource(R.drawable.icon_mydata_add);
            int size = BaseUtils.dip2px(mContext, 40);
            imageView.setPadding(size, size, size, size);
        } else {
            imageView.setPadding(0, 0, 0, 0);
            helper.setGone(R.id.select, "2".equals(item.getType()) ? false : isShow);
            /* 取得扩展名 */
            String end = item.getName()
                    .substring(item.getName().lastIndexOf(".") + 1,
                            item.getName().length()).toLowerCase();
            helper.setText(R.id.tv_name, "2".equals(item.getType()) ? "文件夹" : end);
        }

    }

    public void setShow(boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    public boolean getShow() {
        return isShow;
    }
}
