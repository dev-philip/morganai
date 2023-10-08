package com.morganai.morganai.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class aiInterface{
    static File file = new File("neuron_info.txt");
    public static void main(String[] args){
        //System.out.println("hellowowofwfoerhgerdoifngbvodfkbnhdofbnhdfobghnfd");




    }


    private static ArrayList<ArrayList<neuron>> getNurons(){
        return aiTraining.networkNeurons;
    }
    public static void writeNeuronToFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file); //"/data/neuron_info.csv"
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            file.delete();
            oos.writeObject(getNurons());
            //System.out.println(getNurons().get(0));
            oos.flush();
            oos.close();
        } catch( IOException e){

        }

    }
    public static ArrayList<ArrayList<neuron>> readObjFromFile() throws IOException, ClassNotFoundException{
        ArrayList<ArrayList<neuron>> returnal = null;
        try (FileInputStream fis = new FileInputStream(file); //"/data/neuron_info.csv"
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            //System.out.println("I Readered");
            returnal = (ArrayList<ArrayList<neuron>>) ois.readObject();
        }catch( IOException e) {
            //System.out.println("I fartered: " + e);
        }catch( ClassNotFoundException e) {
            //System.out.println("I classerted");
        }

        return returnal;

    }

    public static String[][] getTrainingData(File file){
        ArrayList<String[]> tempOutput = new ArrayList<>();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null){
                tempArr = line.split(",(?! )");
                tempOutput.add(tempArr);
            }
            br.close();

        }catch(IOException e){
            //System.out.println("Hello I dieded when reading this CSV thingie: " + e);
        }

        String[][] output = new String[tempOutput.size()][2];
        int i = 0;
        for(String[] sArr : tempOutput){
            output[i] = sArr;
            i++;
        }
        return output;
    }

    public static float[][] setTrainingOutputs(String[][] data, int outputs, float peak){
        float[][] output = new float[data.length][outputs];
        int i = 0;
        for(String[] strArr : data){
            String temp = strArr[1];
            switch(strArr[1]){
                case "AAA":output[i] = new float[]{peak,0f,0f,0f,0f,0f,0f};
                    break;
                case "BBB":output[i] = new float[]{0f,peak,0f,0f,0f,0f,0f};
                    break;
                case "CCC":output[i] = new float[]{0f,0f,peak,0f,0f,0f,0f};
                    break;
                case "DDD":output[i] = new float[]{0f,0f,0f,peak,0f,0f,0f};
                    break;
                case "EEE":output[i] = new float[]{0f,0f,0f,0f,peak,0f,0f};
                    break;
                case "FFF":output[i] = new float[]{0f,0f,0f,0f,0f,peak,0f};
                    break;
                case "GGG":output[i] = new float[]{0f,0f,0f,0f,0f,0f,peak};
                    break;
                default:
                    output[i] = new float[]{43110};
                    break;
            }
        i++;
        }
        return output;
    }


}