package org.rotiv.memolina;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;

public class MemolinaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TableLayout tabuleiro = (TableLayout) findViewById(R.id.tabuleiro);
		tabuleiro.addView(new Carta(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
