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

package ru.chmv.fmanager.views;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public final class FileManager extends Activity {
	private FileListView myFileListView;
	
	public static String LOG = "FileManager";
	public static File ROOT_DIR = new File("/");
	public static File SDCARD_DIR = Environment.getExternalStorageDirectory();
	public static File HOME_DIR = new File(SDCARD_DIR.getPath() + "/home");
	
	/**
	 * Called when the activity is first created. Responsible for initializing
	 * the UI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(LOG, "onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_manager);

		// Obtain handles to UI objects
		ImageButton fbhomeButton = (ImageButton) findViewById(R.id.fmanagerFBHomeButton);
		ImageButton cardButton = (ImageButton) findViewById(R.id.fmanagerCardButton);
		ImageButton rootButton = (ImageButton) findViewById(R.id.fmanagerRootButton);
		ImageButton filterButton = (ImageButton) findViewById(R.id.fmanagerFilterButton);
		ImageButton backButton = (ImageButton) findViewById(R.id.fmanagerBackButton);
		
		ListView fileList = (ListView) findViewById(R.id.fileList1);
		myFileListView = new FileListView(this, fileList);
		
		fbhomeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myFileListView.step(HOME_DIR);
			}
		});
		
		cardButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myFileListView.step(SDCARD_DIR);
			}
		});
		
		rootButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myFileListView.step(ROOT_DIR);
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

    private void launchFilterView() {
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
}
