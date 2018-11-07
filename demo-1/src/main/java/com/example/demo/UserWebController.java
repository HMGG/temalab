package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWebController {
	
	@Autowired
	public UserRepo urepo;
	
	@RequestMapping("/saveuser")
	public String saveUser(@RequestParam("name") String n, @RequestParam("email") String e)
	{
		urepo.save(new User(n, e));
		return "Done";
	}
	
	@RequestMapping("/findallusers")
	public String findAllUsers()
	{
		String result="<html>";
		
		for(User u: urepo.findAll())
		{
			result += "<div>"+u.toString()+"<div>";
		}
		
		return result+"<html>";
	}
	
	@RequestMapping("/finduserbyid")
	public String findUserById(@RequestParam("id") long id)
	{
		return urepo.findById(id).toString();
	}
	
	@RequestMapping("/findbyname")
	public String fetchUserByName(@RequestParam("user") String n)
	{
		String result="<html>";
		
		for(User u: urepo.findByName(n))
		{
			result += "<div>"+u.toString()+"<div>";
		}
		return result+"<html>";
	}
}
