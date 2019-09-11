package com.example.administrator.demo.dialog;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.QuickReturnTopEvent;
import com.example.administrator.demo.entity.SexBean;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.ViewHolder;

import org.greenrobot.eventbus.EventBus;

public class SexDialog extends BaseNiceDialog implements View.OnClickListener {
    private SexBean bean;

    public static SexDialog newInstance(SexBean param1) {
        SexDialog fragment = new SexDialog();
        Bundle args = new Bundle();
        args.putSerializable("bean", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_sex;
    }

    @Override
    public void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
        bean = (SexBean) getArguments().getSerializable("bean");
        viewHolder.setText(R.id.tv_take_photo, bean.getUserSex().get(0).getCodeValue());
        viewHolder.setText(R.id.tv_select_photo, bean.getUserSex().get(1).getCodeValue());

        viewHolder.setOnClickListener(R.id.tv_take_photo, this);
        viewHolder.setOnClickListener(R.id.tv_select_photo, this);
        viewHolder.setOnClickListener(R.id.tv_do_cancel, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo://女
                EventBus.getDefault().post(new QuickReturnTopEvent(bean.getUserSex().get(0).getCodeValue(), bean.getUserSex().get(0).getId()));
                dismiss();
                break;
            case R.id.tv_select_photo://男
                EventBus.getDefault().post(new QuickReturnTopEvent(bean.getUserSex().get(1).getCodeValue(), bean.getUserSex().get(1).getId()));
                dismiss();
                break;
            case R.id.tv_do_cancel:
                EventBus.getDefault().post(new QuickReturnTopEvent("",""));
                dismiss();
                break;
        }
    }
}
