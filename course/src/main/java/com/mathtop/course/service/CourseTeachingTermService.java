package com.mathtop.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingTermDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingTerm;

@Service
public class CourseTeachingTermService {
	
	@Autowired
	protected CourseTeachingTermDao dao;

	
	public String add(int teaching_year_begin, int teaching_year_end,int teaching_term,int weeks,Date week_begin) {
		return dao.add(teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin);
	}
	
	public void deleteById(String t_course_teaching_term_id){
		dao.deleteById(t_course_teaching_term_id);
	}
	public void update(String id, int teaching_year_begin, int teaching_year_end,int teaching_term,int weeks,Date week_begin) {
		dao.update(id, teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin);
	}
	
	public boolean isTermExist(int teaching_year_begin, int teaching_year_end,int teaching_term){
		return dao.isTermExist(teaching_year_begin, teaching_year_end, teaching_term);
	}
	public Page<CourseTeachingTerm> getPage(int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);
	}
	public List<CourseTeachingTerm> getAll() {
		return dao.getAll();
	}
}
