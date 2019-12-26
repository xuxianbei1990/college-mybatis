package college.controller;

import college.service.TransactionConnectionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: xuxianbei
 * Date: 2019/7/17
 * Time: 9:24
 * Version:V1.0
 * 结论100次查询2万订单表查询：加事务查询效率提升25%;优化sql 提升25倍左右
 * 500  加事务查询效率提升40%;优化sql 提升36倍左右
 * 连接数问题：事务所有操作在一个会话中；连接数因为mysql有自己的最大连接数池管理，所以即使循环sql查询或者以事务方式
 * 都是占用少量的连接数，只要单个sql够快。应对复杂查询，mysql官网建议也是拆分多个查询会话，因为mysql本来就是针对小会话设计
 * 是可以频繁创建会话和销毁。
 * 会话问题：因为mybtis设计时候就是一个查询一个会话。所以使用事务可以减少会话创建释放。
 */
@RestController
@RequestMapping("/mysql")
public class TransactionConnectionTestController {


    @Autowired
    TransactionConnectionTestService transactionConnectionTestService;

    @GetMapping("/connection/stress/test")
    public String connectionStressTest(Integer connectTimes) {
        return transactionConnectionTestService.connectionStressTest(connectTimes);
    }

    /**
     * 结论添加Transactional 性能提升25%
     */
    @GetMapping("/transaction/stress/test")
    public String transactionStressTest(Integer connectTimes) {
        return transactionConnectionTestService.transactionStressTest(connectTimes);
    }

    /**
     * 优化sql 提升36倍左右
     *
     * @param connectTimes
     * @return
     */
    @GetMapping("/foreach/stress/test")
    public String foreachStressTest(Integer connectTimes) {
        return transactionConnectionTestService.foreachStressTest(connectTimes);
    }

}
