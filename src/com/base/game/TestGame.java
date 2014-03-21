package com.base.game;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game {

    private Mesh mesh;

    public void init(){

        float fieldDepth = 10.f;
        float fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-fieldWidth,    0.0f, -fieldDepth),	    new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth,    0.0f, fieldDepth * 3),  new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth),     new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3),  new Vector2f(1.0f, 1.0f)) };

        int indices[] = { 0,1,2,
                2,1,3};

        mesh = new Mesh(vertices, indices, true);
        Material material = new Material(new Texture("test.png"), new Vector3f(1,1,1), 1, 8);

        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
        GameObject planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().setPos(0,-1,5);

        GameObject directionaLightObject = new GameObject();
        DirectionalLight directionaLight = new DirectionalLight(new Vector3f(0,0,1), 0.4f, new Vector3f(-1,1,-1));
        directionaLightObject.addComponent(directionaLight);


        GameObject pointLightObject = new GameObject();
        PointLight pointLight = new PointLight(new Vector3f(0,1,0), 0.4f, 0,0,1, new Vector3f(5,0,5), 100);
        pointLightObject.addComponent(pointLight);

        SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
                0,0,0.1f,
                new Vector3f(5,0,5), 100,
                new Vector3f(1,0,0), 0.7f);
        GameObject spotLightObject = new GameObject();
        spotLightObject.addComponent(spotLight);

        getRootObject().addChild(planeObject);
        getRootObject().addChild(directionaLightObject);
        getRootObject().addChild(pointLightObject);
        getRootObject().addChild(spotLightObject);
    }
}
