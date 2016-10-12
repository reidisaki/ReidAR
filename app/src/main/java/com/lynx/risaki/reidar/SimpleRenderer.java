package com.lynx.risaki.reidar;/*
 *  SimpleRenderer.java
 *  ARToolKit5
 *
 *  Disclaimer: IMPORTANT:  This Daqri software is supplied to you by Daqri
 *  LLC ("Daqri") in consideration of your agreement to the following
 *  terms, and your use, installation, modification or redistribution of
 *  this Daqri software constitutes acceptance of these terms.  If you do
 *  not agree with these terms, please do not use, install, modify or
 *  redistribute this Daqri software.
 *
 *  In consideration of your agreement to abide by the following terms, and
 *  subject to these terms, Daqri grants you a personal, non-exclusive
 *  license, under Daqri's copyrights in this original Daqri software (the
 *  "Daqri Software"), to use, reproduce, modify and redistribute the Daqri
 *  Software, with or without modifications, in source and/or binary forms;
 *  provided that if you redistribute the Daqri Software in its entirety and
 *  without modifications, you must retain this notice and the following
 *  text and disclaimers in all such redistributions of the Daqri Software.
 *  Neither the name, trademarks, service marks or logos of Daqri LLC may
 *  be used to endorse or promote products derived from the Daqri Software
 *  without specific prior written permission from Daqri.  Except as
 *  expressly stated in this notice, no other rights or licenses, express or
 *  implied, are granted by Daqri herein, including but not limited to any
 *  patent rights that may be infringed by your derivative works or by other
 *  works in which the Daqri Software may be incorporated.
 *
 *  The Daqri Software is provided by Daqri on an "AS IS" basis.  DAQRI
 *  MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 *  THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 *  FOR A PARTICULAR PURPOSE, REGARDING THE DAQRI SOFTWARE OR ITS USE AND
 *  OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 *  IN NO EVENT SHALL DAQRI BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 *  MODIFICATION AND/OR DISTRIBUTION OF THE DAQRI SOFTWARE, HOWEVER CAUSED
 *  AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 *  STRICT LIABILITY OR OTHERWISE, EVEN IF DAQRI HAS BEEN ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 *
 *  Copyright 2015 Daqri, LLC.
 *  Copyright 2011-2015 ARToolworks, Inc.
 *
 *  Author(s): Julian Looser, Philip Lamb
 *
 */

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.Cube;

import android.content.Context;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A very simple Renderer that adds a marker and draws a cube on it.
 */
public class SimpleRenderer extends ARRenderer {

    private Cube cube = new Cube(30.0f, 0.0f, 0.0f, 20.0f);
    private Context mContext;

    private Square mSquare;
    private int willId = -1;
    private int youId = -1;
    private int mId = -1;
    private int mMId = -1;
    private int mStoopidId = -1;

    public SimpleRenderer(Context context) {
        mContext = context;
        mSquare = new Square(context);
    }

    /**
     * Markers can be configured here.
     */
    @Override
    public boolean configureARScene() {

        willId = ARToolKit.getInstance().addMarker("single;Data/bb.patt;80");
        youId = ARToolKit.getInstance().addMarker("single;Data/paris.patt;80");
        mId = ARToolKit.getInstance().addMarker("single;Data/ring.patt;80");
//        willId = ARToolKit.getInstance().addMarker("multi;Data/multi/marker.dat");
//        youId = ARToolKit.getInstance().addMarker("multi;Data/multi/marker2.dat");
//        mId = ARToolKit.getInstance().addMarker("multi;Data/multi/marker3.dat");
        if (willId < 0) {
            return false;
        }

        Log.i("reid", "read markers we're good:" + willId + " youId: " + youId + " mId: " + mId);
        return true;
    }

//    @Override
//    public void onDrawFrame(GL10 gl) {
//        // clear Screen and Depth Buffer
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//
//        // Reset the Modelview Matrix
//        gl.glLoadIdentity();
//
//        // Drawing
//        gl.glTranslatef(0.0f, 0.0f, -5.0f);        // move 5 units INTO the screen
//        // is the same as moving the camera 5 units away
////		gl.glScalef(0.5f, 0.5f, 0.5f);			// scale the square to 50%
//        // otherwise it will be too large
//        mSquare.draw(gl);                        // Draw the triangle
//    }
//
//    @Override
//    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        if (height == 0) {                        //Prevent A Divide By Zero By
//            height = 1;                        //Making Height Equal One
//        }
//
//        gl.glViewport(0, 0, width, height);    //Reset The Current Viewport
//        gl.glMatrixMode(GL10.GL_PROJECTION);    //Select The Projection Matrix
//        gl.glLoadIdentity();                    //Reset The Projection Matrix
//
//        //Calculate The Aspect Ratio Of The Window
//        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
//
//        gl.glMatrixMode(GL10.GL_MODELVIEW);    //Select The Modelview Matrix
//        gl.glLoadIdentity();                    //Reset The Modelview Matrix
//    }
//
//    @Override
//    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        // Load the texture for the square
//        gl.glEnable(GL10.GL_TEXTURE_2D);            //Enable Texture Mapping ( NEW )
//        gl.glShadeModel(GL10.GL_SMOOTH);            //Enable Smooth Shading
//        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    //Black Background
//        gl.glClearDepthf(1.0f);                    //Depth Buffer Setup
//        gl.glEnable(GL10.GL_DEPTH_TEST);            //Enables Depth Testing
//        gl.glDepthFunc(GL10.GL_LEQUAL);            //The Type Of Depth Testing To Do
//
//        //Really Nice Perspective Calculations
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
//    }

    /**
     * Override the draw function from ARRenderer.
     */
    @Override
    public void draw(GL10 gl) {
        mSquare.loadGLTexture(gl, mContext);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//
//        // Apply the ARToolKit projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);
//
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        // If the marker is visible, apply its transformation, and draw a cube

        if (ARToolKit.getInstance().queryMarkerVisible(willId)) {
            Log.i("reid", "marker is visible!!" + willId + " youId: " + youId);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(willId), 0);
            gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
            mSquare.draw(gl, willId);
        }
        if (ARToolKit.getInstance().queryMarkerVisible(youId)) {
            Log.i("reid", "marker is visible!!" + "youId: " + youId);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(youId), 0);
            gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
            mSquare.draw(gl, youId);
        }
        if (ARToolKit.getInstance().queryMarkerVisible(mId)) {
            Log.i("reid", "marker is visible!!" + "mId: " + mId);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(mId), 0);
            gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
            mSquare.draw(gl, mId);
        }
        if (ARToolKit.getInstance().queryMarkerVisible(mMId)) {
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(mMId), 0);
            gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
            mSquare.draw(gl, mMId);
        }
        if (ARToolKit.getInstance().queryMarkerVisible(mStoopidId)) {
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(mStoopidId), 0);
            gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
            mSquare.draw(gl, mStoopidId);
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Load the texture for the square
        initImage(gl);

        gl.glEnable(GL10.GL_TEXTURE_2D);            //Enable Texture Mapping ( NEW )
        gl.glShadeModel(GL10.GL_SMOOTH);            //Enable Smooth Shading
//        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    //Black Background
        gl.glClearDepthf(1.0f);                    //Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST);            //Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL);            //The Type Of Depth Testing To Do

        //Really Nice Perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    private void initImage(GL10 gl) {
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexEnvf(
                GL10.GL_TEXTURE_ENV,
                GL10.GL_TEXTURE_ENV_MODE,
                GL10.GL_REPLACE);
//        Bitmap image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.custom_spinner);
//
//        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, image, 0);
//        image.recycle();
    }
}
