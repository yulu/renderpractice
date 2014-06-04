package com.littlecheesecake.renderpractice;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.RajawaliActivity;
import rajawali.renderer.RajawaliRenderer;
import rajawali.util.RajLog;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends RajawaliActivity {
	
	protected RajawaliRenderer mRenderer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//create renderer
		mRenderer = createRenderer();
		if(mRenderer == null)
			mRenderer = new NullRenderer(this);
		
		mRenderer.setSurfaceView(mSurfaceView);
		setRenderer(mRenderer);
		
	}
	
	private TestRenderer createRenderer(){
		return new TestRenderer(this);
	}

	
	private static final class NullRenderer extends RajawaliRenderer{
		public NullRenderer(Context context){
			super(context);
			RajLog.w(this + ": Fragment created without renderer!");
		}
		
		@Override
		public void onSurfaceDestroyed(){
			stopRendering();
		}
	}

}