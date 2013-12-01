package in.shentie;

import in.shentie.ListView.Listener;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.view.View;

public class DetailView extends LinearLayout {
    public static class Listener {
        public void onBackClick () {
            
        }
        public void onRefreshClick() {
            
        }
    }
    private BaseWebView webview;
    private Menu menu;
    private Context mContext;
    private Listener listener;
    private String currentUrl;
    public DetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackgroundColor(Color.parseColor("#f5f5f5"));
    }
    public void setListener(Listener newListener) {
        listener = newListener;
    }
    public void onFinishInflate() {
        super.onFinishInflate();
        menu = (Menu)this.findViewById(R.id.detailview_menu);
        menu.init(Menu.TYPE_DETAIL);
        menu.setListener(new Menu.Listener(){
           @Override
           public void onButtonClick(String type, View btn) {
               if (type.equals("back")) {
                   listener.onBackClick();
               } else if (type.equals("refresh")){
                   render(currentUrl);
               }
           }
        });
        webview = (BaseWebView)this.findViewById(R.id.detailview_webview);
        webview.setWebViewClient(new BaseWebView.MViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
    }
    public void render(String url) {
        currentUrl = url;
        webview.loadUrl(url);
    }
}