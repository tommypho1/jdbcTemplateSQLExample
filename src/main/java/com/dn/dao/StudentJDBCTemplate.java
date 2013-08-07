package com.dn.dao;

import java.util.List;

import javax.sql.DataSource;



import org.springframework.jdbc.core.JdbcTemplate;

import com.dn.domain.Student;

public class StudentJDBCTemplate implements StudentDAO {
	   private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	   public void create(String name, Integer age) {
	      String SQL = "insert into Student (name, age) values (?, ?)";
	      
	      jdbcTemplateObject.update(SQL, name, age);
	      System.out.println("Created Record Name = " + name + " Age = " + age);
	      return;
	   }

	   public Student getStudent(Integer id) {
	      String SQL = "select * from Student where id = ?";
	      Student student = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new StudentMapper());
	      
	    /*  Student student1 = new Student();
	      String nameSQL = "select name from Student where id = ?";
	      String name = jdbcTemplateObject.queryForObject(nameSQL, String.class, id);
	      student1.setName(name);
	      String ageSQL = "select age from Student where id = ?";
	      int age = jdbcTemplateObject.queryForObject(ageSQL, Integer.class, id);
	      student1.setAge(age);
	      student1.setId(id);*/
	      
	      
	      return student;
	   }

	   public List<Student> listStudents() {
	      String SQL = "select * from Student";
	      List <Student> students = jdbcTemplateObject.query(SQL, 
	                                new StudentMapper());
	      return students;
	   }

	   public void delete(Integer id){
	      String SQL = "delete from Student where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted Record with ID = " + id );
	      return;
	   }

	   public void update(Integer id, Integer age){
	      String SQL = "update Student set age = ? where id = ?";
	      jdbcTemplateObject.update(SQL, age, id);
	      System.out.println("Updated Record with ID = " + id );
	      return;
	   }

	
	}