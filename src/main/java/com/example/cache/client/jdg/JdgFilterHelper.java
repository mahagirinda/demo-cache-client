package com.example.cache.client.jdg;

import java.util.Map;

public class JdgFilterHelper {

	private String fields;
	private String[] filterInRPN;
	private String groupBy;
	private String orderBy;
	private Integer pageSize = 0;
	private Integer offset = 0;
	private Map<String, String> paramValues;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String[] getFilterInRPN() {
		return filterInRPN;
	}

	public void setFilterInRPN(String[] filterInRPN) {
		this.filterInRPN = filterInRPN;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Map<String, String> getParamValues() {
		return paramValues;
	}

	public void setParamValues(Map<String, String> paramValues) {
		this.paramValues = paramValues;
	}
}
