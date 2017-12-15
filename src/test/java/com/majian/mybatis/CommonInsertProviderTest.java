package com.majian.mybatis;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by majian on 2017/12/9.
 */
public class CommonInsertProviderTest {

    private CommonInsertProvider<User> userCommonInsertProvider = new CommonInsertProvider<>();
    @Test
    public void insert() throws Exception {
        String insert = userCommonInsertProvider.insert(new User());
        String expeted = "INSERT INTO user\n"
            + " (user_name, user_age)\n"
            + "VALUES (#{userName}, #{userAge})";
        assertEquals(expeted, insert);
    }

}