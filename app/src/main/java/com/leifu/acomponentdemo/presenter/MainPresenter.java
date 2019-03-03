package com.leifu.acomponentdemo.presenter;

import com.leifu.acomponentdemo.presenter.contract.MainContract;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.RxPresenter;
import com.leifu.commonlib.base.RxUtil;
import com.leifu.commonlib.http.apis.OtherApi;
import com.leifu.commonlib.http.exception.CommonSubscriber;
import com.leifu.commonlib.utils.ParamMapTool;
import com.leifu.commonlib.utils.encode.DesUtils;

import javax.inject.Inject;

/**
 * 创建人:雷富
 * 创建时间:2019/2/20 10:58
 * 描述:
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
//    private OtherApi retrofitHelper;
//
//    @Inject
//    public MainPresenter(OtherApi retrofitHelper) {
//        this.retrofitHelper = retrofitHelper;
//    }
//
//    @Override
//    public void getDatas() {
//        addSubscribe(retrofitHelper.getDatas()
//                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
//                .compose(RxUtil.<BaseBean>handleResult1())
//                .subscribeWith(new CommonSubscriber<BaseBean>(mView) {
//                    @Override
//                    public void onNext(BaseBean bean) {
//                        mView.showContent(bean);
//                    }
//                })
//        );
//    }

    private OtherApi otherApi;

    @Inject
    public MainPresenter(OtherApi otherApi) {
        this.otherApi = otherApi;
    }

    @Override
    public void getData() {
        addSubscribe(otherApi.loginRequest(new ParamMapTool()
                .put("userCode", "18268180705")
                .put("cid", "111")
                .put("mobileModel", "222")
                .put("password", DesUtils.encrypt("1qaz2wsx")).build())
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
