package com.nicholaslocicero.focus.diceware;

import edu.cnm.deepdive.diceware.Generator;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diceware")
public class DicewareController {

  private Random rng;
  private Generator generator;
  private ResourceBundle bundle;

  public DicewareController(Random rng, ResourceBundle bundle) {
    this.rng = rng;
    this.bundle = bundle;
    generator = new Generator(bundle, rng);
  }

  @GetMapping(produces = "application/json")
  public String[] generate(
      @RequestParam(value = "length", defaultValue = "6") int length,
      @RequestParam(value = "duplicates", defaultValue = "true") boolean duplicates) {
    return generator.next(length, duplicates);
  }

  @GetMapping(produces = "text/plain")
  public String generateScalar(
      @RequestParam(value = "length", defaultValue = "6") int length,
      @RequestParam(value = "duplicates", defaultValue = "true") boolean duplicates,
      @RequestParam(value = "delimiter", defaultValue = " ") String delimiter) {
    return String.join(delimiter, generator.next(length, duplicates));
  }

}
