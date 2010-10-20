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
	private Boolean m_active = false;

	private Activity m_context;

	private ListView m_listView;

	private String m_curDir = ".";

	private String m_curFile = ".";

	private List<String> m_history = new ArrayList<String>();

	// **************************************************************************//
	// Constructors //
	// **************************************************************************//
	public FileListView(Activity context, ListView listView) {
		m_context = context;
		m_listView = listView;
		m_history.add(m_curDir);
		goAtDir(m_curDir);

		m_listView.setFocusableInTouchMode(true);

		m_listView.setTextFilterEnabled(true);
		m_listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO - correct selection
				view.setSelected(true);
				m_curFile = ((TextView) view).getText().toString();
				if (m_curFile.equals("..")) {
					m_history.add(m_curFile);
					goAtDir(m_curDir + "/" + m_curFile);
				}
			}
		});

	}

	// **************************************************************************//
	// Getters //
	// **************************************************************************//
	public ListView getListView() {
		return m_listView;
	}

	// **************************************************************************//
	// Setters //
	// **************************************************************************//
	public void setActive(Boolean active) {
		m_active = active;
	}

	// **************************************************************************//
	// Publics //
	// **************************************************************************//
	public Boolean isActive() {
		return m_active;
	}

	public void goAtCurrentDir() {
		m_history.add(m_curFile);
		goAtDir(m_curDir + "/" + m_curFile);
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
		if (path.endsWith("..") && m_history.size() == 2) {
			path = path.substring(0, path.length() - 3);
			m_history.remove(m_history.size() - 1);
			return;
		}
		if (path.endsWith("..")) {
			path = path.substring(0, path.length() - 3);
			m_history.remove(m_history.size() - 1);
			int length = m_history.remove(m_history.size() - 1).length() + 1;
			path = path.substring(0, path.length() - length);
		}
		goAtDir(new File(path));
	}

	private void goAtDir(File file) {
		if (file.isDirectory()) {
			m_curDir = file.getPath();
			int l = file.list().length;
			String files[] = new String[l + 1];
			files[0] = "..";
			System.arraycopy(file.list(), 0, files, 1, file.list().length);
			m_listView.setAdapter(new ArrayAdapter<String>(m_context,
					R.layout.list_item, files));
		} else {
			m_history.remove(m_history.size() - 1);
		}
	}

	// **************************************************************************//
	// Public Statics //
	// **************************************************************************//

	// **************************************************************************//
	// Private Statics //
	// **************************************************************************//

	// **************************************************************************//
	// Internal Classes //
	// **************************************************************************//

}
