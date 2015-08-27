package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.DatabaseLoggingType;

@Repository
public class DatabaseLoggingTypeDao extends SimpleDao<DatabaseLoggingType> {
	DatabaseLoggingTypeDao(){		
		super(DatabaseLoggingType.class,"t_database_logging_type");
	}
}