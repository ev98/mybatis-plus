package com.ev.user.service.impl;

import com.ev.user.entity.User;
import com.ev.user.mapper.UserMapper;
import com.ev.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ev
 * @since 2020-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
