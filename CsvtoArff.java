
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kehinde
 */

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
import java.io.File;
 
public class CsvtoArff {
  public static void main(String[] args) throws Exception {
  
    String args0="/Users/Kehinde/Documents/trainingtest.csv";
    String args1="/Users/Kehinde/Documents/theoutput.arff";
   
    // This is used to load CSV
    CSVLoader myloader = new CSVLoader();
    myloader.setSource(new File(args0));
    Instances mydata = myloader.getDataSet();
    System.out.println(mydata);
 
    // This is used to save ARFF
    ArffSaver mysaver = new ArffSaver();
    mysaver.setInstances(mydata);
    mysaver.setFile(new File(args1));
    mysaver.setDestination(new File(args1));
    mysaver.writeBatch();
  } 
}

