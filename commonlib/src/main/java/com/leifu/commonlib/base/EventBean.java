package com.leifu.commonlib.base;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/30 16:32
 * 描述:eventbus传值
 */

public class EventBean {
    /*在EventBus 3.0中，声明一个订阅方法需要用到@Subscribe注解，因此在订阅者类中添加一个有着@Subscribe注解的方法即可，方法名字可自定义，而且必须是public权限，
    其方法参数有且只能有一个，另外类型必须为第一步定义好的事件类型(比如上面的MessageEvent)
    EventBus.getDefault().post(new EventBean("Hello !....."));
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBean messageEvent){}
    */
    /**
     * @param flag
     * 1.登录页→我的,fragment滚动到顶部
     * 2.ADActivity→WebActivity
     * 3.判断是从myFragment进入开通安全账户还是借款页面进入    -3为借款页面进入,需要认证四步
     * 4.注册页面→首页
     * 5.出借人出借成功后返回首页刷新列表
     */
    private int flag;
    private String message;

    public EventBean(int flag) {
        this.flag = flag;
    }

    public EventBean(int flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }
}
