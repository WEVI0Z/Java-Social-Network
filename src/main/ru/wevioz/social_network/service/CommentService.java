package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class CommentService implements EntityService<Comment> {
    public final static int TEXT_LIMIT = 100;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Comment> comments = new ArrayList<>();

    @Override
    public void add(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void remove(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Optional<Comment> findById(int id) {
        return comments.stream().filter(comment -> comment.getId() == id).findFirst();
    }

    public Comment create(String content, Post post, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        Comment comment = new Comment(nextId.getAndIncrement(), post, owner, content);
        PostService.addComment(comment, post);

        return comment;
    }
}