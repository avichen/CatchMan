package com.roside.config;

import com.roside.BackendApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Eric on 2017-01-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=BackendApplication.class)
@Slf4j
public class RedisConfigTest{

//    protected org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void redisTest() throws Exception {
        redisTemplate.opsForValue().set("aaa", "333");
        log.info(redisTemplate.opsForValue().get("aaa").toString());
    }

    @Test
    public void redisQueueTest() throws Exception{
        log.info("Begin to send message");
        stringRedisTemplate.convertAndSend("chanel","测试中文");


    }
}