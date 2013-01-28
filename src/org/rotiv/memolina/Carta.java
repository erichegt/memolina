package org.rotiv.memolina;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Carta extends FrameLayout {
	private ImageView back;
	private ImageView front;

	private boolean isFirstImage = true;

//	private boolean turned;
//	private boolean enabledToPlay;				
//	private int imageId;
//	private int positionId;
//	private int positionX;
	
	public Carta(Context context) {
		super(context);
		back = new ImageView(context);
		front = new ImageView(context);
		
		
//	}
//	
//	public Carta build(int desenho, int imageId, int positionId) {
		//PQP!!
		
//		back = (ImageView) findViewById(R.id.ImageView01);
//		front = (ImageView) findViewById(R.id.ImageView02);
		back.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_card_back));
		front.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_card10));
		front.setVisibility(View.GONE);
		
		addView(back);
		addView(front);

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
		
//		this.imageId = imageId;
//		this.positionId = positionId;
		
//		return new Carta(this.getContext());
	}
	
	private void applyRotation(float start, float end) {
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
}
