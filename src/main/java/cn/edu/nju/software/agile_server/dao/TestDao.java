package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.TestEntity;

public interface TestDao {
    public void insert(TestEntity entity);

    public TestEntity findByUsername(String username);
}
