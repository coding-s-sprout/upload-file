package test.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.sample.dto.TodoDto;
import test.sample.dto.TodoRequest;
import test.sample.dto.TodoResponse;
import test.sample.repository.BoardRepository;
import test.sample.service.BoardService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


    @GetMapping("/api/todos")
    public TodoResponse getTodos() {
        List<Board> boards = boardRepository.findAll();
        List<TodoDto> todos = boards.stream().map(TodoDto::new).collect(Collectors.toList());
        TodoResponse todoResponse = new TodoResponse(todos);

        return todoResponse;
    }

    @PostMapping("/api/todos")
    public TodoDto createTodo(@RequestBody TodoRequest request) {
        Board board = Board.builder().title(request.getTitle()).build();
        Board savedBoard = boardRepository.save(board);
        return new TodoDto(savedBoard);
    }

    @PutMapping("/api/todos/{board_id}")
    public TodoDto putTodo(@PathVariable("board_id") Long id) {
        boardService.putTodo(id);
        Optional<Board> findBoard = boardRepository.findById(id);
        if (findBoard.isPresent()) {
            return new TodoDto(findBoard.get());
        }
        return null;
    }

    @DeleteMapping("/api/todos/{board_id}")
    public void deleteTodo(@PathVariable("board_id") Long id) {
        boardRepository.deleteById(id);
    }
}
