package com.example.superme198.fundamental;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Link:https://stackoverflow.com/questions/16192195/table-layout-not-fitting-screen*/
public class StudentMarkActivity extends AppCompatActivity {
    TableLayout studentMarkTable;

    List<String> studentMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mark);
        studentMarkTable = findViewById(R.id.student_mark_table);
       runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    login();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //initViews();
    }


    void initViews(){

        addHeaders();
        addRows();


    }

    private void addHeaders() {


        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        for (int i=1; i<8 ; i++){
            tr.addView(getTextView(i, "title" + i, Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));

        }

        studentMarkTable.addView(tr, getTblLayoutParams());
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
          tv.setPadding(20, 20, 20, 20);

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
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }


    private void addRows(){
        for (int i = 1; i < 10; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());
            tr.addView(getRowsTextView(i, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            tr.addView(getRowsTextView(i+1, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            tr.addView(getRowsTextView(i+2, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            tr.addView(getRowsTextView(i+3, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            tr.addView(getRowsTextView(i+4, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            tr.addView(getRowsTextView(i+5, "10", Color.BLACK, Typeface.BOLD , R.drawable.cell_shape));
            studentMarkTable.addView(tr, getTblLayoutParams());

        }

    }

    private TextView getRowsTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title);
        tv.setTextColor(color);
        //tv.setHeight();
        tv.setPadding(5, 5, 5, 5);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
//        Log.e("Width", "" + width);
//        Log.e("height", "" + height);
        // tv.setOnClickListener(context);
        return tv;
    }

    private void login() throws NoSuchAlgorithmException, IOException {
        Connection.Response loginForm = Jsoup.connect("http://115.146.127.72/CMCSoft.IU.Web.Info/Login.aspx")
                .method(Connection.Method.GET)
                .execute();
        Document doc = loginForm.parse();
        Elements hiddenElems = doc.select("input[type=hidden]");
        Map<String, String> nameValue = new HashMap<>();

        for(Element elem : hiddenElems) {
            nameValue.put(elem.attr("name"), elem.attr("value"));
        }
        nameValue.put("PageHeader1$drpNgonNgu", "E43296C6F24C4410A894F46D57D2D3AB");
        loginForm =  Jsoup.connect("http://115.146.127.72/CMCSoft.IU.Web.Info/Login.aspx")
                .data("txtUserName", "CT010338")
                .data("txtPassword", md5("03031998"))
                .data(nameValue)
                .data("btnSubmit", "Đăng nhập")
                .cookies(loginForm.cookies())
                .method(Connection.Method.POST)
                .execute();


    }

    private String md5(String input) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = input.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        StringBuilder sb = new StringBuilder();
        for (byte b : thedigest) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public void showStudentMark(Connection.Response loginForm) throws IOException {
        Map<String, String> cookies = loginForm.cookies();
        Document document2 = Jsoup.connect("http://115.146.127.72/CMCSoft.IU.Web.info/StudentMark.aspx")
                .cookies(cookies)
                .get();
        Elements container = document2.select("div.container");
//        for (Element e:
//            container) {
//            String studentid = e.select("span#lblStudentCode.form-control-lable-value").text();
//            System.out.println(studentid);
//            String namedtudent = e.select("span#lblStudentName.form-control-lable-value").text();
//            System.out.println(namedtudent);
//            String status = e.select("span#lblstudentstatus.form-control-lable-value").text();
//            System.out.println(status);
//            String session = e.select("span#lblAy.form-control-lable-value").text();
//            System.out.println(session);
//            String specialty = e.select("select#drpField.form-control").text();
//            System.out.println(specialty);
//            String semester = e.select("select#drpHK.form-control").text();
//            System.out.println(semester);
//
//        }

//        Elements tableSumMark = document2.select("table#tblSumMark.tableborder table#grdResult");
//        for (Element e:
//            tableSumMark) {
//            String value = e.select("tr td.cssListHeader").text();
//            System.out.println(value);
//            Elements result = e.select("tr.cssListItem");
//            for (Element res:
//                result ) {
//                Elements r = res.select("td");
//                System.out.println("size :" + r.size());
//                for (Element element : r) {
//                    System.out.print(element.text() + " ");
//                }
//                System.out.println();
//
//            }
//    }

        Elements tableSumMark = document2.select("table#tblSumMark.tableborder table#grdResult tbody");
        for (Element e:
                tableSumMark) {
            Elements result = e.select("tr");
            for (Element res:
                    result ) {
                Elements r = res.select("td");
                for (Element element : r) {
                    System.out.print(element.text() + " ");
                }
                System.out.println();

            }
        }

        Elements tableSumMarkDetails = document2.select("table#tblStudentMark tbody");
        for (Element e:
                tableSumMarkDetails) {
            Elements result = e.select("tr");
            for (Element res:
                    result ) {
                Elements r = res.select("td");
                for (Element element : r) {
                    System.out.print(element.text() + " ");
                }
                System.out.println();

            }
        }


    }


}
