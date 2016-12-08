package br.com.techschool.cardboardgame.android;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import br.com.techschool.cardboardgame.MyCardBoardGame;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.CardBoardAndroidApplication;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

public class AndroidLauncher extends CardBoardAndroidApplication implements CardBoardApplicationListener {

    private CardboardCamera cam;
    private Model model;
    private ModelInstance instance;
    private ModelBatch batch;
    private Environment environment;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 1000.0f;
    private static final float CAMERA_Z = 100f;
    private float angles[] = new float[4];
    private float initAngles[] = {0f, 0f, 0f, 0f};
    SpriteBatch spriteBatch;
    Matrix4 viewMatrix;
    Matrix4 tranMatrix;
    BitmapFont font;
    float angle=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(this, config);
    }

    @Override
    public void create() {
        cam = new CardboardCamera();
        cam.position.set(0f, 50f, CAMERA_Z);
        cam.lookAt(0, 0, 0);
        cam.near = Z_NEAR;
        cam.far = Z_FAR;


        viewMatrix = new Matrix4();
        tranMatrix = new Matrix4();
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().scale(10);
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1, 1, 1, 1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

//        ModelBuilder modelBuilder = new ModelBuilder();
//        model = modelBuilder.createBox(5f, 5f, 5f,
//            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
//            Usage.Position | Usage.Normal);

        ModelLoader<ModelLoader.ModelParameters> loader = new G3dModelLoader(new UBJsonReader());

        Model model = loader.loadModel(Gdx.files.internal("minecraft/minecraft.g3db"));
        instance = new ModelInstance(model);
        instance.transform.translate(0, 0, 0);

        Log.d("CARDBOARD_APP", "Model Loaded!!!");

        batch = new ModelBatch();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        model.dispose();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        paramHeadTransform.getQuaternion(angles, 0);
       // cam.lookAt(angles[0], angles[1], angles[2]);

        angle = (float)Math.toDegrees(angles[1] - initAngles[1]);
        //instance.transform.rotateRad(1, 0, 0, -angles[0] / 10); //horizontal axis rotation
        //instance.transform.rotateRad(0, 1, 0, (-(angles[1]) / 50)); //vertical axis rotation
        //instance.transform.rotateRad(0, 0, 1, -angles[2] / 1000); //deep axis rotation
        //cam.rotate(angles[0], 1, 0, 0);
        //cam.rotate(angles[1], 0, 1, 0);
        //cam.rotate(angles[2], 0, 0, 1);
        //cam.lookAt(0, angles[1], 0);


        if(Gdx.input.isKeyPressed(Input.Keys.J)) {
            //instance.transform.translate((float) ((Gdx.graphics.getDeltaTime() * 30) * Math.sin(angles[1])),0, (float) ((Gdx.graphics.getDeltaTime() * 30) * Math.cos(angles[1])));
            //cam.translate(0, 0, (float) (Math.cos(angles[1])) * (-(Gdx.graphics.getDeltaTime() * 30)));
            //cam.translate(((float) Math.sin(angles[1]) * (-(Gdx.graphics.getDeltaTime() * 30))), 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.M)) {
            //instance.transform.translate((float) (-(Gdx.graphics.getDeltaTime() * 30) * Math.cos(angles[1])),0, (float) (-(Gdx.graphics.getDeltaTime() * 30) * Math.sin(angles[1])));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            //cam.position.rotate(Vector3.Y, angle);
            //cam.translate(0,0, -(Gdx.graphics.getDeltaTime() * 10));
        }
        //cam.translate(0, 0, -Gdx.graphics.getDeltaTime() * 10);
        //cam.position.rotate(-angles[0], 1, 0, 0);
        //cam.position.rotate(-angles[1] - initAngles[1], 0, 1, 0);
        //cam.position.rotate(-angles[2], 0, 0, 1);
        initAngles[0] = angles[0];
        initAngles[1] = angles[1];
        initAngles[2] = angles[2];
        initAngles[3] = angles[3];
    }

    @Override
    public void onDrawEye(Eye eye) {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //  Gdx.gl.glClearColor(1, 1, 1, 1);

        // Apply the eye transformation to the camera.
        cam.setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        float[] perspective = eye.getPerspective(Z_NEAR, Z_FAR);
        cam.setEyeProjection(new Matrix4(perspective));
        cam.update();

        batch.begin(cam);
        batch.render(instance, environment);
        batch.end();

        spriteBatch.begin();
        font.draw(spriteBatch, "Angles "+angle, 50, 200);

        spriteBatch.end();
    }

    @Override
    public void onFinishFrame(Viewport paramViewport) {

    }

    @Override
    public void onRendererShutdown() {

    }

    @Override
    public void onCardboardTrigger() {
        Toast toast = Toast.makeText(this, "Touch works!", Toast.LENGTH_LONG);
        toast.show();
    }
}
