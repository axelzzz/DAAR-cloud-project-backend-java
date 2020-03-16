package library;

import java.io.File;

import utils.Parser;

public class Book {

	private File bookFile;
	private String nameFile;
		
	private String title;
	private String author;
	private String postingDate;
	private String releaseDate;
	private String language;
	
	public Book(String filepath) {
		
		bookFile = new File(filepath);
		nameFile = bookFile.getName();
		this.parseMetadata();
	}
	
	public void parseMetadata() { Parser.parseMetadata(this); }
	
	public String getNameFile() { return nameFile; }	
	public File getFile() { return bookFile; }
	
	public String getTitle() { return title; }	
	public String getAuthor() { return author; }
	public String getPostingDate() { return postingDate; }
	public String getReleaseDate() { return releaseDate; }
	public String getLanguage() { return language; }
	
	public void setNameFile(String nameFile) { this.nameFile = nameFile; }	
	public void setTitle(String title) { this.title = title; }	
	public void setAuthor(String author) { this.author = author; }	
	public void setPostingDate(String postingDate) { this.postingDate = postingDate; }	
	public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }	
	public void setLanguage(String language) { this.language = language; }	
	
	
	public String getMetadata() {
		
		return title+"\n"+author+"\n"+postingDate+"\n"+releaseDate+"\n"+language;
	}
}
