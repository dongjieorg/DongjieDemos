package com.dongjie.dongjiedemos.calendar.calendar1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 此demo GitHub地址：https://github.com/myjoybar/android-calendar-view
 * 集成使用：compile 'com.joybar.calendar:librarycalendar:1.0.6'
 * 如果要定制需要源码依赖， 接口貌似没提供多少定制的东西
 */
public class Calendar1Activity extends BaseActivity implements
        CalendarViewPagerFragment.OnPageChangeListener,
        CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener{

    private TextView tv_date;
    private boolean isChoiceModelSingle = false;
    private List<CalendarDate> mListDate = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar1);

        tv_date = (TextView) findViewById(R.id.tv_date);
        initFragment();
    }

    // 单选
    public void onButtonClick1(View v) {
        isChoiceModelSingle = true;
        initFragment();
    }

    // 多选
    public void onButtonClick2(View v) {
        isChoiceModelSingle = false;
        initFragment();
    }

    private void initFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        // Fragment fragment = new CalendarViewPagerFragment();
        Fragment fragment = CalendarViewPagerFragment.newInstance(isChoiceModelSingle);
        trans.replace(R.id.fl_content, fragment);
        trans.commit();
    }

    @Override
    public void onDateClick(CalendarDate calendarDate) {

        int year = calendarDate.getSolar().solarYear;
        int month = calendarDate.getSolar().solarMonth;
        int day = calendarDate.getSolar().solarDay;
        if (isChoiceModelSingle) {
            tv_date.setText(year + "-" + month + "-" + day);
        } else {
            //System.out.println(calendarDate.getSolar().solarDay);
            mListDate.add(calendarDate);
            tv_date.setText(listToString(mListDate));
        }

    }

    @Override
    public void onDateCancel(CalendarDate calendarDate) {
        int count = mListDate.size();
        for (int i = 0; i < count; i++) {
            CalendarDate date = mListDate.get(i);
            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {
                mListDate.remove(i);
                break;
            }
        }
        tv_date.setText(listToString(mListDate));
    }

    @Override
    public void onPageChange(int year, int month) {
        tv_date.setText(year + "-" + month);
        mListDate.clear();
    }

    private static String listToString(List<CalendarDate> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (CalendarDate date : list) {
            stringBuffer.append(date.getSolar().solarYear + "-" + date.getSolar().solarMonth + "-" + date.getSolar().solarDay).append(" ");
        }
        return stringBuffer.toString();
    }
}
