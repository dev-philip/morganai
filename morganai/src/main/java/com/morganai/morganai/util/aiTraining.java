package com.morganai.morganai.util;

import java.io.IOException;
import java.util.*;

public class aiTraining{
    private static ArrayList<neuron> inputNurons;
    private static ArrayList<neuron> hiddenNuronLayer1;
    private static ArrayList<Float> DesiredValues;
    private static ArrayList<neuron> outputNurons;

    static ArrayList<ArrayList<neuron>> networkNeurons;

    public static void main(String[] args){
        InitializeNetwork(InputAndOutputIndex.keywords.length,18,InputAndOutputIndex.Outputs.length);
        networkNeurons = new ArrayList<>();
        networkNeurons.add(inputNurons);
        networkNeurons.add(hiddenNuronLayer1);
        networkNeurons.add(outputNurons);
        SetDesiredValues(aiFunctions.floatArrayToArrayList(InputAndOutputIndex.TrainingDataOut[0]));
        LoadNetworkValues();
        RunNetwork();

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < InputAndOutputIndex.TrainingData.length; j++){
                int x = (int)(Math.random()*InputAndOutputIndex.TrainingData.length);
                SetDesiredValues(aiFunctions.floatArrayToArrayList(InputAndOutputIndex.TrainingDataOut[x]));

                backPropigationOfBias();
                RunNetwork(networkNeurons,aiMain.ConvertInput(InputAndOutputIndex.TrainingData[x][0]));
                //RunNetwork(aiInterface.getNurons(),ConvertInput(inputStr));
            }

        }

        try{saveTrainingData();}
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<neuron> GetOutputNuronArray(){
        return outputNurons;
    }
    public static ArrayList<Float> GetDesiredValuesArray(){
        return DesiredValues;
    }

    public static void SetDesiredValues(ArrayList<Float> arr){
        //same length as output nurons

        DesiredValues = new ArrayList<>(Arrays.asList(1f,1f));
        if(arr != null)
            DesiredValues = arr;
    }
    public static void InitializeNetwork(){



        //System.out.println("Initializing network");



        //set up basic net work

        inputNurons = new ArrayList<>(Arrays.asList(new neuron(),
                                                                        new neuron()));
        //initializing values
        for (neuron n : inputNurons){
            n.value = 1f;
            //n.initialize();
        }

        hiddenNuronLayer1 = new ArrayList<>(Arrays.asList(new neuron(0f,inputNurons, new ArrayList<Float>(Arrays.asList(.2f,.4f))),
                                                          new neuron(0f,inputNurons, new ArrayList<Float>(Arrays.asList(.2f,.4f))),
                                                          new neuron(0f,inputNurons, new ArrayList<Float>(Arrays.asList(.2f,.4f)))));

        outputNurons = new ArrayList<>(Arrays.asList(new neuron(0f,hiddenNuronLayer1, new ArrayList<Float>(Arrays.asList(.2f,.1f,.5f))),
                                                     new neuron(0f,hiddenNuronLayer1, new ArrayList<Float>(Arrays.asList(.2f,.1f,.2f)))));
    }
    public static void InitializeNetwork(int countOfinputNurons, int countOfHiddenNurons, int countOfOutputNurons){

        inputNurons = new ArrayList<>();
        hiddenNuronLayer1 = new ArrayList<>();
        outputNurons = new ArrayList<>();

        //System.out.println("Initializing network");



        //set up basic net work

        //initializing inputs
        for(int i = 0; i < countOfinputNurons; i++){
            inputNurons.add(new neuron());
        }
        //initializing input values
        for (neuron n : inputNurons){
            n.value = 1f;
            //n.initialize();
        }
        //initialize hiddens weight percentages


        //initialize hiddens
        for(int i = 0; i < countOfHiddenNurons; i++){
            //hiddenNuronLayer1.add(new neuron(0f,inputNurons,new ArrayList<Float>(Arrays.asList(.2f,.4f))));
            //randomize weights to avoid semmetry
            hiddenNuronLayer1.add(new neuron(0f,inputNurons,createWeights(inputNurons.size())));
        }
        //initialize outputs
        for(int i = 0; i < countOfOutputNurons; i++){
            //outputNurons.add(new neuron(0f,hiddenNuronLayer1,new ArrayList<Float>(Arrays.asList(.2f,.4f))));
            //randomize weights to avoid semmetry
            outputNurons.add(new neuron(0f,hiddenNuronLayer1,createWeights(hiddenNuronLayer1.size())));
        }

    }
    public static void RunNetwork(){

    //computes hidden nurons
        for (neuron n : hiddenNuronLayer1){
            n.compute();

        }
        //computes output nurons
        for (neuron n : outputNurons){
            n.compute();
            //System.out.println("nuron output = " + n.value);
        }

    }
    public static void RunNetwork(ArrayList<ArrayList<neuron>> network){
        inputNurons = network.get(0);
        hiddenNuronLayer1 = network.get(1);
        outputNurons = network.get(2);

        //computes hidden nurons
        for (neuron n : hiddenNuronLayer1){
            n.compute();

        }
        //computes output nurons
        for (neuron n : outputNurons){
            n.compute();
            //System.out.println("nuron output = " + n.value);
        }

    }
    public static void RunNetwork(ArrayList<ArrayList<neuron>> network, ArrayList<Float> inputArray){

        if (network != null) {

            inputNurons = network.get(0);
            hiddenNuronLayer1 = network.get(1);
            outputNurons = network.get(2);
        }
        else{
            //System.out.println("new network created");
            InitializeNetwork(12,15,6);
        }


        for(int i = 0; i < inputNurons.size() && i < inputArray.size(); i++){
            if(inputArray.get(i)!=null)
                inputNurons.get(i).value = inputArray.get(i);
            else{
                inputNurons.get(i).value = 0f;
            }
        }

        //computes hidden nurons
        for (neuron n : hiddenNuronLayer1){
            n.compute();

        }
        //computes output nurons
        for (neuron n : outputNurons){
            n.compute();
            //System.out.println("nuron output = " + n.value);
        }


    }
    public static void LoadNetworkValues(){
        //System.out.println("loaded values to network");
    }

    public static void backPropigationOfBias(){

        //start in output
        for(int i = 0; i < outputNurons.size(); i++){

            ArrayList<neuron> proceedingNurons = outputNurons.get(i).getProceedingNurons();
            ArrayList<Float> weightsOfProceedingNurons = outputNurons.get(i).getWeightedInputOfInputNurons();
            ArrayList<Float> errorCorrections = aiFunctions.ErrorOfOutputLiniar();

            //goes through all proceeding nurons connected to current output nuron
            for(int c = 0; c < weightsOfProceedingNurons.size(); c++){
                //changes the bias of the proceeding nuron based on the correction value times the weights associated
                proceedingNurons.get(c).bias = proceedingNurons.get(c).bias - (errorCorrections.get(i) * weightsOfProceedingNurons.get(c));
                proceedingNurons.get(c).lastBiasCorrectionValue = errorCorrections.get(i) * weightsOfProceedingNurons.get(c);
                //System.out.println(" for hidden " + c + " bias is changed " + (-errorCorrections.get(i) * weightsOfProceedingNurons.get(c)));

            }



        }

        //start in Hidden layer (assuming there is only 1 hidden layer
        for(int i = 0; i < hiddenNuronLayer1.size(); i++){

            //System.out.println(" for hidden " + i + "bias has been totally changed to" + hiddenNuronLayer1.get(i).bias);

            ArrayList<neuron> proceedingNurons = hiddenNuronLayer1.get(i).getProceedingNurons();
            ArrayList<Float> weightsOfProceedingNurons = hiddenNuronLayer1.get(i).getWeightedInputOfInputNurons();

            //goes through all proceeding nurons connected to current output nuron
            for(int c = 0; c < weightsOfProceedingNurons.size(); c++){
                //changes the bias of the proceeding nuron based on the correction value times the weights associated
                proceedingNurons.get(c).bias = proceedingNurons.get(c).bias - hiddenNuronLayer1.get(i).lastBiasCorrectionValue * weightsOfProceedingNurons.get(c);

            }

        }
        //System.out.println("");
        //debug the weights for the inputs
        for(int i = 0; i < inputNurons.size() && i < outputNurons.size(); i++) {


                //System.out.println("initial correctioin val (-)" + aiFunctions.ErrorOfOutputLiniar().get(i));
                //System.out.println(" for input " + i + "bias has been totally changed to" + inputNurons.get(i).bias);
        }
    }
    public static ArrayList<Float> createWeights(int count){
        ArrayList<Float> weightsOfPrevA = new ArrayList<>();
        float sumOfPrev = 0f;
        for(int i = 0; i < count; i++){

            float x = (float)Math.random();
            weightsOfPrevA.add(x);
            sumOfPrev+=x;
        }
        ArrayList<Float> weightsOfPrevB = new ArrayList<>();
        for(int i = 0; i < count; i++){


            weightsOfPrevB.add(weightsOfPrevA.get(i)/sumOfPrev);
        }
        return weightsOfPrevB;
    }
    public static void saveTrainingData() throws IOException {
        aiInterface.writeNeuronToFile();
    }
    public void loadTrainingData(){

    }
}