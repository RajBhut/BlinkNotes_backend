package com.rajbhut.Blinknotes.entities;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Notes {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int notesid;
  private String branchname;
  private String subjectname;
  private String chaptername;
  private String notesname;
  private String notespath;
}

