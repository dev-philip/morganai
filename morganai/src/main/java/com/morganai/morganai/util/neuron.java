package com.morganai.morganai.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;
public class neuron implements Serializable {

    public float bias = 0.0f;
    public float lastBiasCorrectionValue = 0.0f;
    public float value = 0.0f;
    private ArrayList<neuron> inputNurons;
    private ArrayList<Float> weightedInputOfInputNurons;

    public neuron(float bias, ArrayList<neuron> inputNurons, ArrayList<Float> weightedInputOfInputNurons){
        this.bias = bias;
        this.inputNurons = inputNurons;
        this.weightedInputOfInputNurons = weightedInputOfInputNurons;
    }
    public neuron(float bias){
        this.bias = bias;
    }
    public neuron(){
        this.bias = 0.0f;
    }
    public void compute(){

        float sum =0.0f;
        //loops through and sums all pairings of weights to nurons
        for(int i =0; i < inputNurons.size() && i < weightedInputOfInputNurons.size(); i++){
            sum = sum + inputNurons.get(i).getValue() * weightedInputOfInputNurons.get(i);
        }
        //applies bias and sigmoid
        value = aiFunctions.getSigmoid(sum+ bias);
    }
    public void initialize(){
        value = (float)(Math.random());

    }

    public float getValue(){
        return value;
    }
    public ArrayList<Float> getWeightedInputOfInputNurons(){
        return weightedInputOfInputNurons;
    }
    public ArrayList<neuron> getProceedingNurons(){
        return inputNurons;
    }
    @Override
    public String toString(){
        return "Neuron{" +
                "Bias='" + bias + '\'' +
//                ", Input Neurons='" + inputNurons + '\'' +
                ", weightedInputOfInputNeurons=" + weightedInputOfInputNurons +
                "}";
    }
}