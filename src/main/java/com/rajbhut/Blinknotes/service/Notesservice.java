package com.rajbhut.Blinknotes.service;

import java.util.List;

import com.rajbhut.Blinknotes.entities.Notes;


public interface Notesservice {

	//public List<Notes> findBynotesname(String notesName) ;
	public Notes findBynotesid(int notesid);
	public List<Notes> findbyname(String name);
	public List<Notes> findbynamecontain(String name);
	public List<Notes> findbyanykeyword(String name);

	
}
