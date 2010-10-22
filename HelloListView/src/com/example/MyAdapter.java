package com.example;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;


public class MyAdapter extends ArrayAdapter<String>{

		public MyAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}
		
		public synchronized void syncAdd(String s) {
			add(s);
//			notifyAll();
		}
		
		public synchronized void syncAdd(List<String> list) {
			for(String item : list){
				add(item);
			}
//			notifyAll();
		}
	}