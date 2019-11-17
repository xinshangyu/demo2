package com.example.administrator.demo.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.FolderAdapter;
import com.vincent.filepicker.filter.entity.MyDataBean;
import com.example.administrator.demo.utils.DateUtil;
import com.example.administrator.demo.utils.FileUtil;
import com.example.administrator.demo.utils.FileUtils;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.base.BaseActivity;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的资料
 */
public class FolderActivity extends BaseActivity {

    public static void callActivity(Context context, String title, MyDataBean myDataBean) {
        Intent intent = new Intent(context, FolderActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("data", myDataBean);
        context.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private List<MyDataBean> mBeanList = new ArrayList<>();
    private List<MyDataBean.FolderBean> mFileList = new ArrayList<>();
    private MyDataBean mDataBean;
    private FolderAdapter mAdapter;
    private String userId;

    @Override
    protected int getLayout() {
        return R.layout.activity_folder;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarColor(R.color.themeColor);
        setTitleBar(getIntent().getStringExtra("title"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, true, "编辑");
        mToolbar.setBackgroundColor(getColorV4(R.color.themeColor));

    }

    @Override
    protected void initDate() {
        userId = SharedPreferencesHelper.getPrefString("userId", "");

        getFlieData();

        mDataBean = getIntent().getParcelableExtra("data");

        if (mDataBean != null && mDataBean.getFolderBeans() != null && mDataBean.getFolderBeans().size() > 0) {
            mAdapter = new FolderAdapter(mDataBean.getFolderBeans());
            GridLayoutManager manager = new GridLayoutManager(FolderActivity.this, 3);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    MyDataBean.FolderBean bean = (MyDataBean.FolderBean) adapter.getItem(position);
                    if (view.getId() == R.id.select) {
                        bean.setSelect(!bean.isSelect());
                        if (bean.isSelect()) {
                            mFileList.add(bean);
                        } else {
                            mFileList.remove(bean);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


    }

    /**
     * 获取对应客户数据
     */
    private void getFlieData() {
        Type type = new TypeToken<List<MyDataBean>>() {
        }.getType();
        String files = SharedPreferencesHelper.getPrefString("files", "");
        List<MyDataBean> arrayList = null;
        if (!TextUtils.isEmpty(files)) {
            arrayList = gson.fromJson(files, type);
            if (arrayList != null && arrayList.size() > 0) {
                for (MyDataBean bean : arrayList) {
                    if(userId.equals(bean.getUserId())){
                        bean.setSelect(false);
                        mBeanList.add(bean);
                    }
                }
            }
        }
    }

    @OnClick({R.id.common_toolBar_text_right, R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4})
    public void onClick(View view) {
        if(mAdapter == null){
            showToast("请先选择文件！");
            return;
        }
        switch (view.getId()) {
            case R.id.common_toolBar_text_right://编辑
                compile();
                break;
            case R.id.tv1://修改分组名称
                changeName();
                break;
            case R.id.tv2://删除分组
                deleteGroup();
                break;
            case R.id.tv3://移动到
                moveToGroup();
                break;
            case R.id.tv4://移出分组
                movePullGroup();
                break;
        }
    }

    /**
     * 移出分组
     */
    private void movePullGroup() {
        if (mFileList.size() <= 0) {
            showToast("请选择文件");
            return;
        }
        for (MyDataBean.FolderBean folderBean : mFileList) {
            MyDataBean bean = new MyDataBean();
            bean.setName(folderBean.getName());
            bean.setId(DateUtil.getDateShortSerialYY());
            bean.setPath("");
            bean.setUserId(userId);
            bean.setNewPath(FileUtils.getCacheMD() + folderBean.getName());
            bean.setType("1");//1代表文件
            mBeanList.add(mBeanList.size() - 1, bean);
            mDataBean.getFolderBeans().remove(folderBean);
            FileUtil.copyFile(folderBean.getPath(), bean.getNewPath());
        }
        for (MyDataBean bean : mBeanList) {
            if (bean.getId().equals(mDataBean.getId())) {
                bean.setFolderBeans(mDataBean.getFolderBeans());
            }
        }

        SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
        finish();
    }

    /**
     * 移动到
     */
    private void moveToGroup() {
        if (mFileList.size() <= 0) {
            showToast("请选择文件");
            return;
        }
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_files_list)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                        recyclerView.setLayoutManager(layoutManager);
                        ArrayList<MyDataBean> datas = new ArrayList<>();
                        MyDataBean myDataBean = new MyDataBean();
                        myDataBean.setName("新建分组");
                        myDataBean.setUserId(userId);
                        myDataBean.setId("-2");//-2代表新建分组
                        myDataBean.setType("2");//2代表文件夹
                        datas.add(myDataBean);
                        for (MyDataBean bean :
                                mBeanList) {
                            if ("2".equals(bean.getType()) && !mDataBean.getId().equals(bean.getId())) {
                                datas.add(bean);
                            }
                        }
                        BaseQuickAdapter<MyDataBean, BaseViewHolder> adapter = new BaseQuickAdapter<MyDataBean, BaseViewHolder>(R.layout.item_files, datas) {
                            @Override
                            protected void convert(BaseViewHolder helper, MyDataBean item) {
                                helper.setText(R.id.tv_name, item.getName());
                                TextView textView = helper.getView(R.id.tv_name);
                                textView.setTextColor(getResources().getColor("-2".equals(item.getId()) ? R.color.colorText_back : R.color.huise2));
                            }
                        };
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                                if ("-2".equals(bean.getId())) {//新建文件夹
                                    dialog.dismiss();
                                    newFolderDialog();
                                } else {
                                    //移动文件
                                    for (MyDataBean.FolderBean mBean :
                                            mFileList) {
                                        for (MyDataBean.FolderBean dataBean :
                                                mDataBean.getFolderBeans()) {
                                            if (dataBean.getId().equals(mBean.getId())) {
                                                dataBean.setSelect(false);
                                                bean.getFolderBeans().add(dataBean);
                                                mDataBean.getFolderBeans().remove(dataBean);
                                                FileUtil.copyFile(dataBean.getPath(), bean.getPath() + dataBean.getName());
                                                break;
                                            }
                                        }
                                    }
                                    for (MyDataBean dataBean :
                                            mBeanList) {
                                        if (dataBean.getId().equals(mDataBean.getId())) {
                                            dataBean.setFolderBeans(mDataBean.getFolderBeans());
                                            break;
                                        }
                                    }
                                    mAdapter.notifyDataSetChanged();
                                    SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
                                    mFileList.clear();
                                    dialog.dismiss();
                                    FolderActivity.callActivity(FolderActivity.this, bean.getName(), bean);
                                    finish();
                                }
                            }
                        });
                        recyclerView.setAdapter(adapter);

                        holder.setOnClickListener(R.id.btn_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setMargin(30)
                .show(getSupportFragmentManager());
    }

    /**
     * 删除分组
     */
    private void deleteGroup() {
        for (MyDataBean.FolderBean folderBean : mDataBean.getFolderBeans()) {
            MyDataBean bean = new MyDataBean();
            bean.setName(folderBean.getName());
            bean.setId(DateUtil.getDateShortSerialYY());
            bean.setPath("");
            bean.setUserId(userId);
            bean.setNewPath(FileUtils.getCacheMD() + folderBean.getName());
            bean.setType("1");//1代表文件
            mBeanList.add(mBeanList.size() - 1, bean);
            FileUtil.copyFile(folderBean.getPath(), bean.getNewPath());
        }
        new File(mDataBean.getPath()).delete();
        for (MyDataBean bean : mBeanList) {
            if (bean.getId().equals(mDataBean.getId())) {
                mBeanList.remove(bean);
                break;
            }
        }
        String json = gson.toJson(mBeanList);
        SharedPreferencesHelper.setPrefString("files", json);
        finish();
    }

    /**
     * 修改分组名
     */
    private void changeName() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_add_show)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editText = holder.getView(R.id.et_content);
                                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                                    showToast("请输入文件名");
                                    return;
                                }
                                String folderPath = FileUtils.getUserNew(editText.getText().toString().trim());
                                for (MyDataBean bean : mBeanList) {
                                    if (bean.getId().equals(mDataBean.getId())) {
                                        bean.setPath(folderPath);
                                        bean.setName(editText.getText().toString().trim());
                                        new File(bean.getPath()).renameTo(new File(folderPath));
                                        break;
                                    }
                                }

                                for (MyDataBean.FolderBean folderBean : mDataBean.getFolderBeans()) {
                                    FileUtil.copyFile(folderBean.getPath(), folderPath + folderBean.getName());
                                }
                                new File(mDataBean.getPath()).delete();
                                mTextTitle.setText(editText.getText().toString().trim());
                                SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
                                showToast("修改成功");
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setMargin(30)
                .show(getSupportFragmentManager());
    }

