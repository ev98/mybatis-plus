package com.ev;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ev.bean.User;
import com.ev.mapper.UserMapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectById(1L));
        System.out.println("------------------------------------------");
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("------------------------------------------");
        //批量查询
        System.out.println(userMapper.selectBatchIds(Arrays.asList(1,2,3)));
        System.out.println("------------------------------------------");
        //按条件查询
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","a");
        List<User> users1 = userMapper.selectByMap(map);
        for (User user : users1) {
            System.out.println(user);
        }
    }

    @Test
    void testInsert(){
        User user = new User();
        //user.setId(8L);
        user.setName("a");
        user.setAge(18);
        userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    void testUpdate(){
        User user = new User();
        //通过条件自动拼接sql
        user.setId(6L);
        user.setName("c");
        user.setAge(18);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁成功
    @Test
    void testOptimisticLocker(){
        User user = userMapper.selectById(1);
        user.setName("abc");
        userMapper.updateById(user);
    }
    //测试乐观锁失败
    @Test
    void testOptimisticLocker2(){
        //线程1
        User user = userMapper.selectById(1);
        user.setName("abc");

        //模拟另外一个线程进行了插队操作
        User user2 = userMapper.selectById(1);
        user2.setName("abcd");
        userMapper.updateById(user2);

        userMapper.updateById(user);  //如果没有乐观锁就会覆盖插队线程的值,无法更新
    }

    //分页查询
    @Test
    void testPage(){
        /*
        * current:当前页
        * size:页面大小
        * */
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    //逻辑删除
    @Test
    void testDelete(){
        userMapper.deleteById(10L);
    }
}
