package cn.edu.nju.software.agile_server.dao.impl;

import cn.edu.nju.software.agile_server.dao.TestDao;
import cn.edu.nju.software.agile_server.dto.TestDto;
import cn.edu.nju.software.agile_server.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TestDaoImpl implements TestDao {

    @Override
    public void insert(TestEntity entity) {
        log.info("执行了插入操作！");
    }

    @Override
    public TestEntity findByUsername(String username) {
        TestEntity entity = new TestEntity();
        entity.setPassword("123456");
        entity.setUsername("kingsley");
        return entity;
    }
}
