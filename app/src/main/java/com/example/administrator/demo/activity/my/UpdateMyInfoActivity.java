package com.example.administrator.demo.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.dialog.SexDialog;
import com.example.administrator.demo.entity.ImgBean;
import com.example.administrator.demo.entity.MyModularBen;
import com.example.administrator.demo.entity.QuickReturnTopEvent;
import com.example.administrator.demo.entity.SexBean;
import com.example.administrator.demo.entity.UpdateUserInfoBean;
import com.example.administrator.demo.entity.XlBean;
import com.example.administrator.demo.utils.SPUtils;
import com.example.administrator.demo.utils.SoftKeyboardUtils;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ImageLoader;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人中心
 */
public class UpdateMyInfoActivity extends BaseActivity implements CommonView {

    @BindView(R.id.iv_my_head)
    ImageView ivMyHead;
    @BindView(R.id.rl_my_headImage)
    RelativeLayout rlMyHeadImage;
    @BindView(R.id.tv_nick)
    EditText tvNick;
    @BindView(R.id.et_nick)
    EditText et_nick;
    @BindView(R.id.rl_tv_nick_update)
    RelativeLayout rlTvNickUpdate;
    @BindView(R.id.rl_tv_nick)
    RelativeLayout rlTvNick;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_xl)
    TextView tvXl;
    @BindView(R.id.rl_xl)
    RelativeLayout rlXl;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_gsmc)
    TextView tvGsmc;
    @BindView(R.id.tv_bm)
    TextView tvBm;
    @BindView(R.id.tv_byyx)
    EditText tvByyx;
    @BindView(R.id.tv_zy)
    EditText tvZy;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.tv_email)
    EditText tvEmail;

    private Map<String, String> paramMap;
    private String mCompressPath;
    private String sex;
    private MyModularBen.DataBean.UserInfoBean mUserInfo;
    private String opt2tx;
    private String integralNumber;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.mydata), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        mUserInfo = SPUtils.getUserInfo(mContext);
        if (mUserInfo != null) {
            String userPhoto = mUserInfo.getUserPhoto();
            ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + userPhoto, ivMyHead,
                    new MultiTransformation(new CircleCrop()), R.drawable.defaulthead);

        }

        EventBus.getDefault().register(this);
        cMap.put("userId", "" + SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.edit);
    }


    @OnClick({R.id.iv_my_head, R.id.rl_my_headImage, R.id.rl_sex, R.id.rl_xl, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_head:
            case R.id.rl_my_headImage:
                if (SoftKeyboardUtils.isSoftShowing(this)) {
                    SoftKeyboardUtils.showORhideSoftKeyboard(this);
                }
                // TODO: 2019/9/10 头像选择
                doSelectHead();

                break;
            case R.id.rl_sex:
                if (SoftKeyboardUtils.isSoftShowing(this)) {
                    SoftKeyboardUtils.showORhideSoftKeyboard(this);
                }
                paramMap = new HashMap<>();
                paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
                RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.userSex, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
                    @Override
                    public void onBeforeResult() {

                    }

                    @Override
                    public void onResult(WeatherResult weatherResult) {
                        String json = new Gson().toJson(weatherResult);
                        LogUtil.e("返回数据" + json);
                        if (weatherResult.getCode() == 200) {
                            SexBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), SexBean.class);
                            if (sqBean != null && sqBean.getUserSex().size() > 0) {
                                SexDialog homeUnpaidHintDialog = SexDialog.newInstance(sqBean);
                                homeUnpaidHintDialog.show(getSupportFragmentManager());
                            }
                        } else {
                            ToastUtils.showShort(mContext, "" + weatherResult.getMsg());
                        }
                    }

                    @Override
                    public void onAfterFailure() {
                        showToast("请求失败");
                    }
                });

                break;
            case R.id.rl_xl:
                if (SoftKeyboardUtils.isSoftShowing(this)) {
                    SoftKeyboardUtils.showORhideSoftKeyboard(this);
                }
                paramMap = new HashMap<>();
                paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
                RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.educationBackground, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
                    @Override
                    public void onBeforeResult() {

                    }

                    @Override
                    public void onResult(WeatherResult weatherResult) {
                        String json = new Gson().toJson(weatherResult);
                        LogUtil.e("返回数据" + json);
                        if (weatherResult.getCode() == 200) {
                            XlBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), XlBean.class);
                            if (sqBean != null && sqBean.getEducationBackground().size() > 0) {
                                setFKLX(sqBean.getEducationBackground());
                            }
                        } else {
                            ToastUtils.showShort(mContext, "" + weatherResult.getMsg());
                        }
                    }

                    @Override
                    public void onAfterFailure() {
                        showToast("请求失败");
                    }
                });

                break;
            case R.id.tv_save:
                paramMap = new HashMap<>();
                paramMap.put("id", SharedPreferencesHelper.getPrefString("userId", ""));
                paramMap.put("userName", tvNick.getText().toString());
                paramMap.put("nickName", et_nick.getText().toString());
                paramMap.put("userPhoto", integralNumber);

                if (TextUtils.isEmpty(sex)) {
                    String opt = (String) SPUtils.get("sex", "");
                    paramMap.put("userSex", opt);
                } else {
                    SPUtils.put("sex", sex);
                    paramMap.put("userSex", sex);
                }

                paramMap.put("graduationSchool", tvByyx.getText().toString());

                if (TextUtils.isEmpty(opt2tx)) {
                    String opt = (String) SPUtils.get("opt", "");
                    paramMap.put("educationBackground", opt);
                } else {
                    SPUtils.put("opt", opt2tx);
                    paramMap.put("educationBackground", opt2tx);
                }
                paramMap.put("profession", tvZy.getText().toString());
                paramMap.put("homeSite", tvAddress.getText().toString());

                RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.save, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
                    @Override
                    public void onBeforeResult() {

                    }

                    @Override
                    public void onResult(WeatherResult weatherResult) {
                        String json = new Gson().toJson(weatherResult);
                        if (weatherResult.getCode() == 200) {
                            showToast("保存成功");
                            finish();
                        } else {
                            ToastUtils.showShort(mContext, "" + weatherResult.getMsg());
                        }
                    }

                    @Override
                    public void onAfterFailure() {
                        showToast("请求失败");
                    }
                });


                break;
        }
    }

    /**
     * 学历
     *
     * @param educationBackground
     */
    private void setFKLX(List<XlBean.EducationBackgroundBean> educationBackground) {
        List<String> options1Items = new ArrayList<>();
        List<String> options = new ArrayList<>();
        for (int i = 0; i < educationBackground.size(); i++) {
            options1Items.add(educationBackground.get(i).getCodeValue());
            options.add(educationBackground.get(i).getId());
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(UpdateMyInfoActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ? options1Items.get(options1) : "";
                opt2tx = options.size() > 0 ? options.get(options1) : "";
                String tx = opt1tx;
                tvXl.setText(tx);
            }
        })
                .setSubmitColor(Color.parseColor("#FF0000"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#FF0000"))//取消按钮文字颜色
                .setTitleBgColor(Color.parseColor("#FFFFFF"))
                .setTitleText("请选择")
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setBgColor(Color.parseColor("#F5F5F5"))
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items);//三级选择器
        pvOptions.show();

    }


    @Override
    public void onData(WeatherResult weatherResult) {
        UpdateUserInfoBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), UpdateUserInfoBean.class);
        if (sqBean != null && sqBean.getUserInfo() != null) {
            tvNick.setText("" + sqBean.getUserInfo().getUserName());
            tvSex.setText("" + sqBean.getUserInfo().getUserSexValue());
            et_nick.setText("" + sqBean.getUserInfo().getNickName());
            tvByyx.setText("" + sqBean.getUserInfo().getGraduationSchool());
            tvXl.setText("" + sqBean.getUserInfo().getEducationBackgroundValue());
            tvGsmc.setText("" + sqBean.getUserInfo().getOrgInfo().getCompanyName());
            tvBm.setText("" + sqBean.getUserInfo().getOrgInfo().getDeptName());
            tvZy.setText("" + sqBean.getUserInfo().getProfession());
            tvAddress.setText("" + sqBean.getUserInfo().getHomeSite());
        }
    }

    @Override
    public void onError() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvents(QuickReturnTopEvent event) {
        if ("女".equals(event.current)) {
            tvSex.setText("女");
            sex = event.current2;
        } else if ("男".equals(event.current)) {
            tvSex.setText("男");
            sex = event.current2;
        } else {
            tvSex.setText("");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //选择头像
    private void doSelectHead() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_photo_select)     //设置dialog布局文件
                .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                    @Override
                    public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                        viewHolder.setOnClickListener(R.id.tv_take_photo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                requestPermission(Permission.CAMERA);
                            }
                        });
                        viewHolder.setOnClickListener(R.id.tv_select_photo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                doSelectPhoto();
                            }
                        });
                        viewHolder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        viewHolder.setOnClickListener(R.id.rl_image, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setGravity(Gravity.BOTTOM)
                .show(getSupportFragmentManager());
    }

    /**
     * Request permissions.
     */
    private void requestPermission(String... permissions) {
        AndPermission.with(this).runtime().permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        doTakePhoto();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        }
                    }
                }).start();
    }

    /**
     * 相机拍照
     */
    private void doTakePhoto() {
        PictureSelector.create(UpdateMyInfoActivity.this)
                .openCamera(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .glideOverride(100, 100)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .cropCompressQuality(30)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(30)// 小于100kb的图片不压缩
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    //从本地相册中选择
    private void doSelectPhoto() {
        PictureSelector.create(UpdateMyInfoActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or fals
                .isGif(false)// 是否显示gif图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .glideOverride(100, 100)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .cropCompressQuality(30)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(30)// 小于100kb的图片不压缩
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        if (media.isCompressed()) {
                            mCompressPath = media.getCompressPath();
                        } else {
                            mCompressPath = media.getPath();
                        }
                    }

                    ImageLoader.getInstance().loadCircleWithBorderImage(mContext, mCompressPath, 1, R.color.colorWhite, ivMyHead);
                    if (mCompressPath != null && mCompressPath.length() != 0) {
                        //将图片传给服务器
                        upLoadFile(mCompressPath);
                    }
                    break;
            }
        }
    }

    /**
     * //将图片传给服务器
     */
    private void upLoadFile(String compressPath) {
        File file = new File(compressPath);
        RetrofitRequest.fileUpload(ApiKeys.getApiUrl() + Address.uploadFile, file, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("返回数据" + json);
                if (weatherResult.getCode() == 200) {
                    ImgBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), ImgBean.class);
                    if (sqBean != null) {
                        integralNumber = sqBean.getFileId();
                        mUserInfo.setUserPhoto(integralNumber);
                        SPUtils.setUserInfo(getApplicationContext(), JSONObject.toJSONString(mUserInfo));
                        showToast("正在更新头像");
                        setImage(integralNumber);
                    }
                } else {
                    ToastUtils.showShort(mContext, "" + weatherResult.getMsg());
                }
            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }

    /**
     * 设置图片
     */
    private void setImage(String integralNumber) {
        ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + integralNumber, ivMyHead,
                new MultiTransformation(new CircleCrop()), R.drawable.defaulthead);
    }
}
