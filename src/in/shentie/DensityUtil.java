package in.shentie;

import android.content.Context;
import android.util.Log;
  
public class DensityUtil {
    private static float scale;
    private static boolean inited = false;
    public static void init(Context context) {
        scale = context.getResources().getDisplayMetrics().density;
        inited = true;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */
    public static int dip2px(float dpValue) {
        if (!inited) {
            Log.e("DensityUtil", "call DensityUtil.init(context) first!!!");
        }
        return (int) (dpValue * scale + 0.5f);
    }
  
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        if (!inited) {
            Log.e("DensityUtil", "call DensityUtil.init(context) first!!!");
        }
        return (int) (pxValue / scale + 0.5f);
    }
}