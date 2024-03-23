package org.geekY.shortLink.admin.controller;

import org.geekY.shortLink.admin.common.convention.result.Result;
import org.geekY.shortLink.admin.dto.resp.UserRespDTO;
import org.geekY.shortLink.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekYang
 * @version 1.0
 * @since 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username")String username){
        return userService.getUserByUserName(username);
    }
}
