package com.dongjie.dongjiedemos.calendar.calendar2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class CalendarStyle1Activity extends BaseActivity {
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_style1);
        datePicker = findViewById(R.id.main_dp);

        // 自定义前景装饰物绘制示例 Example of custom date's foreground decor
        List<String> tmpTL = new ArrayList<>();
        tmpTL.add("2019-4-5");
        tmpTL.add("2019-4-6");
        tmpTL.add("2019-4-7");
        tmpTL.add("2019-4-8");
        tmpTL.add("2019-4-9");
        tmpTL.add("2019-4-10");
        tmpTL.add("2019-4-11");
        DPCManager.getInstance().setDecorTL(tmpTL);

        List<String> tmpTR = new ArrayList<>();
        tmpTR.add("2019-4-12");
        tmpTR.add("2019-4-13");
        tmpTR.add("2019-4-14");
        tmpTR.add("2019-4-15");
        tmpTR.add("2019-4-16");
        DPCManager.getInstance().setDecorTR(tmpTR);

        Calendar calendar = Calendar.getInstance();
        datePicker.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1);  // 展示当前年份和月份
        datePicker.setFestivalDisplay(false);  // 节日是否显示
        datePicker.setTodayDisplay(true);     // 是否标识出今天
        datePicker.setHolidayDisplay(false);   // 是否显示节假日
        datePicker.setDeferredDisplay(false);  // 没搞明白这个参数，默认为true
        datePicker.setMode(DPMode.NONE);       // 日历展示模式为none, 有三种模式：none、单选、多选
        datePicker.setDPDecor(new DPDecor() { // 画每个数字左边的标识
            @Override
            public void drawDecorTL(Canvas canvas, Rect rect, Paint paint, String data) {// TL代表左上
                super.drawDecorTL(canvas, rect, paint, data);
                switch (data) {
                    case "2019-4-5":
                    case "2019-4-7":
                    case "2019-4-9":
                    case "2019-4-11":
                        paint.setColor(Color.GREEN);
                        canvas.drawRect(rect, paint);
                        break;
                    default:
                        paint.setColor(Color.RED);
                        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
                        break;
                }
            }

            @Override
            public void drawDecorTR(Canvas canvas, Rect rect, Paint paint, String data) { // 画每个数字右边的标识， TR:右上
                super.drawDecorTR(canvas, rect, paint, data);
                switch (data) {
                    case "2019-4-12":
                        paint.setColor(Color.BLUE);
                        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 5, paint);
                        break;
                    default:
                        paint.setColor(Color.YELLOW);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }
        });
    }
}
