package com.sf.core.exception;

import java.io.Serializable;

/**
 * 描述:
 *
 * Created by 828477[JAX] on 2016/3/8 19:35.
 */
public class DataServiceAccessException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 2082896038681813896L;

    public DataServiceAccessException(Throwable cause) {
        super(cause);
    }

    public DataServiceAccessException(String message) {
        super(message);
    }

    public DataServiceAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
