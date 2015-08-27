package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.AttendanceState;

@Repository
public class AttendanceStateDao extends SimpleDao<AttendanceState> {
	AttendanceStateDao(){		
		super(AttendanceState.class,"t_attendance_state");
	}
}
