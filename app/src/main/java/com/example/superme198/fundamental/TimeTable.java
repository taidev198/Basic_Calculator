package com.example.superme198.fundamental;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeTable {
    Context context;
    TableLayout tableLayout;
    ArrayList<ModelClass> modelList;
    Details[][] detailsOfMonday = new Details[11][6];
    Details[][] detailsOfTuesday = new Details[11][6];
    Details[][] detailsOfWednesday = new Details[11][6];
    Details[][] detailsOfThursday = new Details[11][6];
    Details[][] detailsOfFriday = new Details[11][6];

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(@NonNull Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_optimize);
//
//
//    }

    public TimeTable(){}
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TimeTable(TableLayout tableLayout, Context context){
        this.tableLayout = tableLayout;
        this.context = context;

    }


    class Details{
        String time = "";
        String date = "";
        String details = "";
        String subjectName;
        String teacher;
        String shortenedSubjectName;
        String place;
        public Details(){}
        public Details(String date, String time){
            this.date = date;
            this.time = time;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getShortenedSubjectName() {
            return shortenedSubjectName;
        }

        public void setShortenedSubjectName(String shortenedSubjectName) {
            this.shortenedSubjectName = shortenedSubjectName;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
    void showDate(){
        Calendar now = Calendar.getInstance ();
        now.set(2019, Calendar.JANUARY, 21);
        System.out.println(now.getFirstDayOfWeek() + "now");
        LocalDate localDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDate = LocalDate.of(2019, Month.JANUARY, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println(localDate.lengthOfMonth());//the length of Month.
        }
        int lengthOfMonth = now.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//
//
//        //init Date.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initDateToDetails(localDate, DayOfWeek.MONDAY , detailsOfMonday, lengthOfMonth);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initDateToDetails(localDate, DayOfWeek.TUESDAY , detailsOfTuesday, lengthOfMonth);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initDateToDetails(localDate, DayOfWeek.WEDNESDAY , detailsOfWednesday, lengthOfMonth);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initDateToDetails(localDate, DayOfWeek.THURSDAY , detailsOfThursday, lengthOfMonth);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initDateToDetails(localDate, DayOfWeek.FRIDAY , detailsOfFriday, lengthOfMonth);
        }

        //init Time.
        initTimeToDetails(detailsOfMonday);
        initTimeToDetails(detailsOfTuesday);
        initTimeToDetails(detailsOfWednesday);
        initTimeToDetails(detailsOfThursday);
        initTimeToDetails(detailsOfFriday);
//
        System.out.println(detailsOfMonday[0][4].getDate());

//        YearMonth dateY = YearMonth.now();
//        System.out.printf("%s: %d%n", dateY, dateY.lengthOfMonth());
//        MonthDay month = MonthDay.now();
//        System.out.println("Month:" + month.getDayOfMonth());
//        Instant timestamp = new Date().toInstant();
//        LocalDateTime date = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
//        System.out.println(date);
//        System.out.println( date.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
    }



    private  void initDateToDetails(LocalDate localDate, DayOfWeek dayOfWeek, Details[][] details, int lengthOfMonth){
        int firstDayOfMonth = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            firstDayOfMonth = localDate.with(TemporalAdjusters.firstInMonth(dayOfWeek)).getDayOfMonth();
        }
        int i =firstDayOfMonth % 7==0 ? 2:1;
        while ((firstDayOfMonth) <= lengthOfMonth){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                details[0][i++] = new Details( firstDayOfMonth+ "/" +localDate.getMonth().getValue(), "");
            }
            firstDayOfMonth += 7;
        }
    }

    private  void initTimeToDetails(Details[][] details){
        details[0][0] = new Details("", "Date");
        details[1][0] = new Details("", "1-2");
        details[2][0] = new Details("", "3");
        details[3][0] = new Details("", "4-5");
        details[4][0] = new Details("", "6");
        details[5][0] = new Details("", "7-8");
        details[6][0] = new Details("", "9");
        details[7][0] = new Details("", "10-11");
        details[8][0] = new Details("", "12");
        details[9][0] = new Details("", "13-14");
        details[10][0] = new Details("", "15-16");
    }


    void initViews(){

        addHeaders();
        addRows();


    }

    private void addHeaders() {


        TableRow tr = new TableRow(context);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Date", Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
        for (int i=1; i<detailsOfMonday[0].length ; i++){
            tr.addView(getTextView(i, detailsOfMonday[0][i] == null ? "":detailsOfMonday[0][i].getDate(), Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));

        }
        tableLayout.addView(tr, getTblLayoutParams());
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(context);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        //tv.setOnClickListener(this);
        return tv;
    }


    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        params.width = 170;
        params.height = 50;
       // params.weight = 1;
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }


    private void addRows(){
        // Collections.reverse(trainScheduleList);
        for (int i = 1; i < detailsOfTuesday.length; i++) {
            TableRow tr = new TableRow(context);
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getRowsTextView(i, detailsOfMonday[i][0].getTime(), Color.BLACK, Typeface.BOLD ));
            tr.addView(getRowsTextView(i, detailsOfTuesday[0][1].getDate(), Color.BLACK, Typeface.BOLD ));
            tr.addView(getRowsTextView(i, detailsOfMonday[0][2].getDate(), Color.BLACK, Typeface.BOLD));
            tr.addView(getRowsTextView(i, detailsOfMonday[0][3].getDate(), Color.BLACK, Typeface.BOLD));
            tr.addView(getRowsTextView(i, detailsOfMonday[0][4].getDate(), Color.BLACK, Typeface.BOLD));
            tr.addView(getRowsTextView(i, detailsOfMonday[0][5].getDate(), Color.BLACK, Typeface.BOLD ));

            tableLayout.addView(tr, getTblLayoutParams());

        }

    }

    private TextView getRowsTextView(int id, String title, int color, int typeface) {
        TextView tv = new TextView(context);
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
      //  tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
//        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
       // tv.setOnClickListener(context);
        return tv;
    }

}
