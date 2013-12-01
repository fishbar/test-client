package in.shentie;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private static int STATUS_LIST = 0;
	private static int STATUS_DETAIL = 1;
    private ListView listView;
    private DetailView detailView;
    private HScroller container;
    private Animation mLeftin;
    private Animation mRightin;
    private Animation mLeftOut;
    private Animation mRightOut;
    private int status = STATUS_LIST;
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
        container = (HScroller)findViewById(R.id.container);
        
        listView.setListener(new ListView.Listener(){
            @Override
            public void onListItemClick(String url) {
                switchDetail();
                detailView.render(url);
            }
        });
        
        detailView.setListener(new DetailView.Listener(){
            @Override
            public void onBackClick() {
                switchList();
            }
        });
    }
    protected void onResume() {
        super.onResume();
        listView.render("http://m.taobao.com");
    }
    private void switchDetail() {
    	status = STATUS_DETAIL;
    	container.nextNode();
    }
    private void switchList() {
    	status = STATUS_LIST;
    	container.prevNode();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) { //按下的如果是BACK，同时没有重复
            if (event.getRepeatCount() >= 0 && event.getRepeatCount() < 3) {
            	if (status != STATUS_LIST) {
            		switchList();
            	}
            } else if(event.getRepeatCount() > 5) {
            	// dialog change url
            }
        	return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
