package org.geekY.shortLink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekY.shortLink.admin.common.convention.exception.ClientException;
import org.geekY.shortLink.admin.common.convention.result.Result;
import org.geekY.shortLink.admin.common.convention.result.Results;
import org.geekY.shortLink.admin.common.enums.UserErrorCodeEnum;
import org.geekY.shortLink.admin.dao.entity.UserDO;
import org.geekY.shortLink.admin.dao.mapper.UserMapper;
import org.geekY.shortLink.admin.dto.resp.UserRespDTO;
import org.geekY.shortLink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author geekYang
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public Result<UserRespDTO> getUserByUserName(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
            UserRespDTO result = new UserRespDTO();
            BeanUtils.copyProperties(userDO, result);
            return Results.success(result);


    }
}
