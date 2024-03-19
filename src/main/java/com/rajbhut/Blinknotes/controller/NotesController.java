package com.rajbhut.Blinknotes.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rajbhut.Blinknotes.entities.Notes;
import com.rajbhut.Blinknotes.reposetory.NotesRepo;
import com.rajbhut.Blinknotes.serviceimpl.Notesserviceimpl;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notes")
public class NotesController {
	

	    @Autowired
	    private Notesserviceimpl notesService;
	    @Autowired
	     private NotesRepo noterepo;
	    
	    
//	    @GetMapping({"/{notesId}"})	    
//	    public ResponseEntity<Notes> getnotesbyid(@PathVariable Integer notesId)
//		{
//			return ResponseEntity.ok(this.notesService.findBynotesid(notesId));
//			
//		}
	    @GetMapping("/nn/{notesname}")	    
	    public List<Notes> getnotesbyname(@PathVariable String notesname)
		{
			return(this.notesService.findbyname(notesname));
			
		}
	    @GetMapping("/nnk/{keyword}")
	    public List<Notes> getnotesbykey(@PathVariable String keyword)
	    {
	    	return this.notesService.findbynamecontain(keyword);	
	    }
	    @GetMapping("/k/{keyword}")
	    public List<Notes> getnotesbyanyword(@PathVariable String keyword)
	    {
	    	return this.notesService.findbyanykeyword(keyword);	
	    }
	    
//	    
//	    @GetMapping("/notes/byNotesName")
//   public Notes getNotesByNotesid(@RequestParam int notesid) {
//        return this.notesService.findBynotesid(notesid);
//	    }

//	    @GetMapping("/notes/byNotesName")
//	    public List<Notes> getNotesByNotesName(@RequestParam String notesName) {
//	        return this.notesService.findBynotesname(notesName);
//	    }
//
//	    @GetMapping("/notes/bySubjectName")
//	    public List<Notes> getNotesBySubjectName(@RequestParam String subjectName) {
//	        return notesService.findBySubjectName(subjectName);
//	    }
//
//	    @GetMapping("/notes/byBranchName")
//	    public List<Notes> getNotesByBranchName(@RequestParam String branchName) {
//	        return notesService.findByBranchName(branchName);
//	    }
//	    
//	    @GetMapping("/notes/byChapterName")
//	    public List<Notes> getNotesByChapterName(@RequestParam String chapterName) {
//	        return notesService.findByChapterName(chapterName);
//	    }
	    
	    
	    
	    
	    @Value("${upload.dir}")
	    private String uplodDir;
	    
	    @PostMapping("/upload")
	    public ResponseEntity<String>uploadFile(@RequestParam("file") MultipartFile file)
	    {
	    	try {
	    		File directory = new File(uplodDir);
	    		if(!directory.exists())
	    		{
	    			directory.mkdir();
	    		}
	    		
	    		String originalFilename = file.getOriginalFilename();
	    		String filepath = uplodDir +File.pathSeparator+ originalFilename;
	    		
	    		file.transferTo(new File(filepath));
	    		System.out.println("Enter try part");
	    		return ResponseEntity.ok("File uploded succesfully");
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to uplod file");
			}
	    }
	    
	    
	    @GetMapping("/{noteid}/pdf")
	   public ResponseEntity<byte[]>getpdf(@PathVariable int noteid) throws IOException
	   {
		   String filename = this.notesService.findBynotesid(noteid).getNotespath();
		   String filepath = uplodDir+File.pathSeparator +filename;
		   System.out.println(filepath);
			java.nio.file.Path path = java.nio.file.Paths.get(filepath);
			
			byte[] content = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(content);
		
	   }
	   
	   
	   @CrossOrigin(origins = "http://localhost:3000/material")
	   @PostMapping("/upload/k")
	   public ResponseEntity<?> handleFileUpload(@RequestParam("filename") String filename ,@RequestParam("notename")String notename,
			   @RequestParam("subjectname") String subjectname , @RequestParam("chaptername")String chaptername , @RequestParam("branchname")String branchname,
			   @RequestPart("file") MultipartFile file)
	   {
		   
		   try {
	    		File directory = new File(uplodDir);
	    		if(!directory.exists())
	    		{
	    			directory.mkdir();
	    		}
	    		
	    		String originalFilename = file.getOriginalFilename();
	    		String filepath = uplodDir +File.pathSeparator+ originalFilename;
	    		
	    		file.transferTo(new File(filepath));
	    		Notes k = new Notes();
	    		k.setBranchname(branchname);
	    		k.setChaptername(chaptername);
	    		k.setSubjectname(subjectname);
	    		k.setNotespath(originalFilename);
	    		k.setNotesname(notename);
	    		this.noterepo.save(k);
	    		return ResponseEntity.ok("File uploded succesfully");
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to uplod file");
			}
		
	   }
	   
}
