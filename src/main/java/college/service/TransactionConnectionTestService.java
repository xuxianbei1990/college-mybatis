package college.service;

import college.mapper.OrderBuyerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: xuxianbei
 * Date: 2019/7/17
 * Time: 10:10
 * Version:V1.0
 */
@Service
public class TransactionConnectionTestService {

    @Resource
    OrderBuyerMapper orderBuyerMapper;

    public String connectionStressTest(Integer connectTimes) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < connectTimes; i++) {
            Random random = new Random();
            Long key = (long) (random.nextInt(142457 - 122334) + 122334);
            orderBuyerMapper.selectByPrimaryKey(key);
        }
        return "connection" + (System.currentTimeMillis() - start);
    }

    //结论添加Transactional 性能提升25%
    @Transactional
    public String transactionStressTest(Integer connectTimes) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < connectTimes; i++) {
            Random random = new Random();
            Long key = (long) (random.nextInt(142457 - 122334) + 122334);
            orderBuyerMapper.selectByPrimaryKey(key);
        }
        return "transaction" + (System.currentTimeMillis() - start);
    }

    public String foreachStressTest(Integer connectTimes) {
        Long start = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < connectTimes; i++) {
            Random random = new Random();
            Long key = (long) (random.nextInt(142457 - 122334) + 122334);
            list.add(key);
        }
        orderBuyerMapper.selectByPrimaryKeys(list);
        return "transaction" + (System.currentTimeMillis() - start);
    }

}
