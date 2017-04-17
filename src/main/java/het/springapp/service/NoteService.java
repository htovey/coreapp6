package het.springapp.service;

import het.springapp.model.Note;
import het.springapp.model.Note;

import java.util.List;

import org.springframework.stereotype.Service;

public interface NoteService {
	public Note findByNoteId(Integer noteId);
	
	public void create(Note note);
	
	public void update(Note note);
	
	public void delete(Integer noteId);
	
	public List findNotesByPerson(String userId);
}
