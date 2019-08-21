package com.example.baselibrary.zh.fragment;//package com.qy.commonbaselibrary.fragment;
//
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.webkit.WebChromeClient;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//
//import com.just.agentweb.AgentWeb;
//import com.just.agentweb.AgentWebUIControllerImplBase;
//import com.qy.commonbaselibrary.R;
//import com.qy.commonbaselibrary.base.BaseFragment;
//
//import butterknife.BindView;
//
///**
// * webView fragment.
// */
//public class WebFragment extends BaseFragment {
//    private static final String URL = "url";
//    private static final String ARG_PARAM2 = "param2";
//    @BindView(R.id.web_view_fragment)
//    FrameLayout mFragment;
//    private AgentWeb mAgentWeb;
//
//    public static WebFragment newInstance(String url, String param2) {
//        WebFragment fragment = new WebFragment();
//        Bundle args = new Bundle();
//        args.putString(URL, url);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible && null != mAgentWeb)
//            mAgentWeb.getWebLifeCycle().onResume();
//    }
//
//    @Override
//    protected void onFragmentFirstVisible() {
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(mFragment, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator(ContextCompat.getColor(mContext, R.color.colorWebViewIndicator), 2)
//                .setWebViewClient(mWebViewClient)
//                .setWebChromeClient(mWebChromeClient)
//                .setSecurityType(AgentWeb.SecurityType.DEFAULT_CHECK)
//                .setAgentWebUIController(new AgentWebUIControllerImplBase())
//                .createAgentWeb()
//                .ready()
//                .go(getArguments().getString(URL));
//        mAgentWeb.getWebLifeCycle().onResume();
//    }
//
//    private WebChromeClient mWebChromeClient = new WebChromeClient() {
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//        }
//
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
//        }
//    };
//    private WebViewClient mWebViewClient = new WebViewClient() {
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，
//            //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//            //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，
//            return false;
//        }
//
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            //该方法在Build.VERSION_CODES.LOLLIPOP以后有效
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                String url = request.getUrl().toString();
//            }
//            return false;
//        }
//    };
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.fragment_web;
//    }
//
//    @Override
//    public void onPause() {
////        mAgentWeb.getWebLifeCycle().onPause();
//        super.onPause();
//
//    }
//
//    @Override
//    public void onDestroyView() {
////        mAgentWeb.getWebLifeCycle().onDestroy();
//        super.onDestroyView();
//    }
//}
