package test.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.sample.dto.TodoDto;
import test.sample.dto.TodoRequest;
import test.sample.dto.TodoResponse;
import test.sample.repository.BoardRepository;
import test.sample.s3.S3Uploader;
import test.sample.service.BoardService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final S3Uploader s3Uploader;


    @GetMapping("/api/todos")
    public TodoResponse getTodos() {
        List<Board> boards = boardRepository.findAll();
        List<TodoDto> todos = boards.stream().map(TodoDto::new).collect(Collectors.toList());
        TodoResponse todoResponse = new TodoResponse(todos);
        return todoResponse;
    }

    @PostMapping("/api/todos")
    public TodoDto createTodo(@RequestPart("todo") TodoRequest request, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Board savedBoard = null;
        if (file == null) {
            savedBoard = boardRepository.save(Board.builder().title(request.getTitle()).build());
        } else {
            savedBoard = boardRepository.save(Board.builder().title(request.getTitle()).thumbnail(s3Uploader.uploadFile(file, "todo")).build());
        }
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
