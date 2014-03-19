/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.core;

public class Time { 
    private static double delta;
    private static final long SECOND = 1000000000L;
    
    public static double getTime(){
        return (double)System.nanoTime()/(double)SECOND;
    }
    
//    public static double getDelta(){
//        return delta;
//    }
//
//    public static void setDelta(double delta){
//        Time.delta = delta;
//    }
    
}
