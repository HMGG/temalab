package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9124786094668112769L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="text")
	private String text;
	
	@Column(name="userid")
	private long userid;
	
	public Post(String t, long u) {
		text=t;
		userid=u;
	}
	
	public Post() {}
	
	@Override
	public String toString()
	{
		return String.valueOf(id)+" "+text+" "+String.valueOf(userid);
	}
}
