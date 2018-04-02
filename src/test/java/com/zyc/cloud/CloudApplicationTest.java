package com.zyc.cloud;

import com.zyc.cloud.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 2018/3/29.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class CloudApplicationTest extends BaseServiceTest {

    @Resource
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        initTestData();
    }

    @Test
    public void loginTest() {
        String password = accountRepository.findPasswdByUser("gordon");
        assertThat(password).isNotNull();
    }
}
