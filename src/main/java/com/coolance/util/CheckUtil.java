package com.coolance.util;

import com.coolance.exception.CheckException;

import java.util.stream.Stream;

/**
 * @ClassName CheckUtil
 * @Description 参数校验工具类
 * @Author Coolance
 * @Version
 * @Date 2019/10/14 10:22
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin", "管理员"};

    public static void checkName(String name) {
        Stream.of(INVALID_NAMES)
                .filter(value -> value.equalsIgnoreCase(name))
                .findAny()
                .ifPresent(value -> {
                    throw new CheckException("name", value);
                });
    }
}
