package com.example.superme198.fundamental;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.DAY_OF_WEEK;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Toolbar toolbar;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        actionBar.setTitle("");

        Calendar now = Calendar.getInstance ();
        now.set(Calendar.DATE, 21);
        now.set(Calendar.MONTH, 1);
        now.set(Calendar.YEAR, 2019);
        System.out.println(now.get(Calendar.DAY_OF_WEEK) + "now");
        System.out.println(getFirstMonday(now, 2019, 1));
        System.out.println(dayOfWeek(21, 1, 2019));
    }


    String getFirstDayOfMonth( int day,int month,int year,  int dayOfWeek){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DATE, day);
        now.set(Calendar.MONTH, month-1);
        now.set(Calendar.YEAR, year);
        while (now.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            now.add(Calendar.DATE, 1);
        }
        System.out.println(now.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return (sdf.format(now.getTime()));

    }

    int dayOfWeek(int d, int m, int y)
    {
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        y -= (m < 3) ? 1 : 0;
        return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
    }

    public static int getFirstMonday(Calendar calendar, int year, int month) {

        calendar.set(DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.get(Calendar.DATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_game:
                System.out.println("hello world");
//                invalidateOptionsMenu();
            default:
            return super.onOptionsItemSelected(item);
        }

    }

    public void onGroupItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_game1:

                System.out.println("Hello World");
        }

    }
    /**to modify the options menu based on events that occur during the activity lifecycle.*/
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.removeItem(R.id.new_game1);
//        return super.onPrepareOptionsMenu(menu);
//    }
    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.app_menu, popupMenu.getMenu());
        popupMenu.show();
        //register listener to popup menu



    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.new_game:
                System.out.println("new game");
                return true;
            case R.id.new_game1:
                System.out.println("new game1");
                return true;
            default:
                return false;
        }
    }
}

