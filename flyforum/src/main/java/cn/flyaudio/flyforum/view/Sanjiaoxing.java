package cn.flyaudio.flyforum.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by 叶兴运 on
 * 2019/2/20.21:13
 */
public class Sanjiaoxing extends View {

    private int widthSize;
    private int heightSize;

    public Sanjiaoxing(Context context) {
        this(context,null);
    }

    public Sanjiaoxing(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Sanjiaoxing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        //实例化路径
        Path path = new Path();
        path.moveTo(widthSize, 0);// 此点为多边形的起点
        path.lineTo(0, widthSize);
        path.lineTo(widthSize, widthSize);
        path.close(); // 使这些点构成封闭的多边形

        canvas.drawPath(path, p);
        canvas.drawColor(Color.TRANSPARENT);
        Shader mShader=new LinearGradient(0,0,100,100,
                new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},
                null,Shader.TileMode.REPEAT);
        p.setShader(mShader);
    }
}
