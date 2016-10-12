package com.lynx.risaki.reidar; /**
 *
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * @author impaler
 */
public class Square {

    private int mId;
    private Context mContext;
    private FloatBuffer vertexBuffer;    // buffer holding the squareCoords
    float size = 25.0f;
    private float squareCoords[] = {
            -size, -size, 0.0f,        // V1 - bottom left
            -size, size, 0.0f,        // V2 - top left
            size, -size, 0.0f,        // V3 - bottom right
            size, size, 0.0f            // V4 - top right
    };

    private FloatBuffer textureBuffer;    // buffer holding the texture coordinates
    private float texture[] = {
            // Mapping coordinates for the squareCoords
            0.0f, 1,        // top left		(V2)
            0.0f, 0.0f,        // bottom left	(V1)
            1, 1,        // top right	(V4)
            1, 0.0f        // bottom right	(V3)
    };

    /**
     * The texture pointer
     */
    private int[] textures = new int[1];

    public Square(Context con) {
        mContext = con;
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(squareCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        // allocates the memory from the byte buffer
        vertexBuffer = byteBuffer.asFloatBuffer();

        // fill the vertexBuffer with the squareCoords
        vertexBuffer.put(squareCoords);

        // set the cursor position to the beginning of the buffer
        vertexBuffer.position(0);

        byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuffer.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);
    }

    /**
     * Load the texture for the square
     */
    public void loadGLTexture(GL10 gl, Context context) {
        Bitmap bitmap = null;
        // loading texture
        switch (mId) {
            case 0:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.will);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.you);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.m);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.me);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.stoopid);
                break;
        }

        // generate one texture pointer
        gl.glGenTextures(1, textures, 0);
        // ...and bind it to our array
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        // create nearest filtered texture

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        // Use Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        // Clean up
        bitmap.recycle();
    }

    /**
     * The draw method for the square with the GL context
     */
    public void draw(GL10 gl, int id) {
        mId = id;
        // bind the previously generated texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        // Point to our buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Set the face rotation
        gl.glFrontFace(GL10.GL_CW);

        // Point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

        // Draw the squareCoords as triangle strip
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, squareCoords.length / 3);

        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
}
