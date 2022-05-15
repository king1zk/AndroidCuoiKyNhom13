package com.example.myapplication.Model;

import java.util.Date;

public class KhachHang_PVC {
    int maKH;
    Boolean trangThai;
    String ngayTT;
    String maPVC;

    public KhachHang_PVC() {
    }

    public KhachHang_PVC(int maKH, Boolean trangThai, String ngayTT, String maPVC) {
        this.maKH = maKH;
        this.trangThai = trangThai;
        this.ngayTT = ngayTT;
        this.maPVC = maPVC;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(String ngayTT) {
        this.ngayTT = ngayTT;
    }

    public String getMaPVC() {
        return maPVC;
    }

    public void setMaPVC(String maPVC) {
        this.maPVC = maPVC;
    }
}
