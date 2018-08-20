package com.zyc.skillijserver;

import com.zyc.skillijcommon.domain.mongodb.UserChat;
import com.zyc.skillijcommon.domain.mysql.SkillijPermission;
import com.zyc.skillijcommon.domain.mysql.SkillijRole;
import com.zyc.skillijcommon.domain.mysql.SkillijUser;
import com.zyc.skillijcommon.domain.mysql.UserSkill;
import com.zyc.skillijcommon.dto.UserMessage;
import com.zyc.skillijserver.repository.mongodb.ChatRepository;
import com.zyc.skillijserver.repository.mysql.AccountRepository;
import com.zyc.skillijserver.repository.mysql.PermissionRepository;
import com.zyc.skillijserver.repository.mysql.RoleRepository;
import com.zyc.skillijserver.repository.mysql.SkillRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2018/3/27.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillijServerApplication.class)
public abstract class BaseServiceTest {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private SkillRepository skillRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    private ChatRepository chatRepository;

    @Resource
    private TreeRepository treeRepository;

    //用户
    protected SkillijUser gordon;
    protected SkillijUser traveller_ing;
    protected SkillijUser java;
    protected SkillijUser assistant;

    //用户拥有的技能树
    protected UserTree javaOfgordon;
    protected UserTree cppOfgordon;
    protected UserTree javaOftraveller_ing;
    protected UserTree javaOfjava;

    //角色
    protected SkillijRole admin;
    protected SkillijRole user;

    //权限
    protected SkillijPermission sysTreeOperation;
    protected SkillijPermission userTreeOperation;

    //gordon的技能
    protected UserSkill gJavaProgram;
    protected UserSkill gJvm;
    protected UserSkill gDesignPattern;
    protected UserSkill gAlgorithm;
    protected UserSkill gLinux;
    protected UserSkill gGrammar;
    protected UserSkill gCollection;
    protected UserSkill gJavaee;
    protected UserSkill gFramework;
    protected UserSkill gCppProgram;

    //java的技能
    protected UserSkill jJavaProgram;
    protected UserSkill jJvm;
    protected UserSkill jDesignPattern;
    protected UserSkill jAlgorithm;
    protected UserSkill jLinux;
    protected UserSkill jComputerBasis;
    protected UserSkill jSoftwareEngineering;
    protected UserSkill jData;
    protected UserSkill jDistributedSystem;
    protected UserSkill jProject;
    protected UserSkill jIde;
    protected UserSkill jVersionControl;
    protected UserSkill jGrammar;
    protected UserSkill jCollection;
    protected UserSkill jUtil;
    protected UserSkill jJavaee;
    protected UserSkill jFramework;
    protected UserSkill jServer;
    protected UserSkill jAdvancedFeatures;
    protected UserSkill jStructure;
    protected UserSkill jSecurity;
    protected UserSkill jTest;
    protected UserSkill jEclipse;
    protected UserSkill jIntellijIdea;
    protected UserSkill jList;
    protected UserSkill jSet;
    protected UserSkill jMap;
    protected UserSkill jQueue;
    protected UserSkill jStack;
    protected UserSkill jSynchronizedCollection;
    protected UserSkill jGoogleGuava;
    protected UserSkill jApacheCommon;
    protected UserSkill jJson;
    protected UserSkill jServlet;
    protected UserSkill jJsp;
    protected UserSkill jSession;
    protected UserSkill jCookie;
    protected UserSkill jJstl;
    protected UserSkill jEl;
    protected UserSkill jSpring;
    protected UserSkill jSpringMvc;
    protected UserSkill jStruts;
    protected UserSkill jHibernate;
    protected UserSkill jMybatis;
    protected UserSkill jShiro;
    protected UserSkill jNetty;
    protected UserSkill jIoc;
    protected UserSkill jAop;
    protected UserSkill jAnnotation;
    protected UserSkill jTomcat;
    protected UserSkill jApache;
    protected UserSkill jNginx;
    protected UserSkill jGenericParadigm;
    protected UserSkill jPolyMorphism;
    protected UserSkill jLambda;
    protected UserSkill jConcurrent;
    protected UserSkill jNetworkProgram;
    protected UserSkill jIo;
    protected UserSkill jSerialize;
    protected UserSkill jRpc;
    protected UserSkill jExecutor;
    protected UserSkill jForkJoin;
    protected UserSkill jConcurrentCollection;
    protected UserSkill jThreadPool;
    protected UserSkill jThreadState;
    protected UserSkill jSynchronized;
    protected UserSkill jAtomicOperation;
    protected UserSkill jConcurrentTools;
    protected UserSkill jMaven;
    protected UserSkill jGradle;
    protected UserSkill jHash;
    protected UserSkill jSymmetricEncryption;
    protected UserSkill jAsymmetricEncryption;
    protected UserSkill jMd5;
    protected UserSkill jSha;
    protected UserSkill jRsa;
    protected UserSkill jHttps;
    protected UserSkill jTestCase;
    protected UserSkill jWhiteBox;
    protected UserSkill jBlackBox;
    protected UserSkill jJunit;
    protected UserSkill jSpringTest;
    protected UserSkill jCmdExcetor;
    protected UserSkill jOperatingSystem;
    protected UserSkill jCompilingPrinciple;
    protected UserSkill jComputerNetwork;
    protected UserSkill jDataStructure;
    protected UserSkill jHotspot;
    protected UserSkill jClassLoading;
    protected UserSkill jMemoryModel;
    protected UserSkill jGarbageCollection;
    protected UserSkill jTuning;
    protected UserSkill jJvmTools;
    protected UserSkill jCollectionAlgorithm;
    protected UserSkill jCollector;
    protected UserSkill jJstack;
    protected UserSkill jJmap;
    protected UserSkill jJconsole;
    protected UserSkill jOperation;
    protected UserSkill jContinuousIntegration;
    protected UserSkill jTestDrivenDev;
    protected UserSkill jPrinciple;
    protected UserSkill jJenkins;
    protected UserSkill jDry;
    protected UserSkill jSingleRespon;
    protected UserSkill jOpenClose;
    protected UserSkill jDistribution;
    protected UserSkill jCommands;
    protected UserSkill jNosql;
    protected UserSkill jDistributedCache;
    protected UserSkill jRelationalDB;
    protected UserSkill jSearchEngines;
    protected UserSkill jBigData;
    protected UserSkill jMongoDB;
    protected UserSkill jMemocached;
    protected UserSkill jRedis;
    protected UserSkill jMysql;
    protected UserSkill jDbDesign;
    protected UserSkill jSolr;
    protected UserSkill jElasticSearch;
    protected UserSkill jHadoop;
    protected UserSkill jHbase;
    protected UserSkill jSpark;
    protected UserSkill jStorm;
    protected UserSkill jString;
    protected UserSkill jArray;
    protected UserSkill jLinkedList;
    protected UserSkill jStackAlgo;
    protected UserSkill jQueueAlgo;
    protected UserSkill jTreeAlgo;
    protected UserSkill jHashAlgo;
    protected UserSkill jMapAlgo;
    protected UserSkill jSortAlgo;
    protected UserSkill jKmp;
    protected UserSkill jRegularExpression;
    protected UserSkill jBinaryTree;
    protected UserSkill jBTree;
    protected UserSkill jRedBlackTree;
    protected UserSkill jHashCollisions;
    protected UserSkill jKeyValue;
    protected UserSkill jBfs;
    protected UserSkill jDfs;
    protected UserSkill jInternalSort;
    protected UserSkill jExternalSort;
    protected UserSkill jInsertionSort;
    protected UserSkill jSelectionSort;
    protected UserSkill jExchangeSort;
    protected UserSkill jMergingSort;
    protected UserSkill jBaseSort;
    protected UserSkill jBucketSort;
    protected UserSkill jDirectInsertionSort;
    protected UserSkill jShellSort;
    protected UserSkill jSimpleSelectionSort;
    protected UserSkill jHeapSort;
    protected UserSkill jBubbleSort;
    protected UserSkill jQuickSort;
    protected UserSkill jCentralizedToDistributed;
    protected UserSkill jDistriSession;
    protected UserSkill jDistriCache;
    protected UserSkill jDistriBase;
    protected UserSkill jConsistency;
    protected UserSkill jLoadBalancing;
    protected UserSkill jMessageQueue;
    protected UserSkill jServiceOriented;
    protected UserSkill jVirtualization;
    protected UserSkill jComputingPlatform;
    protected UserSkill jDisasterRecovery;
    protected UserSkill jRemote;

