//package zc.free.acg.controller;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @auther ZhengCong
// * @date 2020/3/29 20:13
// * 主要测试了一下redis使用连接池与不使用之间的区别
// * 在插入数据方面 使用连接池在项目启动后第一次操作时间为150ms左右
// *              不使用连接池在项目启动后第一次操作时间为2780ms左右
// *     而在之后的插入操作中 两者的时间区别不大 均为40ms左右
// */
//@Controller
//public class RedisController {
//    @Autowired
//    private StringRedisTemplate template;
//    @ApiOperation(value = "测试redis连接池速度", notes="true为可用 false为不可用 type=0 数据为用户名 type=1 数据为手机号")
//    @GetMapping("redis")
//    public ResponseEntity<Long> sendRedis(@RequestParam("name") String name) {
//        long start = System.currentTimeMillis();
//        template.opsForValue().set(name,"hello", 5, TimeUnit.MINUTES);
//        long end = System.currentTimeMillis();
//        Long time = end -start;
//        return ResponseEntity.ok(time);
//    }
//}
