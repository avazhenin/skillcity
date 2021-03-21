package com.edu.skillcity.Controllers;

import com.edu.skillcity.Exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    private int counter = 4;
    private List<Map<String, String>> list = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "some text 1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "some text 2");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "some text 3");
        }});
    }};

    @GetMapping
    public String main() {
        return "main";
    }

    @GetMapping("getlist")
    public List<Map<String, String>> getList() {
        return list;
    }

    @GetMapping("getlist/{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getListId(id);
    }

    private Map<String, String> getListId(@PathVariable String id) {
        return list.stream()
                .filter(listItem -> listItem.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("getlist")
    public Map<String, String> addOne(@RequestBody Map<String, String> listItem) {
        System.out.println(listItem);
        listItem.put("id", String.valueOf(counter++));

        list.add(listItem);

        return listItem;
    }

    @PutMapping("getlist/{id}")
    public Map<String, String> updOne(@PathVariable String id, @RequestBody Map<String, String> listItem) {
        System.out.printf("%s %s \n", id, listItem);
        Map<String, String> item = getListId(listItem.get("id"));

        item.putAll(listItem);
        item.put("id", id);

        return item;
    }

    @DeleteMapping("getlist/{id}")
    public void delOne(@PathVariable String id) {
        Map<String, String> foundList = getListId(id);
        list.remove(foundList);
    }
}
