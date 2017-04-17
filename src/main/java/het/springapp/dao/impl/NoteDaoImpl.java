/**
 * 
 */
package het.springapp.dao.impl;

import java.util.List;

import het.springapp.dao.NoteDao;
import het.springapp.model.Note;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author heather
 *
 */

@Repository("noteDao")
public class NoteDaoImpl implements NoteDao {

	@Autowired(required=true)
	SessionFactory sessionFactory;

	public void persistNote(Note note) {
		sessionFactory.getCurrentSession().persist(note);
	}

	public Note findNoteById(Integer noteId) {
		return (Note) sessionFactory.getCurrentSession().get(Note.class, noteId);
	}

	public void updateNote(Note note) {
		sessionFactory.getCurrentSession().update(note);

	}
	public void deleteNote(Integer noteId) {
		Note note = findNoteById(noteId);
		sessionFactory.getCurrentSession().delete(note);

	}

	public List<Note> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Note.findAll");
		return query.list();
	}

	public List<Note> findNotesByPerson(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Note.findNotesByPerson");
		query.setParameter("USER_ID", userId);
		List noteList = query.list();
		return noteList;
	}

}
