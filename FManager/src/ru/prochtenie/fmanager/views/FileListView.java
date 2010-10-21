package ru.prochtenie.fmanager.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ru.prochtenie.fmanager.filter.Filter;

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


	
	
	private Filter myFilter = new Filter();
	
	private String myFilterTypes = "";
	
	// **************************************************************************//
	// Constructors //
	// **************************************************************************//
	public FileListView(Activity context, ListView listView) {
		myContext = context;
		myListView = listView;
		goAtDir(myCurDir);

		myListView.setTextFilterEnabled(true);
		myListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view.setSelected(true);
				
				myCurFile = ((TextView) view).getText().toString();
				if (new File(myCurDir + "/" + myCurFile).isDirectory()){
					myHistory.add(myCurFile);
					goAtDir(myCurDir + "/" + myCurFile);
				}
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
			back();
			goAtDir(myCurDir);
		}
	}
	
	public void setFilter(String filterTypes){
		if (!myFilterTypes.equals(filterTypes)){
			myFilterTypes = filterTypes;
			goAtDir(myCurDir);
		}
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
	
	// stable
	private void back(){
		String dir = myHistory.remove(myHistory.size() - 1);
		myCurDir = myCurDir.substring(0, myCurDir.length() - dir.length() - 1);
	}
	
	private void goAtDir(String path) {
		File file = new File(path);
		if (file.isDirectory()) {		// ненужная проверка
			myCurDir = path;
			if (myFilterTypes.equals("")){			
				myListView.setAdapter(new ArrayAdapter<String>(myContext,
						R.layout.list_item, file.list()));
			}else{
				List<String> resultList = myFilter.getFilteredList(file, myFilterTypes);
				myListView.setAdapter(new ArrayAdapter<String>(myContext,
						R.layout.list_item, resultList.toArray(new String[0])));
			}
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
