package com.example.myapplication;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter_PVC extends BaseAdapter {
    ArrayList<PhieuVanChuyen> arrayList;
    private DBHelper DBhelper;

    public CustomAdapter_PVC(ArrayList<PhieuVanChuyen> arrayList) {
        this.arrayList = arrayList;
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
        View viewitem = View.inflate(parent.getContext(), R.layout.item_pvc, null);
        PhieuVanChuyen PVC = (PhieuVanChuyen) getItem(position);
        TextView tvMaPVC = (TextView) viewitem.findViewById(R.id.tvMaPVC);
        tvMaPVC.setText(String.valueOf(PVC.getMaPVC()));
        TextView tvNgay = (TextView) viewitem.findViewById(R.id.tvNgayVC);
        tvNgay.setText(PVC.getNgayVC());
        TextView tvTenCT = (TextView) viewitem.findViewById(R.id.tvTenCT);
        Cursor dt = DBhelper.GetData("select tenCT from congtrinh where maCT = '"+PVC.getMaCT()+"'");
        try{
            tvTenCT.setText(dt.getString(1));
        }catch (Exception e){
            System.out.print(e.toString());
        }


        ImageView btnDelete = viewitem.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DBhelper.deletePVC(arrayList.get(position));
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
