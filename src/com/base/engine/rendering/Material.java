package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

import java.util.HashMap;

public class Material {
    private HashMap<String, Texture> textureMap;
    private HashMap<String, Vector3f> vectorMap;
    private HashMap<String,Float> floatMap;
    private Texture texture;
    private Vector3f color;
    private float specularIntensity;
    private float specularPower;

    public Material(){
        textureMap = new HashMap<String, Texture>();
        vectorMap = new HashMap<String, Vector3f>();
        floatMap = new HashMap<String, Float>();
    }
    public void addTexture(String name, Texture texture){ textureMap.put(name, texture); }
    public Texture getTexture(String name){
        Texture result = textureMap.get(name);
        if(result != null){
            return result;
        }
        return new Texture("test.png");
    }
    public void addVector3f(String name, Vector3f vector){ vectorMap.put(name, vector); }
    public Vector3f getVector3f(String name){
        Vector3f result = vectorMap.get(name);
        if(result != null){
            return result;
        }
        return new Vector3f(0,0,0);
    }
    public void addFloat(String name, float floatValue){ floatMap.put(name, floatValue); }
    public Float getFloat(String name){
        Float result = floatMap.get(name);
        if(result != null){
            return result;
        }
        return (float)0;
    }

}
