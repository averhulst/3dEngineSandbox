package com.base.engine.core;

import com.base.engine.rendering.Camera;

public class Transform {
    private Vector3f pos;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform(){
        pos = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        scale = new Vector3f(1,1,1);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Matrix4f getTransformation(){
        Matrix4f posMatrix = new Matrix4f().initTranslation(pos.getX(),pos.getY(),pos.getZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(),rotation.getZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

        return posMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y, float z){
        this.pos = new Vector3f(x, y, z);
    }
    public Vector3f getPos(){
        return pos;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    public void setRotation(float x, float y, float z) {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }

//    public static Camera getCamera() {
//        return camera;
//    }
//
//    public static void setCamera(Camera camera) {
//        Transform.camera = camera;
//    }
}