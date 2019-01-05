package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.command.TestCommand;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.dao.TestDao;
import cn.edu.nju.software.agile_server.dto.TestDto;
import cn.edu.nju.software.agile_server.entity.TestEntity;
import cn.edu.nju.software.agile_server.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public Result insert(TestCommand command) {
        TestEntity entity = new TestEntity();
        BeanUtils.copyProperties(command, entity);
        testDao.insert(entity);
        return Result.success().code(200).message("插入成功！");
    }

    @Override
    public Result findByUsername(String username) {
        TestEntity testEntity = testDao.findByUsername(username);
        TestDto dto = new TestDto();
        BeanUtils.copyProperties(testEntity, dto);
        return Result.success().code(200).message("查找成功！").withData(dto);
    }
}
