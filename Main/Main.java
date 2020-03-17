package Main;

import java.util.ArrayList;

import Betweenness.Betweenness;
import KMP.KMP;
import RegEx.RegEx;
import IndexTable.Index;
import SimpleIndexing.SimpleIndexing;
import SimpleRegEx.SimpleRegEx;
import Classement.Classement;


public class Main {

	public static void main(String[] args) throws Exception {

		long startTime, endTime;

		String folder_path = "../database1664";
//		String folder_path = "database1664";
		
		
		
		// EXEMPLE recherche par SimpleIndexing
		String keyword = "PROject";
//		String keyword = "jurisprudence";
		keyword = keyword.toLowerCase();
		startTime = System.currentTimeMillis();
		ArrayList<String> result_SI = SimpleIndexing.recherche(keyword, folder_path);
		endTime = System.currentTimeMillis();
		System.out.println("SimpleIndexing a pris " + (endTime - startTime) + " ms : with " + result_SI.size());

		

		// EXEMPLE recherche par SimpleRegEx
		String keyword_regex = "PROjec(e|t)";
		keyword_regex = keyword_regex.toLowerCase();
		startTime = System.currentTimeMillis();
		ArrayList<String> result_SR = SimpleRegEx.recherche(keyword_regex, folder_path);
		endTime = System.currentTimeMillis();
		System.out.println("SimpleRegEx a pris " + (endTime - startTime) + " ms : with " + result_SR.size());

		
		
		// EXEMPLE classement
		startTime = System.currentTimeMillis();
		ArrayList<String> result_classement = Classement.classement(result_SI);
		endTime = System.currentTimeMillis();
		System.out.println("Classement a pris " + (endTime - startTime) + " ms ");


		
		
	}

}