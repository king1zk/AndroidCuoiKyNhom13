package com.example.myapplication;

import static com.example.myapplication.ext.ConstExt.POSITION;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DBHelper;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;
import com.example.myapplication.SuaCongTrinhActivity;

import java.util.ArrayList;

public class CustomAdapter_CongTrinh extends BaseAdapter {
    ArrayList<CongTrinh> arrayList;
    Context context;
    int layout;
    DBHelper DBhelper;

    public CustomAdapter_CongTrinh(ArrayList<CongTrinh> arrayList, Context context, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DBhelper = new DBHelper(parent.getContext(), "qlvc.sqlite", null, 1);
        View viewitem = View.inflate(parent.getContext(), R.layout.item_dsct, null);
        CongTrinh CT = (CongTrinh) getItem(position);
        TextView tvMaCT = (TextView) viewitem.findViewById(R.id.tvMaCT);
        tvMaCT.setText(String.valueOf(CT.getMaCT()));
        TextView tvTenCT = (TextView) viewitem.findViewById(R.id.tvTenCT);
        tvTenCT.setText(CT.getTenCT());
        TextView tvDChi = (TextView) viewitem.findViewById(R.id.tvDC);
        tvDChi.setText(CT.getDiaChi());

        ImageView btnEditCT = viewitem.findViewById(R.id.btnUpdate);
        btnEditCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POSITION = position;
                Intent intent = new Intent(context, SuaCongTrinhActivity.class);
                context.startActivity(intent);
            }
        });
        ImageView btnXoa = viewitem.findViewById(R.id.btnDeleteCT);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DBhelper.deleteCT(arrayList.get(position));
                } catch (Exception ex) {
                    Log.d("huy", "ko xoa");
                }
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });


        return viewitem;
    }
}