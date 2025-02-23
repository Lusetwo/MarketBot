package com.market.bot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * =============================================================
 *
 * @Project : MarketBot
 * @Package : com.market.bot.config
 * @Class : OpenApiConfig
 * @Author : 22488
 * @Date : 2025/2/23 下午7:27
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
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("示例 API")
                        .version("1.0")
                        .description("这是一个 Spring Boot + Swagger 3（OpenAPI 3）的示例应用")
                );
    }
}
