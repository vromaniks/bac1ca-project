package ru.chmv.fmanager.runnable;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.util.Log;

public class Provider implements Runnable {
	private Activity myParent;
	private Runnable myAction;
	private List<File> myFileOrders;

	private File myFile;
	private String myNewTypes;

	public Provider(Activity parent, List<File> orders, Runnable action) {
		myParent = parent;
		myFileOrders = orders;
		myAction = action;
	}
	
	public void setPreferences(File file, String types) {
		myFile = file;
		myNewTypes = types;
		((Consumer) myAction).refresh();
	}

	public void run() {
		getOrders();
	}
	
	private void getOrders() {
		try {
			for (File file : myFile.listFiles()) {
				if (!Thread.currentThread().isInterrupted()) {

					// В реальных условиях здесь
					// код будет красивее
					// timeKiller() - работаем c файлом
					// FIXME delete later!!!
					// TODO DELETE LATER
					timeKiller(); 

					File f = null;
					if (file.isDirectory())
						f = file;
					else if (condition(file, myNewTypes))
						f = file;
					if (!Thread.currentThread().isInterrupted() && f != null) {
						myFileOrders.add(f);
					}
					myParent.runOnUiThread(myAction);
				}
			}
			myParent.runOnUiThread(myAction);
		} catch (Exception e) {
			Log.e("BACKGROUND_PROC", e.getMessage());
		}
	}

	private boolean condition(File file, String types) {
		return condition(file.getName(), types);
	}

	private boolean condition(String val, String types) {
		if (types.equals(""))
			return true;
		for (String type : types.split("[\\s]+")) {
			if (val.endsWith(type))
				return true;
		}
		return false;
	}

	private void timeKiller() {
		for (int i = 0; i < 200; i++) {
			System.out.println(i);
		}
	}
	
}
