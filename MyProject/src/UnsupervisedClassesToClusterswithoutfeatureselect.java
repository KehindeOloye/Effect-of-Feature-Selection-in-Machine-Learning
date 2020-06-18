
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kehinde
 */
import java.io.*;
import weka.core.*;
import weka.clusterers.*;
import weka.filters.*;
import weka.filters.unsupervised.attribute.Remove;

public class UnsupervisedClassesToClusterswithoutfeatureselect {
   public void myUnsupervisednofeatureselect() throws Exception {
    // This is used to load myData
    Instances myData = new Instances(new BufferedReader(new FileReader("theoutput.arff")));
    myData.setClassIndex(myData.numAttributes() - 1);

    // This is used to generate myData for myclusterer without class attribute
    Remove myFilter = new Remove();
    myFilter.setAttributeIndices("" + (myData.classIndex() + 1));
    myFilter.setInputFormat(myData);
    Instances dataClusterer = Filter.useFilter(myData, myFilter);

    // This is used to train myclusterer
    EM myclusterer = new EM();
    myclusterer.buildClusterer(dataClusterer);

    // This is used to evaluate myclusterer
    ClusterEvaluation evaluation = new ClusterEvaluation();
    evaluation.setClusterer(myclusterer);
    evaluation.evaluateClusterer(myData);
    // This is used to print results
    System.out.println(evaluation.clusterResultsToString());
  }
}

