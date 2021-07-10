package com.example.config;

import com.bcos.asset.client.EDVclient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: 谢佳辉
 * @date 2021/5/9 4:16 下午
 */
@Component
public class BcosConfig {
    public static EDVclient edVclient;
    @Bean
    public EDVclient BcosConfigImpl() throws Exception {
        if(edVclient==null){
            edVclient = new EDVclient();
            edVclient.initialize();
        }
        return  edVclient;

    }
}
