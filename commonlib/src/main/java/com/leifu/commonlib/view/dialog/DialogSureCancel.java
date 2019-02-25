package com.leifu.commonlib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.leifu.commonlib.R;
import com.leifu.commonlib.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DialogSureCancel extends Dialog {


    private int cancelColor = -1;
    private int sureColor = -1;
    private String title;
    private String cancel;
    private String sure;
    @BindView(R2.id.title)
    TextView mTitle;
    @BindView(R2.id.btnCancel)
    TextView mBtnCancel;
    @BindView(R2.id.btnSure)
    TextView mBtnSure;
    @BindView(R2.id.line)
    View mLine;
    private onDialogListener listener;
    private onDialogOkListener listenerOk;

    public DialogSureCancel(Context context, String title) {
        super(context, R.style.DialogSureCancelStyle);
        this.title = title;
    }

    public DialogSureCancel(Context context, String title, String cancel, String sure) {
        super(context, R.style.DialogSureCancelStyle);
        this.title = title;
        this.cancel = cancel;
        this.sure = sure;
    }

    public DialogSureCancel(Context context, String title, String cancel, int cancelColor, String sure, int sureColor) {
        super(context, R.style.DialogSureCancelStyle);
        this.title = title;
        this.cancel = cancel;
        this.sure = sure;
        this.cancelColor = cancelColor;
        this.sureColor = sureColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sure_cancel);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
//       设置黑暗度
        Window mWindow = getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.dimAmount = 0.3f;
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mWindow.setAttributes(params);
        if (title != null) {
            mTitle.setText(title);
        }
        if (cancel != null) {
            mBtnCancel.setText(cancel);
        }
        if (cancelColor != -1) {
            mBtnCancel.setTextColor(cancelColor);
        }
        if (sure != null) {
            mBtnSure.setText(sure);
        }
        if (sureColor != -1) {
            mBtnSure.setTextColor(sureColor);
        }
    }
    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public interface onDialogListener {
        void onCancelClick();

        void onOkClick();
    }

    public interface onDialogOkListener {
        void onOkClick();
    }

    public void setOnDialogListener(onDialogListener listener) {
        this.listener = listener;
    }

    public void setOnDialogOkListener(onDialogOkListener listener) {
        this.listenerOk = listener;
    }

    @OnClick({R2.id.btnCancel, R2.id.btnSure})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btnCancel) {
            dismiss();
            if (listener != null) {
                listener.onCancelClick();
            }

        } else if (i == R.id.btnSure) {
            dismiss();
            if (listener != null) {
                listener.onOkClick();
            }
            if (listenerOk != null) {
                listenerOk.onOkClick();
            }
        }
    }
}
