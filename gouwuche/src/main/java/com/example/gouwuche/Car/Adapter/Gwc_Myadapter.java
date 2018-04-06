package com.example.gouwuche.Car.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;
import com.example.gouwuche.Car.Presenter.gwc_Pc;
import com.example.gouwuche.Car.Bean.slpriceBean;
import com.example.gouwuche.R;
import com.example.gouwuche.utils.manager.DataManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * author:Created by MingShao on 2018/4/5.
 */

public class Gwc_Myadapter extends BaseExpandableListAdapter {
    gwc_Bean gwc_bean;
    Context context;
    int childIndex;
    gwc_Pc gwc_pc;
    Handler handler;
    int allIndex;


    public Gwc_Myadapter(gwc_Bean gwc_bean, Context context, gwc_Pc gwc_pc, Handler handler) {
        this.gwc_bean = gwc_bean;
        this.context = context;
        this.gwc_pc = gwc_pc;
        this.handler = handler;


    }
    @Override
    public int getGroupCount() {
        return gwc_bean.getData().size();
    }


    @Override
    public int getChildrenCount(int i) {
        return gwc_bean.getData().get(i).getList().size();
    }


    @Override
    public Object getGroup(int i) {
        return gwc_bean.getData().get(i);
    }


    @Override
    public Object getChild(int i, int i1) {
        return gwc_bean.getData().get(i).getList().get(i1);
    }


    @Override
    public long getGroupId(int i) {
        return i;
    }


    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final faViewholder fv;
        if(view==null){
            view=View.inflate(context, R.layout.father,null);
            fv=new faViewholder();
            fv.fa_cb=view.findViewById(R.id.fa_cb);
            fv.fa_text=view.findViewById(R.id.fa_text);
            view.setTag(fv);
        }else{
            fv= (faViewholder) view.getTag();
        }
        final gwc_Bean.DataBean dataBean = gwc_bean.getData().get(i);
        fv.fa_text.setText(dataBean.getSellerName());
        fv.fa_cb.setChecked(dataBean.isGroupcheck());
        fv.fa_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childIndex=0;
                qxchild(dataBean,fv.fa_cb.isChecked());
            }
        });
        return view;
    }
    private void qxchild(final gwc_Bean.DataBean dataBean, final boolean checked) {
        final gwc_Bean.DataBean.ListBean listBean = dataBean.getList().get(childIndex);
        Log.d("zzzz", "qxchild: "+listBean.toString());
                     childIndex++;
                    if(childIndex<dataBean.getList().size()){
                        qxchild(dataBean,checked);
                    }else{
                                    gwc_pc.gwcinfo1("4885",String.valueOf(listBean.getSellerid()),String.valueOf(listBean.getPid()),String.valueOf(checked?1:0),String.valueOf(listBean.getNum()));
                    }

    }
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        childViewHolder cv;
        if(view==null){
            view=View.inflate(context,R.layout.child,null);
            cv=new childViewHolder();
            cv.child_cb=view.findViewById(R.id.child_cb);
            cv.child_img=view.findViewById(R.id.child_img);
            cv.child_jia=view.findViewById(R.id.child_jia);
            cv.child_jian=view.findViewById(R.id.child_jian);
            cv.child_sc=view.findViewById(R.id.child_sc);
            cv.child_sl=view.findViewById(R.id.child_sl);
            cv.child_title=view.findViewById(R.id.child_title);
            cv.child_price=view.findViewById(R.id.child_price);
            view.setTag(cv);
        }else{
            cv= (childViewHolder) view.getTag();
        }
        final gwc_Bean.DataBean.ListBean listBean = gwc_bean.getData().get(i).getList().get(i1);
        cv.child_title.setText(listBean.getTitle());
        cv.child_price.setText(listBean.getPrice()+"");
        String[] split = listBean.getImages().split("\\|");
        cv.child_img.setImageURI(split[0]);
        cv.child_sl.setText(listBean.getNum()+"");
        cv.child_cb.setChecked(listBean.getSelected()==0?false:true);
        cv.child_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            gwc_pc.gwcinfo1("4885",String.valueOf(listBean.getSellerid()),String.valueOf(listBean.getPid()),String.valueOf(listBean.getSelected()==0?1:0),String.valueOf(listBean.getNum()));
            }
        });
        cv.child_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            gwc_pc.gwcinfo1("4885",String.valueOf(listBean.getSellerid()),String.valueOf(listBean.getPid()),String.valueOf(listBean.getSelected()),String.valueOf(listBean.getNum()+1));
            }
        });
        cv.child_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            gwc_pc.gwcinfo1("4885",String.valueOf(listBean.getSellerid()),String.valueOf(listBean.getPid()),String.valueOf(listBean.getSelected()),String.valueOf(listBean.getNum()-1));

            }
        });
        cv.child_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            gwc_pc.gwcinfo2("4885",String.valueOf(listBean.getPid()));
            }
        });
        return view;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    //计算总价
    public void zongjia(){
        double price=0;
        int count=0;
        for (int i=0;i<gwc_bean.getData().size();i++){
            List<gwc_Bean.DataBean.ListBean> list = gwc_bean.getData().get(i).getList();
            for(int j=0;j<list.size();j++){
                if(list.get(j).getSelected()==1){
                    price+=list.get(j).getPrice()*list.get(j).getNum();
                    count+=list.get(j).getNum();
                }
            }
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String format = decimalFormat.format(price);
        slpriceBean slpriceBean=new slpriceBean(format,count);
        Message msg = Message.obtain();
        msg.what=0;
        msg.obj=slpriceBean;
        handler.sendMessage(msg);
    }
    public void quanxuan(boolean checked){
        List<gwc_Bean.DataBean.ListBean> list = new ArrayList<>();
        for (int i=0;i<gwc_bean.getData().size();i++){
            List<gwc_Bean.DataBean.ListBean> list1 = gwc_bean.getData().get(i).getList();
            for(int j=0;j<list1.size();j++){
                list.add(list1.get(j));
            }
        }
        allIndex=0;
        gxallChecked(list,checked);
    }
    private void gxallChecked(final List<gwc_Bean.DataBean.ListBean> list, final boolean checked) {
        final gwc_Bean.DataBean.ListBean listBean = list.get(allIndex);
        DataManager manager = new DataManager(context);
        CompositeSubscription mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(
                //observerble 被观察者
                manager.getcall1("4885", String.valueOf(listBean.getSellerid()), String.valueOf(listBean.getPid()), String.valueOf(checked?1:0), String.valueOf(listBean.getNum()))
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
                                        allIndex++;

                                        if(allIndex<list.size()){
                                            gxallChecked(list,checked);
                                        }else{
                                            gwc_pc.gwcinfo("4885");
                                        }
                                    }

                                })
        );
    }
    class faViewholder{
        CheckBox fa_cb;
        TextView fa_text;
    }
    class childViewHolder{
        CheckBox child_cb;
        SimpleDraweeView child_img;
        TextView child_title;
        TextView child_jian;
        TextView child_jia;
        TextView child_sl;
        Button child_sc;
        TextView child_price;
    }
}