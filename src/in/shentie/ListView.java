package in.shentie;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class ListView extends LinearLayout {
    public static class Listener {
        public void onListItemClick(String url, long id) {}
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
        webview = (BaseWebView)this.findViewById(R.id.listview_webview);
        webview.setWebViewClient(new BaseWebView.MViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.addJavascriptInterface(new JsBridge(mContext), "bridge");
    }
    public void render(String url) {
        String originUrl = webview.getUrl();
        if (url.equals(originUrl)) {
            return;
        }
        webview.loadUrl(url);
    }
    public void setListener(Listener newlistener) {
        listener = newlistener;
    }
    private class JsBridge {
        Context mContext;

        /** Instantiate the interface and set the context */
        JsBridge(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void send(final String event, final String msg){
            if (event.equals("openPost")) {
                try {
                    JSONObject obj = new JSONObject(msg);
                    final String url = obj.getString("url");
                    final long id = obj.getInt("id");
                    webview.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onListItemClick(url, id);
                        }
                        
                    });
                } catch (JSONException e) {
                    //
                }
            }
            Log.i("browser send:", event + ":" + msg);
        }
    }
}
