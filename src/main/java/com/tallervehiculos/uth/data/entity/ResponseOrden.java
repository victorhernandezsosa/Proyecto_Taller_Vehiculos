package com.tallervehiculos.uth.data.entity;

import java.util.List;

public class ResponseOrden {
	
	private List<Orden_reparacion> items_orden;
	private boolean hasMore;
	private int limit;
	private int offset;
	private int count;
	
	
	public List<Orden_reparacion> getItems_orden() {
		return items_orden;
	}
	public void setItems_orden(List<Orden_reparacion> items_orden) {
		this.items_orden = items_orden;
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