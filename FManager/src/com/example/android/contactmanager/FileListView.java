package com.example.android.contactmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FileListView {
	// **************************************************************************//
	// Enums //
	// **************************************************************************//

	// **************************************************************************//
	// Members //
	// **************************************************************************//
	private Activity myContext;

	private ListView myListView;

	private String myCurDir = START_DIR;

	private String myCurFile = START_DIR;

	private List<String> myHistory = new ArrayList<String>();

	// **************************************************************************//
	// Constructors //
	// **************************************************************************//
	public FileListView(Activity context, ListView listView) {
		myContext = context;
		myListView = listView;
		goAtDir(myCurDir);

		myListView.setFocusableInTouchMode(true);

		myListView.setTextFilterEnabled(true);
		myListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO - correct selection
				
				view.setSelected(true);
				myCurFile = ((TextView) view).getText().toString();
				goAtDir(myCurDir + "/" + myCurFile);
			}
		});

	}

	// **************************************************************************//
	// Getters //
	// **************************************************************************//
	public ListView getListView() {
		return myListView;
	}

	// **************************************************************************//
	// Setters //
	// **************************************************************************//

	// **************************************************************************//
	// Publics //
	// **************************************************************************//
	public void goAtBack(){
		if (!myCurDir.equals(START_DIR)){
			String backPath = myHistory.remove(myHistory.size() - 1);
			myCurDir = myCurDir.substring(0, myCurDir.length() - backPath.length() - 1);
			myListView.setAdapter(new ArrayAdapter<String>(myContext,
					R.layout.list_item, new File(myCurDir).list()));
		}
	}
	
	public void setFilter(String filterStr){
		// TODO
	}
	// **************************************************************************//
	// Abstracts //
	// **************************************************************************//

	// **************************************************************************//
	// Protected //
	// **************************************************************************//

	// **************************************************************************//
	// Privates //
	// **************************************************************************//
	private void goAtDir(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			myCurDir = path;
			myHistory.add(myCurFile);
			myListView.setAdapter(new ArrayAdapter<String>(myContext,
					R.layout.list_item, file.list()));
		}
	}
	
	// **************************************************************************//
	// Public Statics //
	// **************************************************************************//

	// **************************************************************************//
	// Private Statics //
	// **************************************************************************//
	private static final String START_DIR = ".";
	
	// **************************************************************************//
	// Internal Classes //
	// **************************************************************************//

}
