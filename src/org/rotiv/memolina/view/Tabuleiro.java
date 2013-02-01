package org.rotiv.memolina.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.rotiv.memolina.model.ObservadorDeCartas;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Tabuleiro implements ObservadorDeCartas{
	private TableLayout tableLayout;
	private Context ctxt;
	
	private List<Integer> ids;
	private List<Integer> shuffledIds;
	private List<Carta> shuffledCards;
	
	private int nCards;

	private int cols;
	private int rows;
	private int equalCards;
	
	private List<Carta> buffer;
	
	public Tabuleiro(Context ctxt) {
		this.ctxt = ctxt;
		this.tableLayout = new TableLayout(ctxt);
		
		ids = new ArrayList<Integer>();
		shuffledIds = new ArrayList<Integer>();
		shuffledCards = new ArrayList<Carta>();
	}
	
	public void constroiTabuleiro() {
		buffer = new ArrayList<Carta>();
		
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
			carta = new Carta(ctxt);
			carta.setObservador(this);
			carta.setImageId(shuffledIds.get(a));
			carta.setPositionId(a);
			shuffledCards.add(carta);
			if (a % cols == 0) {
				linha = new TableRow(ctxt);
				tableLayout.addView(linha);
			}
			linha.addView(carta);
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

	@Override
	public void lidaComASelecaoDa(Carta c) {
		buffer.add(c);
		//LOGICA DE VERIFICAO PARA DUAS JOGADAS (equalCards == 2)
		if (buffer.size() > 2) {
			Carta c1 = buffer.get(0);
			Carta c2 = buffer.get(1);
			
//			Log.i("RESULTADO", buffer.toString());
			
			if (c1.equals(c2)) {
//				Log.i("RESULTADO", "ACERTOU!!!");
				c1.setEnabled(false);
				c2.setEnabled(false);
				c1.setFront(true);
				c2.setFront(true);
			} else {
//				Log.i("RESULTADO:", "ERROU :(");
				c1.rotacionaCarta();
				c2.rotacionaCarta();
				c1.setEnabled(true);
				c2.setEnabled(true);
//				c1.setFront(false);
//				c2.setFront(false);						
			}
			buffer.remove(0);
			buffer.remove(0);
		}
	}
}
