package com.lead.service;

import com.lead.entity.User;

/**
 * @author mac
 * @date 2020/9/13
 */
public interface UserService {

    /**
     * 根据name查询用户
     *
     * @param user
     * @return
     */
    User verifyUser(User user);
}
