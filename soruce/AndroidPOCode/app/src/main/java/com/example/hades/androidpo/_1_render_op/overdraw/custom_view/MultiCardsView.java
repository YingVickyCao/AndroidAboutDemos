package com.example.hades.androidpo._1_render_op.overdraw.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/*
第一次默认显示是，效果相同。
D/MultiCardsView: onDraw: clip bounds 0 0 1440 2864
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=1
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=2
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=3
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=4

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=4
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 0
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 40 40 280 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=3
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 1
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 280 40 520 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=2
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 2
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 520 40 760 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=1
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 3
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 760 40 1480 1424

当invalidate()时，不同。
// opt
D/MultiCardsView: onDraw: clip bounds 0 0 1440 2864
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=1
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=2
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=3
D/MultiCardsView: drawCardsWithoutOverDraw: saveCount=4

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=4
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 0
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 40 40 280 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=3
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 1
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 280 40 520 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=2
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 2
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 520 40 760 1424

D/MultiCardsView: drawCardsWithoutOverDraw: saveCount2=1
D/MultiCardsView: drawCardsWithoutOverDraw: overdraw opt: draw cards index: 3
D/MultiCardsView: drawCardsWithoutOverDraw: current clip bounds 760 40 1480 1424

invalidate()
// NO OP
D/MultiCardsView: onDraw: clip bounds 0 0 1440 2864
D/MultiCardsView: drawCardsNormal: draw cards index: 0
D/MultiCardsView: drawCardsNormal: draw cards index: 1
D/MultiCardsView: drawCardsNormal: draw cards index: 2
D/MultiCardsView: drawCardsNormal: draw cards index: 3
 */
public class MultiCardsView extends View {
    private static final String TAG = MultiCardsView.class.getSimpleName();

    private ArrayList<SingleCard> cardsList = new ArrayList<SingleCard>(5);
    private boolean enableOverdrawOpt = true;

    public MultiCardsView(Context context) {
        this(context, null, 0);
    }

    public MultiCardsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiCardsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addCards(SingleCard card) {
        cardsList.add(card);
    }

    //设置是否消除过度绘制
    public void enableOverdrawOpt(boolean enableOrNot) {
        this.enableOverdrawOpt = enableOrNot;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (cardsList == null || canvas == null)
            return;
        Rect clip = canvas.getClipBounds();
        Log.d(TAG, String.format("onDraw: clip bounds %d %d %d %d", clip.left, clip.top, clip.right, clip.bottom));
        //根据enableOverdrawOpt值来调用不同的绘制方法，对比效果
        if (enableOverdrawOpt) {
            drawCardsWithoutOverDraw(canvas, cardsList.size() - 1);
        } else {
            drawCardsNormal(canvas, cardsList.size() - 1);
        }
    }

    protected void drawCardsWithoutOverDraw(Canvas canvas, int index) {// index = 3 -> 2 -> 0 -> 1
        if (canvas == null || index < 0 || index >= cardsList.size())
            return;
        SingleCard singleCard = cardsList.get(index);

        if (null == singleCard) {
            return;
        }

        // SESSION:canvas.quickReject  判断是否（部分/全部）在Canvas的绘制区域。在，则标记绘制区域->绘制；否则直接返回，避免 GPU 和 CPU 计算和渲染
        if (!canvas.quickReject(singleCard.area, Canvas.EdgeType.BW)) {
            // 在
            int saveCount = canvas.save();
            Log.d(TAG, "drawCardsWithoutOverDraw: saveCount=" + saveCount);

            // SESSION:canvas.clipRect 标记要绘制的可见区域。避免绘制越界
            if (canvas.clipRect(singleCard.area, Region.Op.DIFFERENCE)) {
                drawCardsWithoutOverDraw(canvas, index - 1); // SESSION:标记完了，继续标记下一个，直到标记结束
            }
            canvas.restoreToCount(saveCount);// 恢复为之前堆栈保存的编号为 int 的 canvas 状态
            saveCount = canvas.save(); // 将Canvas 当前状态保存在堆栈，并编号int
            Log.d(TAG, "drawCardsWithoutOverDraw: saveCount2=" + saveCount);

            //只绘制可见区域
            if (canvas.clipRect(singleCard.area)) {
                Log.d(TAG, "drawCardsWithoutOverDraw: overdraw opt: draw cards index: " + index);
                Rect clip = canvas.getClipBounds();
                Log.d(TAG, String.format("drawCardsWithoutOverDraw: current clip bounds %d %d %d %d", clip.left, clip.top, clip.right, clip.bottom));
                singleCard.draw(canvas);// SESSION:canvas.draw  绘制可见区域
            }
            canvas.restoreToCount(saveCount);// 恢复为之前堆栈保存的编号为 int 的 canvas 状态
        } else {
            // 否
            drawCardsWithoutOverDraw(canvas, index - 1);
        }
    }

    protected void drawCardsNormal(Canvas canvas, int index) {
        if (canvas == null || index < 0 || index >= cardsList.size())
            return;
        SingleCard card = cardsList.get(index);
        if (card != null) {
            drawCardsNormal(canvas, index - 1);
            Log.d(TAG, "drawCardsNormal: draw cards index: " + index);
            card.draw(canvas);
        }
    }
}
