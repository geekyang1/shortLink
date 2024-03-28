package org.geekY.shortLink.admin.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.geekY.shortLink.admin.common.convention.exception.ClientException;
import org.geekY.shortLink.admin.common.enums.UserErrorCodeEnum;
import org.geekY.shortLink.admin.dao.entity.UserDO;
import org.geekY.shortLink.admin.dao.mapper.UserMapper;
import org.geekY.shortLink.admin.dto.req.UserRegisterReqDTO;
import org.geekY.shortLink.admin.dto.resp.UserRespDTO;
import org.geekY.shortLink.admin.service.UserService;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static org.geekY.shortLink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static org.geekY.shortLink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * @author geekYang
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String>  userRegisterCachePenetrationBloomFilter;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
            UserRespDTO result = new UserRespDTO();
            BeanUtils.copyProperties(userDO, result);
            return result;


    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }




    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(!hasUsername(requestParam.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        int insert = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        if(insert<1){
            throw new ClientException(USER_SAVE_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
    }
}
