package org.rotiv.memolina;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class Carta {
	private ImageView back;
	public ImageView getBack() {
		return back;
	}

	public ImageView getFront() {
		return front;
	}

	private ImageView front;

	private boolean isFirstImage = true;

	private int padding = 2;
	private boolean turned;
	private boolean enabledToPlay;				
	private int imageId;
	public int getImageId() {
		return imageId;
	}

	private int positionId;
	private int positionX;

	private Context ctxt;
	
	public Carta(Context ctxt, int codFigura, int imageId, int positionId) {
		this.ctxt = ctxt;
		back = new ImageView(ctxt);
		front = new ImageView(ctxt);
		back.setImageDrawable(ctxt.getResources().getDrawable(R.drawable.ic_card_back));
		front.setImageDrawable(getCardDrawable(codFigura));
		front.setVisibility(View.GONE);
		
		//padding
		back.setPadding(padding, padding, padding, padding);
		front.setPadding(padding, padding, padding, padding);
		
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (isFirstImage) {
					applyRotation(0, 90);
					isFirstImage = !isFirstImage;
				} else {
					applyRotation(0, -90);
					isFirstImage = !isFirstImage;
				}
			}
		});
		
		this.imageId = imageId;
		this.positionId = positionId;
	}
	
	public void applyRotation(float start, float end) { //TODO PRIVATE!!!!
		// Find the center of image
		final float centerX = back.getWidth() / 2.0f;
		final float centerY = back.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(isFirstImage, back,
				front));

		if (isFirstImage) {
			back.startAnimation(rotation);
		} else {
			front.startAnimation(rotation);
		}
	}
	
	private Drawable getCardDrawable(int codigo) {
	    String uri = "drawable/ic_card"+codigo;

	    // int imageResource = R.drawable.icon;
	    int imageResource = ctxt.getResources().getIdentifier(uri, null, ctxt.getPackageName());
	    Drawable image = ctxt.getResources().getDrawable(imageResource);
	    
	    return image;
	}
}
