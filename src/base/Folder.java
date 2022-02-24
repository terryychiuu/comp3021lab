package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	private String name;
	private ArrayList<Note> notes;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for(Note note : notes) {
			if(note instanceof TextNote) {
				nText++;
			}else if(note instanceof ImageNote) {
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}
	
}
