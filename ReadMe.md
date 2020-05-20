# Student-registration-information-management-system
## 菜鸡的练习项目之servlet实现的学生报名信息管理系统，顺便交学校课设


### ----------------------2020年5月20日提交---------------------------
菜鸡的练习项目之servlet实现的学生报名信息管理系统，顺便交学校课设
这是一个练手的项目，也用来交学校课设作业。主要是为了熟悉servlet的原理，同时通过接入支付宝支付来练习第三方api的调用
这个小项目包括了用户注册，验证码发送，密码找回，报名，支付宝扫描付费，成绩查询，付费情况统计并导出Excel表格等功能。
如果要尝试运行验证码发送功能，需要修改src/deal/Register中的这行代码：
` ``` `
email.setAuthentication("*****","*****");//设置发送人的邮箱和用户名和授权码(授权码是自己设置的)
` ``` `

将发送邮箱设置为你自己的

如果要尝试运行支付宝支付功能，需要修改src/com.alipay.config/AlipayConfig中的配置信息：
` ``` `
// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "****";
// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "*****";
` ``` `

注意，这个api是沙箱环境的，测试账号需要去支付宝开发者中心自行申请
如果想要在真实环境下使用，需要修改配置文件中的网关信息。

使用的技术只有：java，servlet，HTML，css，JavaScrip。
