package com.example.gouwuche.Car.Presenter;

import android.content.Context;

import com.example.gouwuche.Car.Bean.CC_Bean;
import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;
import com.example.gouwuche.Car.Model.gwc_Mc;
import com.example.gouwuche.Car.View.gwc_Vi;

/**
 * author:Created by MingShao on 2018/4/5.
 */

public class gwc_Pc implements gwc_Pi{
    gwc_Vi gwc_vi;
    gwc_Mc gwc_mc;
    Context context;


    public gwc_Pc(gwc_Vi gwc_vi,Context context) {
        this.gwc_vi = gwc_vi;
        gwc_mc=new gwc_Mc(context);
        this.context = context;
    }

    public void gwcinfo(String uid){
        gwc_mc.gwcinfo(this,uid);
    }
    public void gwcinfo1(String uid, String sellerid, String pid, String selected, String num){
        gwc_mc.gwcinfo1(this,uid, sellerid, pid, selected, num);
    }
    public void gwcinfo2(String uid,String pid){
        gwc_mc.gwcinfo2(this,uid,pid);
    }


    @Override
    public void Onsuccess(gwc_Bean gwc_bean) {
        gwc_vi.Onsuccess(gwc_bean);
    }

    @Override
    public void Onsuccess1(XG_Bean xg_bean) {
        gwc_vi.Onsuccess1(xg_bean);
    }

    @Override
    public void Onsuccess2(CC_Bean xg_bean) {
        gwc_vi.Onsuccess2(xg_bean);
    }
}