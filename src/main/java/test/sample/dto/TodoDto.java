package test.sample.dto;

import lombok.Data;
import test.sample.Board;

@Data
public class TodoDto {

    private Long id;

    private String title;

    private boolean done;

    public TodoDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.done = board.isDone();
    }
}
