package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

public class Vertex {

    public static final int SIZE = 8;

    private Vector3f pos;
    private Vector2f texCoord;
    private Vector3f normal;
    //data being sent to glsl shader


    public Vertex(Vector3f pos){
        this(pos, new Vector2f(0,0));
    }
    public Vertex(Vector3f pos, Vector2f textCoord, Vector3f normal){
        this.pos = pos;
        this.texCoord = textCoord;
        this.normal = normal;
    }
    public Vertex(Vector3f pos, Vector2f texCoord){
        this(pos, texCoord, new Vector3f(0,0,0));
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getTexCoord() {
        return texCoord;
    }

    public void setTexCoord(Vector2f texCoord) {
        this.texCoord = texCoord;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
