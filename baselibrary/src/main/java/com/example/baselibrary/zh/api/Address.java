package com.example.baselibrary.zh.api;

/**
 * 接口定义
 */

public class Address {
    //TODO:登录
    public static final String userIogin = "user/login/loginByCode?";
    //TODO:Token更换
    public static final String token = "user/login/token/refresh?";
    /**
     * 我的--设置--账号与安全--变更手机号  GET  http://192.168.0.195:9993/user/me/setting/changePhone
     */
    public final static String update_number_zh = "user/me/setting/changePhone?";
    /**
     * 我的--设置--账号与安全--绑定 邮箱   GET   http://192.168.0.195:9993/user/me/setting/changeEmail
     */
    public final static String update_email = "user/me/setting/changeEmail?";
    /**
     * 我的--vip  GET   http://192.168.0.195:8980/user/getVipEquities
     */
    public final static String vip_getVipEquities = "user/getVipEquities?";
    /**
     * 1.我的--vip--升级vip  GET
     */
    public final static String vip_upgradeVipLevel = "user/upgradeVipLevel?";
    /**
     * 我的--vip--充值金额   GET
     */
    public final static String upgradeAmount = "user/upgradeAmount?";
    /**
     * 我的--帮助中心--查询FAQ   GET   http://192.168.0.195:9993/user/me/helpCenter/findFAQs
     */
    public final static String findFAQs = "user/me/helpCenter/findFAQs?";
    /**
     * 我的--帮助中心--查询客服信息   GET   http://192.168.0.195:9993/user/me/helpCenter/findServiceInfo
     */
    public final static String findServiceInfo = "user/me/helpCenter/findServiceInfo?";
    /**
     * 我的--帮助中心--意见反馈   POST  http://192.168.0.195:9993/user/me/helpCenter/feedbackProblem
     */
    public final static String feedbackProblem = "user/me/helpCenter/feedbackProblem?";
    /**
     * 我的--积分商城--个人积分   GET  http://192.168.0.195:8980/user/pointsMall/personalPoints
     */
    public final static String personalPoints = "user/pointsMall/personalPoints?";
    /**
     * 我的--积分商城--精选兑换   GET   http://192.168.0.195:8980/user/pointsMall/selectedFor
     */
    public final static String selectedFor = "user/pointsMall/selectedFor?";
    /**
     * 我的--钱包--账户余额   GET
     */
    public final static String accountBalance = "user/wallet/accountBalance?";
    /**
     * 积分规则   GET  http://192.168.0.195:8980/store/pointsMall/pointsRules
     */
    public final static String pointsRules = "store/pointsMall/pointsRules?";
    /**
     * 我的--设置--检查更新    GET   http://192.168.0.195:9993/user/me/setting/checkForUpdates
     */
    public final static String checkForUpdates = "user/me/setting/checkForUpdates?";
    /**
     * 我的--设置--账号与安全--修改密码（用户短信码）  GET
     */
    public final static String bySmsCode = "user/me/setting/modifyPwd/bySmsCode?";
    /**
     * 我的--设置--账号与安全--修改密码(用旧密码)   GET   http://192.168.0.195:9993/user/me/setting/modifyPwd/byOldPwd
     */
    public final static String byOldPwd = "user/me/setting/modifyPwd/byOldPwd?";
    /**
     * 我的--首页   GET
     */
    public final static String index = "user/me/index?";
    /**
     * 我的--首页--查看收藏信息   GET
     */
    public final static String scanCollectionInfo = "area/me/scanCollectionInfo?";
    /**
     * 我的--首页--分享上报    GET
     */
    public final static String reportInfo = "user/userinfo/share/reportInfo?";
    /**
     * 我的--首页--粉丝列表    POST
     */
    //public final static String payAttentionToFans = "user/me/payAttentionToFans?";
    public final static String payAttentionToFans = "area/me/payAttentionToFans?";
    /**
     * 我的--首页--获取关注人列表
     */
    public final static String follow = "store/enterpriseMyOrganization/getAttentionList?";
    /**
     * 我的--首页--获取关注人列表--关注   GET
     */
    public final static String attention = "user/me/getAttentions/attention?";
    /**
     * 我的--首页--浏览历史   GET
     */
    public final static String browerHistoty = "area/me/browerHistoty?";
    /**
     * 我的--首页--商圈信息列表   GET   http://192.168.0.195:9991/area/me/bizCircleStatistics
     */
    public final static String bizCircleStatistics = "area/me/bizCircleStatistics?";
    /**
     * 我的--首页--商圈信息列表--删除商圈信息   GET  http://192.168.0.195:9991/area/me/bizCircle/deleteBizCircle
     */
    public final static String deleteBizCircle = "area/me/bizCircle/deleteBizCircle?";
    /**
     * 我的--首页--商圈信息列表--设置商圈阅读权限   POST
     */
    public final static String setReadPerm = "user/me/bizCircle/setReadPerm?";
    /**
     * 我的--首页--我的点赞   GET
     */
    public final static String praisedArticles = "area/me/praisedArticles?";
    /**
     * 我的--首页--我的评论   GET  http://192.168.0.195:9991/area/me/commertArtiles
     */
    public final static String commertArtiles = "area/me/commertArtiles?";
    /**
     * 我的--首页--用户个人基本信息   GET
     */
    public final static String userInfo = "user/userInfo?";
    /**
     * 我的--首页--用户个人基本信息--编辑个人资料   POST
     */
    public final static String edit = "user/userinfo/edit?";
    /**
     * 我的--首页--用户个人基本信息--编辑个人资料--保存  POST
     */
    public final static String save = "user/userinfo/save?";
    /**
     * 我的--首页--用户个人基本信息--历史成绩   GET
     */
    public final static String historyRanking = "user/userInfo/historyRanking?";
    /**
     * 1.我的--首页--用户个人基本信息--我在阅读  GET
     */
    public final static String readingBooks = "user/userInfo/readingBooks?";
    /**
     * 1.我的--我的组织--待审批列表   GET
     */
    public final static String waitingAuditList = "user/me/org/waitingAuditList?";
    /**
     * 我的--我的组织--管理组织--企业列表  GET
     */
    public final static String manage_list = "user/me/org/manage/list?";
    /**
     * 1.我的--我的组织--管理组织--设置主企业  GET
     */
    public final static String setMainOrg = "user/org/manage/setMainOrg?";
    /**
     * 我的--我的组织--管理组织--退出组织   GET
     */
    public final static String leaveOrg = "user/me/org/manage/leaveOrg?";
    /**
     * 我的--我的组织--加入组织--查询组织  GET
     */
    public final static String queryOrgInfo = "user/me/org/queryOrgInfo?";
    /**
     * 我的--我的组织--加入组织--加入  GET
     */
    public final static String join = "user/me/org/join?";
    /**
     * 我的--我的组织--审批成员--审核操作  GET
     */
    public final static String audit = "user/me/org/audit?";
    /**
     * 我的--我的组织--我的同事  GET
     */
    public final static String myColleague = "user/me/myColleague?";
    /**
     * 我的--足迹--查询足迹信息  GET
     */
    public final static String footprint_list = "user/me/footprint/list?";
    /**
     * 我的--足迹--添加足迹   GET
     */
    public final static String add_footprint = "user/me/footprint/add?";


}
