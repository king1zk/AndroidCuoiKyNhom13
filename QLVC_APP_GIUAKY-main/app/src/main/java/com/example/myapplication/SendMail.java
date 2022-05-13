package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SendMail extends AppCompatActivity {

    DBHelper DBhelper;
    EditText myEmail;
    EditText myPassword;
    EditText receiverEmail;
    EditText Title;
    EditText Message;
    Button BtnSendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmail);

      //  myEmail=(EditText)findViewById(R.id.myEmail);
       // myPassword=(EditText)findViewById(R.id.myPassword);
        receiverEmail=(EditText)findViewById(R.id.receiverEmail);
     //   Title=(EditText)findViewById(R.id.title);
        Message=(EditText)findViewById(R.id.message);
        BtnSendEmail=(Button) findViewById(R.id.SendEmail);
 //       ListView listViewKH = findViewById(R.id.lvDSKH);

//        ArrayList<KH> ArrKH = new ArrayList<>();
//        DBhelper = new DBHelper(this, "qlvc.sqlite", null, 1);
//        Cursor kh = DBhelper.GetData("select * from KH");
//        while (kh.moveToNext()) {
//            Log.d("SelectKH", kh.getString(0));
//            KH KH = new KH(kh.getInt(0), kh.getString(1), kh.getString(2), kh.getString(3),kh.getString(4));
//            ArrKH.add(KH);
//        }
//        CustomAdapter_KhachHang adapter = new CustomAdapter_KhachHang(ArrKH);
//        listViewKH.setAdapter(adapter);

        BtnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String sender=myEmail.getText().toString();
                String sender="khanhhatasudai@gmail.com";
            //    String senderPass=myPassword.getText().toString();
                String senderPass="nguyenkhanh123";
                String receiver=receiverEmail.getText().toString();
            //    String receiver=kh.getString(3);
             //   String title=Title.getText().toString();
                String title="Trạng thái";
                String message=Message.getText().toString();

                sendEmail(sender,senderPass,receiver,title,message);
            }
        });

    }

    private void sendEmail(final String Sender,final String Password,final String Receiver,final String Title,final String Message)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Receiver);
                    makeAlert();

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }
    private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(SendMail.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
