package ru.chmv.fmanager.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.chmv.fmanager.runnable.Consumer;
import ru.chmv.fmanager.runnable.Provider;
import ru.chmv.fmanager.utils.IsNamed;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FileListView {
	private File myCurDir;
	private Activity myParent;
	private String myFilterTypes = "";

	// Members for dynamic loading //
	private Provider myProvider;
	private Consumer myConsumer;
	private Thread myCurFilterThread;
	private ProgressDialog myProgressDialog;
	
	public FileListView(Activity parent, ListView listView) {
		myParent = parent;
		myProgressDialog = creatProgressDialog();

		List<File> fileOrders = Collections.synchronizedList(new ArrayList<File>());
		FileAdapter adapter = new FileAdapter(myParent, R.layout.list_item, fileOrders);
		listView.setAdapter(adapter);

		myConsumer = new Consumer(fileOrders, adapter, myProgressDialog);
		myProvider = new Provider(myParent, fileOrders, myConsumer);
		
		init(FileManager.ROOT_DIR);
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view.setSelected(true);
				step(new File(((TextView)(view.findViewById(R.id.f_path))).getText().toString()));
			}
		});

	}

	public String getFilterTypes(){
		return myFilterTypes;
	}
	
	public void goAtBack(){
		if (myCurDir.getParentFile() != null)
			step(myCurDir.getParentFile());
	}
	
	public void setFilter(String filterTypes){
		if (!myFilterTypes.equals(filterTypes)){
			myFilterTypes = filterTypes;
			step(myCurDir); 
		}
	}
		
	private void init(File file){
		myCurDir = file;
		myProgressDialog.show();
		myProvider.setPreferences(file, myFilterTypes);
		myCurFilterThread = new Thread(null, myProvider, "MagentoBackground");
		myCurFilterThread.start();
	}
	
	public void step(File file) {
		if (file.isDirectory()){
			myCurDir = file;
			myProgressDialog.show();
			myCurFilterThread.interrupt();
			myProvider.setPreferences(file, myFilterTypes);
			myCurFilterThread = new Thread(null, myProvider, "MagentoBackground");
			myCurFilterThread.start();
		}
		else{
			Toast.makeText(myParent, IsNamed.TOAST_IT_IS_FILE, Toast.LENGTH_SHORT).show();
		}
	}

	private ProgressDialog creatProgressDialog(){
	  	ProgressDialog pd = new ProgressDialog(myParent);
		pd.setTitle(IsNamed.PROGRESS_DIALOG_TITLE);
		pd.setMessage(IsNamed.PROGRESS_DIALOG_MESSAGE);
		return pd; 
	}

	
	
	class FileAdapter extends ArrayAdapter<File>{
		private Context myContext;
		private List<File> myFileOrders;
		
		public FileAdapter(Context context, int textViewResourceId, List<File> fileOrders) {
			super(context, textViewResourceId);
			myContext = context;
			myFileOrders = fileOrders;
		}
		
	    public View getView(int position, View convertView, ViewGroup parent) {
	        // Inflate a view template
	        if (convertView == null) {
	            LayoutInflater layoutInflater = ((Activity) myContext).getLayoutInflater();
	            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
	        }
	        
	        TextView fileName = (TextView) convertView.findViewById(R.id.f_name);
	        TextView filePath = (TextView) convertView.findViewById(R.id.f_path);
	        ImageView fileIcon = (ImageView) convertView.findViewById(R.id.f_icon);

	        File file = myFileOrders.get(position);
	        fileName.setText(file.getName());
	        filePath.setText(file.getPath());
	        if (file.isDirectory())
		        fileIcon.setImageResource(R.drawable.blue_folder);
	        else
	        	fileIcon.setImageResource(R.drawable.file);
	        return convertView;
	    }
	}
}



 