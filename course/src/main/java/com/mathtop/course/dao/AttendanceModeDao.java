package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.AttendanceMode;

@Repository
public class AttendanceModeDao extends SimpleDao<AttendanceMode> {
	AttendanceModeDao(){		
		super(AttendanceMode.class,"t_attendance_mode");
	}
}
