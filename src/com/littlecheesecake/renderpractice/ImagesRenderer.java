package com.littlecheesecake.renderpractice;

import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.Texture;
import rajawali.primitives.ScreenQuad;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImagesRenderer extends RajawaliRenderer{
	private ATexture[] mTextures;
	private ScreenQuad mScreenQuad;
	private int mFrameCount;
	private Material mMaterial;
	private final static int NUM_TEXTURES = 80;
	
	public ImagesRenderer(Context context) {
		super(context);
	}
	
	protected void initScene(){
		if(mTextureManager != null){
			mTextureManager.reset();
		}
		if(mMaterial != null){
			mMaterial.getTextureList().clear();
		}
		
		getCurrentScene().setBackgroundColor(0xffffff);
		
		mMaterial = new Material();
		
		mScreenQuad = new ScreenQuad();
		mScreenQuad.setMaterial(mMaterial);
		addChild(mScreenQuad);
		
		/**
		 * load the textures to a list at the beginning
		 */
		if(mTextures == null){
			mTextures = new ATexture[NUM_TEXTURES];
		}
		mFrameCount = 0;
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPurgeable = true;
		options.inInputShareable = true;
		
		for(int i = 1; i <= NUM_TEXTURES; ++i){
			int resourceId = mContext.getResources().getIdentifier(
					i < 10 ? "m0" + i : "m" + i, "drawable", "com.littlecheesecake.renderpractice");
			
			Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceId, options);
			
			ATexture texture = new Texture("bm" + i, bitmap);
			texture.setMipmap(false);
			texture.shouldRecycle(true);
			mTextures[i-1] = mTextureManager.addTexture(texture);
		}
		try{
			mMaterial.addTexture(mTextures[0]);
			mMaterial.setColorInfluence(0);
		}catch(TextureException e){
			e.printStackTrace();
		}
	}
	
	public void onDrawFrame(GL10 glUnused){
		super.onDrawFrame(glUnused);
		
		//--get the texture info list and remove the previous textureinfo object
		mMaterial.getTextureList().remove(
				mTextures[mFrameCount++ % NUM_TEXTURES]);
		//--add a new textureinfo object
		mMaterial.getTextureList().add(
				mTextures[mFrameCount % NUM_TEXTURES]);
	}

}
