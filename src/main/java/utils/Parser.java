package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import library.Book;

public class Parser {
	
	private final static int NBLIGNES = 100;
	
	public static void listFilesFolder(final File folder) {

		for(final File fileEntry : folder.listFiles()) {
			/*
	        if (fileEntry.isDirectory()) {
	            listFilesFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	        */
			if (!fileEntry.isDirectory()) 
				System.out.println(fileEntry.getName());
		
		}
	}
	
	
	public static void streamFilesFolder(String path) {
		
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void parseMetadata(Book b) {
		
		try (BufferedReader br = new BufferedReader( new FileReader(b.getFile()) )) {
			
			int i=0;
			String line;
			
			while(i<NBLIGNES) {
				
				if( (line=br.readLine()).contains("Title:") ) 
					b.setTitle( line.replace("Title: ", "") );
				else {
					if(line.contains("Author:")) 
						b.setAuthor( line.replace("Author: ", "") );					
					else {
						if(line.contains("Posting Date:")) 
							b.setPostingDate( line.replace("Posting Date: ", "") );
						else {
							if(line.contains("Release Date:"))
								b.setReleaseDate( line.replace("Release Date: ", "") );
							else {
								if(line.contains("Language:")) 
									b.setLanguage( line.replace("Language: ", "") );								
			
							}
						}
					}
				}
				
				i++;
			}
			
			
			if(b.getAuthor() == null)
				b.setAuthor("Unknown");
			if(b.getTitle() == null)
				b.setTitle("");
			if(b.getPostingDate() == null)
				b.setPostingDate("");
			if(b.getReleaseDate() == null)
				b.setReleaseDate("");
			if(b.getLanguage() == null)
				b.setLanguage("");
				
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
    
	public static void main(String[] args) {
		
		/*
		final File folder = new File("/root/bigFatWorkspace/M2/DAAR/DAAR_Online_Project_Django/database");
		listFilesFolder(folder);
	    */
		//streamFilesFolder("/root/bigFatWorkspace/M2/DAAR/DAAR_Online_Project_Django/database");
	
	    Book b = new Book("/root/bigFatWorkspace/M2/DAAR/DAAR_Online_Project_Django/database/9.txt.utf-8");
	    System.out.println(b.getMetadata());
	}

}
