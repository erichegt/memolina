package org.rotiv.memolina;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Carta extends FrameLayout {
	private ImageView back;
	private ImageView front;
	

	private boolean isFirstImage = true;

	
	private boolean turned;
	private boolean enabledToPlay;				
	private int imageId;
	private int positionId;
	
	private int positionX;
	
	public Carta(Context context) {
		super(context);
//		setImageDrawable(context.getResources().getDrawable(R.drawable.ic_card_back));
	}
	
	public Carta build(int desenho, int imageId, int positionId) {
		back = new ImageView(this.getContext());
		back.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_card_back));
		front = new ImageView(getContext());
		front.setImageDrawable(this.getContext().getResources().getDrawable(desenho));			
		
		this.imageId = imageId;
		this.positionId = positionId;
		
		return new Carta(this.getContext());
	}
}
