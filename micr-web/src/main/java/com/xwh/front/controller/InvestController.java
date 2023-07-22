package com.xwh.front.controller;

import com.xwh.api.model.User;
import com.xwh.common.constants.RedisKey;
import com.xwh.common.util.CommonUtil;
import com.xwh.front.view.R;
import com.xwh.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 * 有关投资的功能
 */
//@CrossOrigin
@RestController
@RequestMapping("/v1")
@Api(tags = "投资理财产品")
public class InvestController extends BaseController {


    /**
     * @description: 投资排行榜
     * @author 陈方银
     * @date 2023/6/22 13:27
     * @version 1.0
     */
    @ApiOperation(value = "投资排行榜", notes = "显示投资信息最高的三位信息")
    @GetMapping("/invest/rank")
    public R showInvestRank() {
        // redis 查询数据
        BoundZSetOperations<String, String> stringStringBoundZSetOperations = stringRedisTemplate.boundZSetOps(RedisKey.KEY_INVEST_RANK);
        Set<ZSetOperations.TypedTuple<String>> sets = stringStringBoundZSetOperations.reverseRangeWithScores(0, 2);
        // 遍历集合
        List<RankView> rankList = new ArrayList<>();
        if (Objects.nonNull(sets) && sets.size() > 0) {
            rankList = sets.stream().map(stringTypedTuple -> RankView.builder()
                    .phone(CommonUtil.phoneDesensitization(stringTypedTuple.getValue()))
                    .money(new BigDecimal(String.valueOf(stringTypedTuple.getScore())))
                    .build()).collect(Collectors.toList());
        }
        return R.ok().setList(rankList);
    }

    /**
     * @description: 购买理财产品，更新投资排行榜
     * @author 血无痕
     * @date 2023/7/22 13:17
     * @version 1.0
     */
    @ApiOperation(value = "投资理财产品", notes = "投资理财产品")
    @PostMapping("/invest/product")
    public R investProduct(
            @RequestHeader("uid") Integer uid,
            @RequestParam("productId") Integer productId,
            @RequestParam("money") BigDecimal money) {
        if (Objects.nonNull(uid) && Objects.nonNull(productId) &&
                (Objects.nonNull(money) &&
                        (CommonUtil.geBigDecimal(money.remainder(new BigDecimal("100")), BigDecimal.ZERO)) && CommonUtil.geBigDecimal(money, new BigDecimal("100")))) {
            investService.investProduct(uid, productId, money);
            modifyInvestRank(uid, money);
            return R.ok();
        }
        return R.error();
    }

    private void modifyInvestRank(Integer uid, BigDecimal money) {
        User user = userService.queryById(uid);
        String keyInvestRank = RedisKey.KEY_INVEST_RANK;
        if (Objects.nonNull(uid)) {
            stringRedisTemplate.boundZSetOps(keyInvestRank).incrementScore(user.getPhone(), money.doubleValue());
        }
    }
}
