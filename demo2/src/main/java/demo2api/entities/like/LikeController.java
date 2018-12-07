package demo2api.entities.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.like.LikeService;

@RestController
public class LikeController {
	
	@Autowired
	private LikeService likeService;

}