package com.back.domain.wiseSaying;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class WiseSayingController {

    private int lastId = 5;
    private final List<WiseSaying> wiseSayingList = new ArrayList<>(){
        {
            add(new WiseSaying(1, "내용1", "작가1"));
            add(new WiseSaying(2, "내용2", "작가2"));
            add(new WiseSaying(3, "내용3", "작가3"));
            add(new WiseSaying(4, "내용4", "작가4"));
            add(new WiseSaying(5, "내용5", "작가5"));
        }
    };

    @GetMapping("/write")
    @ResponseBody
    public String write(@RequestParam String content, @RequestParam String author) {

        if(content == null || content.trim().length() == 0) {
            throw new RuntimeException("명언을 입력해주세요.");
        }

        if(author == null || author.trim().length() == 0) {
            throw new RuntimeException("작가를 입력해주세요.");
        }

        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayingList.add(wiseSaying);

        return "%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId());
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {

        String wiseSayings = wiseSayingList.stream()
                .map(w -> "<li>%s / %s / %s</li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                %s
                </ul>
                """.formatted(wiseSayings);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(
            @PathVariable int id
    ) {

        Optional<WiseSaying> wiseSaying = wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

        if(wiseSaying.isEmpty()) {
            throw new RuntimeException("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        wiseSayingList.remove(wiseSaying.get());

        return "%d번 명언이 삭제되었습니다".formatted(id);
    }

    @GetMapping("/modify/{id}")
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "기본값") String content,
            @RequestParam(defaultValue = "기본값") String author
    ) {

        WiseSaying wiseSaying = findById(id);
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        return "%d번 명언이 수정되었습니다.".formatted(wiseSaying.getId());
    }

    private WiseSaying findById(int id) {
        Optional<WiseSaying> wiseSaying = wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

        if(wiseSaying.isEmpty()) {
            throw new RuntimeException("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        return wiseSaying.get();


    }
}
