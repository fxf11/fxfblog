package com.fxf.blogbs.controller;

import com.fxf.blogbs.dao.CommentMapper;
import com.fxf.blogbs.pojo.Comment;
import com.fxf.blogbs.utils.RegexMatchesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;


    @PostMapping("insertComment")
    public String insertComment(@RequestBody Comment comment){

        String commentName = comment.getCommentName();
        String commentContent = comment.getCommentContent();
        String commentEmail = comment.getCommentEmail();
        System.out.println(comment);
        if (RegexMatchesUtils.isBlank(commentName) || RegexMatchesUtils.isBlank(commentContent) || RegexMatchesUtils.isEmail(commentEmail)){
            return "error";
        }
        if (comment!=null){
            comment.setCommentDate(new Date());
            int comment1 = commentMapper.insert(comment);
            if (comment1>0){
                return "success";
            }
        }
        return "null";

    }

    @GetMapping("queryAllComment")
    public List<Comment> queryAllComment(){
        List<Comment> comments = commentMapper.selectList(null);
        return comments;
    }

}
