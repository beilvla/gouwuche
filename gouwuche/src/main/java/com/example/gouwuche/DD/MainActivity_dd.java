package com.example.gouwuche.DD;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gouwuche.DD.Adapter.MyViewpagerAdapter;
import com.example.gouwuche.DD.Presenter.MyPresenter;
import com.example.gouwuche.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by MingShao on 2018/4/5.
 */

public class MainActivity_dd extends AppCompatActivity {
    /**
     * 待支付
     */
    private RadioButton mRaido01;
    /**
     * 已支付
     */
    private RadioButton mRaido02;
    /**
     * 已取消
     */
    private RadioButton mRaido03;
    private RadioGroup mGroup;
    private ViewPager mViewpager;
    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd);
        initView();
        myPresenter = new MyPresenter();

        mGroup.setOnCheckedChangeListener(myPresenter);
        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FragmentThree());
        list.add(new FragmentFour());

        myPresenter.setToast(new MyPresenter.ContentListeren() {
            @Override
            public void setContent(int i) {
                switch (i){
                    default:break;
                    case 0:
                        mViewpager.setCurrentItem(0,true);

                        break;
                    case 1:
                        mViewpager.setCurrentItem(1,true);

                        break;
                    case 2:
                        mViewpager.setCurrentItem(2,true);

                        break;
                    case 3:
                        mViewpager.setCurrentItem(3,true);

                        break;
                }
            }
        });
        MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter(getSupportFragmentManager(),list);


        mViewpager.setAdapter(myViewpagerAdapter);
    }

    private void initView() {
        mRaido01 = (RadioButton) findViewById(R.id.raido01);
        mRaido02 = (RadioButton) findViewById(R.id.raido02);
        mRaido03 = (RadioButton) findViewById(R.id.raido03);
        mGroup = (RadioGroup) findViewById(R.id.group);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

    }
}
