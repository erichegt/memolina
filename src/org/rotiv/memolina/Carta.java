package org.rotiv.memolina;

import android.content.Context;
import android.widget.ImageView;

public class Carta extends ImageView {
	private ImageView back;
	private ImageView front;
	
	private boolean turned;
	private boolean enabledToPlay;				
	private int imageId;
	private int positionId;
	
	private int positionX;
	
	public Carta(Context context) {
		super(context);
		setImageDrawable(context.getResources().getDrawable(R.drawable.ic_card_back));
	}
}
