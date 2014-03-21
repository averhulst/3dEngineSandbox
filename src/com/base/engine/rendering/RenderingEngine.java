package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.PointLight;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine {
    private Camera mainCamera;
    private Vector3f ambientLight;

    // permanent structures
    private ArrayList<BaseLight> lights;
    private BaseLight activeLight;

    public RenderingEngine(){
        lights = new ArrayList<BaseLight>();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        //todo depth clamp
        glEnable(GL_DEPTH_CLAMP);
        glEnable(GL_TEXTURE_2D);
        mainCamera = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
        ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
//        activeDirectionalLight = new DirectionalLight(new BaseLight(new Vector3f(0,0,1), 0.4f), new Vector3f(1,1,1));
//        directionalLight2 = new DirectionalLight(new BaseLight(new Vector3f(1,0,0), 0.4f), new Vector3f(-1,1,-1));
//        activePointLight = new PointLight(new BaseLight(new Vector3f(0,1,0), 0.4f), new Attenuation(0,0,1), new Vector3f(5,0,5), 100);
//        spotLight = new SpotLight(new PointLight(new BaseLight(new Vector3f(0,1,1), 0.6f), new Attenuation(0,0,0.1f), new Vector3f(5,0,5), 100), new Vector3f(1,0,0), 0.7f);

    }
    public Vector3f getAmbientLight(){
        return ambientLight;
    }
    public void input(float delta){
        mainCamera.input(delta);
    }

    public void render(GameObject object){
        clearScreen();
        lights.clear();
        object.addToRenderingEngine(this);
        Shader forwardAmbient = ForwardAmbient.getInstance();

        forwardAmbient.setRenderingEngine(this);


        forwardAmbient.setRenderingEngine(this);
        object.render(forwardAmbient);

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        //        disables writing to depth buffer
        glDepthFunc(GL_EQUAL);


        for(BaseLight light : lights){
            light.getShader().setRenderingEngine(this);
            activeLight = light;
            //TODO active light replacement

            object.render(light.getShader());
        }
//        object.render(forwardDirectional);
//
//        DirectionalLight temp = directionalLight;
//        directionalLight = directionalLight2;
//        directionalLight2 = temp;
//
//       object.render(forwardDirectional);
//        temp = directionalLight;
//        directionalLight = directionalLight2;
//        directionalLight2 = temp;
//
//       object.render(forwardPoint);
//       object.render(forwardSpot);

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);

//        Shader shader = BasicShader.getInstance();
//        shader.setRenderingEngine(this);
//        object.render(BasicShader.getInstance());
    }
    public static void clearScreen(){

        //todo stencil buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    private static void setTextures(boolean enabled){
        if(enabled) {
            glEnable(GL_TEXTURE_2D);
        }else{
            glDisable(GL_TEXTURE_2D);
        }
    }

    public static void setClearColor(Vector3f color){
        glClearColor(color.getX(), color.getY(),color.getZ(), 1.0f);
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(Camera mainCamera) {
        this.mainCamera = mainCamera;
    }

    private static String getOpenGLVersion(){

        return glGetString(GL_VERSION);
    }
    private static void unbindTextures(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    public void addLight(BaseLight light){
        lights.add(light);
    }
    public BaseLight getActiveLight(){
        return activeLight;
    }
}
