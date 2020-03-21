package SimpleRegEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStreamReader;

public class SimpleRegEx {

	public static ArrayList<String> recherche(String keyword, String folder_path) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		File file = new File(folder_path);
		File[] list_files = file.listFiles();
		for (int i = 0; i < list_files.length; i++) {
			if (list_files[i].isFile()) {
				if (matchingAlgo(keyword, list_files[i].getPath())) {
					result.add(list_files[i].getPath());
				}
			}
		}

		return result;
	}

	public static boolean matchingAlgo(String keyword, String file_path) throws Exception {
		
		Pattern p=Pattern.compile(keyword); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
		String line;
		while ((line = br.readLine()) != null) {
			String reg = "[^a-zA-Z]";
			for (String s : Arrays.asList(line.split(reg))) {
				if (s.isEmpty())
					continue;
				String tmp = s.toLowerCase();
				Matcher m=p.matcher(tmp); 
				if (m.lookingAt()) {
					br.close();
					return true;
				}
			}
		}
		br.close();
		
		return false;
	}

	public static void main(String[] args) throws Exception {

		long startTime, endTime;
		String keyword = "Project(s|t)";
		keyword = keyword.toLowerCase();
		String folder_path = "database1664";

		startTime = System.currentTimeMillis();
		ArrayList<String> result_SR = recherche(keyword, folder_path);
		endTime = System.currentTimeMillis();
		System.out.println("SimpleRegEx a pris " + (endTime - startTime) + " ms : with " + result_SR.size());

	}

}