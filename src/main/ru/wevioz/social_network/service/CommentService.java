package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;

@Getter
public class CommentService implements EntityService<Comment> {
    private final ArrayList<Comment> comments = new ArrayList<>();

    @Override
    public void add(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void remove(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Comment findById(int id) {
        return comments.stream().filter(comment -> comment.getId() == id).findFirst().get();
    }

    public Comment create(String content, Post post, User owner) throws TextLimitException {
        if (content.length() > Comment.textLimit) {
            throw new TextLimitException("content", Comment.textLimit);
        }

        Comment comment = new Comment(post, owner, content);
        post.addComment(comment);

        return comment;
    }
}