    //聊天
    protected UserChat gordon2travellering;
    protected UserChat travellering2gordon;

    private Long currentTreeId;//当前技能点所属技能树id


    public void initTestData() throws Exception {
        accountRepository.deleteAll();
        skillRepository.deleteAll();
        roleRepository.deleteAll();
        permissionRepository.deleteAll();
        chatRepository.deleteAll();
        treeRepository.deleteAll();

        //权限
        userTreeOperation = createPermission("用户技能树操作");
        sysTreeOperation = createPermission("系统技能树操作");
        permissionRepository.saveAndFlush(userTreeOperation);
        permissionRepository.saveAndFlush(sysTreeOperation);

        //角色
        List<SkillijPermission> adminPermissions = new ArrayList<>();
        adminPermissions.add(sysTreeOperation);
        adminPermissions.add(userTreeOperation);
        admin = createRole("管理员", "Skillij管理员", adminPermissions);
        roleRepository.saveAndFlush(admin);

        List<SkillijPermission> userPermissions = new ArrayList<>();
        userPermissions.add(userTreeOperation);
        user = createRole("用户", "Skillij用户", userPermissions);
        roleRepository.saveAndFlush(user);

        //用户
        gordon = createUser("gordon", "gordon","438@qq.com");
        gordon.setPortraitUrl("./message/images/gordon.jpg");
        List<SkillijRole> gordonRoles = new ArrayList<>();
        gordonRoles.add(admin);
        gordonRoles.add(user);
        gordon.setRoles(gordonRoles);

        traveller_ing = createUser("traveller_ing", "traveller_ing","666@qq.com");
        traveller_ing.setPortraitUrl("./message/images/travellering.jpg");
        List<SkillijRole> travellerRoles = new ArrayList<>();
        travellerRoles.add(user);
        traveller_ing.setRoles(travellerRoles);

        java = createUser("java", "java","java@qq.com");
        List<SkillijRole> javaRoles = new ArrayList<>();
        javaRoles.add(admin);
        java.setRoles(javaRoles);

        assistant = createUser("私信助手", "assistant", "assistant@skillij.com");

        accountRepository.saveAndFlush(gordon);
        accountRepository.saveAndFlush(traveller_ing);
        accountRepository.saveAndFlush(java);
        accountRepository.saveAndFlush(assistant);

        //用户的技能树
        javaOfgordon = createTree(gordon.getId(), "Java");
        cppOfgordon = createTree(gordon.getId(), "C++");
        treeRepository.saveAndFlush(javaOfgordon);
        treeRepository.saveAndFlush(cppOfgordon);

        javaOftraveller_ing = createTree(traveller_ing.getId(), "Java");
        treeRepository.saveAndFlush(javaOftraveller_ing);

        javaOfjava = createTree(java.getId(), "Java");
        treeRepository.saveAndFlush(javaOfjava);

        //gordon的Java技能
        currentTreeId = javaOfgordon.getTreeId();//设置以下节点所属技能树
        Long first = 0L;
        gJavaProgram = createSkill("Java编程", 3, null, gordon.getId(), first, 0L);
        gJvm = createSkill("JVM", 2, null, gordon.getId(), first, 0L);
        gDesignPattern = createSkill("设计模式", 2, null, gordon.getId(), first, 0L);
        gAlgorithm = createSkill("算法", 2, null, gordon.getId(), first, 0L);
        gLinux = createSkill("Linux", 3, null, gordon.getId(), first, 0L);
        skillRepository.saveAndFlush(gJavaProgram);
        skillRepository.saveAndFlush(gJvm);
        skillRepository.saveAndFlush(gDesignPattern);
        skillRepository.saveAndFlush(gAlgorithm);
        skillRepository.saveAndFlush(gLinux);

        Long gjp = gJavaProgram.getId();//Java编程的子节点
        gGrammar = createSkill("语法", 3, null, gordon.getId(), gjp, 1L);
        gCollection = createSkill("集合", 2, null, gordon.getId(), gjp, 1L);
        gJavaee = createSkill("JavaEE", 2, null, gordon.getId(), gjp, 1L);
        gFramework = createSkill("框架", 3, null, gordon.getId(), gjp, 1L);
        skillRepository.saveAndFlush(gGrammar);
        skillRepository.saveAndFlush(gCollection);
        skillRepository.saveAndFlush(gJavaee);
        skillRepository.saveAndFlush(gFramework);

        //gordon的C++技能
        currentTreeId = cppOfgordon.getTreeId();//设置以下节点所属技能树
        gCppProgram = createSkill("C++编程", 3, null, gordon.getId(), first, 0L);
        skillRepository.saveAndFlush(gCppProgram);


        //完整的java技能树
        currentTreeId = javaOfjava.getTreeId();//设置以下节点所属技能树
        jJavaProgram = createSkill("Java编程", 6, null, java.getId(), first, 0L);
        jJvm = createSkill("JVM", 6, null, java.getId(), first, 0L);
        jDesignPattern = createSkill("设计模式", 6, null, java.getId(), first, 0L);
        jAlgorithm = createSkill("算法", 6, null, java.getId(), first, 0L);
        jLinux = createSkill("Linux", 6, null, java.getId(), first, 0L);
        jComputerBasis = createSkill("计算机基础", 6, null, java.getId(), first, 0L);
        jSoftwareEngineering = createSkill("软件工程", 6, null, java.getId(), first, 0L);
        jData = createSkill("数据", 6, null, java.getId(), first, 0L);
        jDistributedSystem = createSkill("分布式系统", 6, null, java.getId(), first, 0L);
        jProject = createSkill("项目", 6, null, java.getId(), first, 0L);
        skillRepository.saveAndFlush(jJavaProgram);
        skillRepository.saveAndFlush(jJvm);
        skillRepository.saveAndFlush(jDesignPattern);
        skillRepository.saveAndFlush(jAlgorithm);
        skillRepository.saveAndFlush(jLinux);
        skillRepository.saveAndFlush(jComputerBasis);
        skillRepository.saveAndFlush(jSoftwareEngineering);
        skillRepository.saveAndFlush(jData);
        skillRepository.saveAndFlush(jDistributedSystem);
        skillRepository.saveAndFlush(jProject);

        Long jjp = jJavaProgram.getId();//java编程的子节点
        jIde = createSkill("IDE", 6, null, java.getId(), jjp, 1L);
        jVersionControl = createSkill("版本控制", 6, null, java.getId(), jjp, 1L);
        jGrammar = createSkill("语法", 6, null, java.getId(), jjp, 1L);
        jCollection = createSkill("集合", 6, null, java.getId(), jjp, 1L);
        jUtil = createSkill("工具类", 6, null, java.getId(), jjp, 1L);
        jJavaee = createSkill("JavaEE", 6, null, java.getId(), jjp, 1L);
        jFramework = createSkill("框架", 6, null, java.getId(), jjp, 1L);
        jServer = createSkill("服务器", 6, null, java.getId(), jjp, 1L);
        jAdvancedFeatures = createSkill("高级特性", 6, null, java.getId(), jjp, 1L);
        jStructure = createSkill("构建工具", 6, null, java.getId(), jjp, 1L);
        jSecurity = createSkill("安全", 6, null, java.getId(), jjp, 1L);
        jTest = createSkill("测试", 6, null, java.getId(), jjp, 1L);
        skillRepository.saveAndFlush(jIde);
        skillRepository.saveAndFlush(jVersionControl);
        skillRepository.saveAndFlush(jGrammar);
        skillRepository.saveAndFlush(jCollection);
        skillRepository.saveAndFlush(jUtil);
        skillRepository.saveAndFlush(jJavaee);
        skillRepository.saveAndFlush(jFramework);
        skillRepository.saveAndFlush(jServer);
        skillRepository.saveAndFlush(jAdvancedFeatures);
        skillRepository.saveAndFlush(jStructure);
        skillRepository.saveAndFlush(jSecurity);
        skillRepository.saveAndFlush(jTest);

        Long ji = jIde.getId();//IDE的子节点
        jEclipse = createSkill("Eclipse", 6, null, java.getId(), ji, 2L);
        jIntellijIdea = createSkill("IntellijIdea", 6, null, java.getId(), ji, 2L);
        skillRepository.saveAndFlush(jEclipse);
        skillRepository.saveAndFlush(jIntellijIdea);

        Long jc = jCollection.getId();//java集合的子节点
        jList = createSkill("List", 6, null, java.getId(), jc, 2L);
        jSet = createSkill("Set", 6, null, java.getId(), jc, 2L);
        jMap = createSkill("Map", 6, null, java.getId(), jc, 2L);
        jQueue = createSkill("Queue", 6, null, java.getId(), jc, 2L);
        jStack = createSkill("Stack", 6, null, java.getId(), jc, 2L);
        jSynchronizedCollection = createSkill("同步集合", 6, null, java.getId(), jc, 2L);
        skillRepository.saveAndFlush(jList);
        skillRepository.saveAndFlush(jSet);
        skillRepository.saveAndFlush(jMap);
        skillRepository.saveAndFlush(jQueue);
        skillRepository.saveAndFlush(jStack);
        skillRepository.saveAndFlush(jSynchronizedCollection);

        Long ju = jUtil.getId();//java工具类的子节点
        jGoogleGuava = createSkill("GoogleGuava", 6, null, java.getId(), ju, 2L);
        jApacheCommon = createSkill("Apache common", 6, null, java.getId(), ju, 2L);
        jJson = createSkill("Json", 6, null, java.getId(), ju, 2L);
        skillRepository.saveAndFlush(jGoogleGuava);
        skillRepository.saveAndFlush(jApacheCommon);
        skillRepository.saveAndFlush(jJson);

        Long je = jJavaee.getId();//javaEE的子节点
        jServlet = createSkill("Servlet", 6, null, java.getId(), je, 2L);
        jJsp = createSkill("Jsp", 6, null, java.getId(), je, 2L);
        jSession = createSkill("Session", 6, null, java.getId(), je, 2L);
        jCookie = createSkill("Cookie", 6, null, java.getId(), je, 2L);
        jJstl = createSkill("JSTL", 6, null, java.getId(), je, 2L);
        jEl = createSkill("EL", 6, null, java.getId(), je, 2L);
        skillRepository.saveAndFlush(jServlet);
        skillRepository.saveAndFlush(jJsp);
        skillRepository.saveAndFlush(jSession);
        skillRepository.saveAndFlush(jCookie);
        skillRepository.saveAndFlush(jCookie);
        skillRepository.saveAndFlush(jJstl);
        skillRepository.saveAndFlush(jEl);

        Long jf = jFramework.getId();//java框架的子节点
        jSpring = createSkill("Spring", 6, null, java.getId(), jf, 2L);
        jSpringMvc = createSkill("SpringMVC", 6, null, java.getId(), jf, 2L);
        jStruts = createSkill("Struts", 6, null, java.getId(), jf, 2L);
        jHibernate = createSkill("Hibernate", 6, null, java.getId(), jf, 2L);
        jMybatis = createSkill("MyBatis", 6, null, java.getId(), jf, 2L);
        jShiro = createSkill("Shiro", 6, null, java.getId(), jf, 2L);
        jNetty = createSkill("Netty", 6, null, java.getId(), jf, 2L);
        skillRepository.saveAndFlush(jSpring);
        skillRepository.saveAndFlush(jSpringMvc);
        skillRepository.saveAndFlush(jStruts);
        skillRepository.saveAndFlush(jHibernate);
        skillRepository.saveAndFlush(jMybatis);
        skillRepository.saveAndFlush(jShiro);
        skillRepository.saveAndFlush(jNetty);

        Long jsp = jSpring.getId();//spring框架的子节点
        jIoc = createSkill("IOC", 6, null, java.getId(), jsp, 3L);
        jAop = createSkill("AOP", 6, null, java.getId(), jsp, 3L);
        jAnnotation = createSkill("Annotation", 6, null, java.getId(), jsp, 3L);
        skillRepository.saveAndFlush(jIoc);
        skillRepository.saveAndFlush(jAop);
        skillRepository.saveAndFlush(jAnnotation);

        Long jse = jServer.getId();//服务器的子节点
        jTomcat = createSkill("Tomcat", 6, null, java.getId(), jse, 2L);
        jApache = createSkill("Apache", 6, null, java.getId(), jse, 2L);
        jNginx = createSkill("Nginx", 6, null, java.getId(), jse, 2L);
        skillRepository.saveAndFlush(jTomcat);
        skillRepository.saveAndFlush(jApache);
        skillRepository.saveAndFlush(jNginx);

        Long jaf = jAdvancedFeatures.getId();//java高级特性的子节点
        jGenericParadigm = createSkill("泛型", 6, null, java.getId(), jaf, 2L);
        jPolyMorphism = createSkill("多态", 6, null, java.getId(), jaf, 2L);
        jLambda = createSkill("Lambda表达式", 6, null, java.getId(), jaf, 2L);
        jConcurrent = createSkill("并发", 6, null, java.getId(), jaf, 2L);
        jNetworkProgram = createSkill("网络编程", 6, null, java.getId(), jaf, 2L);
        jIo = createSkill("IO", 6, null, java.getId(), jaf, 2L);
        jSerialize = createSkill("序列化", 6, null, java.getId(), jaf, 2L);
        jRpc = createSkill("远程调用", 6, null, java.getId(), jaf, 2L);
        skillRepository.saveAndFlush(jGenericParadigm);
        skillRepository.saveAndFlush(jPolyMorphism);
        skillRepository.saveAndFlush(jLambda);
        skillRepository.saveAndFlush(jConcurrent);
        skillRepository.saveAndFlush(jNetworkProgram);
        skillRepository.saveAndFlush(jIo);
        skillRepository.saveAndFlush(jSerialize);
        skillRepository.saveAndFlush(jRpc);

        Long jcc = jConcurrent.getId();//java并发的子节点
        jExecutor = createSkill("Executor框架", 6, null, java.getId(), jcc, 3L);
        jForkJoin = createSkill("Fork/join", 6, null, java.getId(), jcc, 3L);
        jConcurrentCollection = createSkill("并发集合", 6, null, java.getId(), jcc, 3L);
        jThreadPool = createSkill("线程池", 6, null, java.getId(), jcc, 3L);
        jThreadState = createSkill("线程状态", 6, null, java.getId(), jcc, 3L);
        jSynchronized = createSkill("锁与synchronized", 6, null, java.getId(), jcc, 3L);
        jAtomicOperation = createSkill("原子操作", 6, null, java.getId(), jcc, 3L);
        jConcurrentTools = createSkill("并发工具", 6, null, java.getId(), jcc, 3L);
        skillRepository.saveAndFlush(jExecutor);
        skillRepository.saveAndFlush(jForkJoin);
        skillRepository.saveAndFlush(jConcurrentCollection);
        skillRepository.saveAndFlush(jThreadPool);
        skillRepository.saveAndFlush(jThreadState);
        skillRepository.saveAndFlush(jSynchronized);
        skillRepository.saveAndFlush(jAtomicOperation);
        skillRepository.saveAndFlush(jConcurrentTools);

        Long jst = jStructure.getId();//构建工具的子节点
        jMaven = createSkill("Maven", 6, null, java.getId(), jst, 2L);
        jGradle = createSkill("Gradle", 6, null, java.getId(), jst, 2L);
        skillRepository.saveAndFlush(jMaven);
        skillRepository.saveAndFlush(jGradle);

        Long jsec = jSecurity.getId();//安全的子节点
        jHash = createSkill("哈希算法", 6, null, java.getId(), jsec, 2L);
        jSymmetricEncryption = createSkill("对称加密", 6, null, java.getId(), jsec, 2L);
        jAsymmetricEncryption = createSkill("非对称加密", 6, null, java.getId(), jsec, 2L);
        skillRepository.saveAndFlush(jHash);
        skillRepository.saveAndFlush(jSymmetricEncryption);
        skillRepository.saveAndFlush(jAsymmetricEncryption);

        Long jha = jHash.getId();//散列算法的子节点
        jMd5 = createSkill("MD5", 6, null, java.getId(), jha, 3L);
        jSha = createSkill("SHA", 6, null, java.getId(), jha, 3L);
        skillRepository.saveAndFlush(jMd5);
        skillRepository.saveAndFlush(jSha);

        Long jasy = jAsymmetricEncryption.getId();//非对称加密的子节点
        jRsa = createSkill("RSA", 6, null, java.getId(), jasy, 3L);
        jHttps = createSkill("HTTPS", 6, null, java.getId(), jasy, 3L);
        skillRepository.saveAndFlush(jRsa);
        skillRepository.saveAndFlush(jHttps);

        Long jte = jTest.getId();//测试的子节点
        jTestCase = createSkill("测试用例", 6, null, java.getId(), jte, 2L);
        jWhiteBox = createSkill("白盒", 6, null, java.getId(), jte, 2L);
        jBlackBox = createSkill("黑盒", 6, null, java.getId(), jte, 2L);
        jJunit = createSkill("junit", 6, null, java.getId(), jte, 2L);
        jSpringTest = createSkill("SpringTest", 6, null, java.getId(), jte, 2L);
        jCmdExcetor = createSkill("CmdExcetor", 6, null, java.getId(), jte, 2L);
        skillRepository.saveAndFlush(jTestCase);
        skillRepository.saveAndFlush(jWhiteBox);
        skillRepository.saveAndFlush(jBlackBox);
        skillRepository.saveAndFlush(jJunit);
        skillRepository.saveAndFlush(jSpringTest);
        skillRepository.saveAndFlush(jCmdExcetor);

        Long jcb = jComputerBasis.getId();//计算机基础的子节点
        jOperatingSystem = createSkill("操作系统", 6, null, java.getId(), jcb, 1L);
        jCompilingPrinciple = createSkill("编译原理", 6, null, java.getId(), jcb, 1L);
        jComputerNetwork = createSkill("计算机网络", 6, null, java.getId(), jcb, 1L);
        jDataStructure = createSkill("数据结构", 6, null, java.getId(), jcb, 1L);
        skillRepository.saveAndFlush(jOperatingSystem);
        skillRepository.saveAndFlush(jCompilingPrinciple);
        skillRepository.saveAndFlush(jComputerNetwork);
        skillRepository.saveAndFlush(jDataStructure);

        Long jjvm = jJvm.getId();//java虚拟机的子节点
        jHotspot = createSkill("Hotspot实现", 6, null, java.getId(), jjvm, 1L);
        jClassLoading = createSkill("类加载机制", 6, null, java.getId(), jjvm, 1L);
        jMemoryModel = createSkill("内存模型", 6, null, java.getId(), jjvm, 1L);
        jGarbageCollection = createSkill("垃圾回收", 6, null, java.getId(), jjvm, 1L);
        jTuning = createSkill("调优", 6, null, java.getId(), jjvm, 1L);
        jJvmTools = createSkill("JVM工具", 6, null, java.getId(), jjvm, 1L);
        skillRepository.saveAndFlush(jHotspot);
        skillRepository.saveAndFlush(jClassLoading);
        skillRepository.saveAndFlush(jMemoryModel);
        skillRepository.saveAndFlush(jGarbageCollection);
        skillRepository.saveAndFlush(jTuning);
        skillRepository.saveAndFlush(jJvmTools);

        Long jgc = jGarbageCollection.getId();//垃圾回收的子节点
        jCollectionAlgorithm = createSkill("回收算法", 6, null, java.getId(), jgc, 2L);
        jCollector = createSkill("垃圾收集器", 6, null, java.getId(), jgc, 2L);
        skillRepository.saveAndFlush(jCollectionAlgorithm);
        skillRepository.saveAndFlush(jCollector);

        Long jjt = jJvmTools.getId();//JVM工具的子节点
        jJstack = createSkill("jstack", 6, null, java.getId(), jjt, 2L);
        jJmap = createSkill("jmap", 6, null, java.getId(), jjt, 2L);
        jJconsole = createSkill("jconsole", 6, null, java.getId(), jjt, 2L);
        skillRepository.saveAndFlush(jJstack);
        skillRepository.saveAndFlush(jJmap);
        skillRepository.saveAndFlush(jJconsole);

        Long jsen = jSoftwareEngineering.getId();//软件工程的子节点
        jOperation = createSkill("运维", 6, null, java.getId(), jsen, 1L);
        jContinuousIntegration = createSkill("持续集成", 6, null, java.getId(), jsen, 1L);
        jTestDrivenDev = createSkill("测试驱动开发TDD", 6, null, java.getId(), jsen, 1L);
        jPrinciple = createSkill("原则", 6, null, java.getId(), jsen, 1L);
        skillRepository.saveAndFlush(jOperatingSystem);
        skillRepository.saveAndFlush(jContinuousIntegration);
        skillRepository.saveAndFlush(jTestDrivenDev);
        skillRepository.saveAndFlush(jPrinciple);

        Long jci = jContinuousIntegration.getId();//持续集成的子节点
        jJenkins = createSkill("原则", 6, null, java.getId(), jci, 2L);
        skillRepository.saveAndFlush(jJenkins);

        Long jpr = jPrinciple.getId();//软件原则的子节点
        jDry = createSkill("避免重复", 6, null, java.getId(), jpr, 2L);
        jSingleRespon = createSkill("单一职责", 6, null, java.getId(), jci, 2L);
        jOpenClose = createSkill("开闭原则", 6, null, java.getId(), jci, 2L);
        skillRepository.saveAndFlush(jJenkins);
        skillRepository.saveAndFlush(jSingleRespon);
        skillRepository.saveAndFlush(jSingleRespon);

        Long jli = jLinux.getId();//Linux的子节点
        jDistribution = createSkill("发行版", 6, null, java.getId(), jli, 1L);
        jCommands = createSkill("命令", 6, null, java.getId(), jli, 1L);
        skillRepository.saveAndFlush(jDistribution);
        skillRepository.saveAndFlush(jCommands);

        Long jda = jData.getId();//数据的子节点
        jNosql = createSkill("jNosql", 6, null, java.getId(), jda, 1L);
        jDistributedCache = createSkill("分布式缓存", 6, null, java.getId(), jda, 1L);
        jRelationalDB = createSkill("关系数据库", 6, null, java.getId(), jda, 1L);
        jSearchEngines = createSkill("搜索引擎", 6, null, java.getId(), jda, 1L);
        jBigData = createSkill("大数据", 6, null, java.getId(), jda, 1L);
        skillRepository.saveAndFlush(jNosql);
        skillRepository.saveAndFlush(jDistributedCache);
        skillRepository.saveAndFlush(jRelationalDB);
        skillRepository.saveAndFlush(jSearchEngines);
        skillRepository.saveAndFlush(jBigData);

        Long jno = jNosql.getId();//NoSQL的子节点
        jMongoDB = createSkill("MongoDB", 6, null, java.getId(), jno, 2L);
        skillRepository.saveAndFlush(jMongoDB);

        Long jdis = jDistributedCache.getId();//分布式缓存的子节点
        jMemocached = createSkill("Memocached", 6, null, java.getId(), jdis, 2L);
        jRedis = createSkill("Redis", 6, null, java.getId(), jdis, 2L);
        skillRepository.saveAndFlush(jMemocached);
        skillRepository.saveAndFlush(jRedis);

        Long jrdb = jRelationalDB.getId();//关系数据库的子节点
        jMysql = createSkill("MySQL", 6, null, java.getId(), jrdb, 2L);
        jDbDesign = createSkill("数据库设计", 6, null, java.getId(), jrdb, 2L);
        skillRepository.saveAndFlush(jMysql);
        skillRepository.saveAndFlush(jDbDesign);

        Long jsee = jSearchEngines.getId();//搜索引擎的子节点
        jSolr = createSkill("Solr", 6, null, java.getId(), jsee, 2L);
        jElasticSearch = createSkill("ElasticSearch", 6, null, java.getId(), jsee, 2L);
        skillRepository.saveAndFlush(jSolr);
        skillRepository.saveAndFlush(jElasticSearch);

        Long jbd = jBigData.getId();//大数据的子节点
        jHadoop = createSkill("Hadoop", 6, null, java.getId(), jbd, 2L);
        jHbase = createSkill("Hbase", 6, null, java.getId(), jbd, 2L);
        jSpark = createSkill("Spark", 6, null, java.getId(), jbd, 2L);
        jStorm = createSkill("Storm", 6, null, java.getId(), jbd, 2L);
        skillRepository.saveAndFlush(jHadoop);
        skillRepository.saveAndFlush(jHbase);
        skillRepository.saveAndFlush(jSpark);
        skillRepository.saveAndFlush(jStorm);

        Long jal = jAlgorithm.getId();//算法的子节点
        jString = createSkill("字符串", 6, null, java.getId(), jal, 1L);
        jArray = createSkill("数组", 6, null, java.getId(), jal, 1L);
        jLinkedList = createSkill("链表", 6, null, java.getId(), jal, 1L);
        jStackAlgo  = createSkill("栈算法", 6, null, java.getId(), jal, 1L);
        jQueueAlgo = createSkill("队列算法", 6, null, java.getId(), jal, 1L);
        jTreeAlgo = createSkill("树算法", 6, null, java.getId(), jal, 1L);
        jHashAlgo = createSkill("哈西算法", 6, null, java.getId(), jal, 1L);
        jMapAlgo = createSkill("图算法", 6, null, java.getId(), jal, 1L);
        jSortAlgo = createSkill("排序算法", 6, null, java.getId(), jal, 1L);
        skillRepository.saveAndFlush(jString);
        skillRepository.saveAndFlush(jArray);
        skillRepository.saveAndFlush(jLinkedList);
        skillRepository.saveAndFlush(jStackAlgo);
        skillRepository.saveAndFlush(jQueueAlgo);
        skillRepository.saveAndFlush(jTreeAlgo);
        skillRepository.saveAndFlush(jHashAlgo);
        skillRepository.saveAndFlush(jMapAlgo);
        skillRepository.saveAndFlush(jSortAlgo);

        Long jstr = jString.getId();//字符串算法的子节点
        jKmp = createSkill("查找匹配KMP", 6, null, java.getId(), jstr, 2L);
        jRegularExpression = createSkill("正则表达式", 6, null, java.getId(), jstr, 2L);
        skillRepository.saveAndFlush(jKmp);
        skillRepository.saveAndFlush(jRegularExpression);

        Long jtr = jTreeAlgo.getId();//树算法的子节点
        jBinaryTree = createSkill("二叉树", 6, null, java.getId(), jtr, 2L);
        jBTree = createSkill("B树", 6, null, java.getId(), jtr, 2L);
        jRedBlackTree = createSkill("红黑树", 6, null, java.getId(), jtr, 2L);
        skillRepository.saveAndFlush(jBinaryTree);
        skillRepository.saveAndFlush(jBTree);
        skillRepository.saveAndFlush(jRedBlackTree);

        Long jhaa = jHashAlgo.getId();//哈希算法的子节点
        jHashCollisions = createSkill("哈希冲突", 6, null, java.getId(), jhaa, 2L);
        jKeyValue = createSkill("KeyValue", 6, null, java.getId(), jhaa, 2L);
        skillRepository.saveAndFlush(jHashCollisions);
        skillRepository.saveAndFlush(jKeyValue);

        Long jma = jMapAlgo.getId();//图算法的子节点
        jBfs = createSkill("BFS", 6, null, java.getId(), jma, 2L);
        jDfs = createSkill("DFS", 6, null, java.getId(), jma, 2L);
        skillRepository.saveAndFlush(jBfs);
        skillRepository.saveAndFlush(jDfs);

        Long jso = jSortAlgo.getId();//排序算法的子节点
        jInternalSort =  createSkill("内部排序", 6, null, java.getId(), jso, 2L);
        jExternalSort =  createSkill("外部排序", 6, null, java.getId(), jso, 2L);
        skillRepository.saveAndFlush(jInternalSort);
        skillRepository.saveAndFlush(jExternalSort);

        Long jis = jInternalSort.getId();//内部排序的子节点
        jInsertionSort = createSkill("插入排序", 6, null, java.getId(), jis, 3L);
        jSelectionSort = createSkill("选择排序", 6, null, java.getId(), jis, 3L);
        jExchangeSort = createSkill("交换排序", 6, null, java.getId(), jis, 3L);
        jMergingSort = createSkill("归并排序", 6, null, java.getId(), jis, 3L);
        jBaseSort = createSkill("基数排序", 6, null, java.getId(), jis, 3L);
        jBucketSort = createSkill("桶排序", 6, null, java.getId(), jis, 3L);
        skillRepository.saveAndFlush(jInsertionSort);
        skillRepository.saveAndFlush(jSelectionSort);
        skillRepository.saveAndFlush(jExchangeSort);
        skillRepository.saveAndFlush(jMergingSort);
        skillRepository.saveAndFlush(jBaseSort);
        skillRepository.saveAndFlush(jBucketSort);

        Long jins = jInsertionSort.getId();//插入排序的子节点
        jDirectInsertionSort = createSkill("直接插入排序", 6, null, java.getId(), jins, 4L);
        jShellSort = createSkill("希尔排序", 6, null, java.getId(), jins, 4L);
        skillRepository.saveAndFlush(jDirectInsertionSort);
        skillRepository.saveAndFlush(jShellSort);

        Long jss = jSelectionSort.getId();//选择排序的子节点
        jSimpleSelectionSort = createSkill("简单选择排序", 6, null, java.getId(), jss, 4L);
        jHeapSort = createSkill("堆排序", 6, null, java.getId(), jss, 4L);
        skillRepository.saveAndFlush(jSimpleSelectionSort);
        skillRepository.saveAndFlush(jHeapSort);

        Long jes = jExchangeSort.getId();//交换排序的子节点
        jBubbleSort = createSkill("冒泡排序", 6, null, java.getId(), jes, 4L);
        jQuickSort = createSkill("快速排序", 6, null, java.getId(), jes, 4L);
        skillRepository.saveAndFlush(jBubbleSort);
        skillRepository.saveAndFlush(jQuickSort);

        Long jds = jDistributedSystem.getId();//分布式系统的子节点
        jCentralizedToDistributed = createSkill("从集中式到分布式", 6, null, java.getId(), jds, 1L);
        jDistriSession = createSkill("分布式Session", 6, null, java.getId(), jds, 1L);
        jDistriCache = createSkill("分布式缓存", 6, null, java.getId(), jds, 1L);
        jDistriBase = createSkill("分布式数据库", 6, null, java.getId(), jds, 1L);
        jConsistency = createSkill("一致性", 6, null, java.getId(), jds, 1L);
        jLoadBalancing = createSkill("负载均衡", 6, null, java.getId(), jds, 1L);
        jMessageQueue = createSkill("消息队列", 6, null, java.getId(), jds, 1L);
        jServiceOriented = createSkill("服务化", 6, null, java.getId(), jds, 1L);
        jVirtualization = createSkill("虚拟化", 6, null, java.getId(), jds, 1L);
        jComputingPlatform = createSkill("计算平台", 6, null, java.getId(), jds, 1L);
        jDisasterRecovery = createSkill("容灾", 6, null, java.getId(), jds, 1L);
        jRemote = createSkill("异地多活", 6, null, java.getId(), jds, 1L);
        skillRepository.saveAndFlush(jCentralizedToDistributed);
        skillRepository.saveAndFlush(jDistriSession);
        skillRepository.saveAndFlush(jDistriCache);
        skillRepository.saveAndFlush(jDistriBase);
        skillRepository.saveAndFlush(jConsistency);
        skillRepository.saveAndFlush(jLoadBalancing);
        skillRepository.saveAndFlush(jMessageQueue);
        skillRepository.saveAndFlush(jServiceOriented);
        skillRepository.saveAndFlush(jVirtualization);
        skillRepository.saveAndFlush(jComputingPlatform);
        skillRepository.saveAndFlush(jDisasterRecovery);

        //初始化对话数据
        initChatData();
    }

