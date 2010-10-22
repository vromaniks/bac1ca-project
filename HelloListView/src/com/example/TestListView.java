package com.example;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestListView extends ListActivity {
	private String curDir = ".";
	private List<String> history = new ArrayList<String>();

	String [] arr = {"1.pdf", "2.pdf", "3.pdf", "4.pdf", "5.pdf", 
			"6.pdf", "7.pdf", "8.pdf", "9.pdf","10.pdf",
			"11.pdf", "12.pdf", "13.pdf", "14.pdf", "15.pdf", 
			"16.pdf", "17.pdf", "18.pdf", "19.pdf","20.pdf"};

	MyAdapter myAdapter;
	ListView lv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		lv = getListView();
		lv.setTextFilterEnabled(true);

		
		List<String> list = new ArrayList<String>(); 
		list.add("1");
		list.add("2");
		list.add("3");
		myAdapter = new MyAdapter(this, R.layout.list_item);
		myAdapter.syncAdd(list);
		lv.setAdapter(myAdapter);

		
		
		SyncList sList = new SyncList();

		FilterRunnable r = new FilterRunnable(sList, arr);
		Thread t = new Thread(r);
		t.start();
		
		FilterRunnable r2 = new FilterRunnable(sList, arr);
		Thread t2 = new Thread(r2);
		t2.start();
		
		BridgeRunnable r3 = new BridgeRunnable(sList, myAdapter);
		Thread t3 = new Thread(r3);
		t3.start();
		
		
//		ArrayList<Future<List<String>>> myThreads = new ArrayList<Future<List<String>>>();
//		ExecutorService pool = Executors.newFixedThreadPool(1);
//		for(int i = 0; i < 4; i++){
//			Filter filter = new Filter(myArrayAdapter, arr, null);
//			Future<List<String>> result = pool.submit(filter);
//			myThreads.add(result);
//		}
//		
//		try{
//			for(Future<List<String>> f : myThreads){
//				myArrayAdapter.add(f.get(3, TimeUnit.SECONDS));
//			}
//		}catch (ExecutionException e) {
//			e.printStackTrace();
//		}catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		}
//		pool.shutdown();
		
	}
	
	
//	public synchronized void setData(List<String> list){
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
//		lv.setAdapter(adapter);
//	}
	
	
//	private void goAtDir(String path){
//		if (path.endsWith("..")){
//			path = path.substring(0, path.length() - 3);
//			history.remove(history.size()-1);
//			int length = history.remove(history.size() -1).length() + 1;
//			path = path.substring(0, path.length() - length);
//		}
//		File file = new File(path);
//		if (file.isDirectory()){
//			curDir = file.getPath();
//			int l = file.list().length;
//			String files[] = new String[l+1]; files[0] = "..";
//			System.arraycopy(file.list(), 0, files, 1, file.list().length);
//			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, files));
//		}
//		
////		String mkDirPath = "./sdcard/DCIM/Camera/file";
////		for(int i = 1; i < 5000; i++){
////			String s = mkDirPath + Integer.toString(i);
////			new File(s).mkdir();
////		}
//		
//	}
}