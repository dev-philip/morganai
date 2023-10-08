package com.morganai.morganai.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

public class InputAndOutputIndex {
    public static String[] keywords = new String[]{
            "Jurisdiction",
            "Liability",
            "Litigation",
            "Contract",
            "Defendant",
            "Case",
            "Evidence",
            "Settlement",
            "Statute",
            "When",
            "?",
            "number",
            "Morgan",
            "case",
            "evidence",
            "settlement",
            "lawyers",
            "lawyer",
            "trial",
            "account",
            "login",
            "password",
            "office",
            "Offices",
            "Office",
            "offices",
            "attorney",
            "attorneys",
            "outcome",
            "outcomes",
            "status",
            "location",
            "Location",
            "deadline",
            "result",
            "current",
            "state"


    };
    public static String[] Outputs = new String[]{
            "AAA",
            "BBB",
            "CCC",
            "DDD",
            "EEE",
            "FFF",
            "GGG"

    };
    public static String[] stringOutputs = new String[]{
            "For information regarding your case, please either navigate to the Morgan and Morgan \nWebsite and search your case number or contact your lawyer.",
            "I'm not a lawyer, but I can provide general information. If you are in question of if \nyou should hire a lawyer it is recommended to call Morgan and Morgan and ask for \na consult. From there you can decide further action.",
            "Morgan & Morgan handles a wide range of cases, including personal injury, medical malpractice\n and more. With free consultations and a contingency basest payment system, \nMorgan and Morgan can help with what ever legal troubles.",
            "To create an account, you need to provide basic personal information such as your name, \nemail address, and a password. As for how to do that, you may navigate the page \nto the sign up icon to do so. This would greatly help us manage and \nsave your inquiries and case status.",
            "Morgan & Morgan has a strong track record of success in court, but specific success rates \nmay vary by case type. Although we cant provide you details on specific case \ntype, rest assured that Morgan and Morgan is willing to give their best \nfor their clients.",
            "If you are looking for the closest office to you, we apologize but we cant assist you \ndue to this user not having an account with their location information. Please note \noffice hours may vary depending on location.",
            "If you would like an attorney many have different specializations so please contact one \nof our offices so that they may put you in contact with one. Please note that \nhaving a consultation before hiring an attorney is recommended."

    };

//    public static String[][] TrainingData= aiInterface.getTrainingData(new File("Training_Data.csv"));
    public static String[][] TrainingData;
    static {
        try {
            File file = new ClassPathResource("Training_Data.csv").getFile();
            TrainingData = aiInterface.getTrainingData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//            new String[][]{
//        {"I'm injured, Contract?","AAA-well if your injured you should call a legal asistant"},
//        {"What is the Statute?","BBB-this is the statute #### "}
//    };
    public static float[][] TrainingDataOut=  aiInterface.setTrainingOutputs(TrainingData,7,.1f);

}