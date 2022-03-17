package base;

import java.util.Date;
import java.util.Objects;

import java.io.Serializable;

public class Note implements Comparable<Note>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis()); 
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	//lab3
	
	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return date+"\t"+title;
	}
	
	@Override
	public int compareTo(Note o) {
		return this.date.compareTo(o.date);
	}
	
	
}
