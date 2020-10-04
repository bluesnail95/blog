package gdut.ff.aop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author bluesnail95
 * @Date 2020/7/20 21:55
 * @Description
 */
@Service
@Transactional
public class TestServiceImpl implements ITestService{
    @Override
    public void test() {
        System.out.println("Test");
    }
}
