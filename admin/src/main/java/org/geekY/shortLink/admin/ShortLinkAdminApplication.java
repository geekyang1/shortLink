package org.geekY.shortLink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekYang
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@MapperScan("org.geekY.shortLink.admin.dao.mapper")
public class ShortLinkAdminApplication {
    public static void main(String[] args) {
         SpringApplication.run(ShortLinkAdminApplication.class,args);
    }
}
