package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String content;
	public TextNote(String title) {
		super(title);
	}
	
	//lab3
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	
	//lab4
	/**
	* load a TextNote from File f
	*
	* the tile of the TextNote is the name of the file
	* the content of the TextNote is the content of the file
	*
	* @param File f
	*/
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	/**
	* get the content of a file
	*
	* @param absolutePath of the file
	* @return the content of the file
	*/
	private String getTextFromFile(String absolutePath) {
		String result = "";
		
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));
			while((line = br.readLine()) != null) {
    			result += line;
    			System.out.println(result);
    		}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		return result;
	}
	/**
	* export text note to file
	*
	*
	* @param pathFolder path of the folder where to export the note
	* the file has to be named as the title of the note with extension ".txt"
	*
	* if the tile contains white spaces " " they has to be replaced with underscores "_"
	*
	*
	*/
	public void exportTextToFile(String pathFolder) {
		String fileTitle = "";
		fileTitle = this.getTitle().replaceAll(" ", "_");
		try {
			File file = new File(pathFolder + fileTitle + ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//
	public void updateContent(String content) {
		this.content = content;
	}
}

