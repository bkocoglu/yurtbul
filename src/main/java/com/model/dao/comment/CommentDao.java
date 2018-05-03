package com.model.dao.comment;

import com.model.entities.Advert;
import com.model.entities.Comment;

import java.util.List;

public interface CommentDao {

    public List<Comment> getPendingComment(Advert advert);

    public List<Comment> findByAdvert(Advert advert);

    public List<Comment> getNotifiedComment(Advert advert);

    public Comment findById(int id);

    public List<Integer> getStars(Advert advert);

}
