package deal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.config.AlipayConfig;
import deal.PayConfig.PayConfig;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Alipay {
    public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        PayConfig payConfig = new PayConfig();
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = get_out_trade_no(request);
        //付款金额，必填
        String total_amount = payConfig.total_amount;
        //订单名称，必填
        String subject = payConfig.subject;
        //商品描述，可空
        String body = payConfig.body;

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        AlipayTradePagePayResponse alipayResponse = null;
        try {
            alipayResponse=alipayClient.pageExecute(alipayRequest);
            System.out.println(alipayResponse.getBody());
            System.out.println(alipayResponse.getMsg());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(alipayResponse.getBody());
    }

    String get_out_trade_no(HttpServletRequest request) throws Exception {
        dbManage db = new dbManage();
        String out_trade_no = db.get_out_trade_no((String) request.getSession().getAttribute("userName"));
        if(out_trade_no!=null){
            return  out_trade_no;
        }else {
            out_trade_no =PayConfig.get_out_trade_no();
            System.out.println(out_trade_no);
            String sql = "INSERT INTO payInfo VALUES ('"+request.getSession().getAttribute("userName")+"','"+out_trade_no+"','0')";
            System.out.println(sql);
            db.setUser(sql);
            return out_trade_no;
        }
    }

    public void checkOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016101800712960","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMkafUyn0Oi7FE1Ilfe+P47QebokUtIFVVVYRGxbf9WDgH1tm3ztFDvtzYIV3rx2yTFaFynhqX2c9e+SH6eDAyl6dHG1uLYhjx+RYPF4h0IUMEVGa4ixfVXe6/XnFzBFkPMGNhHMf0QnJghtAhbW8AcSXFNtAoKMdnE9RedPulLDkCFVWbTA9x+16+tjlCISbWXO70shN6c8gHsqIntdPNQEl0Bh6FxohMGrisF64GFPSABOQxzvZePw/r+OO2q9lm2pBP+j/OcYMnPh8l3hbDn/RBqqCG7WIXut19AgiBbzLzlcYLvXxvw2ZYKyniLeg4e8dkgL3g4ASHf3osTXlTAgMBAAECggEAVh3t6glbcAvYwEhNGLg3xArCvHA/XRQP80V+0jTTMVxYh0TzAQ6tWYmWlrpqZcY5GCsKS8PcmucPiimqzV3QeU47Zu61uMCSc7ub7gcGy86M1hw3Chv8r/TqDUd4zXP+c7W7TIVrYpNKqyQsYi4+8NBgyV95SwEgmF+nnW6htGBoTzx6d5sECJ/LaqG6ALifPNBT3fzXtNznW7xfySkmzqQyOZ+kKedN9sMvRKUo4b72P84AW/4jJKg6EPwS/duBB7YBIB2ESAKPIlQr/dA0327bYsJYkrjcC0GO6/q4I8HS8bD2ifJnbKavRKdzKFaggpGf9eLPHdfvFciVBP9RcQKBgQDLqMbFv823E2Pu+dvd8y4Rh7Ws0d9QLqnIQt8x68a2LbodUKERWN2oXfwkpuK7R/WRFduU4lzgi3XqeqakM0zih1YjL8+6jBhD7CjGtB0Pt/LK5OBM93tPrdMp4/qGaRM/TBIrjENSVLrmo1SIOAEw0Izc3S/OD27l77jkXp6svwKBgQCwsgTOI3UW46ykqYsShB9FEVf5RJ/yiDE2vUqFdOrlG1dkU1S+Jy/0ytOr9Q9RzexuP8jGVta33Dnyin5BiowZ9ZjzstmulEnsoRetVfC0G5SBoq/OXqjMpVHtEyWAJBqrPOGFl2Gr6oKtkUSbhQDhvFBGkG3W7zlpfPxKz4EUbQKBgQC6+/O8HdT3ELOA9yCaECqs56eyy2I30RALEPKxd+MS4rrtLVkkfy3L5raYxCyJfJEHK0VW1domQ4T4yrFzMrKVjWJVnhEgGn4w5oOtpDLGesJM7ITPxa0Ow+6LmIgsng6Q3/wSxY4N6kNLfIwhISJeuiuKoP3COEM+k7Fy4zjWKwKBgGS3VB3cACXn6MSDD46iWOCZnjercjBgiQzWoN+pYSruhsXc6fTpiWBTLIxbxzut0RKWsO69GO78TNt/LbBWNL2nTGO8km/8fpauzDw1PFqtTd0GyocrVbQwl542x61e5wSsUzA+taG0roAYya6MBZCmMmvGb5WQDn2ejIBn/v+RAoGAHCEiniLycgAxyPez+PmxzTION+HVdJAWuzryxVwJp/MNBQaU114Wu2cktWYKIM4BBVDUv9D6XsM2/iubykiXpmKdjMkA7T/eUmJKtfZkq9SIWlXeQdyzLQcQZydzNuNWb/ba0dPUQk13nlPoIGce+9Dxqbgkay/5nMv8MIXVAT8=","json","GBK","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlp0REnsuH1JFQVbNvB0pwil0uxLxgnE7bH/s8x80s1UBT0U46mWS85RZREvuxMHx/EQy4V/RLdk0JiLmZJsHXrpRe7SwBzNHgwY5ybREn+kV2/gqskYH6Ore9xN7xuheV3HXmerfk2LYrhClaUvCMqugwGXZ1n8mhjtXMzy6YEt4rJEXYkIluxkHQCvy8zoCt9EYgJOjLQ1K0ZI9iNNg/W5gRB6tTP9oYQOkCzymZw21kiXUDBqkQ+NmtadCiu04fOCqR+tFBKXSsLe/LKnhrHk2yMFpCzuTySyNwVPMIszv2atZFdCxnte6pug/x09feGsyy53oJ/f8/f+8J5BoTwIDAQAB","RSA2");
        AlipayTradeQueryRequest Alirequest = new AlipayTradeQueryRequest();
        Alirequest.setBizContent("{" +
                "\"out_trade_no\":\""+get_out_trade_no(request)+"\"," +
                "\"trade_no\":\"\"," +
                "\"org_pid\":\"\"," +
                "      \"query_options\":[" +
                "        \"TRADE_SETTLE_INFO\"" +
                "      ]" +
                "  }");
        AlipayTradeQueryResponse Aliresponse = alipayClient.execute(Alirequest);
        if(Aliresponse.isSuccess()){
          //支付成功
            dbManage db = new dbManage();
            String sql = "update payInfo set Paid='1' where username='"+request.getSession().getAttribute("userName")+"';";
            db.setUser(sql);
        } else {
         //支付失败
        }
    }

    public void flushOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
    dbManage db = new dbManage();
    String paid = db.getPaid((String) request.getSession().getAttribute("userName"));
    if(paid.equals("1")){//数据库中付过，就直接返回已付
        response.setContentType("application/text");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.write("1");
        out.close();
    }
    else {//没有付过，先和支付宝网关查询，确定没有付，再返回未付
        checkOrder(request,response);//网关查询
        paid = db.getPaid((String) request.getSession().getAttribute("userName"));
        if(paid.equals("1")){//核实已付，返回已付
            response.setContentType("application/text");
            response.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            out.write("1");
            out.close();
        }
        else {//核实未付，返回未付
            response.setContentType("application/text");
            response.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            out.write("0");
            out.close();
        }
    }
    }
}
