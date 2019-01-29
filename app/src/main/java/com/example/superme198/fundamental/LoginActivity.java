package com.example.superme198.fundamental;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    /**Link:https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //new FetchURLAsyncTask().execute();
    }

    public class FetchURLAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                login();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
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
            showStudentMark(loginForm);
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
}
