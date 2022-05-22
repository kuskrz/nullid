package kus.krzysztof.nullid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kus.krzysztof.nullid.entity.Knumbers;
import kus.krzysztof.nullid.service.KnumbersService;

@RestController
public class KnumbersController {
	@Autowired
	KnumbersService ns;
	
	@GetMapping(value = "/number/{number}")
	public Knumbers selectNumber(@PathVariable Integer number) {
		Knumbers kn = ns.selectNumber(number);
		System.out.println(number);
		return kn;
	}
	
	@PostMapping(value = "/number/{number}")
	public Knumbers insertNumber(@RequestBody Knumbers knumber, @PathVariable Integer number) {
		Knumbers kn = ns.insertNumber(knumber, number);
		return kn;
	}
}
