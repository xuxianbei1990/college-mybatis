package college.controller;

import college.mapper.OrderBuyerMapper;
import college.model.OrderBuyer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 16:17
 * Version:V1.0
 */
@RestController
@RequestMapping("/orderbuyer")
public class OrderBuyerController {

    @Resource
    OrderBuyerMapper orderBuyerMapper;


    @GetMapping("/query")
    public OrderBuyer connectionStressTest(Long keyId) {
        return orderBuyerMapper.selectByPrimaryKey(keyId);
    }
}
