package com.example;

import java.util.ArrayList;

public class SyncList extends ArrayList<String> {
	
	private static final long serialVersionUID = 1L;

	public synchronized void syncAdd(String s) {
		add(s);
	}
	
	public synchronized void syncAddAll(ArrayList<String> list) {
		super.addAll(list);
	}
	
	public synchronized String syncGet(int idx) {
		return get(idx);
	}
	
	public synchronized int syncSize(){
		return size();
	}
	
}
