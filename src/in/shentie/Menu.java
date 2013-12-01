package in.shentie;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Menu extends LinearLayout {
    private Context mContext;
    public Menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(50)));
        setBackgroundColor(Color.parseColor("#CA3115"));
        mContext = context;
    }
    public void mark(String str) {
        TextView txt = new TextView(mContext);
        txt.setText(str);
        addView(txt);
    }
}
