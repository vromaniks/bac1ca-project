/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.prochtenie.fmanager.views;

import ru.prochtenie.fmanager.views.R;
import ru.prochtenie.fmanager.views.R.id;
import ru.prochtenie.fmanager.views.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public final class FileManager extends Activity {

	// **************************************************************************//
	// Enums //
	// **************************************************************************//

	// **************************************************************************//
	// Members //
	// **************************************************************************//

	FileListView myFileListView;
	
	// **************************************************************************//
	// Constructors //
	// **************************************************************************//

	// **************************************************************************//
	// Getters //
	// **************************************************************************//

	// **************************************************************************//
	// Setters //
	// **************************************************************************//

	// **************************************************************************//
	// Publics //
	// **************************************************************************//
	/**
	 * Called when the activity is first created. Responsible for initializing
	 * the UI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(FILE_MANAGER_LOG_TAG, "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_manager);

		// Obtain handles to UI objects
		ImageButton fbhomeButton = (ImageButton) findViewById(R.id.fmanagerFBHomeButton);
		ImageButton cardButton = (ImageButton) findViewById(R.id.fmanagerCardButton);
		ImageButton rootButton = (ImageButton) findViewById(R.id.fmanagerRootButton);
		ImageButton filterButton = (ImageButton) findViewById(R.id.fmanagerFilterButton);
		ImageButton backButton = (ImageButton) findViewById(R.id.fmanagerBackButton);

		Button okButton = (Button) findViewById(R.id.fmanagerOkButton);
		Button cancelButton = (Button) findViewById(R.id.fmanagerOkButton);
		
		ListView fileList = (ListView) findViewById(R.id.fileList1);
		myFileListView = new FileListView(this, fileList);

		
		fbhomeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
				myFileListView.goAtDir(FB_HOME_DIR);
			}
		});
		
		cardButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
				myFileListView.goAtDir(SDCARD_DIR);
			}
		});
		
		rootButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
				myFileListView.goAtDir(ROOT_DIR);
			}
		});
		
		filterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				launchFilterView();
			}
		});
		
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myFileListView.goAtBack();
			}
		});
		
	}

	// **************************************************************************//
	// Abstracts //
	// **************************************************************************//

	// **************************************************************************//
	// Protected //
	// **************************************************************************//
    protected void launchFilterView() {
        Intent i = new Intent(this, FilterView.class);
        i.setAction(myFileListView.getFilterTypes());
        startActivityForResult(i, 1);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null)
			myFileListView.setFilter(data.getAction());
	}
    
	// **************************************************************************//
	// Privates //
	// **************************************************************************//

	// **************************************************************************//
	// Public Statics //
	// **************************************************************************//
	public static String FILE_MANAGER_LOG_TAG = "FileManager";
	
	// **************************************************************************//
	// Private Statics //
	// **************************************************************************//
	private static String FB_HOME_DIR = "./sdcard/Book";
	private static String ROOT_DIR = ".";
	private static String SDCARD_DIR = "./sdcard";
	
	// **************************************************************************//
	// Internal Classes //
	// **************************************************************************//
}
