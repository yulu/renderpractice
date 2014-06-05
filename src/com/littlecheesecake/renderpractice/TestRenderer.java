package com.littlecheesecake.renderpractice;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Object3D;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.Texture;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;

/**
 * This is a test renderer:
 * render a sphere with world map
 * TODO: add gesture control later
 * @author yulu
 *
 */
public class TestRenderer extends RajawaliRenderer{

	private DirectionalLight mLight;
	private Object3D mSphere;
	
	public TestRenderer(Context context) {
		super(context);
	}
	
	protected void initScene(){
		mLight = new DirectionalLight(1f, 0.2f, -1.0f);
		mLight.setColor(1.0f, 1.0f, 1.0f);
		mLight.setPower(2);
		
		getCurrentScene().addLight(mLight);
		
		try{
			Material material = new Material();
			material.addTexture(new Texture("earthColors", R.drawable.world_map_1));
			material.setColorInfluence(0);
			mSphere = new Sphere(1, 24, 24);
			mSphere.setMaterial(material);
			getCurrentScene().addChild(mSphere);
		}catch(TextureException e){
			e.printStackTrace();
		}
		
		getCurrentCamera().setZ(5);
	}
	
	public void onDrawFrame(GL10 glUnused){
		super.onDrawFrame(glUnused);
		mSphere.setRotY(mSphere.getRotY() + 1);
	}

}
