package com.littlecheesecake.renderpractice;

import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.textures.ATexture.TextureException;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;

public class SkyBoxRenderer extends RajawaliRenderer{

	public SkyBoxRenderer(Context context) {
		super(context);
	}
	
	protected void initScene(){
		getCurrentCamera().setFarPlane(1000);
		
		try{
			getCurrentScene().setSkybox(R.drawable.posx,
							R.drawable.negx,
							R.drawable.posy,
							R.drawable.negy,
							R.drawable.posz,
							R.drawable.negz);
		}catch(TextureException e){
			e.printStackTrace();
		}
		
	}
	
	public void onDrawFrame(GL10 glUnused){
		super.onDrawFrame(glUnused);
		getCurrentCamera().setRotY(getCurrentCamera().getRotY() - .2f);
	}

}
