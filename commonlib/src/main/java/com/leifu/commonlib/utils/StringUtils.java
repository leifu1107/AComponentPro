package com.leifu.commonlib.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 创建人:雷富
 * 创建时间:2018/8/4 14:03
 * 描述:
 */

public class StringUtils {

    /**
     * 判断字符串是否为null
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return null == string || string.trim().length() < 1;
    }

    /**
     * 判断字符串是否为null
     *
     * @return
     */
    public static boolean isEmpty(EditText view) {
        return isEmpty(viewToString(view));
    }

    public static boolean isEmpty(TextView view) {
        return isEmpty(viewToString(view));
    }

    /**
     * 字符串null提示
     *
     * @return
     */
    public static boolean emptyToast(String string, String toast) {
        if (isEmpty(string)) {
            ToastUtil.shortShow(toast);
            return true;
        } else
            return false;

    }

    /**
     * 字符串null提示
     *
     * @param editText
     * @param string
     * @return
     */
    public static boolean emptyToast(EditText editText, String string) {
        if (TextUtils.isEmpty(editText.getText().toString().trim()) || isEmpty(editText.getText().toString().trim())) {
            ToastUtil.shortShow(string);
            return true;
        } else
            return false;

    }

    /**
     * 字符串null提示
     *
     * @param textView
     * @param string
     * @return
     */
    public static boolean emptyToast(TextView textView, String string) {
        if (TextUtils.isEmpty(textView.getText().toString().trim()) || isEmpty(textView.getText().toString().trim())) {
            ToastUtil.shortShow(string);
            return true;
        } else
            return false;
    }

    public static String removeNull(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        } else
            return string;
    }

    /**
     * EditText转化为string
     *
     * @param editText
     * @return
     */
    public static String viewToString(EditText editText) {
        if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
            return editText.getText().toString().trim();
        }
        return "";
    }

    /**
     * TextView转化为string
     *
     * @param editText
     * @return
     */
    public static String viewToString(TextView editText) {
        if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
            return editText.getText().toString().trim();
        }
        return "";
    }

//---------------------------------------------------------------------------------

    /**
     * @param phone
     * @return
     * @Description: 将手机号中间4位，显示成*
     * @Author: gyz
     */
    public static String hidePhone(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            phone = phone.substring(0, 3) + "****" + phone.substring(7);
        }
        return phone;
    }

    /**
     * 隐藏身份证号码,显示前6后4
     *
     * @param numble
     * @return
     */
    public static String hideIdNumble(String numble) {
        if (!StringUtils.isEmpty(numble)) {
            numble = numble.substring(0, 6) + "********" + numble.substring(numble.length() - 4);
        }
        return numble;
    }

    /**
     * @return
     * @Description: 将银行卡尾数4位
     */
    public static String hideBankCard(String bankCard) {
        if (!StringUtils.isEmpty(bankCard)) {
            bankCard = bankCard.substring(bankCard.length() - 4, bankCard.length());
        }
        return bankCard;
    }

    /**
     * 隐藏姓名,最多显示3位数
     *
     * @param name
     * @return
     */
    public static String hideName(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        if (name.length() > 2) {
            name = name.substring(0, 1) + "*" + name.substring(name.length() - 1);
        } else {
            name = name.substring(0, 1) + "*";
        }
        return name;
    }


    /**
     * 小数点后面最多2位  有逗号,如果是0显示0.00
     *
     * @param money
     * @return 124, 456  123,45.12
     */
    public static String string2DecimalComma(double money) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(money);
    }

    /**
     *   有逗号,不显示小数点
     *
     * @param money
     * @return 124,456
     */
    public static String string0DecimalComma(double money) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        return nf.format(money);
    }

    /**
     * 是否为100的倍数
     *
     * @param editText
     * @return
     */
    public static boolean is100Multiple(EditText editText) {
        Double mDouble = Double.valueOf(viewToString(editText));
        if (mDouble % 100 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 超过一万显示万元,小于1万元显示元
     *
     * @return
     */
    public static String formatWanYuan(double numble) {
        try {
            if (numble >= 10000) {
                return priceDeleteZero(ArithUtils.div(numble, 10000, 2)) + "万元";
            } else {
                return priceDeleteZero(numble) + "元";
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }
        return "";
    }


    /**
     * 两位小数点
     *
     * @param num
     * @return
     */
    public static String String2Decimal(Object num) {
        return new DecimalFormat("0.00").format(num);
    }


    public static String getString(String str) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(Double.parseDouble(str));
    }

    public static String priceWhitZero(float price) {
        String money = new DecimalFormat("#.##").format(price);
        if (money.endsWith(".0"))
            money = money + "0";
        return money;
    }


    public static String priceDeleteZero(double price) {
        String money = new DecimalFormat("#.##").format(price);
        String f = Double.parseDouble(money) + "";
        if (f.endsWith(".0") || f.endsWith(".00")) {
            return (int) Float.parseFloat(money) + "";
        }
        return f;
    }

    public static String priceDeleteZero(String price) {
        String money = new DecimalFormat("#.##").format(Float.parseFloat(price));
        String f = Float.parseFloat(money) + "";
        if (f.endsWith(".0") || f.endsWith(".00")) {
            return (int) Float.parseFloat(money) + "";
        }
        return f;
    }

    public static String loatDeleteZero(String f) {
        if (f.endsWith(".0") || f.endsWith(".00")) {
            return (int) Float.parseFloat(f) + "";
        }
        return f;
    }
}
