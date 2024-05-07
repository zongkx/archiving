package com.demo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 用于返回分页结果
 */
public class ResultInfo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long total;

    private List<?> rows;

    public ResultInfo(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PaginationResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
