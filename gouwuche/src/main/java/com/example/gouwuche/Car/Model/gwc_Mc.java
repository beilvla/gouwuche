package com.example.gouwuche.Car.Model;

import android.content.Context;
import android.util.Log;

import com.example.gouwuche.Car.Bean.CC_Bean;
import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;
import com.example.gouwuche.Car.Presenter.gwc_Pi;
import com.example.gouwuche.utils.manager.DataManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * author:Created by MingShao on 2018/4/5.
 */

public class gwc_Mc {
    Context context;

    public gwc_Mc(Context context) {
        this.context = context;
    }

    public void gwcinfo(final gwc_Pi gwc_pi, String uid){
        DataManager manager = new DataManager(context);
        CompositeSubscription mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(

                //observerble 被观察者
                manager.getcall(uid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //订阅
                        .subscribe(
                                //观察者
                                new Observer<gwc_Bean>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.d("xxx", "onCompleted: "+"onCompleted");
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(gwc_Bean bean) {

                                        gwc_pi.Onsuccess(bean);
                                    }

                                })
        );
    }
    public void gwcinfo1(final gwc_Pi gwc_pi,String uid,String sellerid, String pid, String selected, String num){
        DataManager manager = new DataManager(context);
        CompositeSubscription mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(

                //observerble 被观察者
                manager.getcall1(uid, sellerid, pid, selected, num)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //订阅
                        .subscribe(
                                //观察者
                                new Observer<XG_Bean>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.d("xxx", "onCompleted: "+"onCompleted");
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(XG_Bean bean) {

                                        gwc_pi.Onsuccess1(bean);
                                    }

                                })
        );
    }
    public void gwcinfo2(final gwc_Pi gwc_pi,String uid,String pid){
        DataManager manager = new DataManager(context);
        CompositeSubscription mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(

                //observerble 被观察者
                manager.getcall2(uid,pid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //订阅
                        .subscribe(
                                //观察者
                                new Observer<CC_Bean>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.d("xxx", "onCompleted: "+"onCompleted");
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(CC_Bean bean) {

                                        gwc_pi.Onsuccess2(bean);
                                    }

                                })
        );
    }
//        Map<String,String> map = new HashMap<>();
//        map.put("uid","4885");
//        OkHttp3Util.doPost(Wz.cx, map, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//
//            }
//
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if(response.isSuccessful()){
//                    String string = response.body().string();
//                    gwc_Bean gwc_bean = new Gson().fromJson(string, gwc_Bean.class);
//
//
//                    gwc_pi.Onsuccess(gwc_bean);
//                }
//            }
//        });
    }

