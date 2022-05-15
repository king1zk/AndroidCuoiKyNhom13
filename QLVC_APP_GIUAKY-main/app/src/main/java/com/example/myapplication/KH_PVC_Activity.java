package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Model.KH;
import com.example.myapplication.Model.KhachHang_PVC;

import java.util.ArrayList;

public class KH_PVC_Activity extends AppCompatActivity {
    int position;
    DBHelper DBhelper;
    ImageView btnAdd;
    ListView listViewKH_PVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_pvc);
        DBhelper = new DBHelper(KH_PVC_Activity.this, "qlvc.sqlite", null, 1);
        Intent intent = getIntent();
        KH kh = (KH) intent.getSerializableExtra("KhachHang");
        btnAdd = findViewById(R.id.imgbtn_addKH_PVC);
        listViewKH_PVC = findViewById(R.id.lvDSKH_PVC);

        //GET DANH SÁCH Khách hàng - Phiếu vận chuyển
        ArrayList<KhachHang_PVC> arrKH_PVC = new ArrayList<>();
        Cursor dt = DBhelper.GetData("select * from KH_PVC where maKH = '"+kh.getMaKH()+"'");
        while (dt.moveToNext()) {
            Log.d("SelectKH_PVC", dt.getString(0));
            KhachHang_PVC kh_pvc = new KhachHang_PVC(Integer.parseInt(dt.getString(0)), Boolean.parseBoolean(dt.getString(2)), dt.getString(3),dt.getString(1));
            arrKH_PVC.add(kh_pvc);
        }
        CustomAdapter_KH_PVC adapter = new CustomAdapter_KH_PVC(arrKH_PVC, this, R.layout.item_kh_pvc );
        listViewKH_PVC.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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