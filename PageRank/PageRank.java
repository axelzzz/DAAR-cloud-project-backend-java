package PageRank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRank {

	private static HashMap<String, Integer> readFromFile(String filename) throws Exception {
		HashMap<String, Integer> strList = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		String line;
		while ((line = br.readLine()) != null) {
			String reg = "[^a-zA-Z]";
			for (String s : Arrays.asList(line.split(reg))) {
				if (s.isEmpty())
					continue;
				String tmp = s.toLowerCase();
				if (strList.containsKey(tmp)) {
					strList.put(tmp, strList.get(tmp) + 1);
				} else {
					strList.put(tmp, 1);
				}
			}
		}
		br.close();
//		System.out.println(strList);
		return strList;
	}

	private static float[][] calculateMatrixPageRank(ArrayList<HashMap<String, Integer>> list, double seuil) {

		float matrice[][] = new float[list.size()][list.size()];

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
			
				HashMap<String, Integer> hm1 = list.get(i);
				HashMap<String, Integer> hm2 = list.get(j);
				int hm1_occu = 0;
				float hm1_sum = 0;
				int hm2_occu = 0;
				float hm2_sum = 0;
				for (Map.Entry<String, Integer> entry : hm1.entrySet()) {
					if (hm2.containsKey(entry.getKey())) {
						hm1_occu += entry.getValue();
					}
					hm1_sum += entry.getValue();
				}
				for (Map.Entry<String, Integer> entry : hm2.entrySet()) {
					if (hm1.containsKey(entry.getKey())) {
						hm2_occu += entry.getValue();
					}
					hm2_sum += entry.getValue();
				}
				
				if (hm1_occu/hm1_sum > seuil) {
					matrice[i][j] = 1;	// il existe une arrete de i vers j
				} else {
					matrice[i][j] = 0;
				}
				if (hm2_occu/hm2_sum > seuil) {
					matrice[j][i] = 1;	// il existe une arrete de j vers i
				} else {
					matrice[j][i] = 0;
				}

			}
		}

//		for (int i = 0; i < list.size(); i++) {
//			for (int j = 0; j < list.size(); j++) {
//				System.out.print(matrice[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();


		return matrice;
	}
	

	private static ArrayList<String> calculateIndex(float matrice[][], ArrayList<String> paths_list) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> vertices = new ArrayList<String>();
		float[] PageRankIndice = new float[vertices.size()];
		

		// sort by value
		Map<String, Float> map = new HashMap<String, Float>();
		for (String v : vertices) {
			map.put(v, PageRankIndice[vertices.indexOf(v)]);
		}
		List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, Float>>() {
			public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
				return (o2.getValue()).toString().compareTo(o1.getValue().toString());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			String id = infoIds.get(i).toString();
//			System.out.println(id);
			result.add(id);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		
		double seuil = 0.85;
		
		ArrayList<String> paths_list = new ArrayList<String>();
		paths_list.add("test/t1.utf-8");
		paths_list.add("test/t2.utf-8");
		paths_list.add("test/t3.utf-8");
		paths_list.add("test/t4.utf-8");
		paths_list.add("test/t5.utf-8");
		paths_list.add("test/t6.utf-8");
		paths_list.add("test/t7.utf-8");
		paths_list.add("test/t8.utf-8");
		paths_list.add("test/t9.utf-8");
		paths_list.add("test/t10.utf-8");
		

		ArrayList<HashMap<String, Integer>> hm_list = new ArrayList<HashMap<String, Integer>>();

		for (String file_path : paths_list) {
			HashMap<String, Integer> tmp = readFromFile(file_path);
			hm_list.add(tmp);
		}
		
		// matrice[i][j]=1 => il existe une arrete de i vers j
		float matrice[][] = calculateMatrixPageRank(hm_list, seuil);

		ArrayList<String> result = calculateIndex(matrice, paths_list);	// TODO
		
		
	}
	
}