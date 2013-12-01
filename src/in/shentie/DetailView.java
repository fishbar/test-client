package in.shentie;

import in.shentie.ListView.Listener;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class DetailView extends LinearLayout {
    private BaseWebView webview;
    private Menu menu;
    private Context mContext;
    public DetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackgroundColor(Color.parseColor("#f5f5f5"));
    }
    public void onFinishInflate() {
        super.onFinishInflate();
        menu = (Menu)this.findViewById(R.id.detailview_menu);
        menu.mark("detail");
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
        webview.loadUrl(url);
    }
}