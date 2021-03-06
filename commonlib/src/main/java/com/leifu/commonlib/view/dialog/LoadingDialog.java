package com.leifu.commonlib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.leifu.commonlib.R;


/**
 * 自定义Dialog
 */
public class LoadingDialog extends Dialog {
    private Context context;
    private String msg; //跟随Dialog 一起显示的message 信息！

    public LoadingDialog(Context context, int theme, String msg) {
        super(context, theme);
        this.context = context;
        this.msg = msg;
    }
    public LoadingDialog(Context context, String msg) {
        super(context, R.style.CustomHttpWaitDialog);
        this.context = context;
        this.msg = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view ;
        TextView textView ;
        view = View.inflate(context, R.layout.custom_view_loading, null);
        textView = (TextView) view.findViewById(R.id.tv_message);

        if (!TextUtils.isEmpty(msg)) {
            textView.setText(msg);
        }
        setContentView(view);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

}
