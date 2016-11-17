package com.ruanmeng.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义日历卡
 *
 * @author wuwenjie
 */
public class CalendarCard extends View {

    private static final int TOTAL_COL = 7; // 7列
    private static final int TOTAL_ROW = 6; // 6行
    private final Context context;

    private Paint mCirclePaint; // 绘制圆形的画笔
    private Paint mTextPaint; // 绘制文本的画笔
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());
    private int mViewWidth; // 视图的宽度
    private int mViewHeight; // 视图的高度
    private int mCellSpace; // 单元格间距
    private Row rows[] = new Row[TOTAL_ROW]; // 行数组，每个元素代表一行
    private static CustomDate mShowDate; // 自定义的日期，包括year,month,day
    private OnCellClickListener mCellClickListener; // 单元格点击回调事件
    private int touchSlop; //
    private boolean callBackCellSpace;
    private List<String> data = new ArrayList<>();
    private Cell mClickCell;
    private float mDownX;
    private float mDownY;

    /**
     * 单元格点击的回调接口
     *
     * @author wuwenjie
     */
    public interface OnCellClickListener {
        void clickDate(CustomDate date); // 回调点击的日期

        void changeDate(CustomDate date); // 回调滑动ViewPager改变的日期
    }

    public CalendarCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    public CalendarCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public CalendarCard(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public CalendarCard(Context context, OnCellClickListener listener) {
        super(context);
        this.mCellClickListener = listener;
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#f60c23")); // 红色圆形
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        initDate();
    }

    private void initDate() {
        mShowDate = new CustomDate();
        fillDate();//
    }

    private void fillDate() {
        int monthDay = DateUtil.getCurrentMonthDay(); // 今天
        int lastMonthDays = DateUtil.getMonthDays(mShowDate.year,
                mShowDate.month - 1); // 上个月的天数
        int currentMonthDays = DateUtil.getMonthDays(mShowDate.year,
                mShowDate.month); // 当前月的天数
        int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year,
                mShowDate.month);
        boolean isCurrentMonth = false;
        if (DateUtil.isCurrentMonth(mShowDate)) {
            isCurrentMonth = true;
        }
        int day = 0;
        for (int j = 0; j < TOTAL_ROW; j++) {
            rows[j] = new Row(j);
            for (int i = 0; i < TOTAL_COL; i++) {
                int position = i + j * TOTAL_COL; // 单元格位置
                // 这个月的
                if (position >= firstDayWeek
                        && position < firstDayWeek + currentMonthDays) {
                    day++;
                    rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(
                            mShowDate, day), State.CURRENT_MONTH_DAY, i, j);
                    // 今天
                    if (isCurrentMonth && day == monthDay) {
                        CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
                        rows[j].cells[i] = new Cell(date, State.TODAY, i, j);
                    }

                    if (isCurrentMonth && day > monthDay) { // 如果比这个月的今天要大，表示还没到
                        rows[j].cells[i] = new Cell(
                                CustomDate.modifiDayForObject(mShowDate, day),
                                State.UNREACH_DAY, i, j);
                    }
                    if (isCurrentMonth && day < monthDay) {
                        rows[j].cells[i] = new Cell(
                                CustomDate.modifiDayForObject(mShowDate, day),
                                State.REACH_DAY, i, j);
                    }

                    if (data != null) {
                        for (int k = 0; k < data.size(); k++) {
                            String[] d = data.get(k).split("-");
                            if (mShowDate.year==Integer.valueOf(d[0])&mShowDate.month==Integer.valueOf(d[1])&day == Integer.valueOf(d[2])) {
                                CustomDate date = CustomDate
                                        .modifiDayForObject(mShowDate, day);
                                rows[j].cells[i] = new Cell(date,
                                        State.MAKER_DAY, i, j);
                            }
                        }
                    }

                    // 过去一个月
                } else if (position < firstDayWeek) {
                    rows[j].cells[i] = new Cell(new CustomDate(mShowDate.year,
                            mShowDate.month - 1, lastMonthDays
                            - (firstDayWeek - position - 1)),
                            State.PAST_MONTH_DAY, i, j);
                    // 下个月
                } else if (position >= firstDayWeek + currentMonthDays) {
                    rows[j].cells[i] = new Cell((new CustomDate(mShowDate.year,
                            mShowDate.month + 1, position - firstDayWeek
                            - currentMonthDays + 1)),
                            State.NEXT_MONTH_DAY, i, j);
                }
            }
        }
        mCellClickListener.changeDate(mShowDate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < TOTAL_ROW; i++) {
            if (rows[i] != null) {
                rows[i].drawCells(canvas);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h + dip2px(context, 50);
        // mCellSpace = Math.min(mViewHeight / TOTAL_ROW, mViewWidth / TOTAL_COL);
        mCellSpace = mViewWidth / TOTAL_COL;
        if (!callBackCellSpace) {
            callBackCellSpace = true;
        }
        // mTextPaint.setTextSize(mCellSpace / 3);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float disX = event.getX() - mDownX;
                float disY = event.getY() - mDownY;
                if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
                    int col = (int) (mDownX / mCellSpace);
                    int row = (int) (mDownY / (mCellSpace - dip2px(context, 10)));
                    measureClickCell(col, row);
                }
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * 计算点击的单元格
     *
     * @param col 行
     * @param row 列
     */
    private void measureClickCell(int col, int row) {
        if (col >= TOTAL_COL || row >= TOTAL_ROW)
            return;
        if (mClickCell != null) {
            rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
        }
        if (rows[row] != null) {
            mClickCell = new Cell(rows[row].cells[col].date,
                    rows[row].cells[col].state, rows[row].cells[col].i,
                    rows[row].cells[col].j);

            CustomDate date = rows[row].cells[col].date;
            date.week = col;
            mCellClickListener.clickDate(date);

            // 刷新界面
            update();
        }
    }

    /**
     * 组元素
     *
     * @author wuwenjie
     */
    class Row {
        public int j;

        Row(int j) {
            this.j = j;
        }

        public Cell[] cells = new Cell[TOTAL_COL];

        // 绘制单元格
        public void drawCells(Canvas canvas) {
            for (Cell cell : cells) {
                if (cell != null) {
                    cell.drawSelf(canvas);
                }
            }
        }

    }

    /**
     * 单元格元素
     *
     * @author wuwenjie
     */
    class Cell {
        public CustomDate date;
        public State state;
        public int i;
        public int j;

        public Cell(CustomDate date, State state, int i, int j) {
            super();
            this.date = date;
            this.state = state;
            this.i = i;
            this.j = j;
        }

        public void drawSelf(Canvas canvas) {
            switch (state) {
                case TODAY: // 今天
                    mTextPaint.setColor(Color.WHITE);
                    mCirclePaint.setColor(Color.parseColor("#1583e6"));
                    canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
                            (float) ((j + 0.5) * (mCellSpace - dip2px(context, 10))), mCellSpace / 3,
                            mCirclePaint);
                    break;
                case MAKER_DAY: // 标签
                    mTextPaint.setColor(Color.WHITE);
                    mCirclePaint.setColor(Color.parseColor("#f60c23"));
                    canvas.drawCircle((float) (mCellSpace * (i + 0.5)),
                            (float) ((j + 0.5) * (mCellSpace - dip2px(context, 10))), mCellSpace / 3,
                            mCirclePaint);
                    break;
                case CURRENT_MONTH_DAY: // 当前月日期
                    mTextPaint.setColor(Color.parseColor("#333333"));
                    break;
                case PAST_MONTH_DAY: // 过去一个月
                case NEXT_MONTH_DAY: // 下一个月
                    mTextPaint.setColor(Color.WHITE);
                    break;
                case UNREACH_DAY: // 还未到的天
                    mTextPaint.setColor(Color.parseColor("#333333"));
                    break;
                case REACH_DAY: // 已过的天
                    mTextPaint.setColor(Color.parseColor("#cccccc"));
                    break;
                default:
                    break;
            }
            // 绘制文字
            String content = date.day + "";
            canvas.drawText(content,
                    (float) ((i + 0.5) * mCellSpace - mTextPaint
                            .measureText(content) / 2), (float) ((j + 0.7)
                            * (mCellSpace - dip2px(context, 10)) - mTextPaint
                            .measureText(content, 0, 1) / 2), mTextPaint);
        }
    }

    /**
     * 单元格的状态 当前月日期，过去的月的日期，下个月的日期
     */
    enum State {
        TODAY,
        MAKER_DAY,
        CURRENT_MONTH_DAY,
        PAST_MONTH_DAY,
        NEXT_MONTH_DAY,
        UNREACH_DAY,
        REACH_DAY
    }

    // 从左往右划，上一个月
    public void leftSlide() {
        if (mShowDate.month == 1) {
            mShowDate.month = 12;
            mShowDate.year -= 1;
        } else {
            mShowDate.month -= 1;
        }
        update();
    }

    // 从右往左划，下一个月
    public void rightSlide() {
        if (mShowDate.month == 12) {
            mShowDate.month = 1;
            mShowDate.year += 1;
        } else {
            mShowDate.month += 1;
        }
        update();
    }

    public void update() {
        fillDate();
        invalidate();
    }

    public void setQiandaoList(List<String> data) {
        this.data = data;
        update();
    }

    //dip转像素值
    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

}
