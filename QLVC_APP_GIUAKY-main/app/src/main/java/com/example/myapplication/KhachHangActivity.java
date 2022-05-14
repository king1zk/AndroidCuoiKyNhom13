package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.myapplication.ext.ConstExt.POSITION;

public class KhachHangActivity extends AppCompatActivity {

    DBHelper DBhelper;
    ImageView btnSend;
    private ArrayList<KH> ArrKH = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        // GET THONG TIN CT
        ImageButton imgbtn_addkh = findViewById(R.id.imgbtn_addkh);
        ListView listViewKH = findViewById(R.id.lvDSKH);
        btnSend = findViewById(R.id.btnSend);

        ArrayList<KH> ArrKH = new ArrayList<>();
        DBhelper = new DBHelper(this, "qlvc.sqlite", null, 1);
        Cursor kh = DBhelper.GetData("select * from KH");
        while (kh.moveToNext()) {
            Log.d("SelectKH", kh.getString(0));
            KH KH = new KH(kh.getInt(0), kh.getString(1), kh.getString(2), kh.getString(3));
            ArrKH.add(KH);
        }
        CustomAdapter_KhachHang adapter = new CustomAdapter_KhachHang(ArrKH, this, R.layout.item_dskh);
        listViewKH.setAdapter(adapter);
//
//        listViewKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                POSITION = position;
//                Intent intent = new Intent(KhachHangActivity.this, SuaKHActivity.class);
//                startActivity(intent);
//            }
//        });
        imgbtn_addkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhachHangActivity.this, ThemKHActivity.class);
                startActivity(intent);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KhachHangActivity.this, SendMail.class));
            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_danhsach, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.memu_home:
                Intent intent =new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}