    /**
     * 编辑
     */
    private void compile() {
        if (mAdapter.getShow()) {
            mTextRight.setText("编辑");
            mAdapter.setShow(false);
            llTop.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            mFileList.clear();
        } else {
            mTextRight.setText("取消");
            mAdapter.setShow(true);
            llTop.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 创建新文件夹
     */
    private void newFolderDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_add_show)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editText = holder.getView(R.id.et_content);
                                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                                    showToast("请输入文件名");
                                    return;
                                }
                                String folderPath = FileUtils.getUserNew(editText.getText().toString().trim());
                                //将新文件夹放到集合里面
                                MyDataBean myDataBean = new MyDataBean();
                                myDataBean.setName(editText.getText().toString().trim());
                                myDataBean.setId(DateUtil.getDateShortSerialYY());
                                myDataBean.setType("2");//2代表文件夹
                                myDataBean.setPath(folderPath);
                                myDataBean.setUserId(userId);
                                MyDataBean.FolderBean folderBean = null;
                                //将最外层文件移动到文件夹里
                                for (MyDataBean.FolderBean bean :
                                        mFileList) {
                                    for (MyDataBean.FolderBean dataBean :
                                            mDataBean.getFolderBeans()) {
                                        if (dataBean.getId().equals(bean.getId())) {
                                            folderBean = new MyDataBean.FolderBean();
                                            folderBean.setName(dataBean.getName());
                                            folderBean.setId(DateUtil.getDateShortSerialYY());
                                            folderBean.setPath(folderPath + dataBean.getName());
                                            folderBean.setType("3");//3代表文件夹内的文件
                                            myDataBean.getFolderBeans().add(folderBean);
                                            FileUtil.copyFile(dataBean.getNewPath(), folderPath + dataBean.getName());
                                            break;
                                        }
                                    }
                                }

                                mBeanList.add(0, myDataBean);
                                mAdapter.notifyDataSetChanged();
                                SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
                                mFileList.clear();
                                FolderActivity.callActivity(FolderActivity.this, myDataBean.getName(), myDataBean);
                                showToast("创建成功");
                                dialog.dismiss();
                                finish();
                            }
                        });
                    }
                })
                .setMargin(30)
                .show(getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        if (mAdapter != null && mAdapter.getShow()) {
            mAdapter.setShow(false);
            mFileList.clear();
            for (MyDataBean bean : mBeanList) {
                bean.setSelect(false);
            }
            mTextRight.setText("编辑");
            llTop.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
