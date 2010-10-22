package com.example;


public class FilterRunnable implements Runnable {
	SyncList myList;
	private String[] myArr;


	public FilterRunnable(SyncList list, String[] arr) {
		myList = list;
		myArr = arr;
	}

	@Override
	public void run() {
		try {
			for (String s : myArr) {
				timeKiller();
				myList.syncAdd(s);
				Thread.sleep((int) 10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void timeKiller() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(i);
		}
	}

}
