package demo2api.entities.like;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.entities.like.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;
}