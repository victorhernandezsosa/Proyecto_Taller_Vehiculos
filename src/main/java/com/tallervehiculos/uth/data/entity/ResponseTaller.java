package com.tallervehiculos.uth.data.entity;

import java.util.List;


public class ResponseTaller {

	private List<Vehiculo> items;
	private boolean hasMore;
	private int limit;
	private int offset;
	private int count;
	
	public List<Vehiculo> getItems() {
		return items;
	}
	public void setItems(List<Vehiculo> items) {
		this.items = items;
	}
	public boolean isHasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
