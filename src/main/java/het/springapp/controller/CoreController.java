package het.springapp.controller;

import het.springapp.model.Note;
import het.springapp.service.NoteService;
import het.springapp.service.PersonService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class CoreController {

	private NoteService noteService;
	private PersonService personService;
	private boolean firstLoad = false;

		
	public final Log log = LogFactory.getLog(CoreController.class);
	@Autowired
	public CoreController(NoteService noteService, PersonService personService) {
		this.noteService = noteService;
		this.personService = personService;
	}
	
//	@RequestMapping(value= "/coreapp6", method = RequestMethod.GET)
//	public ModelAndView springapp(@RequestParam(value = "userId", required = false) String userId) {
//		if (userId == null || userId.equals("")) {
//			userId = "htovey";
//		}
//		populateModel(userId);
//		ModelAndView view = new ModelAndView("index");
//		return view;
//	}
	@RequestMapping(value= "/index", method = RequestMethod.GET, produces = "text/javascript")
	public String springapp(@RequestParam(value = "userId", required = false) String userId) {
		return "index";
	}
	
	private void populateModel(String userId) {
		personService.findByPersonId(userId);
	}
	
	@RequestMapping(value = "/createNote", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public @ResponseBody String createNote(@RequestBody Note note) {
		
		if (note.getNoteId() == 0) {
			//new note
			log.info("attempting to create note: "+note.getUserId());
			noteService.create(note);
			return "success";
		} else {
			log.info("attempting to update note: "+note.getUserId());
			noteService.update(note);
			return "success";
		}
		
		
	}
	
	@RequestMapping(value = "/getNotesForUser", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List getNotesForUser(String userId) {
		log.info("getting notes for "+userId);
		List noteList = noteService.findNotesByPerson(userId);
		
		return noteList;
	}
	
	@RequestMapping(value = "deleteNote", method = RequestMethod.POST)
	@ResponseBody
	public String deleteNote(Integer noteId) {
		log.info("deleting note "+noteId);
		noteService.delete(noteId);
		return "success";
	}
	
}