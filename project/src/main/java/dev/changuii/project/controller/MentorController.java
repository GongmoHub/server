package dev.changuii.project.controller;

import dev.changuii.project.dto.MentorDto;
import dev.changuii.project.dto.TokenDto;
import dev.changuii.project.service.impl.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    private final MentorServiceImpl mentorService;
    public MentorController(@Autowired MentorServiceImpl mentorServiceImpl) {
        this.mentorService = mentorServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<MentorDto> createMentor(@RequestBody MentorDto mentorDto){
        MentorDto createMentor = mentorService.createMentor(mentorDto);
        return ResponseEntity.ok().body(createMentor);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signinMentor(
            @RequestBody MentorDto mentorDto
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.mentorService.signinMentor(mentorDto));
    }


    @GetMapping("/{mentorId}")
    public ResponseEntity<MentorDto> readMentor(@PathVariable("mentorId") Long mentorId){
        MentorDto readMentor = mentorService.readMentor(mentorId);
        return ResponseEntity.ok().body(readMentor);
    }

    @GetMapping("")
    public ResponseEntity<List<MentorDto>> readAllMentors(){
        List<MentorDto> mentors = mentorService.readAllMentors();
        return ResponseEntity.ok().body(mentors);
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteMentor(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        this.mentorService.deleteByEmailAndPassword(email, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(true);
    }




}
