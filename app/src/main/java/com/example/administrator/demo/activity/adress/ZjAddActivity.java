package com.example.administrator.demo.activity.adress;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.TrackAdapter;
import com.example.administrator.demo.entity.TrackBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 足迹
 */
public class ZjAddActivity extends BaseActivity implements CommonView {

    @BindView(R.id.et_number)
    EditText editText;
    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.tv_save)
    TextView tv1;
    @BindView(R.id.tv_do_cancel)
    TextView tv2;

    @Override
    protected int getLayout() {
        return R.layout.activity_zj;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onData(WeatherResult weatherResult) {

    }

    @OnClick({R.id.tv_save, R.id.tv_do_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                if(TextUtils.isEmpty(editText.getText().toString().trim())){
                    showToast("请输入标题");
                    NiceDialog.init()
                            .setLayoutId(R.layout.dialog_photo_select)     //设置dialog布局文件
                            .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                                @Override
                                public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                                    viewHolder.setOnClickListener(R.id.tv_take_photo, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    });
                                    viewHolder.setOnClickListener(R.id.tv_select_photo, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    });
                                }
                            }).setGravity(Gravity.BOTTOM)
                            .show(getSupportFragmentManager());
                    return;
                }

                break;
            case R.id.tv_do_cancel:
                finish();
                break;
        }
    }
}
