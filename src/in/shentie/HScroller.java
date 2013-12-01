package in.shentie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class HScroller extends FrameLayout {
    protected Scroller mScroller;
    protected LinearLayout mContainer;
    private Context mContext;
    private int childWidth = 0;
    private int screens = 0;
    private int currentScreen = 0;
    
    public HScroller(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mScroller = new Scroller(context);
	}
    @Override
    public void onFinishInflate(){
        super.onFinishInflate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int count = getChildCount();
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        
        int wSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int hSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        childWidth = width;
        screens = count;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            child.measure(wSpec, hSpec);
        }
        setMeasuredDimension(width, height);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        int w;
        int h;
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            w = child.getMeasuredWidth();
            h = child.getMeasuredHeight();
            final int x0 = childLeft;
            final int y0 = 0;
            final int x1 = x0 + w;
            final int y1 = y0 + h;
            child.layout(x0, y0, x1, y1);
            childLeft += w;
        }
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
    public void prevNode() {
        if ((currentScreen - 1) < 0) {
            return;
        }
        currentScreen --;
        int startX = this.getScrollX();
        int dx = -childWidth;
        mScroller.startScroll(startX, 0, dx, 0);
        invalidate();
    }
    public void nextNode() {
        if ((currentScreen + 1) >= screens) {
            return;
        }
        currentScreen ++;
        int startX = this.getScrollX();
        int dx = childWidth;
        mScroller.startScroll(startX, 0, dx, 0);
        invalidate();
    }
}
