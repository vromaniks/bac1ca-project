package ru.chmv.fmanager.runnable;

import java.io.File;
import java.util.List;

import android.app.ProgressDialog;
import android.widget.ArrayAdapter;

public class Consumer implements Runnable {
	private int myCurIdx = 0;
	private List<File> myFileOrders;
	private ArrayAdapter<File> myAdapter;
	private ProgressDialog myProgressDialog;
	
	public Consumer(List<File> orders, ArrayAdapter<File> adapter,
			ProgressDialog pd) {
		myFileOrders = orders;
		myAdapter = adapter;
		myProgressDialog = pd;
	}

	public void refresh() {
		myCurIdx = 0;
		myFileOrders.clear();
		myAdapter.clear();
		myAdapter.notifyDataSetChanged();
	}
	
	public void run() {
		if (myFileOrders != null && myFileOrders.size() > 0) {
			myAdapter.notifyDataSetChanged();
			int i = myCurIdx;
			for (i = myCurIdx; i < myFileOrders.size(); i++) {
				myAdapter.add(myFileOrders.get(i));
			}
			myCurIdx = i;
		}
		myAdapter.notifyDataSetChanged();
		myProgressDialog.dismiss();
	}
};
