

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classement {

	public static ArrayList<String> classement(ArrayList<String> book_list) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		String BC_Path = "indici_BC.txt";
//		String PR_Path = "Classement/indici_PageRank.txt";	// TODO
//		String CR_Path = "Classement/indici_ClickRate.txt";	// TODO
		
		File file = new File(BC_Path);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		BufferedReader br = new BufferedReader(reader);
		String line;
		ArrayList<String> books = new ArrayList<String>();
		ArrayList<String> BC = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] sp = line.split(",");
			books.add(sp[0]);
			BC.add(sp[1]);
		}
		br.close();
		
		System.out.println(books.size() + "\t" + BC.size());
		System.out.println(books.get(0) + "\t" + BC.get(0));
		System.out.println(books.get(books.size()-1) + "\t" + BC.get(books.size()-1));

		return result;
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<String> tmp = classement(new ArrayList<String>());
	}


}