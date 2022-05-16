package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSpinnerAdapterPVC extends BaseAdapter {
    DBHelper DBhelper;
    ArrayList<PhieuVanChuyen> arrayList;

    public CustomSpinnerAdapterPVC(ArrayList<PhieuVanChuyen> arrayList) {
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
        DBhelper= new DBHelper(parent.getContext(),"qlvc.sqlite",null,1);
        View viewitem= View.inflate(parent.getContext(), R.layout.spn_pvc,null);
        PhieuVanChuyen pvc= (PhieuVanChuyen) getItem(position);
        TextView tvMaPVC =(TextView) viewitem.findViewById(R.id.tvMaPVC_KH);
        tvMaPVC.setText(pvc.getMaPVC());
        TextView tvMaCT =(TextView) viewitem.findViewById(R.id.tvMaCT_KH);
        tvMaCT.setText(pvc.getMaCT());
        TextView tvNgayVC = (TextView) viewitem.findViewById(R.id.tvNgayVC_KH);
        tvNgayVC.setText(pvc.getNgayVC());
        return viewitem;
    }
}
