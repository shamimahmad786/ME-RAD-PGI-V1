package com.me.dataentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.AcademicResults;
import com.me.dataentry.modal.NcertTextBookDetails;

public interface NcertTextBookDetailsRepostory extends JpaRepository<NcertTextBookDetails, Integer>{

}
