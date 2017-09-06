package com.rstang.mall.util;

import com.rstang.mall.em.BusinessWay;
import com.rstang.util.key.IDCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商城工具类，获取商品ID、订单ID实现
 * Created by yeyx on 2017/9/6.
 */
public abstract class MallUtils {

    private static Logger logger = LoggerFactory.getLogger(MallUtils.class);

    public static String getOrderId(BusinessWay way) {
        try {
            if (BusinessWay.SELF.equals(way)) {
                return IDCreator.getInstance().getID("mall.order.self");
            } else if (BusinessWay.THREE.equals(way)) {
                return IDCreator.getInstance().getID("mall.order.3rd");
            } else {
                throw new RuntimeException("getOrderId BusinessWay don't support, way = " + way.getDesc());
            }
        } catch (Exception e) {
            logger.error("getOrderId", e);
            return null;
        }
    }

    public static String getGoodsId(BusinessWay way) {
        try {
            if (BusinessWay.SELF.equals(way)) {
                return IDCreator.getInstance().getID("mall.goods.self");
            } else if (BusinessWay.THREE.equals(way)) {
                return IDCreator.getInstance().getID("mall.goods.3rd");
            } else {
                throw new RuntimeException("getGoodsId BusinessWay don't support, way = " + way.getDesc());
            }
        } catch (Exception e){
            logger.error("getGoodsId", e);
            return null;
        }
    }
}
