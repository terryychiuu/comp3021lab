package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	public boolean insertNote(String folderName, Note note) {
		Folder tem = null;
		for(Folder f : folders) {
			if(f.getName().equals(folderName)) {
				tem = f;
			}
		}
		
		if(tem == null) {
			Folder f = new Folder(folderName);
			folders.add(f);
			f.addNote(note);
		}else {
			boolean contain = false;
			for(Note n : tem.getNotes()) {
				if(n.equals(note)) {
					contain = true;
					System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
					return false;
				}
			}
			if(contain == false) {
				tem.addNote(note);
			}
		}
		return true;
	}
	
	//lab3
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public void sortFolders() {
		for(Folder f : folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<>();
		for(Folder f : folders) {
			result.addAll(f.searchNotes(keywords));
		}
		return result;
	}
	
	//lab4
	/**
	* method to save the NoteBook instance to file
	*
	* @param file, the path of the file where to save the object serialization
	* @return true if save on file is successful, false otherwise
	*/
	public boolean save(String file){
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(this);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	*
	* Constructor of an object NoteBook from an object serialization on disk
	*
	* @param file, the path of the file for loading the object serialization
	*/
	public NoteBook(String file){
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			NoteBook n = (NoteBook) in.readObject();	//cast down to NoteBook, as it return Object
			this.folders = n.getFolders();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//
	public void addFolder(String folderName) {
		Folder folder = new Folder(folderName);
		folders.add(folder);
	}
	
}


