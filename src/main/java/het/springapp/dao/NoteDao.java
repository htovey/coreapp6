package het.springapp.dao;

import java.util.List;

import het.springapp.model.Note;

public interface NoteDao {
	
	void persistNote(Note note);
	  
	Note findNoteById(Integer noteId);
	  
	void updateNote(Note note);
	  
	void deleteNote(Integer noteId);
	
	public List<Note> findAll();
	
	public List<Note> findNotesByPerson(String userId);

}
