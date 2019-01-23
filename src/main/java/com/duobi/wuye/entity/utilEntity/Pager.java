package com.duobi.wuye.entity.utilEntity;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private int start;          //起始条数
    private int pageNo = 1;     //当前页数
    private int pageSize;       //每页的条数
    private long count = 0;     //总记录数，可能是多页的总和
    private String orderBy = "";//排序条件
    private Boolean autoPage = false;   //自动分页标志，true时使用pageHelper由系统分页
    private List<T> list;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getAutoPage() {
        return autoPage;
    }

    public void setAutoPage(Boolean autoPage) {
        this.autoPage = autoPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        if (list == null) list = new ArrayList<>();
        this.list = list;
    }
}
