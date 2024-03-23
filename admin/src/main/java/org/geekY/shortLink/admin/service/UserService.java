package org.geekY.shortLink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekY.shortLink.admin.common.convention.result.Result;
import org.geekY.shortLink.admin.dao.entity.UserDO;
import org.geekY.shortLink.admin.dto.resp.UserRespDTO;


public interface UserService extends IService<UserDO> {
    Result<UserRespDTO> getUserByUserName(String username);
}
