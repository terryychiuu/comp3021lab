package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.io.Serializable;

public class Folder implements Comparable<Folder>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	//lab 3
	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}
	public void sortNotes() {
		Collections.sort(notes, Collections.reverseOrder());
	}
	public List<Note> searchNotes(String keywords) {
		String keyUp = keywords.toUpperCase();
		
		List<String> keyArr = new ArrayList<>(Arrays.asList(keyUp.split("\\W+")));		
		
		List<Note> resultOrAnd = new ArrayList<>();
		
		int numOR = 0;
		
		for(int i=1; i<keyArr.size()-1; i++) {
			if(keyArr.get(i).equals("OR")) {
				
				for(Note n : notes) {
					if(n instanceof ImageNote) {
						String s = n.getTitle().toUpperCase();
						//1. need to split into string array(can't use `contains`) 2. need to remove all punctuation(`\\W+`)
						String[] words = s.split("\\W+");
						boolean contains = Arrays.stream(words).anyMatch(keyArr.get(i-1)::equals) 
								|| Arrays.stream(words).anyMatch(keyArr.get(i+1)::equals);
						if(contains) {
							resultOrAnd.add(n);
						}
					}
					if(n instanceof TextNote) {
						String s = n.getTitle().toUpperCase()+" "+((TextNote) n).getContent().toUpperCase();
						String[] words = s.split("\\W+");
						boolean contains = Arrays.stream(words).anyMatch(keyArr.get(i-1)::equals) 
								|| Arrays.stream(words).anyMatch(keyArr.get(i+1)::equals);
						if(contains) {
							resultOrAnd.add(n);
						}
					}
					
				}
				keyArr.set(i-1, "-1");
				keyArr.set(i+1, "-1");
				numOR++;
			}
		}
		
		
		//remove OR 
		while(keyArr.contains("OR")) {
			keyArr.remove("OR");
		}
		//calculate number of AND operator
		int numAND = keyArr.size()-1-numOR;
		//remove key done by OR
		while(keyArr.contains("-1")) {
			keyArr.remove("-1");
		}

		
		
		for(String k : keyArr) {
			for(Note n : notes) {
				if(n instanceof ImageNote) {
					String s = n.getTitle().toUpperCase();
					String[] words = s.split("\\W+");
					boolean contains = Arrays.stream(words).anyMatch(k::equals) 
							|| Arrays.stream(words).anyMatch(k::equals);
					if(contains) {
						resultOrAnd.add(n);
					}
				}
				else if(n instanceof TextNote) {
					String s = n.getTitle().toUpperCase()+" "+((TextNote) n).getContent().toUpperCase();
					String[] words = s.split("\\W+");
					boolean contains = Arrays.stream(words).anyMatch(k::equals) 
							|| Arrays.stream(words).anyMatch(k::equals);
					if(contains) {
						resultOrAnd.add(n);
					}
				}
			}
		}
		
		
		List<Note> result = new ArrayList<Note>();
		
		for(Note n : resultOrAnd) {
			if(numAND+1 == Collections.frequency(resultOrAnd, n)) {
				if(result.contains(n)) {
					continue;
				}
				result.add(n);
			}
		}
		
		return result;
	}
	
}
