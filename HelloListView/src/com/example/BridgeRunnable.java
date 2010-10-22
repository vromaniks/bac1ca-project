package com.example;

import java.util.ArrayList;


public class BridgeRunnable implements Runnable {
	private SyncList myList;
	private MyAdapter myAdapter;

	private int curIdx = 0; 
	
	public BridgeRunnable(SyncList list, MyAdapter adapter) {
		myList = list;
		myAdapter = adapter;
	}

	@Override
	public void run() {
		try {
			while(true){
				if (curIdx != (myList.syncSize() - 1)){
					ArrayList<String> list = new ArrayList<String>();
					for (int i = 0; i < myList.syncSize(); i++) {
						list.add(myList.syncGet(i));
						
						myAdapter.add(myList.syncGet(i));
						
					}
					// FIXME
//					myAdapter.syncAdd(list);
				}
				Thread.sleep((int) 10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}