    /**
     * 初始化对话数据
     */
    private void initChatData() {
        //生成时间戳，都为现在
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp now =Timestamp.valueOf(nowTime);

        //生成消息
        UserMessage gt1 = createMessage(now, "我是gordon", true);
        UserMessage gt2 = createMessage(now, "我是travellering", false);
        UserMessage tg1 = createMessage(now, "我是gordon", false);
        UserMessage tg2 = createMessage(now, "我是travellering", true);
        List<UserMessage> gtMessages = new ArrayList<>();
        gtMessages.add(gt1);
        gtMessages.add(gt2);
        List<UserMessage> tgMessages = new ArrayList<>();
        tgMessages.add(tg1);
        tgMessages.add(tg2);

        //创建gordon的对话
        gordon2travellering = createChat(gordon.getId(), gordon.getUsername(), "./message/images/gordon.jpg",
                traveller_ing.getId(), traveller_ing.getUsername(), "./message/images/travellering.jpg", gtMessages);
        travellering2gordon = createChat(traveller_ing.getId(), traveller_ing.getUsername(), "./message/images/travellering.jpg",
                gordon.getId(), gordon.getUsername(), "./message/images/gordon.jpg", tgMessages);

        chatRepository.save(gordon2travellering);
        chatRepository.save(travellering2gordon);
    }

