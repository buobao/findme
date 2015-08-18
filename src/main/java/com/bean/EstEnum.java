package com.bean;

/**
 * Created by dqf on 2015/8/17.
 */
public class EstEnum {
    public enum AdminStatusEnum{
        unuse("未使用",0),
        using("使用中",1),
        expired("已过期",2),
        pause("暂停中",3);



        private final String value;
        private final int key;

        private AdminStatusEnum(final String value, final int key) {
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

}
