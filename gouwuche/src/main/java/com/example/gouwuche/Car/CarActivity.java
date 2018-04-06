package com.example.gouwuche.Car;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gouwuche.Car.Adapter.Gwc_Myadapter;
import com.example.gouwuche.Car.Bean.CC_Bean;
import com.example.gouwuche.Car.Bean.XG_Bean;
import com.example.gouwuche.Car.Bean.gwc_Bean;
import com.example.gouwuche.Car.Bean.slpriceBean;
import com.example.gouwuche.Car.Presenter.gwc_Pc;
import com.example.gouwuche.Car.View.gwc_Vi;
import com.example.gouwuche.DD.MainActivity_dd;
import com.example.gouwuche.R;

import java.util.List;

public class CarActivity extends AppCompatActivity implements gwc_Vi {

private CheckBox qx;
    private myGwc my;
    private TextView zj;
    private Button js;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                slpriceBean slpriceBean = (slpriceBean) msg.obj;
                zj.setText("人民币¥"+slpriceBean.getPrice());
                js.setText("去结算"+slpriceBean.getCount());
            }
        }
    };
    private gwc_Pc gwc_pc;
    private Gwc_Myadapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        qx = findViewById(R.id.qx);
        my = findViewById(R.id.my);
        zj = findViewById(R.id.zj);
        js = findViewById(R.id.js);
        gwc_pc = new gwc_Pc(this,this);
        gwc_pc.gwcinfo("4885");
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CarActivity.this, MainActivity_dd.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void Onsuccess(final gwc_Bean gwc_bean) {


        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.qx:
                        if(myadapter!=null){
                            myadapter.quanxuan(qx.isChecked());
                        }
                        break;
                }
            }
        });


                if(gwc_bean!=null){
                    Log.d("zzz", "run: "+gwc_bean.toString());
                    for (int i=0;i<gwc_bean.getData().size();i++){
                        if(isChildInGroupChecked(i,gwc_bean)){
                            gwc_bean.getData().get(i).setGroupcheck(true);
                        }
                    }
                    qx.setChecked(isAllGroupChecked(gwc_bean));
                    myadapter = new Gwc_Myadapter(gwc_bean,CarActivity.this,gwc_pc,handler);
                    my.setAdapter(myadapter);
                    for (int i=0;i<gwc_bean.getData().size();i++){
                        my.expandGroup(i);
                    }
                    myadapter.zongjia();
                }else{
                    Toast.makeText(CarActivity.this, "购物车空", Toast.LENGTH_SHORT).show();
                }



    }

    @Override
    public void Onsuccess1(XG_Bean xg_bean) {
        if (xg_bean.getCode().equals("0")){
            gwc_pc.gwcinfo("4885");
            myadapter.notifyDataSetChanged();
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Onsuccess2(CC_Bean xg_bean) {
        if (xg_bean.getCode().equals("0")){
            gwc_pc.gwcinfo("4885");
            myadapter.notifyDataSetChanged();
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAllGroupChecked(gwc_Bean gwc_bean){
        for (int i=0;i<gwc_bean.getData().size();i++){
            if(!gwc_bean.getData().get(i).isGroupcheck()){
                return false;
            }
        }
        return  true;
    }
    private  boolean isChildInGroupChecked(int i,gwc_Bean gwc_bean){
        List<gwc_Bean.DataBean.ListBean> list = gwc_bean.getData().get(i).getList();
        for (int j=0;j<list.size();j++){
            if(list.get(j).getSelected()==0){
                return  false;
            }
        }
        return  true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        gwc_pc.gwcinfo("4885");
    }
}
