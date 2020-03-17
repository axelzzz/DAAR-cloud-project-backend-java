package Classement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classement {

	public static ArrayList<String> classement(ArrayList<String> books_list) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		String BC_Path = "Classement/indice_BC.txt";
//		String PR_Path = "Classement/indice_PageRank.txt";	// TODO
//		String CR_Path = "Classement/indice_ClickRate.txt";	// TODO
		
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
		
		// sort by indice
		Map<String, Float> map = new HashMap<String, Float>();
		for (String book : books_list) {
			float indice = Float.valueOf(BC.get(books.indexOf(book)));	// TODO : add PR and CR indices
			map.put(book, indice);
		}
		List<Map.Entry<String, Float>> list_sorted = new ArrayList<Map.Entry<String, Float>>(map.entrySet());

		Collections.sort(list_sorted, new Comparator<Map.Entry<String, Float>>() {
			public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
				return (o2.getValue()).toString().compareTo(o1.getValue().toString());
			}
		});
		
		
		for (int i = 0; i < list_sorted.size(); i++) {
			String id = list_sorted.get(i).getKey();
//			System.out.println(id);
			result.add(id);
		}


		return result;
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<String> books = new ArrayList<String>();
		books.add("database1664\59292.txt.utf-8");
		books.add("database1664\25964-0.txt");
		books.add("database1664\231.txt.utf-8");
		ArrayList<String> tmp = classement(books);
	}


}