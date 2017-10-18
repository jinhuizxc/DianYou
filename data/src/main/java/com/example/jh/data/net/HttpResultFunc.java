package com.example.jh.data.net;


import com.example.jh.data.entity.BaseEntity;
import com.example.jh.data.exception.ApiException;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import rx.functions.Func1;

/**
 * Created by fflamingogo on 2016/8/22.
 */
@RxLogSubscriber
public class HttpResultFunc<T> implements Func1<BaseEntity<T>, T> {

    @Override
    public T call(BaseEntity<T> baseEntity) {
        if (baseEntity.getErrcode() == 0) {
            if(baseEntity.getData()==null) {
                    return null;
            }else {
                return baseEntity.getData();
            }
        }else{
            throw new ApiException(baseEntity);
        }

    }


}