    /**
     * 创建用户的技能树
     * @param userId
     * @param treeName
     * @return
     */
    private UserTree createTree(Long userId, String treeName) {
        UserTree result = new UserTree();
        result.setUserId(userId);
        result.setTreeName(treeName);

        return result;
    }

    /**
     * 创建用户
     * @param username
     * @param password
     * @return
     */
    protected SkillijUser createUser(String username, String password, String mail) {
        SkillijUser result = new SkillijUser();
        result.setUsername(username);
        result.setPassword(password);
        result.setMail(mail);

        return result;
    }

    /**
     * 创建技能
     * @param skillName
     * @param proficiency
     * @param description
     * @param userId
     * @param parentId
     * @param level
     * @return
     */
    protected UserSkill createSkill(String skillName, Integer proficiency, String description, Long userId, Long parentId, Long level) {
        UserSkill result = new UserSkill();
        result.setSkillName(skillName);
        result.setProficiency(proficiency);
        result.setDescription(description);
        result.setUserId(userId);
        result.setParentId(parentId);
        result.setLevel(level);

        result.setTreeId(currentTreeId);

        return result;
    }

    /**
     * 创建权限角色
     * @param roleName
     * @param desciription
     * @param permissions
     * @return
     */
    protected SkillijRole createRole(String roleName, String desciription, List<SkillijPermission> permissions) {
        SkillijRole role = new SkillijRole();

        role.setRole(roleName);
        role.setDescription(desciription);
        role.setPermissions(permissions);

        return role;
    }

