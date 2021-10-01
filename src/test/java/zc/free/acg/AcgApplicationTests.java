package zc.free.acg;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zc.free.acg.domain.*;
import zc.free.acg.mapper.UserMapper;
import zc.free.acg.service.CooperateService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class AcgApplicationTests {
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private ExhibitionMapper exhibitionMapper;
//    @Autowired
//    StringRedisTemplate template;
//    @Autowired
//    CooperateMapper cooperateMapper;
//
//    @Autowired
//    CooperateService cooperateService;

    @Test
    void contextLoads() {
//        long start = System.currentTimeMillis();
//        template.opsForValue().set("15993940509", "test2", 1, TimeUnit.MINUTES);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);

    }

    /**
     * 测试密码编码器
     * 编码方式 bCryptPasswordEncoder
     */
    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    /**
     * 测试支付宝支付接口
     */
    @Test
    void testAlibabaPay() {
        //应用私钥
        /*String APP_PRIVATE_KEY = "";
        //支付宝公钥
        String ALIPAY_PUBLIC_KEY = "";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);
//调用成功，则处理业务逻辑
        if(response.isSuccess()){
            //.....
        }*/

    }
//    @Autowired
//    private TopicMsgMapper topicMsgMapper;
    //
    @Test
    public void testFindTopicMsg00() {
//        List<TopicMsg> topicMsgs = this.topicMsgMapper.selectMsg(1, 0, 3);
//        List<TopicMsg> result = new ArrayList<>();
//        for (TopicMsg topicMsg : topicMsgs) {
//            result.add(topicMsg);
//            queryReply(topicMsg.getId(),result);
//        }
//
//        for (TopicMsg a : result) {
//            System.out.println(a);
//        }


    }
    @Test
    public void testFindTopicMsg0() {
//        int topic_id=1;
//        TopicMsg topicMsg = new TopicMsg();
//        topicMsg.setTopicId(1);
//        List<TopicMsg> topicMsgs = this.topicMsgMapper.select(topicMsg);
//        ArrayList<TopicMsg> results = new ArrayList<>();
//        for (TopicMsg msg : topicMsgs) {
//            System.out.println(msg);
//            queryReply(msg.getId(),results);
//        }
    }

    @Test
    public void testFindTopicMsg() {
//        TopicMsg topicMsg = new TopicMsg();
//        int parent_id=1;
////        topicMsg.setTopicId(1);
//        topicMsg.setParentId(6);
//        List<TopicMsg> topicMsgs = this.topicMsgMapper.select(topicMsg);
//        for (TopicMsg msg : topicMsgs) {
//            System.out.println(msg);
//            queryReply(msg.getId(),topicMsgs);
//        }
    }
    public void queryReply(Integer id,List<TopicMsg> result) {
//        TopicMsg topicMsg = new TopicMsg();
//        topicMsg.setParentId(id);
//        List<TopicMsg> replys = this.topicMsgMapper.select(topicMsg);
//        if (replys.size() > 0) {
//            for(TopicMsg reply : replys) {
//                result.add(reply);
//                System.out.println(reply);
//                queryReply(reply.getId(),result);
//            }
//        }
    }
    @Test
    public void testExhibitionMapper() {
//        List<ExhibitionBo1> result = this.exhibitionMapper.selectExhibitionsAndForkNumByPage("%漫展%");
//        System.out.println(result);
    }


    @Test
    public void testExhibitionMapper02() {
//        ExhibitionBo1 result = this.exhibitionMapper.selectExhibitionAndOwnerNameAndForkNumById(1);
//        System.out.println(result);
    }

    @Test
    public void testUserMapper02() {
//        int i = this.userMapper.selectUserIsForkExhibition(1, 5);
//        System.out.println(i);
    }

    @Test
    public void testGenerator() throws Exception {
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        //当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        //读取我们的 MBG 配置文件
        InputStream is = AcgApplicationTests.class.getResourceAsStream("/config/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }




}
