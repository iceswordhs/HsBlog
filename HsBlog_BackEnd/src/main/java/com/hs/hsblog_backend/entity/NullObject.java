package com.hs.hsblog_backend.entity;

import java.io.Serializable;

/**
 * 一个空对象，用于返回无数据时的Result
 * @author Hs
 * @Date 2021/11/29 21:53
 */
public class NullObject implements Serializable {
    public static final NullObject INSTANCE=new NullObject();

    // 必须要有一个属性 不然jackson会报错
    public Object object=null;

    private static final long serialVersionUID=1L;

    public NullObject(){

    }

    @Override
    public String toString() {
        return "";
    }
}
