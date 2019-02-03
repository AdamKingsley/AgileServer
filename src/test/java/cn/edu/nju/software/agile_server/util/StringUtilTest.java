package cn.edu.nju.software.agile_server.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StringUtilTest {

    @Test
    public void test() {
        StringUtil.getList("1234,53434", ",");
        StringUtil.getList("", ",");
        StringUtil.getList(null, ",");
    }
}
