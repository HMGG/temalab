package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostWebController {

	@Autowired
	public PostRepo prepo;
	
	@RequestMapping("/savepost")
	public String savePost(@RequestParam("text") String t, @RequestParam("user") long u)
	{
		prepo.save(new Post(t, u));
		return "Done";
	}
	
	@RequestMapping("/findallposts")
	public String findAllPosts()
	{
		String result="<html>";
		
		for(Post p: prepo.findAll())
		{
			result += "<div>"+p.toString()+"<div>";
		}
		
		return result+"<html>";
	}
	
	@RequestMapping("/findpostbyid")
	public String findPostById(@RequestParam("id") long id)
	{
		return prepo.findById(id).toString();
	}
	
	@RequestMapping("/findpostbyuser")
	public String fetchPostByUser(@RequestParam("user") long u)
	{
		String result="<html>";
		
		for(Post p: prepo.findByUserid(u))
		{
			result += "<div>"+p.toString()+"<div>";
		}
		
		return result+"<html>";
	}
}
