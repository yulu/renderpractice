package com.littlecheesecake.renderpractice;

import java.util.Stack;

import rajawali.animation.Animation3D.RepeatMode;
import rajawali.animation.RotateAnimation3D;
import rajawali.animation.TranslateAnimation3D;
import rajawali.materials.Material;
import rajawali.math.vector.Vector3;
import rajawali.math.vector.Vector3.Axis;
import rajawali.primitives.Line3D;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.Color;

public class ColorLineRenderer extends RajawaliRenderer{

	public ColorLineRenderer(Context context) {
		super(context);
	}
	
	protected void initScene(){
		getCurrentCamera().setPosition(0, 0, 10);
		getCurrentCamera().setLookAt(0, 0, 0);
		
		Stack<Vector3> points = new Stack<Vector3>();
		int[] colors = new int[2000];
		int colorCount = 0;
		//define each vertex
		for(int i = -1000; i < 1000; i++){
			float j = i * .5f;
			Vector3 v = new Vector3();
			v.x = (float) (Math.cos(j * .4f));
			v.y = (float) (Math.sin(j * .3f));
			v.z = j * .01f;
			points.add(v);
			colors[colorCount++] = Color.argb(255, 
					(int) (190.f * Math.sin(j)),
					(int) (190.f * Math.cos(j * .3f)),
					(int) (190.f * Math.sin(j * 2) * Math.cos(j)));
		}
		
		Line3D line = new Line3D(points, 1, colors);
		Material material = new Material();
		material.useVertexColors(true);
		line.setMaterial(material);
		getCurrentScene().addChild(line);
		
		RotateAnimation3D lineAnim = new RotateAnimation3D(Axis.Y, 359);
		lineAnim.setDuration(10000);
		lineAnim.setRepeatMode(RepeatMode.INFINITE);
		lineAnim.setTransformable3D(line);
		registerAnimation(lineAnim);
		lineAnim.play();
		
		TranslateAnimation3D camAnim = new TranslateAnimation3D(
				new Vector3(0, 0, 10), new Vector3(0, 0, -10));
		camAnim.setDuration(12000);
		camAnim.setRepeatMode(RepeatMode.REVERSE_INFINITE);
		camAnim.setTransformable3D(getCurrentCamera());
		registerAnimation(camAnim);
		camAnim.play();
	}

}
