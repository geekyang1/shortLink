package org.geekY.shortLink.admin.dto.resp;

import lombok.Data;

/**
 * @author geekYang
 * @version 1.0
 * @since 1.0
 */
@Data
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
