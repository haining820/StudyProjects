package com.haining820.service.impl;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-07
 * Time: 13:54
 */

import com.haining820.pojo.User;
import com.haining820.service.UserService;
import com.haining820.futureconvert.MyTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getUserById(int id) {
        return new User(id, "yhy", 18, "getUserById->" + MyTools.getCurTime());
    }


}
