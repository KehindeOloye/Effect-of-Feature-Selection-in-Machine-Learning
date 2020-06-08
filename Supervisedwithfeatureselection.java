
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */


	/**
	 *
	 * @author Kehinde
	 */

	import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;
import weka.filters.*;
import weka.attributeSelection.*;


public class Supervisedwithfeatureselection {
	    public static BufferedReader readDataFile(String filename) {
	        BufferedReader inputReader = null;
	        try {
	            inputReader = new BufferedReader(new FileReader(filename));
	        } catch (FileNotFoundException ex) {
	            System.err.println("File not found: " + filename);
	        }
	        return inputReader;
	    }
	    
	    public static Evaluation simpleClassify(Classifier model, Instances trainingSet, Instances testingSet) throws Exception {
	        Evaluation validation = new Evaluation(trainingSet);
	        model.buildClassifier(trainingSet);
	        validation.evaluateModel(model, testingSet);
	        
	        return validation;
	    }
	    
	    public static double calculateAccuracy(FastVector predictions) {
	        double correct = 0;
	        
	        for (int i = 0; i < predictions.size(); i++) {
	            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
	            if (np.predicted() == np.actual()) {
	                correct++;
	            }
	        }
	        
	        return 100 * correct / predictions.size();
	    }
	    
	    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
	        Instances[][] split = new Instances[2][numberOfFolds];
	        
	        for (int i = 0; i < numberOfFolds; i++) {
	            split[0][i] = data.trainCV(numberOfFolds, i);
	            split[1][i] = data.testCV(numberOfFolds, i);
	        }
	        
	        return split;
	    }
	    public void mySupervisedwithfeatureselection() throws Exception {
	    
	        
	    	BufferedReader reader = new BufferedReader(new FileReader("theoutput.arff"));
	        Instances data = new Instances(reader);
	        if (data.classIndex() == -1)
	          data.setClassIndex(data.numAttributes() - 1);
	        
	    	weka.filters.supervised.attribute.AttributeSelection myfilter = new weka.filters.supervised.attribute.AttributeSelection();
	        InfoGainAttributeEval evaluation = new InfoGainAttributeEval();
	        Ranker mysearch = new Ranker();
	        mysearch.setGenerateRanking(true);
	        mysearch.setNumToSelect(-1);
	        mysearch.setThreshold(-1.7976931348623157E308);
	        myfilter.setEvaluator(evaluation);
	        myfilter.setSearch(mysearch);
	        myfilter.setInputFormat(data);
	        Instances newData = Filter.useFilter(data, myfilter);
	        System.out.println(newData);
	        
	        newData.setClassIndex(data.numAttributes() - 1);
	        
	        // Choose a type of validation split
	        Instances[][] split = crossValidationSplit(newData, 10);
	        
	        // Separate split into training and testing arrays
	        Instances[] trainingSplits = split[0];
	        Instances[] testingSplits  = split[1];
	        
	        // Choose a set of classifiers
	        Classifier[] models = {     new J48(),
	                                    new DecisionTable(),
	                                    new NaiveBayes(),
	                                    new DecisionStump() };
	        
	        // Run for each classifier model
	        for(int j = 0; j < models.length; j++) {

	            // Collect every group of predictions for current model in a FastVector
	            FastVector predictions = new FastVector();
	            // For each training-testing split pair, train and test the classifier
	            for(int i = 0; i < trainingSplits.length; i++) {
	                Evaluation validation = simpleClassify(models[j], trainingSplits[i], testingSplits[i]);
	               predictions.appendElements(validation.predictions());
	               // Uncomment to see the summary for each training-testing pair.
	               System.out.println(models[j].toString());
	     
	            }
	            
	            // Calculate overall accuracy of current classifier on all splits
	            double accuracy = calculateAccuracy(predictions);
	            
	            // Print current classifier's name and accuracy in a complicated, but nice-looking way.
	            System.out.println(models[j].getClass().getSimpleName() + ": " + String.format("%.2f%%", accuracy) + "\n=====================");
	        
	        
	    }
	}

}
