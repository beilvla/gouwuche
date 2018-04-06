package com.example.gouwuche.utils.manager;

import android.content.Context;

import com.example.gouwuche.Car.Bean.CC_Bean;
import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    //构造器里获取RetrofitService
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    //网络请求方法getSearchBooks
    public Observable<gwc_Bean> getcall(String uid){
        return mRetrofitService.getcall(uid);
    }
    public Observable<XG_Bean> getcall1(String uid, String sellerid, String pid, String selected, String num){
        return mRetrofitService.getcall1(uid, sellerid, pid, selected, num);
    }
    public Observable<CC_Bean> getcall2(String uid,String pid){
        return mRetrofitService.getcall2(uid,pid);
    }
}
