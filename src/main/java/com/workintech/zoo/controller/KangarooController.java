package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
        kangaroos.put(1, new Kangaroo(1, "Boksör", 1.80, 100, "male", true));
    }

    @GetMapping
    public List<Kangaroo> getAll() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id) {

        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addNewKangaroo(@RequestBody Kangaroo kangaroo) {

        if(kangaroo == null || kangaroo.getId() == null || kangaroo.getName() == null || kangaroo.getWeight() == 0.0
                || kangaroo.getHeight() == 0.0 || kangaroo.getGender() == null) {
            throw new ZooException("Lütfen geçerli bir kanguru giriniz", HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id,@RequestBody Kangaroo kangaroo) {

        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id) {

        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        Kangaroo deletedOne = kangaroos.get(id);
        kangaroos.remove(id);
        return deletedOne;
    }

}
