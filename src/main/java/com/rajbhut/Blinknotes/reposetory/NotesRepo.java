package com.rajbhut.Blinknotes.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajbhut.Blinknotes.entities.Notes;

public interface NotesRepo extends JpaRepository<Notes ,Integer>{

public List<Notes> findByNotesname(String notesname);
public List<Notes> findByNotesnameContaining(String keyword);
	




@Query("select n from Notes n where (n.notesname like %:keyword%) OR (n.subjectname like %:keyword%) OR (n.branchname like %:keyword%)")
public List<Notes> findNotesByKeyword(@Param("keyword") String keyword);



  //List<Notes> findByNotesNameContaining(String notesName);
  
  // Additional methods (uncomment if needed)
//  List<Notes> findBySubjectNameContaining(String subjectName);
//  List<Notes> findByBranchNameContaining(String branchName);
//  List<Notes> findByChapterNameContaining(String chaptername);
}
