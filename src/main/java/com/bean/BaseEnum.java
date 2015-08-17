package com.bean;

/**
 * Created by dqf on 2015/7/14.
 */
public class BaseEnum {
    public BaseEnum(){}

    /*
    * 性别
    * */
    public static enum SexEnum{
        male("男",1),
        female("女",2);

        private final String value;
        private final int key;
        private SexEnum(String value, int key) {
            this.value = value;
            this.key = key;
        }

        public String value() {
            return this.value;
        }

        public int key() {
            return this.key;
        }

    }

    /*
    * 排序方式
    * */
    public static enum OrderType{
        asc,
        desc, OrderType;

        private OrderType(){}
    }


    /*
    * 应用模块
    * */
    public static enum AppEnum{
        COM("新闻管理",1),
        Blog("博客管理",2),
        MANAGER("用户管理",3);

        private final String value;
        private final int key;


        AppEnum(String value, int key) {
            this.value = value;
            this.key = key;
        }

        public String value() {
            return this.value;
        }

        public int key() {
            return this.key;
        }
    }

    /*
    * 角色类别
    * */
    public static enum RoleEnum{
        ROLE_ADMIN("系统管理员",1),
        ROLE_MANAGER("板块管理员",2),
        ROLE_USER("用户",3),
        ROLE_ANO("游客",4);

        private final String value;
        private final int key;

        RoleEnum(String value, int key) {
            this.value = value;
            this.key = key;
        }

        public String value(){
            return this.value;
        }

        public int key(){
            return this.key;
        }
    }

    public static enum StateEnum {
        Delete("删除", 1),
        Enable("启用", 2),
        Disenable("停用", 3),
        History("历史", 4);

        private final String value;
        private final int key;

        private StateEnum(String value, int key) {
            this.value = value;
            this.key = key;
        }

        public String value() {
            return this.value;
        }

        public int key() {
            return this.key;
        }
    }

    public static enum AccountEnum{
        MAIL("邮箱",1),
        MOBILE("手机号",2),
        WEIXIN("微信",3),
        UNKNOW("未知",4);

        private final String value;
        private final int key;

        AccountEnum(String value, int key){
            this.value = value;
            this.key = key;
        }

        public String value(){
            return this.value;
        }

        public int key(){
            return  this.key;
        }
    }

    public static enum MsgStateEnum{
        LOOKED("已查看",1),
        UNLOOK("未查看",0);

        private final String value;
        private final int key;

        MsgStateEnum(String value, int key){
            this.value = value;
            this.key = key;
        }

        public String value(){return this.value;}
        public int key(){return this.key;}
    }

    public static  enum NewsStateEnum{
        USING("有效",1),
        DELETE("删除",0);

        private final String value;
        private final int key;

        NewsStateEnum(String value, int key){
            this.value = value;
            this.key = key;
        }

        public String value(){return this.value;}
        public int key(){return this.key;}
    }

    /*账号状态*/
    public static enum AccountState{
        NORMAL("正常",1),
        FORBIDDEN("禁用",2),
        DELETE("删除",3);

        private final String value;
        private final int key;

        AccountState(String value, int key){
            this.value = value;
            this.key = key;
        }

        public String value(){return this.value;}
        public int key(){return this.key;}
    }
}



























