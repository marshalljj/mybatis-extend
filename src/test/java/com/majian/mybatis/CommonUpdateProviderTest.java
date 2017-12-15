package com.majian.mybatis;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by majian on 2017/12/9.
 */
public class CommonUpdateProviderTest {

    private CommonUpdateProvider<User> userCommonUpdateProvider = new CommonUpdateProvider<>();

    @Test
    public void update() throws Exception {
        String update = userCommonUpdateProvider.update(new User());
        String expected = "UPDATE user\n"
            + "SET user_name=#{userName}, user_age=#{userAge}, version=version+1\n"
            + "WHERE (id=#{id} AND version=#{version})";
        assertEquals(expected, update);
    }

}