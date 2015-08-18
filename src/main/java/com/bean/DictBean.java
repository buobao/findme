package com.bean;

/**
 * Created by dqf on 2015/8/17.
 */
public class DictBean {
    /**
     * 系统字典
     */
    public static enum DictEnum {
        ClientType("客户性质", 1),
        ProInfoCategory("项目类别", 2),
        ProBackCategory("反馈类别",3),
        SuperKnowledgeType("知识大类",4),
        SubKnowledgeType("知识小类",5);


        private final String value;
        private final int key;

        private DictEnum(final String value, final int key) {
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
