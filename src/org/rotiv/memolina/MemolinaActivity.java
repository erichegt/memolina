package org.rotiv.memolina;

import org.rotiv.memolina.view.Tabuleiro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow.LayoutParams;

public class MemolinaActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout jogo = (RelativeLayout) findViewById(R.id.jogo);
		LinearLayout tempo = (LinearLayout) findViewById(R.id.tempo);

		Tabuleiro tabuleiro = new Tabuleiro(this);
		tabuleiro.getTableLayout().setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		tabuleiro.getTableLayout().setLayoutParams(lp);
		
		//Fase 1:
		tabuleiro.setRows(4);
		tabuleiro.setCols(4);
		tabuleiro.setEqualCards(2);
		tabuleiro.constroiTabuleiro();
				
		jogo.addView(tabuleiro.getTableLayout());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	

}