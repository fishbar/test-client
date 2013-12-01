package in.shentie;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Menu extends FrameLayout implements View.OnClickListener {
    public static class Listener{
        public void onButtonClick(String type, View btn) {
             
        }
    }
    public static int TYPE_LIST = 0;
    public static int TYPE_DETAIL = 1;
    private Context mContext;
    private Listener listener;
    private int btnSize = DensityUtil.dip2px(38);
    public Menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(50)));
        setBackgroundColor(Color.parseColor("#CA3115"));
        mContext = context;
        int size = DensityUtil.dip2px(38);
        listener = new Listener();
    }
    public void setListener(Listener newListener) {
       listener = newListener;
    }
    public void onClick(View view){
        String tag = (String)view.getTag();
        listener.onButtonClick(tag, view);
    }
    public void init(int type) {
        switch(type) {
        case 0:
            break;
        case 1:
            ImageButton back = new ImageButton(mContext, R.drawable.back);
            LayoutParams backParams = new LayoutParams(btnSize, btnSize);
            backParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
            addView(back, backParams);
            back.setOnClickListener(this);
            back.setTag("back");

            ImageButton refresh = new ImageButton(mContext, R.drawable.refresh);
            LayoutParams refreshParams = new LayoutParams(btnSize, btnSize);
            refreshParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
            addView(refresh, refreshParams);
            refresh.setOnClickListener(this);
            refresh.setTag("refresh");
        }
    }
    private static class ImageButton extends ImageView {
        public ImageButton(Context context) {
            super(context);
            setClickable(true);
            setBackgroundDrawable(getBg("#00ffffff", "#33000000"));
        }
        public ImageButton(Context context, int res) {
            super(context);
            setClickable(true);
            setBackgroundDrawable(getBg("#00ffffff", "#33000000"));
            setImageResource(res);
        }
        private static StateListDrawable getBg(String normal, String press) {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[] {android.R.attr.state_pressed},
                new ColorDrawable(Color.parseColor(press)));
            states.addState(new int[] { },
                    new ColorDrawable(Color.parseColor(normal)));
            return states;
        }
    }
    
}
