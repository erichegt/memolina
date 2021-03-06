package org.rotiv.memolina.view;

import org.rotiv.memolina.R;
import org.rotiv.memolina.controller.Flip3dAnimation;
import org.rotiv.memolina.model.ObservadorDeCartas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class Carta extends ImageView{
	private int padding = 2;
	private boolean front;
	private int imageId;
	private int size;
	private int positionId;
	private Context ctxt;
	private ObservadorDeCartas observador;

	public Carta(Context ctxt) {
		super(ctxt);
		this.ctxt = ctxt;
		this.setImageDrawable(ctxt.getResources().getDrawable(R.drawable.ic_card99));
		this.size = this.getDrawable().getMinimumWidth();
		
		//padding
		this.setPadding(padding, padding, padding, padding);
		
		this.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				view.setEnabled(false);
				rotacionaCarta();
				Carta.this.setFront(true);
				observador.lidaComASelecaoDa(Carta.this);
//				front = !front;
			}
		});
	}
	
	public void setObservador(ObservadorDeCartas observador) {
		this.observador = observador;
		
	}
	
	public void rotacionaCarta() {
		Flip3dAnimation anim = new Flip3dAnimation(0f, 90f, size/2.0f, size/2.0f);
		anim.setInterpolator(new AccelerateInterpolator());
//		anim.setRepeatCount(Animation.INFINITE);
//		anim.setFillAfter(true);
		anim.setDuration(250);

		// Start animating the image
		this.startAnimation(anim);
		anim.setAnimationListener(new AnimationListener() {
		    public void onAnimationStart(Animation animation) {}
		    public void onAnimationRepeat(Animation animation) {}
		    public void onAnimationEnd(Animation animation) {
		    	if (front) {
		    		Carta.this.setImageDrawable(Carta.this.getCardDrawable(Carta.this.getImageId()));
		    	} else {
		    		Carta.this.setImageDrawable(Carta.this.getCardDrawable(99));
		    	}
		    	Flip3dAnimation anim2 = new Flip3dAnimation(90f, 0f, size/2.0f, size/2.0f);
				anim2.setInterpolator(new AccelerateInterpolator());
//				anim.setRepeatCount(Animation.INFINITE);
				anim2.setDuration(250);

				// Start animating the image
				Carta.this.startAnimation(anim2);
				front = !front;
		    }
		});
	}
	
	public Drawable getCardDrawable(int codigo) {
	    String uri = "drawable/ic_card"+codigo;

	    // int imageResource = R.drawable.icon;
	    int imageResource = ctxt.getResources().getIdentifier(uri, null, ctxt.getPackageName());
	    Drawable image = ctxt.getResources().getDrawable(imageResource);
	    
	    return image;
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
	public void setFront(boolean front) {
		this.front = front;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.imageId);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Carta) {
			if (this.imageId == ((Carta) o).imageId) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
