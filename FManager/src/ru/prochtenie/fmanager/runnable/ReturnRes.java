package ru.prochtenie.fmanager.runnable;

import java.util.List;

import android.app.ProgressDialog;
import android.widget.ArrayAdapter;

public class ReturnRes implements Runnable {
	private int myCurIdx = 0;
	private List<String> myOrders;
	private ArrayAdapter<String> myAdapter;
	private ProgressDialog myProgressDialog;
	
	public ReturnRes(List<String> orders, ArrayAdapter<String> adapter) {
		myOrders = orders;
		myAdapter = adapter;
	}
	
	public ReturnRes(List<String> orders, ArrayAdapter<String> adapter, ProgressDialog pd) {
		myOrders = orders;
		myAdapter = adapter;
		myProgressDialog = pd;
	}
	
	public void refresh() {
		myCurIdx = 0;
		myOrders.clear();
		myAdapter.clear();
		myAdapter.notifyDataSetChanged();
	}

	@Override
	public void run() {
		if (myOrders != null && myOrders.size() > 0) {
			myAdapter.notifyDataSetChanged();
			int i = myCurIdx;
			for (i = myCurIdx; i < myOrders.size(); i++) {
				myAdapter.add(myOrders.get(i));
			}
			myCurIdx = i;
		} 
		myAdapter.notifyDataSetChanged();
		myProgressDialog.dismiss();
	}
};
