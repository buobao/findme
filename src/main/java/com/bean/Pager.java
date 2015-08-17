package com.bean;

import java.util.List;

/**
 * Created by dqf on 2015/7/14.
 */
public class Pager {
    public static final Integer MAX_PAGE_SIZE = Integer.valueOf(500);
    private Integer pageNumber;
    private Integer pageSize = Integer.valueOf(10);
    private Integer totalCount = Integer.valueOf(0);
    private Integer pageCount = Integer.valueOf(0);
    private String property;
    private String keyword;
    private String orderBy = "createDate";
    private BaseEnum.OrderType orderType = BaseEnum.OrderType.desc;
    private List<?> list;

    public Pager(){

        this.pageNumber = Integer.valueOf(1);
    }

    public Pager(Integer pageNumber){
        this.pageNumber = Integer.valueOf(pageNumber);
    }

    public Pager(Integer pageNumber, Integer pagerSize){
        this.pageNumber = pageNumber;
        this.pageSize = pagerSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber<1? Integer.valueOf(1):pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize<1? Integer.valueOf(1):(pageSize>MAX_PAGE_SIZE)?MAX_PAGE_SIZE.intValue():pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        int count = this.getTotalCount().intValue();
        int pagesize = this.getPageCount().intValue();
        this.pageCount = Integer.valueOf(count / pagesize + (count % pagesize > 0 ? 1 : 0));
        return this.pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public BaseEnum.OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(BaseEnum.OrderType orderType) {
        this.orderType = orderType;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }



}
