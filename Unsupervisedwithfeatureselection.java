
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author Kehinde
 */
public class Unsupervisedwithfeatureselection {
   public void myUnsupervisedfeatureselect() throws Exception {
    // This is used to load mydata
    Instances mydata = new Instances(new BufferedReader(new FileReader("theoutput.arff")));
    mydata.setClassIndex(mydata.numAttributes() - 1);

    // This is used to generate myData for myclusterer without class attribute
    Remove myfilter = new Remove();
    myfilter.setAttributeIndices("" + (mydata.classIndex() + 1));
    myfilter.setInputFormat(mydata);
    Instances dataClusterer = Filter.useFilter(mydata, myfilter);

    // This is used to carry out feature selection using filters
    weka.filters.supervised.attribute.AttributeSelection filter = new weka.filters.supervised.attribute.AttributeSelection();
    InfoGainAttributeEval theevaluation = new InfoGainAttributeEval();
    Ranker mysearch = new Ranker();
    mysearch.setGenerateRanking(true);
    mysearch.setNumToSelect(-1);
    mysearch.setThreshold(-1.7976931348623157E308);
    filter.setEvaluator(theevaluation);
    filter.setSearch(mysearch);
    filter.setInputFormat(mydata);
    Instances newData = Filter.useFilter(mydata, filter);
    // This is used to train myclusterer
    EM myclusterer = new EM();
    myclusterer.buildClusterer(dataClusterer);

    // This is used to evaluate myclusterer
    ClusterEvaluation evaluation = new ClusterEvaluation();
    evaluation.setClusterer(myclusterer);
    evaluation.evaluateClusterer(newData);

    // This is used to print results
    System.out.println(evaluation.clusterResultsToString());
  }  
}

