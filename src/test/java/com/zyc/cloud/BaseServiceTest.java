package com.zyc.cloud;

import com.zyc.cloud.domain.SkillijUser;
import com.zyc.cloud.domain.UserSkill;
import com.zyc.cloud.repository.AccountRepository;
import com.zyc.cloud.repository.SkillRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created on 2018/3/27.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudApplication.class)
public abstract class BaseServiceTest {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private SkillRepository skillRepository;

    protected SkillijUser gordon;
    protected SkillijUser traveller_ing;

    protected UserSkill gJavaProgram;
    protected UserSkill gJvm;
    protected UserSkill gDesignPattern;
    protected UserSkill gAlgorithm;
    protected UserSkill gLinux;


    protected UserSkill gGrammar;
    protected UserSkill gCollection;
    protected UserSkill gJavaee;
    protected UserSkill gFramework;

    public void initTestData() throws Exception {
        accountRepository.deleteAll();
        skillRepository.deleteAll();

        //用户
        gordon = createUser("gordon", "gordon");
        traveller_ing = createUser("traveller_ing", "traveller_ing");
        accountRepository.saveAndFlush(gordon);
        accountRepository.saveAndFlush(traveller_ing);

        //技能
        long first = 0;
        gJavaProgram = createSkill("Java编程", 3, null, gordon.getId(), first);
        gJvm = createSkill("JVM", 2, null, gordon.getId(), first);
        gDesignPattern = createSkill("设计模式", 2, null, gordon.getId(), first);
        gAlgorithm = createSkill("算法", 2, null, gordon.getId(), first);
        gLinux = createSkill("Linux", 3, null, gordon.getId(), first);
        skillRepository.saveAndFlush(gJavaProgram);
        skillRepository.saveAndFlush(gJvm);
        skillRepository.saveAndFlush(gDesignPattern);
        skillRepository.saveAndFlush(gAlgorithm);
        skillRepository.saveAndFlush(gLinux);

        long gjp = gJavaProgram.getId();
        gGrammar = createSkill("语法", 3, null, gordon.getId(), gjp);
        gCollection = createSkill("集合", 2, null, gordon.getId(), gjp);
        gJavaee = createSkill("JavaEE", 2, null, gordon.getId(), gjp);
        gFramework = createSkill("框架", 3, null, gordon.getId(), gjp);
        skillRepository.saveAndFlush(gGrammar);
        skillRepository.saveAndFlush(gCollection);
        skillRepository.saveAndFlush(gJavaee);
        skillRepository.saveAndFlush(gFramework);
    }

    /**
     * 创建用户
     * @param username
     * @param password
     * @return
     */
    protected SkillijUser createUser(String username, String password) {
        SkillijUser result = new SkillijUser();
        result.setUsername(username);
        result.setPassword(password);

        return result;
    }

    /**
     * 创建技能
     * @param skillName
     * @param proficiency
     * @param description
     * @param userId
     * @param parentId
     * @return
     */
    protected UserSkill createSkill(String skillName, Integer proficiency, String description, Long userId, Long parentId) {
        UserSkill result = new UserSkill();
        result.setSkillName(skillName);
        result.setProficiency(proficiency);
        result.setDescription(description);
        result.setUserId(userId);
        result.setParentId(parentId);

        return result;
    }


}
