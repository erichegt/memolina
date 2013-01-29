package org.rotiv.memolina.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rotiv.memolina.Carta;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Tabuleiro {
	private TableLayout tableLayout;
	private Context ctxt;
	
	private List<Integer> ids;
	private List<Integer> shuffledIds;
	private List<Carta> shuffledCards;
	
	private int cardSize;
	private int nCards;

	private int cols;
	private int rows;
	private int equalCards;

	public Tabuleiro(Context ctxt) {
		this.ctxt = ctxt;
		this.tableLayout = new TableLayout(ctxt);
//		cardSize = ctxt.getResources().getDrawable(R.drawable.ic_card_back).getMinimumWidth();
		cardSize = 40;
		ids = new ArrayList<Integer>();
		shuffledIds = new ArrayList<Integer>();
		shuffledCards = new ArrayList<Carta>();
	}
	
	public void constroiTabuleiro() {
		this.setnCards(getCols()*getRows());
		
		// Cards:
		for (int n = 0; n < nCards / equalCards; n++) {
			for (int z = 0; z < equalCards; z++) {
				ids.add(n);
				shuffledIds.add(n);
			}
		}
		Collections.shuffle(shuffledIds);

		// Log.i("ids:",ids.toString());
		// Log.i("shuffledIds",shuffledIds.toString());

		// Shuffle IDs:
		Carta carta;
		TableRow linha = null;
		for (int a = 0; a < nCards; a++) {
			carta = new Carta(ctxt, shuffledIds.get(a), shuffledIds.get(a), a);
			shuffledCards.add(carta);
			if (a % cols == 0) {
				linha = new TableRow(ctxt);
				tableLayout.addView(linha);
			}
			linha.addView(carta.getFrameLayout());
		}
	}
	
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getEqualCards() {
		return equalCards;
	}

	public void setEqualCards(int equalCards) {
		this.equalCards = equalCards;
	}

	public TableLayout getTableLayout() {
		return tableLayout;
	}

	public List<Integer> getIds() {
		return ids;
	}
	public List<Integer> getShuffledIds() {
		return shuffledIds;
	}

	public List<Carta> getShuffledCards() {
		return shuffledCards;
	}
	public int getnCards() {
		return nCards;
	}
	
	public void setnCards(int nCards) {
		this.nCards = nCards;
	}
}
