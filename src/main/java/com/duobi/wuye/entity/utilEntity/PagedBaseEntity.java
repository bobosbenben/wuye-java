package com.duobi.wuye.entity.utilEntity;

public class PagedBaseEntity extends BaseEntity {

    private Pager pager;

    public Pager getPager() {
        if (pager == null) pager = new Pager();
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
