package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreEngine {
    private boolean isRunning;
    public Game game;
    private int width;
    private int height;
    private String title;
    private RenderingEngine renderingEngine;
    private double frameTime;

    
    public CoreEngine(int width, int height, double frameRate, Game game){

        isRunning = false;
        this.game = game;
        this.width = width;
        this.height = height;
        this.frameTime = 1.0/frameRate;

//        game = new Game();
    }

    public void createWindow(String title){
        Window.createWindow(width, height, title);
        this.renderingEngine = new RenderingEngine();
    }
    
    public void start(){
        if(isRunning){
            return;
        }
        run();
    }
    private void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
    }
    private void run(){

        isRunning = true;
        int frames = 0;
        long frameCounter = 0;

        game.init();

        double lastTime = Time.getTime();
        double unprocessedTime = 0;
        
        
        while(isRunning){
            boolean render = false;

            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;
            
            unprocessedTime += passedTime;
            frameCounter += passedTime;
            
             while(unprocessedTime > frameTime){
                 render = true;
                 
                 unprocessedTime -= frameTime;
                 
                if(Window.isCloseRequested()){
                    stop();
                }
                
                
                game.input((float)frameTime);
                Input.update();
                game.update((float)frameTime);
                if(frameCounter >= 1){
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
             }
            
            if(render){
                game.render(renderingEngine);
//                renderingEngine.render(game.getRootObject());
                Window.render();
                //render();
                frames++;
            }else{
                //instead of running the while loop over and over until render = true, just sleep
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(CoreEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        cleanUp();
    }

    
    private void cleanUp(){
        
    }
    
}
