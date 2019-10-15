package com.coolance.exception;

import lombok.Data;

/**
 * @ClassName CheckException
 * @Description 自定义异常类
 * @Author Coolance
 * @Version
 * @Date 2019/10/14 10:24
 */
@Data
public class CheckException extends RuntimeException {

    private String fieldName;

    private String fieldValue;

    public CheckException(String filedName, String filedValue) {
        super();
        this.fieldName = filedName;
        this.fieldValue = filedValue;
    }
}
