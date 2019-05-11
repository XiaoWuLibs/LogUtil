package com.demo.library.exception;

/**
 * Created by  wsl
 * on 2019/5/10 18:07
 * 抛出全局异常
 */
public class NoInitException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public static final String APPLICATION_CONTEXT_IS_NULL = "请检查你项目的Application中是否引入LogUtilApplication.init()方法或者实现LogUtilApplication";

    /**
     * Constructor of NoInitException.
     *
     * @param errorMessage the description of this exception.
     */
    public NoInitException(String errorMessage) {
        super(errorMessage);
    }
}
