package com.duobi.wuye.service;

import com.duobi.wuye.dao.TestDao;
import com.duobi.wuye.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public User getUserById(User user){
        return testDao.getUserById(user);
    }

}
