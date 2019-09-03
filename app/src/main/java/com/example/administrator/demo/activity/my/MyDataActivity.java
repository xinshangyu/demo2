package com.example.administrator.demo.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.MyDataAdapter;
import com.example.administrator.demo.entity.MyDataBean;
import com.example.administrator.demo.utils.DateUtil;
import com.example.administrator.demo.utils.FileUtil;
import com.example.administrator.demo.utils.FileUtils;
import com.example.administrator.demo.weight.GlideImageLoader;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;
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
    private Gson gson = new Gson();

    public static final String IS_NEED_FOLDER_LIST = "isNeedFolderList";


    @Override
    protected int getLayout() {
        return R.layout.activity_mydata;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        GridLayoutManager manager = new GridLayoutManager(MyDataActivity.this,3);

        recyclerView.setLayoutManager(manager);
        mAdapter = new MyDataAdapter(mBeanList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if("-1".equals(bean.getId())){
                    requestPermission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE);
                }else{
//                    OpenFileUtil.openFile(bean.getNewPath());
                }
            }
        });

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if("-1".equals(bean.getId())){
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
                                            FileUtils.getUserNew(editText.getText().toString().trim());
                                            showToast("创建成功");
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            })
                            .setMargin(30)
                            .show(getSupportFragmentManager());

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
                                .withHiddenFiles(true)
                                .start();

//                        Intent intent4 = new Intent(MyDataActivity.this, NormalFilePickActivity.class);
//                        intent4.putExtra(Constant.MAX_NUMBER, 1);
//                        intent4.putExtra(IS_NEED_FOLDER_LIST, true);
//                        intent4.putExtra(NormalFilePickActivity.SUFFIX,
//                                new String[] {"xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf", ".epub"});
//                        startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);

//                        new MaterialFilePicker()
//                                .withActivity(MyDataActivity.this)
//                                .withRequestCode(1)
//                                .withFilter(Pattern.compile(".*\\.txt$" +
//                                        ".*\\.doc$" +
//                                        ".*\\.docx$" +
//                                        ".*\\.docm$" +
//                                        ".*\\.dotx$" +
//                                        ".*\\.dotm$" +
//                                        ".*\\.xls$" +
//                                        ".*\\.xlsx$" +
//                                        ".*\\.xlsm$" +
//                                        ".*\\.xltx$" +
//                                        ".*\\.xltm$" +
//                                        ".*\\.xlam$" +
//                                        ".*\\.pptx$" +
//                                        ".*\\.pptm$" +
//                                        ".*\\.ppsx$" +
//                                        ".*\\.potx$" +
//                                        ".*\\.potm$" +
//                                        ".*\\.ppam$" +
//                                        ".*\\.pdf$" +
//                                        ".*\\.epub$"
//                                )) // Filtering files and directories by file name using regexp
//                                .withFilterDirectories(true) // Set directories filterable (false by default)
//                                .withHiddenFiles(true) // Show hidden files and folders
//                                .start();
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

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(filePath);
            if(!TextUtils.isEmpty(filePath)){
                String newPath = FileUtils.getCacheMD() + file.getName();
                FileUtil.copyFile(filePath, newPath);
                bean = new MyDataBean();
                bean.setName(file.getName());
                bean.setId(DateUtil.getDateShortSerial());
                bean.setPath(filePath);
                bean.setNewPath(newPath);
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
//                        bean.setId(DateUtil.getDateShortSerial());
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
    public void onBackPressed() {
        if(mAdapter.getShow()){
            mAdapter.setShow(false);
            mFileList.clear();
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
