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
	private Button m_mkDirButton;

	private Button m_delButton;

	private Button m_copyButton;

	private Button m_moveButton;

	private Button m_openButton;

	private FileListView m_simpleListView1;

	private FileListView m_simpleListView2;

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
		setContentView(R.layout.contact_manager);

		// Obtain handles to UI objects
		m_mkDirButton = (Button) findViewById(R.id.mkDirButton);
		m_delButton = (Button) findViewById(R.id.delButton);
		m_copyButton = (Button) findViewById(R.id.copyButton);
		m_moveButton = (Button) findViewById(R.id.moveButton);
		m_openButton = (Button) findViewById(R.id.openButton);
		ListView fileList1 = (ListView) findViewById(R.id.fileList1);
		ListView fileList2 = (ListView) findViewById(R.id.fileList2);

		m_simpleListView1 = new FileListView(this, fileList1);
		m_simpleListView2 = new FileListView(this, fileList2);

		m_mkDirButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		m_delButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		m_copyButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		m_moveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO
			}
		});

		m_openButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (m_simpleListView1.isActive())
					m_simpleListView1.goAtCurrentDir();
				else
					m_simpleListView2.goAtCurrentDir();
			}
		});

		m_simpleListView1.getListView().setOnTouchListener(
				new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						m_simpleListView1.setActive(true);
						m_simpleListView2.setActive(false);
						return false;
					}
				});

		m_simpleListView2.getListView().setOnTouchListener(
				new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						m_simpleListView2.setActive(true);
						m_simpleListView1.setActive(false);
						return false;
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
