package com.example.administrator.demo.activity.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.MyDataAdapter;
import com.example.administrator.demo.entity.MyDataBean;
import com.example.administrator.demo.utils.DateUtil;
import com.example.administrator.demo.utils.FileUtil;
import com.example.administrator.demo.utils.FileUtils;
import com.example.administrator.demo.weight.GlideImageLoader;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.youth.banner.Banner;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyDataActivity extends BaseActivity {
    @BindView(R.id.home_search_edittext)
    EditText homeSearchEdittext;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.navigation_view)
    BottomNavigationView navigationView;
    @BindView(R.id.my)
    TextView my;

    private List<MyDataBean> mBeanList = new ArrayList<>();
    private MyDataBean bean;
    private MyDataAdapter mAdapter;
    private List<Integer> images = new ArrayList<>();
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    private List<MyDataBean> mFileList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_mydata;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarColor(R.color.themeColor);
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        GridLayoutManager manager = new GridLayoutManager(MyDataActivity.this,3);
        disableShiftMode(navigationView);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MyDataAdapter(mBeanList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if("-1".equals(bean.getId())){
                    requestPermission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE);
                }else if("2".equals(bean.getType())){

                    FolderActivity.callActivity(MyDataActivity.this, bean.getName(), bean);
                }else{
//                    OpenFileUtil.openFile(bean.getNewPath());
                }
            }
        });

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if("-1".equals(bean.getId()) || "2".equals(bean.getType())){
                    return false;
                }
                mAdapter.setShow(true);
                navigationView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if(view.getId() == R.id.select){
                    bean.setSelect(!bean.isSelect());
                    if(bean.isSelect()){
                        mFileList.add(bean);
                    }else{
                        mFileList.remove(bean);
                    }
                    mAdapter.notifyDataSetChanged();

                }
            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.m_delete) {
                    for (MyDataBean bean: mFileList) {
                        for(int i = 0; i < mBeanList.size(); i++){
                            if(bean.getId().equals(mBeanList.get(i).getId())){
                                mBeanList.remove(i);
                                FileUtils.deletePath(bean.getNewPath());
                                break;
                            }
                        }
                    }
                    mFileList.clear();
                    String json = gson.toJson(mBeanList);
                    SharedPreferencesHelper.setPrefString("files", json);
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.m_add) {

                    newFolderDialog();

                } else if (item.getItemId() == R.id.m_top) {
                    for (MyDataBean bean: mFileList) {
                        for(int i = 0; i < mBeanList.size(); i++){
                            if(bean.getId().equals(mBeanList.get(i).getId())){
                                mBeanList.remove(i);
                                break;
                            }
                        }
                    }
                    mBeanList.addAll(0, mFileList);
                    String json = gson.toJson(mBeanList);
                    SharedPreferencesHelper.setPrefString("files", json);
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.m_all) {
                    for (MyDataBean bean: mBeanList) {
                       bean.setSelect(true);
                    }
                    mFileList.clear();
                    mFileList.addAll(mBeanList);
                    mAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.m_move) {
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
                                    myDataBean.setId("-2");//-2代表新建分组
                                    myDataBean.setType("2");//2代表文件夹
                                    datas.add(myDataBean);
                                    for (MyDataBean bean:
                                         mBeanList) {
                                        if("2".equals(bean.getType())){
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
                                            if("-2".equals(bean.getId())){//新建文件夹
                                                dialog.dismiss();
                                                newFolderDialog();
                                            }else{
                                                MyDataBean.FolderBean folderBean;
                                                //将最外层文件移动到文件夹里
                                                for (MyDataBean mBean:
                                                        mFileList) {
                                                    for (MyDataBean dataBean:
                                                            mBeanList) {
                                                        if(dataBean.getId().equals(mBean.getId())){
                                                            folderBean = new MyDataBean.FolderBean();
                                                            folderBean.setName(dataBean.getName());
                                                            folderBean.setId(DateUtil.getDateShortSerialYY());
                                                            folderBean.setPath(bean.getPath() + dataBean.getName());
                                                            folderBean.setType("3");//3代表文件夹内的文件
                                                            bean.getFolderBeans().add(folderBean);
                                                            mBeanList.remove(dataBean);
                                                            FileUtil.copyFile(dataBean.getNewPath(), bean.getPath() + dataBean.getName());
                                                            break;
                                                        }
                                                    }
                                                }
                                                mAdapter.notifyDataSetChanged();
                                                SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
                                                mFileList.clear();
                                                dialog.dismiss();
                                                FolderActivity.callActivity(MyDataActivity.this, bean.getName(), bean);
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
                } else if (item.getItemId() == R.id.m_cancel) {
                    if(mAdapter.getShow()){
                        mAdapter.setShow(false);
                        mFileList.clear();
                        for (MyDataBean bean: mBeanList) {
                            bean.setSelect(false);
                        }
                        navigationView.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });


    }

    @Override
    protected void initDate() {
        Type type = new TypeToken<List<MyDataBean>>() {}.getType();
        String files = SharedPreferencesHelper.getPrefString("files", "");

        List<MyDataBean> arrayList = null;
        if(!TextUtils.isEmpty(files)){
            arrayList = gson.fromJson(files, type);
            if(arrayList != null && arrayList.size() > 0){
                mBeanList.addAll(arrayList);
                for (MyDataBean bean: mBeanList) {
                    bean.setSelect(false);
                }
            }
        }
        if(mBeanList.size() == 0){
            bean = new MyDataBean();
            bean.setName("");
            bean.setId("-1");
            bean.setNewPath("");
            bean.setPath("");
            bean.setType("1");//1代表文件
            mBeanList.add(bean);
        }
        mAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.terrace, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.terrace:
                //平台资料是

                break;
            case R.id.my:
                //我的资料

                break;
        }
    }

    private void newFolderDialog(){
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
                                if(TextUtils.isEmpty(editText.getText().toString().trim())){
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
                                MyDataBean.FolderBean folderBean = null;
                                //将最外层文件移动到文件夹里
                                for (MyDataBean bean:
                                     mFileList) {
                                    for (MyDataBean dataBean:
                                         mBeanList) {
                                        if(dataBean.getId().equals(bean.getId())){
                                            folderBean = new MyDataBean.FolderBean();
                                            folderBean.setName(dataBean.getName());
                                            folderBean.setId(DateUtil.getDateShortSerialYY());
                                            folderBean.setPath(folderPath + dataBean.getName());
                                            folderBean.setType("3");//3代表文件夹内的文件
                                            myDataBean.getFolderBeans().add(folderBean);

                                            mBeanList.remove(dataBean);
                                            FileUtil.copyFile(dataBean.getNewPath(), folderPath + dataBean.getName());
                                            break;
                                        }
                                    }
                                }

                                mBeanList.add(0, myDataBean);
                                mAdapter.notifyDataSetChanged();
                                SharedPreferencesHelper.setPrefString("files", gson.toJson(mBeanList));
                                mFileList.clear();
                                FolderActivity.callActivity(MyDataActivity.this, myDataBean.getName(), myDataBean);
                                showToast("创建成功");
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setMargin(30)
                .show(getSupportFragmentManager());
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
                        if(mAdapter.getShow()){
                            return;
                        }
                        new MaterialFilePicker()
                                .withActivity(MyDataActivity.this)
                                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                                .withHiddenFiles(true)
                                .withTitle("文件选择")
                                .start();

//                        Intent intent4 = new Intent(MyDataActivity.this, NormalFilePickActivity.class);
//                        intent4.putExtra(Constant.MAX_NUMBER, 1);
//                        intent4.putExtra(IS_NEED_FOLDER_LIST, true);
//                        intent4.putExtra(NormalFilePickActivity.SUFFIX,
//                                new String[] {"xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf", ".epub"});
//                        startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
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

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        int count = view.getChildCount();
        if (count > 0) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            if (menuView != null) {
                menuView.setLabelVisibilityMode(1);
                menuView.updateMenuView();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(filePath);
            if(!TextUtils.isEmpty(filePath)){
                String newPath = FileUtils.getCacheMD() + file.getName();
                FileUtil.copyFile(filePath, newPath);
                bean = new MyDataBean();
                bean.setName(file.getName());
                bean.setId(DateUtil.getDateShortSerialYY());
                bean.setPath(filePath);
                bean.setNewPath(newPath);
                bean.setType("1");//1代表文件
                mBeanList.add(0, bean);
                mAdapter.notifyDataSetChanged();
                String json = gson.toJson(mBeanList);
                SharedPreferencesHelper.setPrefString("files", json);
            }
            // Do anything with file
        }

//        switch (requestCode) {
//            case Constant.REQUEST_CODE_PICK_FILE:
//                if (resultCode == RESULT_OK) {
//                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
//                    String filePath = list.get(0).getPath();
//                    File file = new File(filePath);
//                    if(!TextUtils.isEmpty(filePath)){
//                        String newPath = FileUtils.getCacheMD() + file.getName();
//                        FileUtil.copyFile(filePath, newPath);
//                        bean = new MyDataBean();
//                        bean.setName(file.getName());
//                        bean.setId(DateUtil.getDateShortSerialYY());
//                        bean.setPath(filePath);
//                        bean.setNewPath(newPath);
//                        mBeanList.add(0, bean);
//                        mAdapter.notifyDataSetChanged();
//                        String json = gson.toJson(mBeanList);
//                        SharedPreferencesHelper.setPrefString("files", json);
//                    }
//                }
//                break;
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mBeanList.clear();
        Type type = new TypeToken<List<MyDataBean>>() {}.getType();
        String files = SharedPreferencesHelper.getPrefString("files", "");
        List<MyDataBean> arrayList = null;
        if(!TextUtils.isEmpty(files)){
            arrayList = gson.fromJson(files, type);
            if(arrayList != null && arrayList.size() > 0){
                mBeanList.addAll(arrayList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(mAdapter.getShow()){
            mAdapter.setShow(false);
            mFileList.clear();
            for (MyDataBean bean: mBeanList) {
                bean.setSelect(false);
            }
            navigationView.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }
}
