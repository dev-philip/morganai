package com.morganai.morganai.util;

import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class aiMain{

    public static String theReturn = "";

    public static void main(String[] args){


        // read the files
        String inputStr = "Morgan and Morgan?";
        //System.out.println("accociated inputs are ### " + ConvertInput(inputStr));
        try {
            aiTraining.RunNetwork(aiInterface.readObjFromFile(),ConvertInput(inputStr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<neuron> outputs =  aiTraining.GetOutputNuronArray();

        int nuronIndex = -1;
        nuronIndex = runNetwork(outputs);
        float uncertainty = getUncertainty(outputs);
        //System.out.println("It was neuron " + nuronIndex);// + " with an uncertainty of " + uncertainty);
        ArrayList<ArrayList<neuron>> test = new ArrayList<>();

        try {
            test = aiInterface.readObjFromFile();
//            System.out.println("I tried to readered");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



//        String[][] testArr = new String[][]{
//                {"I'm injured, Contract?","AAA-well if your injured you should call a legal asistant"},
//                {"What is the Statute?","BBB-this is the statute #### "}};
//        String[][] newArr = aiInterface.getTrainingData(new File("Training_Data.csv"));
//        float[][] outputArr = aiInterface.setTrainingOutputs(newArr,7,3f);
//        System.out.println("Did this explodedered? Answer: " + test);
////        System.out.println("Heyro, I ams to tell you that I got the OLD datas: " + printDoubleString(testArr));
//        System.out.println("Heyro, I ams to tell you that I got the NEW datas: " + printDoubleString(newArr));
//        System.out.println("Hollo I am here with OUTPUTRESULTIES: " +
//                printDoubleFloat(outputArr));
//        System.out.println("\nHollo I am here with the array Length: " + outputArr.length);

        System.out.println(getAiResponce("help case"));

    }
    public static String getAiResponce(String inputStr){

        //System.out.println("accociated inputs are ### " + ConvertInput(inputStr));
        try {
            aiTraining.RunNetwork(aiInterface.readObjFromFile(),ConvertInput(inputStr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<neuron> outputs =  aiTraining.GetOutputNuronArray();

        int nuronIndex = -1;
        nuronIndex = runNetwork(outputs);
        float uncertainty = getUncertainty(outputs);
        //System.out.println("It was neuron " + nuronIndex);// + " with an uncertainty of " + uncertainty);
        ArrayList<ArrayList<neuron>> test = new ArrayList<>();

        try {
            test = aiInterface.readObjFromFile();
//            System.out.println("I tried to readered");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        theReturn = InputAndOutputIndex.stringOutputs[nuronIndex];
        return theReturn;
    }

    public static String printDoubleString(String[][] array){
        String builder = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                builder += (array[i][j] + ",");
            }
            builder += "\n";
        }
        return builder;
    }
    public static String printDoubleFloat(float[][] array){
        String builder = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                builder += (array[i][j] + ",");
            }
            builder += "\n";
        }
        return builder;
    }


    public static ArrayList<Float> ConvertInput(String str){
        ArrayList<Float> inputs = new ArrayList<>();

        for(int i =0; i<InputAndOutputIndex.keywords.length;i++){
            inputs.add((float)lookForWord(str,InputAndOutputIndex.keywords[i])/str.length()*InputAndOutputIndex.keywords[i].length());
        }
        return  inputs;
    }
    public static int lookForWord(String str, String word) {
        int count = 0;
        for (int i = 0; i <= str.length() - word.length(); i++) {
            if (str.substring(i, i + word.length()).equals(word)) {
                count++;
            }
        }
        return count;
    }
    public static int runNetwork(ArrayList<neuron> outputs){

        //randomize a bit
        for(int i = 0; i < outputs.size(); i++){
            outputs.get(i).value+=(float)Math.random();
        }



        //get the maximum value as the responce
        int maxNuronIndex = 0;
        for(int i = 0; i < outputs.size(); i++){
            //System.out.println("nuron "+ i +" val of "+outputs.get(i).value);
            if(outputs.get(i).value > outputs.get(maxNuronIndex).value){

                maxNuronIndex = i;
            }
        }
        return maxNuronIndex;
    }
    public static float getUncertainty(ArrayList<neuron> outputs){
        //the average distribution for gauging certainty
        float sum = 0f;
        for(int i = 0; i < outputs.size(); i++){

            sum += outputs.get(i).value;
        }
        float average = sum/outputs.size();

        float sum2 = 0f;
        for(int i = 0; i < outputs.size(); i++){

            sum += (float)(Math.pow((outputs.get(i).value-average),2f));
        }
        float meanAbsoluteDeviation = sum2/ outputs.size();//AKA uncertainty
        return meanAbsoluteDeviation;
    }
}