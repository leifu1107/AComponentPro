package com.leifu.modulea.presenter;

import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.RxPresenter;
import com.leifu.commonlib.base.RxUtil;
import com.leifu.commonlib.exception.CommonSubscriber;
import com.leifu.commonlib.http.Apis;
import com.leifu.commonlib.http.RetrofitHelper;
import com.leifu.modulea.presenter.contract.MainContract;

import javax.inject.Inject;

/**
 * 创建人:雷富
 * 创建时间:2019/2/20 10:58
 * 描述:
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private Apis apis;
    private RetrofitHelper retrofitHelper;

//    @Inject
//    public MainPresenter(RetrofitHelper retrofitHelper) {
//        this.retrofitHelper = retrofitHelper;
//    }
    @Inject
    public MainPresenter(Apis apis) {
        this.apis = apis;
    }

    @Override
    public void getDatas() {
        addSubscribe(apis.getDatas()
                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
                .compose(RxUtil.<BaseBean>handleResult1())
                .subscribeWith(new CommonSubscriber<BaseBean>(mView) {
                    @Override
                    public void onNext(BaseBean bean) {
                        mView.showContent(bean);
                    }
                })
        );
    }
}
