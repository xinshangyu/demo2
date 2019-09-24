package com.example.administrator.demo.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.Const;
import com.example.administrator.demo.entity.ImgBean;
import com.example.administrator.demo.entity.MyModularBen;
import com.example.administrator.demo.utils.SPUtils;
import com.example.administrator.demo.weight.FullyGridLayoutManager;
import com.example.administrator.demo.weight.GridImageAdapter;
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
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 意见反馈
 */
public class CallBackActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> upList = new ArrayList<>();
    private List<String> lastSub = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.feedback), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initPicture();
    }

    private void initPicture() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(CallBackActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(CallBackActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(CallBackActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(CallBackActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(CallBackActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
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
    };

    /**
     * 相机拍照
     */
    private void doTakePhoto() {
        PictureSelector.create(CallBackActivity.this)
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
        PictureSelector.create(CallBackActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or fals
                .maxSelectNum(9)
                .isGif(false)// 是否显示gif图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .compress(true)// 是否压缩 true or false
                .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    //提交图片

                    break;
            }
        }
    }


    @Override
    protected void initDate() {
//        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
//        cMap.put("content", editContent.getText().toString());//
//        cMap.put("visitTel", etNumber.getText().toString());// TODO: 2019/8/28 意见反馈
//        cMap.put("imgIds", "");
//
//        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.feedbackProblem, cMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
//            @Override
//            public void onBeforeResult() {
//
//            }
//
//            @Override
//            public void onResult(WeatherResult weatherResult) {
//                LogUtil.e("返回数据" + new Gson().toJson(weatherResult));
//                if (weatherResult.getCode() == 200) {
//
//
//                }
//            }
//
//            @Override
//            public void onAfterFailure() {
//
//            }
//        });


    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        subPics();


    }

    private String video_id = "";
    private String id;


    private void subPics() {
        if (SPUtils.contains(Const.TALK_TYPE))
            SPUtils.remove(Const.TALK_TYPE);

        if (selectList.size() > 0) {
            upList.addAll(selectList);
            String path;
            List<File> files = new ArrayList<>();
            for (int i = 0; i < upList.size(); i++) {
                LocalMedia media = upList.get(i);
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
//                lastSub.add(media.getPath());
                File file = new File(path);
                files.add(file);

            }

            RetrofitRequest.fileUploads(ApiKeys.getApiUrl() + Address.uploadFiles, files, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {


                @Override
                public void onBeforeResult() {

                }

                @Override
                public void onResult(WeatherResult weatherResult) {
                    String json = new Gson().toJson(weatherResult);
                    LogUtil.e("返回数据" + json);
                    if (weatherResult.getCode() == 200) {
                        ImgBean sqBean = new Gson().fromJson(new Gson().toJson(weatherResult.getData()), ImgBean.class);
                        if (sqBean != null) {
                            showToast("上传成功id = " + sqBean.getFileId());

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
}
