package com.muyang.snackbardemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_normal, btn_callback, btn_custom;
    private CoordinatorLayout mCoor;
    private Snackbar mSnackBar, mCustomSnackBar;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        mSnackBar = Snackbar.make(mCoor, R.string.callback, Snackbar.LENGTH_SHORT)
                .setAction(R.string.UNDO, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        //SnackBar回调方法
        mSnackBar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Toast.makeText(MainActivity.this,"SnackBar Dismiss",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Toast.makeText(MainActivity.this,"SnackBar Show",Toast.LENGTH_SHORT).show();
            }
        });

//       1、Resources resource = (Resources) getBaseContext().getResources();
//       ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.PeachPuff);
//       mSnackBar.setActionTextColor(csl);
//       2、mSnackBar.setActionTextColor(Color.rgb(232,44,123))
//       3、mSnackBar.setActionTextColor(Color.BLUE)
        mSnackBar.setActionTextColor(Color.parseColor("#FFDAB9"));

        //自定义SnackBar样式
        mCustomSnackBar = Snackbar.make(mCoor, R.string.custom, Snackbar.LENGTH_SHORT)
                .setAction(R.string.UNDO, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        mCustomSnackBar.setActionTextColor(Color.parseColor("#FFDAB9"));
        //获得SnackBar这个View
        view = mCustomSnackBar.getView();
        if (view != null) {
            view.setBackgroundColor(Color.parseColor("#7B68EE"));
            //获取Snackbar的message控件，修改字体颜色
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#FFDAB9"));
            //添加图标
            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) view;
            //custom_layout是你自定义的布局
            View add_view = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_layout, null);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.gravity = Gravity.CENTER_VERTICAL;
            //数字表示新加的布局在SnackBar中的位置，从0开始,取决于你SnackBar里面有多少个子View
            snackbarLayout.addView(add_view, 0, p);
        }
    }


    private void initWidget() {
        btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(this);
        btn_callback = (Button) findViewById(R.id.btn_callback);
        btn_callback.setOnClickListener(this);
        btn_custom = (Button) findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(this);
        mCoor = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                Snackbar.make(mCoor, R.string.normal, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_callback:

//                Snackbar.make(mCoor,R.string.callback,Snackbar.LENGTH_SHORT)
//                        .setAction(R.string.UNDO, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        }).show();
                mSnackBar.show();
                break;
            case R.id.btn_custom:
                mCustomSnackBar.show();
//                if (mSnackBar.isShown()) {
//                    mSnackBar.dismiss();
//                }
                break;
        }
    }
}
