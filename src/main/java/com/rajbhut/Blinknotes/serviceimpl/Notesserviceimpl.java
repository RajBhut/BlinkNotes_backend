package com.rajbhut.Blinknotes.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajbhut.Blinknotes.entities.Notes;
import com.rajbhut.Blinknotes.reposetory.NotesRepo;
import com.rajbhut.Blinknotes.service.Notesservice;

@Service
public class Notesserviceimpl implements Notesservice{

  @Autowired
  private NotesRepo noterepo;

@Override
public Notes findBynotesid(int notesid) {
	
	Notes note = this.noterepo.findById(notesid).orElseThrow();
	return note;
}

public List<Notes> findbyname(String name)
{
	return this.noterepo.findByNotesname(name);
}

public List<Notes> findbynamecontain(String name)
{
	return this.noterepo.findByNotesnameContaining(name);
}

@Override
public List<Notes> findbyanykeyword(String name) {
	
	return this.noterepo.findNotesByKeyword(name);
}

//@Override
//public List<Notes> findBynotesname(String notesName) {
//	// TODO Auto-generated method stub
//	return null;
//}

  

  // Additional methods (uncomment if needed)
//  public List<Notes> findBySubjectName(String subjectName) {
//    return this.noterepo.findBySubjectNameContaining(subjectName);
//  }
//  public List<Notes> findByBranchName(String branchName) {
//    return this.noterepo.findByBranchNameContaining(branchName);
//  }
//  public List<Notes> findByChapterName(String chaptername) {
//    return this.noterepo.findByChapterNameContaining(chaptername);
//  }



}
