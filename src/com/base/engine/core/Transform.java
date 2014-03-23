package com.base.engine.core;

public class Transform {
    private Vector3f pos;
    private Quaternion rotation;
    private Vector3f scale;

    public Transform(){
        pos = new Vector3f(0,0,0);
        rotation = new Quaternion(0,0,0,1);
        scale = new Vector3f(1,1,1);
    }

    public Matrix4f getTransformation(){
        Matrix4f posMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
        Matrix4f rotationMatrix = rotation.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

        return posMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getPos(){
        return pos;
    }

    public Vector3f getScale() {
        return scale;
    }
    public Quaternion getRotation(){
        return rotation;
    }
    public void setRotation(Quaternion rotation){
        this.rotation = rotation;
    }
    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }
}