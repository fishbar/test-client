package in.shentie;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class ListView extends LinearLayout {
    public static class Listener {
        public void onListItemClick(String url) {
        
        }
    }
    private BaseWebView webview;
    private Menu menu;
    private Context mContext;
    private Listener listener;
    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackgroundColor(Color.parseColor("#f5f5f5"));
    }
    public void onFinishInflate() {
        super.onFinishInflate();
        menu = (Menu)this.findViewById(R.id.listview_menu);
        menu.mark("list");
        webview = (BaseWebView)this.findViewById(R.id.listview_webview);
        webview.setWebViewClient(new BaseWebView.MViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                listener.onListItemClick(url);
                return true;
            }
        });
    }
    public void render(String url) {
        webview.loadUrl(url);
    }
    public void setListener(Listener newlistener) {
        listener = newlistener;
    }
}
