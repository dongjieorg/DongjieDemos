package com.dongjie.dongjiedemos.calendar.calendar2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;

import java.util.Calendar;

import cn.aigestudio.datepicker.bizs.themes.DPCNTheme;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

public class CalendarStyle3Activity extends Activity {

    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_style3);

        DPCNTheme dpcnTheme = new DPCNTheme() {
            @Override
            public int colorBG() {
                // 设置日历背景颜色
                return super.colorBG();
            }

            @Override
            public int colorBGCircle() {
                // 设置选中的日期的圆背景颜色
                return super.colorBGCircle();
            }

            @Override
            public int colorTitleBG() {
                // 设置日历上面title的背景颜色
                return super.colorTitleBG();
            }

            @Override
            public int colorTitle() {
                return super.colorTitle();
            }

            @Override
            public int colorToday() {
                // 日期为今天的圆背景颜色
                return super.colorToday();
            }

            @Override
            public int colorG() {
                return super.colorG();
            }

            @Override
            public int colorF() {
                return super.colorF();
            }

            @Override
            public int colorWeekend() {
                // 是日历中周末的文字的颜色，不是背景色
                return Color.YELLOW;
            }

            @Override
            public int colorHoliday() {
                return super.colorHoliday();
            }
        };

        DPTManager.getInstance().initCalendar(dpcnTheme);

        datePicker = findViewById(R.id.main_dp);
        Calendar c = Calendar.getInstance();
        datePicker.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
        datePicker.setMode(DPMode.SINGLE);
        datePicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(CalendarStyle3Activity.this, date, Toast.LENGTH_LONG).show();
            }
        });
    }
}
