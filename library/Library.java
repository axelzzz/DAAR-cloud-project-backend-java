package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import Betweenness.Betweenness;
import IndexTable.Index;
import KMP.KMP;
import SimpleIndexing.SimpleIndexing;

public class Library {

	private static Library library = null;
	private static final String DATABASE_PATH = "../database1664";
	private static final String DATABASE_INDEX_PATH = "../index";
	//private static final String DATABASE_PATH = "D:\Workspace\UPMC\DAAR\cloud\database1664";
	//private static final String DATABASE_INDEX_PATH = "D:\Workspace\UPMC\DAAR\cloud\index";
	
	private List<Book> books = new ArrayList<>();
	
	private Library(String folderPath) {
	
		try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach( p -> books.add(new Book(p.toString()) )
		        );
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Library getInstance() {
		if(library == null)
			library = new Library(DATABASE_PATH);
		
		return library;
	}
	
	/*only the 30 first*/
	public List<Book> getBooks() { return new ArrayList<>(books.subList(0, 29)); }
	
	
	public List<Book> getFilteredBooksKMP(String pattern) {		
		
		List<Book> filteredBooks = new ArrayList<>();
		
		try {
			ArrayList<String> result_KMP = KMP.recherche(pattern, DATABASE_PATH);	
			//result_KMP = Betweenness.classement(result_KMP, 0.75);
			
			for(String filepath : result_KMP) {
				Book b = new Book(filepath);
				if(b.getTitle() != "")
					filteredBooks.add(b);
			}							
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return filteredBooks;
		
	}
	
	
	public List<Book> getFilteredBooksIndex(String pattern) {		
		
		List<Book> filteredBooks = new ArrayList<>();
		
		try {
			ArrayList<String> result_Index = SimpleIndexing.recherche(pattern, DATABASE_PATH);	
			//result_KMP = Betweenness.classement(result_KMP, 0.75);
			
			for(String filepath : result_Index) {
				Book b = new Book(filepath);
				if(b.getTitle() != "")
					filteredBooks.add(b);
			}							
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return filteredBooks;
		
	}
	
	
	public int size() { return books.size(); }
}
