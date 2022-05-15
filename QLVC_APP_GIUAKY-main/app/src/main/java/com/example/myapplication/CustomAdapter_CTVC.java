package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DBHelper;
import com.example.myapplication.Model.ChiTietPVC;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapter_CTVC extends BaseAdapter {
    ArrayList<ChiTietPVC> arrayList;
    Context context;
    int layout;
    private DBHelper DBhelper;

    public CustomAdapter_CTVC(ArrayList<ChiTietPVC> arrayList, Context context, int layout) {
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
        DBhelper = new DBHelper(context, "qlvc.sqlite", null, 1);
        View viewitem = View.inflate(parent.getContext(), R.layout.item_ctvc, null);
        ChiTietPVC Ctvc = (ChiTietPVC) getItem(position);
        TextView tvMaPVC = (TextView) viewitem.findViewById(R.id.tvMaPVC);
        tvMaPVC.setText(String.valueOf(Ctvc.getMaPVC()));
        TextView tvMAVT = (TextView) viewitem.findViewById(R.id.tvMaVT);
        tvMAVT.setText(String.valueOf(Ctvc.getMaVt()));
        TextView tvSL = (TextView) viewitem.findViewById(R.id.tvSoLuong);
        tvSL.setText(String.valueOf(Ctvc.getSoLuong()));
        TextView tvCuLy = (TextView) viewitem.findViewById(R.id.tvCuLy);
        tvCuLy.setText(Ctvc.getCuLy() + "km");
        ImageView btnDelete = viewitem.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DBhelper.deleteCTVC(arrayList.get(position));
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
