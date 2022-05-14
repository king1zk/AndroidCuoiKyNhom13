package com.example.myapplication;

import static com.example.myapplication.ext.ConstExt.POSITION;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter_KhachHang extends BaseAdapter {
    ArrayList<KH> arrayList;
    Context context;
    int layout;
    DBHelper DBhelper;

    public CustomAdapter_KhachHang(ArrayList<KH> arrayList, Context context, int layout) {
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
        View viewitem = View.inflate(parent.getContext(), R.layout.item_dskh, null);
        KH KH = (KH) getItem(position);
        TextView tvMaKH = (TextView) viewitem.findViewById(R.id.tvMaKH);
        tvMaKH.setText(String.valueOf(KH.getMaKH()));
        TextView tvTenKH = (TextView) viewitem.findViewById(R.id.tvTenKH);
        tvTenKH.setText(KH.getTenKH());
        TextView tvEmail = (TextView) viewitem.findViewById(R.id.tvEmail);
        tvEmail.setText(KH.getEmail());
        TextView tvSdt = (TextView) viewitem.findViewById(R.id.tvSdt);
        tvSdt.setText(KH.getSdt());
        ImageView btnDelete = viewitem.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DBhelper.deleteKH(arrayList.get(position));
                } catch (Exception ex) {
                    Log.d("huy", "ko xoa");
                }
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        ImageView btnEditKH = viewitem.findViewById(R.id.btnEditKH);
        btnEditKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POSITION = position;
                Intent intent = new Intent(context, SuaKHActivity.class);
                context.startActivity(intent);
            }
        });
        ImageView btnSendMail = viewitem.findViewById(R.id.btnSendMail);
        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBhelper.sendKH(arrayList.get(position));
                } catch (Exception ex) {
                    Log.d("huy", "ko xoa");
                }
                arrayList.get(position).getEmail();
            }
        });
        return viewitem;
    }
}