package com.example.administrator.demo.activity.adress;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.ImgBean;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

/**
 * 足迹添加
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

    private List<LocalMedia> selectList = new ArrayList<>();
    private String mIvPath;

    @Override
    protected int getLayout() {
        return R.layout.activity_zj;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.zj), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError() {
        showToast("请求失败");
    }

    @OnClick({R.id.tv_save, R.id.tv_do_cancel, R.id.iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                NiceDialog.init()
                        .setLayoutId(R.layout.dialog_photo_select)     //设置dialog布局文件
                        .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                            @Override
                            public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                                viewHolder.setOnClickListener(R.id.tv_take_photo, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        requestPermission(Permission.CAMERA, Permission.READ_PHONE_STATE, Permission.WRITE_EXTERNAL_STORAGE);
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

                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                    showToast("请输入标题");
                    return;
                }
                if (TextUtils.isEmpty(mIvPath)) {
                    showToast("请选择图片");
                    return;
                }
                upLoadFile(mIvPath);
                break;
            case R.id.tv_do_cancel:
                finish();
                break;
        }
    }

    /**
     * 权限申请
     *
     * @param permissions
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
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .glideOverride(100, 100)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .cropCompressQuality(30)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(30)// 小于100kb的图片不压缩
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 从本地相册中选择
     */
    private void doSelectPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or fals
                .maxSelectNum(1)
                .isGif(false)// 是否显示gif图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .compress(true)// 是否压缩 true or false
                .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = selectList.get(0);
                    String path = "";
                    if (media.isCut() && !media.isCompressed()) {
                        // 裁剪过
                        path = media.getCutPath();
                    } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                        // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                        path = media.getCompressPath();
                    } else {
                        // 原图
                        path = media.getPath();
                    }
                    mIvPath = path;
                    imageView.setPadding(0, 0, 0, 0);
                    ImageLoader.getInstance().loadingImage(this, path, imageView);
                    //提交图片
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
                        String integralNumber = sqBean.getFileId();
                        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
                        cMap.put("footprintName", editText.getText().toString().trim());
                        cMap.put("footprintImgSrc", integralNumber);
                        cPresenter.requestData(mContext, cMap, Address.add_footprint);
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
}
