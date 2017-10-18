package com.example.jh.data.exception;



import com.example.jh.data.MessageEvent;
import com.example.jh.data.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;


public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = -2;

    public ApiException(BaseEntity baseEntity) {
        this(getApiExceptionMessage(baseEntity));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }


    private static String getApiExceptionMessage(BaseEntity baseEntity){
        String message = "";
        switch (baseEntity.getErrcode()) {
            case USER_NOT_EXIST:
                message = "用户名或密码错误";
                EventBus.getDefault().post(new MessageEvent("x"));
                break;
            case -7:
                message = "没有定位数据";
                break;
            case -9:
                message = "请求认证不通过";
                break;
            case -99:
                message = "您的操作过于频繁，请稍后再试！";  // 获取验证码错误 改为 您的操作过于频繁，请稍后再试！
                break;
            case -10:
                message = "没有管理员权限";
                break;
            case -11:
                message = "设备离线，请开启设备";
                break;
            case -15:
                message = "验证码错误";
                break;
            default:
                if(baseEntity.getErrmsg().equals("exceed max size")) {
                    message="超过的最大限制";
                }else {
                    message = baseEntity.getErrmsg();
                }

                break;

        }
        return message;
    }
}

