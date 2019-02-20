package com.leifu.acomponentdemo;

import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.BasePresenter;
import com.leifu.commonlib.base.BaseView;

/**
 * 创建人:雷富
 * 创建时间:2019/2/20 11:01
 * 描述:
 */
public interface MainContract {
    interface View extends BaseView {
        void showContent(BaseBean baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getDatas();
    }
}
