package com.tallervehiculos.uth.data.entity;

import java.util.List;

public class ResponseOrdenSR {

	private List<OrdenSR> items_OrdenSR;
	//private List<Orden_reparacion> items_orden;
	private boolean hasMore;
	private int limit;
	private int offset;
	private int count;

	public List<OrdenSR> getItems_ordenSR() {
		return items_OrdenSR;
	}
	public void getItems_ordenSR(List<OrdenSR> items_SR) {
		this.items_OrdenSR = items_SR;
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
