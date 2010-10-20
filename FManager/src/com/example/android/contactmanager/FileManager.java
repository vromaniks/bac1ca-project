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

package com.example.android.contactmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
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
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_manager);

		// Obtain handles to UI objects
		Button mkDirButton = (Button) findViewById(R.id.mkDirButton);
		Button delButton = (Button) findViewById(R.id.delButton);
		Button copyButton = (Button) findViewById(R.id.copyButton);
		Button moveButton = (Button) findViewById(R.id.moveButton);
		Button backButton = (Button) findViewById(R.id.backButton);
		Button openButton = (Button) findViewById(R.id.openButton);
		ListView fileList1 = (ListView) findViewById(R.id.fileList1);
	
		myFileListView = new FileListView(this, fileList1);

		mkDirButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		delButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		copyButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		moveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myFileListView.goAtBack();
			}
		});
		
		
		openButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// DELETE LATER
			}
		});

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

	// **************************************************************************//
	// Public Statics //
	// **************************************************************************//
	public static final String TAG = "FileManager";

	// **************************************************************************//
	// Private Statics //
	// **************************************************************************//

	// **************************************************************************//
	// Internal Classes //
	// **************************************************************************//
}
