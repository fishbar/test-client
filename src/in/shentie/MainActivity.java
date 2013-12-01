package in.shentie;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private ListView listView;
    private DetailView detailView;
    private Animation mLeftin;
    private Animation mRightin;
    private Animation mLeftOut;
    private Animation mRightOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityUtil.init(this);
        setContentView(R.layout.activity_main);
        mLeftin = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        mRightin = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        mLeftOut = AnimationUtils.loadAnimation(this, R.anim.out_to_left);
        mRightOut = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        
        listView = (ListView)findViewById(R.id.listview);
        detailView = (DetailView)findViewById(R.id.detaiview);
        detailView.setVisibility(View.GONE);
        
        listView.setListener(new ListView.Listener(){
            @Override
            public void onListItemClick(String url) {
                switchDetail();
                detailView.render(url);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        listView.render("http://m.taobao.com");
    }
    private void switchDetail() {
        detailView.setVisibility(View.VISIBLE);
        mLeftOut.reset();
        mRightin.reset();
        listView.startAnimation(mLeftOut);
        detailView.startAnimation(mRightin);
    }
    private void switchList() {
        mLeftin.reset();
        mRightOut.reset();
        listView.startAnimation(mLeftin);
        detailView.startAnimation(mRightOut);
    }
}
