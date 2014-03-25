package com.base.engine.core;

public class Transform {
    private Vector3f position;
    private Quaternion rotation;
    private Vector3f scale;
    private Transform parent;
    private Matrix4f parentMatrix;

    private Vector3f oldPosition;
    private Quaternion oldRotation;
    private Vector3f oldScale;

    public Transform(){
        position = new Vector3f(0,0,0);
        rotation = new Quaternion(0,0,0,1);
        scale = new Vector3f(1,1,1);
        parentMatrix = new Matrix4f().initIdentity();
    }

    public Matrix4f getTransformation(){
        Matrix4f posMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
        Matrix4f rotationMatrix = rotation.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());


        return getParentMatrix().mul(posMatrix.mul(rotationMatrix.mul(scaleMatrix)));
    }

    public Vector3f getTransformedPosition(){
        return parentMatrix.transform(position);
    }
    public void update(){
        if(oldPosition != null){
            oldPosition.set(position);
            oldRotation.set(rotation);
            oldScale.set(scale).set(scale);
        }else{
            oldPosition = new Vector3f(0,0,0).set(position).add(1.0f);
            oldRotation = new Quaternion(0,0,0,0).set(rotation).mul(0.5f);
            oldScale = new Vector3f(0,0,0).add(1.0f);
        }
    }

    public boolean hasChanged() {


        if(parent != null && parent.hasChanged()){
            return true;
        }

        if(!position.equals(oldPosition)){
            return true;
        }
        if(!rotation.equals(oldRotation)){
            return true;
        }
        if(!scale.equals(oldScale)){
            return true;
        }

        return false;
    }
    public Quaternion getTransformedRotation(){
        Quaternion parentRotation = new Quaternion(0,0,0,1);
        if(parent != null && parent.hasChanged()){
            parentRotation = parent.getTransformedRotation();
        }
        return parentRotation.mul(rotation);
    }
    private Matrix4f getParentMatrix(){
        if(parent != null){
            parentMatrix = parent.getTransformation();
        }
        return parentMatrix;
    }
    public void rotate(Vector3f axis, float angle){
        rotation = new Quaternion(axis, angle).mul(rotation).normalize();
    }
    public void setParent(Transform parent){
        this.parent = parent;
    }

    public void setPos(Vector3f pos) {
        this.position = pos;
    }

    public Vector3f getPos(){
        return position;
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