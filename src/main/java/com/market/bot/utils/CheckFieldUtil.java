package com.market.bot.utils;

import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

import java.util.Arrays;

/**
 * =============================================================
 *
 * @Project : MarketBot
 * @Package : com.market.bot.utils
 * @Class : CheckFieldUtil
 * @Author : 22488
 * @Date : 2025/2/23 下午6:27
 * @Version : 1.0
 * @Description : [功能描述]
 * <p>
 * =============================================================
 * @History Date            Author      Version     Description
 * -------------------------------------------------------------
 * 2025/2/23        22488      1.0         初始创建
 * [其他修改记录]
 * =============================================================
 * Copyright (c) 2025. All rights reserved.
 */
@Component
public class CheckFieldUtil {
    //获取对象中所有null值的字段名
    public static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(source.getClass().getDeclaredFields())
                .filter(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(source) == null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .map(Field::getName)
                .toArray(String[]::new);
    }
}
