package deal.PayConfig;

import java.io.FileWriter;
import java.io.IOException;

public class PayConfig {

//↓↓↓↓↓↓↓↓↓↓这里配置支付数据↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


    // 支付金额
    public static String total_amount ="100";

    // 订单名称
    public static String subject = "某某某考试报名费";

    // 商品描述，可为空
    public static String body = "我是商品描述";

    // 唯一订单号
    public static String get_out_trade_no(){
        SnowflakeDistributeId idWorker = new SnowflakeDistributeId(0, 0);
            long id = idWorker.nextId();
            return Long.toString(id);
    }

}

