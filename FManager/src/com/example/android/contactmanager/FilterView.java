package com.example.android.contactmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class FilterView extends Activity{
	// **************************************************************************//
	// Enums //
	// **************************************************************************//

	// **************************************************************************//
	// Members //
	// **************************************************************************//
	
	List<CheckBox> myListChBox = new ArrayList<CheckBox>();
	
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filter);

		myListChBox.add((CheckBox) findViewById(R.id.checkboxTypePDF));
		myListChBox.add((CheckBox) findViewById(R.id.checkboxTypeDOC));
		
		Button cancelButton = (Button) findViewById(R.id.cancelButton);
		Button okButton = (Button) findViewById(R.id.okButton);

		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String filterStr = "";
				for (CheckBox ch : myListChBox){
					if (ch.isChecked())
						filterStr += " " + ch.getText().toString();
				}
				setResult(1, new Intent(filterStr));
				finish();
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

	// **************************************************************************//
	// Private Statics //
	// **************************************************************************//

	// **************************************************************************//
	// Internal Classes //
	// **************************************************************************//
	
	
}
