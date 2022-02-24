package base;

import java.util.ArrayList;

public class NoteBook {
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
	
}