    /**
     * 创建权限
     * @param permissionName
     * @return
     */
    protected SkillijPermission createPermission(String permissionName) {
        SkillijPermission permission = new SkillijPermission();

        permission.setPermission(permissionName);

        return permission;
    }

    /**
     * 创建聊天
     * @param senderId
     * @param senderName
     * @param senderImgUrl
     * @param receiverId
     * @param receiverName
     * @param receiverImgUrl
     * @param userMessages
     * @return
     */
    protected UserChat createChat(Long senderId, String senderName, String senderImgUrl,
                                  Long receiverId, String receiverName, String receiverImgUrl,
                                  List<UserMessage> userMessages) {
        UserChat result = new UserChat();
        result.setSenderId(senderId);
        result.setSenderName(senderName);
        result.setSenderImgUrl(senderImgUrl);
        result.setReceiverId(receiverId);
        result.setReceiverName(receiverName);
        result.setReceiverImgUrl(receiverImgUrl);
        result.setMessages(userMessages);

        return result;
    }

    /**
     * 创建消息
     * @param date
     * @param content
     * @param self
     * @return
     */
    protected UserMessage createMessage(Timestamp date, String content, Boolean self) {
        UserMessage result = new UserMessage();
        result.setDate(date);
        result.setContent(content);
        result.setSelf(self);

        return result;
    }


}
