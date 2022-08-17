package com.easytobuy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

  @Autowired
  public ProfileRepository profileRepository;

  @PostMapping("/signUp")
  public ResponseEntity<Profile> signUp(@RequestBody Profile profile) {profileRepository.save(profile);
    return ResponseEntity.ok(profile);
  }

  @CrossOrigin(origins = "http://angular:4200")
  @GetMapping("/signIn/{name}/{password}")
  public Profile signIn(@PathVariable("name") String name, @PathVariable("password") String password) {
    return profileRepository.findByNameAndPassword(name,password).orElseThrow(() -> new RuntimeException("User Name or Password does not exists: " + name));
  }
}
