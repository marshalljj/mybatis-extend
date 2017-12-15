package com.majian.mybatis;

import com.majian.mybatis.Column;
import com.majian.mybatis.Id;
import com.majian.mybatis.Table;
import com.majian.mybatis.Transient;
import com.majian.mybatis.Version;
import java.util.Date;

/**
 * Created by majian on 2017/12/9.
 */
@Table("user")
public class User {
    @Id
    private long id;
    @Column("user_name")
    private String userName;
    private int userAge;
    @Transient
    private Date gmtCreate;
    @Version
    private int version;

}
