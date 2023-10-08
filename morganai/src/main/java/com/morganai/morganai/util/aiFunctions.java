package com.morganai.morganai.util;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class aiFunctions {

    static float e = 2.71828f;
    public static float getSigmoid( float x){
        return x;//(float)(1/(1+Math.pow(e,-x)));
    }
    public static float getSigmoidDerivitive(float x){
        return (float)(Math.pow(e,x)/Math.pow((1+Math.pow(e,x)),2));
    }
    //composit the enteire eq before taking the derivitive

    public static ArrayList<Float> ErrorOfOutput(){
        //returns of an array of how far off the nurons are
        ArrayList<neuron> OutputNuronArray = aiTraining.GetOutputNuronArray();
        ArrayList<Float> DesiredValuesArray = aiTraining.GetDesiredValuesArray();
        ArrayList<Float> output = new ArrayList<Float>();
        int i = 0;
        for(neuron n : OutputNuronArray){
            float error = (float)Math.pow((OutputNuronArray.get(i).value - DesiredValuesArray.get(i)), 2f);
            output.add(error);
            i++;
        }
        return output;

    }
    public static ArrayList<Float> ErrorOfOutputLiniar(){
        //returns of an array of how far off the nurons are
        ArrayList<neuron> OutputNuronArray = aiTraining.GetOutputNuronArray();
        ArrayList<Float> DesiredValuesArray = aiTraining.GetDesiredValuesArray();
        ArrayList<Float> output = new ArrayList<Float>();
        int i = 0;
        for(neuron n : OutputNuronArray){

            float error = (OutputNuronArray.get(i).value - DesiredValuesArray.get(i));
            output.add(error);
            i++;
        }
        return output;

    }
    public static ArrayList<Float> floatArrayToArrayList(float[] arr){
        ArrayList<Float> output = new ArrayList<>();
        for(float item: arr){
            output.add(item);
        }
        return output;
    }


}