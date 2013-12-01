/**
 * Copyright Taobao.com since 2013
 * Author: jianxun.zxl
 * Create: 2013-8-26
 */
package in.shentie;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaseWebView extends WebView {
    protected final Context mContext;
    protected Handler mainThreadhandler;
    public BaseWebView(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
        mainThreadhandler = new Handler();
        initWebView();
    }
    protected void initWebView() {
        WebSettings websetting = getSettings();
        requestFocusFromTouch();
        websetting.setJavaScriptEnabled(true);
        websetting.setSupportZoom(false);
        websetting.setBuiltInZoomControls(false);
        websetting.setDomStorageEnabled(true);
        websetting.setNeedInitialFocus(true);
        websetting.setUseWideViewPort(true);
        websetting.setLoadWithOverviewMode(true);
        websetting.setUseWideViewPort(true);
        setInitialScale(1);
        setWebViewClient(new MViewClient());
        setWebChromeClient(new MChromeClient());
        //webview.addJavascriptInterface(new JsBridge(mContext), "bridge");
        clearCache(false);
    }
    protected void cleanWebView() {
        clearHistory();
        clearView();
    }
    public static class MViewClient extends WebViewClient {
        public MViewClient() {
            super();
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //
            return true;
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,    SslError  error) {
            handler.proceed();
        }
    }
    protected class MChromeClient extends WebChromeClient {
        public MChromeClient() {
            super();
        }
        @Override
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            Log.i("WEBVIEW", "alert info: " + message + " line: " + lineNumber + " source:" + sourceID);
        }
        @Override       
        public boolean onJsAlert(WebView view,String url,        
                                 String message,JsResult result) {        
            Log.d("WEBVIEW","onJsAlert:"+message);
            return super.onJsAlert(view, url, message, result);
        }
        @Override       
        public boolean onJsConfirm(WebView view, String url, String message,        
                JsResult result) {
            Log.d("WEBVIEW","onJsConfirm:"+message);
            return super.onJsConfirm(view, url, message, result);
        }
    }
    @Override
    public void onScrollChanged(int left, int top, int oldleft, int oldtop) {
        super.onScrollChanged(left, top, oldleft, oldtop);
    }
    
    //HACK : android webview中误将点击事件判断为滑动的bug
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int temp_ScrollY = getScrollY();
            scrollTo(getScrollX(), getScrollY() + 1);
            scrollTo(getScrollX(), temp_ScrollY);
        }
        return super.onTouchEvent(event);
    }
}
