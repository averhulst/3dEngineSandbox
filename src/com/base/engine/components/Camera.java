package com.base.engine.components;

import com.base.engine.core.*;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent {
    public static final Vector3f yAxis = new Vector3f(0,1,0);
    private Matrix4f projection;

    public Camera(float fov, float aspect, float zNear, float zFar)
    {

        this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
    }
    public Matrix4f getViewProjection(){
        Matrix4f cameraRotation = getTransform().getRotation().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(getTransform().getPos().getX(), getTransform().getPos().getY(), getTransform().getPos().getZ());

        return projection.mul(cameraRotation.mul(cameraTranslation));
    }

    @Override
    public void addToRenderingEngine(RenderingEngine renderingEngine)
    {
        renderingEngine.addCamera(this);
    }

    boolean mouseLocked = false;
    Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    @Override
    public void input(float delta)
    {
        float sensitivity = -0.5f;
        float movAmt = (10 * delta);


        if(Input.getKey(Input.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouseDown(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }
        System.out.println(delta);
        if(Input.getKey(Input.KEY_W))
            move(getTransform().getRotation().getForward(), movAmt);
        if(Input.getKey(Input.KEY_S))
            move(getTransform().getRotation().getForward(), -movAmt);
        if(Input.getKey(Input.KEY_A))
            move(getTransform().getRotation().getLeft(), movAmt);
        if(Input.getKey(Input.KEY_D))
            move(getTransform().getRotation().getRight(), movAmt);

        if(mouseLocked)
        {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if(rotY)
                getTransform().setRotation(getTransform().getRotation().mul(new Quaternion().initRotation(yAxis, (float) Math.toRadians(deltaPos.getX() * sensitivity))).normalize());
            if(rotX)
                getTransform().setRotation(getTransform().getRotation().mul(new Quaternion().initRotation(getTransform().getRotation().getRight(), (float)Math.toRadians(-deltaPos.getY() * sensitivity))).normalize());

            if(rotY || rotX)
                Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
        }
    }

    public void move(Vector3f dir, float amt){
        try{
            throw new Exception();
        }catch(Exception e){
            e.printStackTrace();
        }

        getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));}
}