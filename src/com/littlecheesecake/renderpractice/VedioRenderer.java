package com.littlecheesecake.renderpractice;

import javax.microedition.khronos.opengles.GL10;

import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.textures.VideoTexture;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.primitives.Plane;
import rajawali.primitives.ScreenQuad;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.view.Surface;

public class VedioRenderer extends RajawaliRenderer{
	private MediaPlayer mMediaPlayer;
	private DirectionalLight mLight;
	private SurfaceTexture mTexture;
	private VideoTexture mVideoTexture;

	public VedioRenderer(Context context) {
		super(context);
	}
	
	protected void initScene() {
	    super.initScene();
	    mLight = new DirectionalLight(0, 0, 1);
	    getCurrentCamera().setPosition(0, 0, -17);
   
	    //TextureInfo tInfo = mTextureManager.addVideoTexture();
	    mVideoTexture = new VideoTexture("sintelTrailer", mMediaPlayer);
	    mTexture = new SurfaceTexture(mVideoTexture.getUniformHandle());
   
	    mMediaPlayer = MediaPlayer.create(getContext(), R.raw.sintel_trailer_480p);
	    mMediaPlayer.setSurface(new Surface(mTexture));

	    Material material = new Material();
	    material.setColorInfluence(0);
		try {
			material.addTexture(mVideoTexture);
		} catch (TextureException e) {
			e.printStackTrace();
		}

		ScreenQuad mScreenQuad = new ScreenQuad();
		mScreenQuad.setMaterial(material);
	    addChild(mScreenQuad);
	    
	    mMediaPlayer.start();
	}

	public void onDrawFrame(GL10 glUnused) {
	    // -- important! update every frame
	    mTexture.updateTexImage();
	    super.onDrawFrame(glUnused);
	}
	
	public void onVisibilityChanged(boolean visible) {
		super.onVisibilityChanged(visible);
		if (!visible)
			if (mMediaPlayer != null)
				mMediaPlayer.pause();
			else if (mMediaPlayer != null)
				mMediaPlayer.start();
	}

	public void onSurfaceDestroyed() {
		super.onSurfaceDestroyed();
		mMediaPlayer.stop();
		mMediaPlayer.release();
	}

}
