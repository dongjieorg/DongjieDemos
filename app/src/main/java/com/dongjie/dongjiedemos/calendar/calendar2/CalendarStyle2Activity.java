package com.dongjie.dongjiedemos.calendar.calendar2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.views.DatePicker;

public class CalendarStyle2Activity extends BaseActivity {
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_style2);
        datePicker = findViewById(R.id.main_dp);

        // 默认style1里面的参数都是打开的, 默认多选
        // 自定义背景绘制示例
        List<String> tmp = new ArrayList<>();
        tmp.add("2019-4-1");
        tmp.add("2019-4-8");
        tmp.add("2019-4-16");
        DPCManager.getInstance().setDecorBG(tmp); // 添加指定日期的背景
        datePicker.setDate(2019, 4);
        datePicker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.RED);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2F, paint);
            }
        });

        datePicker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(List<String> date) {
                String result = "";
                Iterator iterator = date.iterator();
                while (iterator.hasNext()) {
                    result += iterator.next();
                    if (iterator.hasNext()) {
                        result += "\n";
                    }
                }
                Toast.makeText(CalendarStyle2Activity.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }
}
