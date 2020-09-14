package com.lead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lead.dao.UserDao;
import com.lead.entity.User;
import com.lead.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author mac
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 登陆验证用户
     *
     * @param user
     * @return
     */
    @Override
    public User verifyUser(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", user.getName());
        return userDao.selectOne(userQueryWrapper);
    }
}
