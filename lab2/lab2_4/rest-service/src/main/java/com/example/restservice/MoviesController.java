package com.example.restservice;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

	private HashMap<Integer, Shows_Quotes> id_show = new HashMap<Integer, Shows_Quotes>();

	public MoviesController() {

		id_show.put(0, new Shows_Quotes("Harry Potter and the Order of the Phoenix (2007)", Arrays.asList("We've all got both light and darkness inside us. What matters is the part we choose to act on. That's who we really are.")));
		id_show.put(1, new Shows_Quotes("Grey’s Anatomy", Arrays.asList("You’re my person.")));
		id_show.put(2, new Shows_Quotes("Harry Potter and the Prisoner of Azkaban (2004)", Arrays.asList("The ones that love us never really leave us. ")));
		id_show.put(3, new Shows_Quotes("Rick and Morty (2014)", Arrays.asList("Having a family doesn’t mean that you stop being an individual. You know the best thing you can do for the people that depend on you? Be honest with them, even if it means setting them free.", "Nobody exists on purpose, nobody belongs anywhere, everybody's gonna die. Come watch TV?")));
		id_show.put(4, new Shows_Quotes("Jurassic Park (1993)", Arrays.asList("Life, uh, finds a way.")));
		id_show.put(5, new Shows_Quotes("The Lion King (1994)", Arrays.asList("Oh yes, the past can hurt. But you can either run from it, or learn from it.", "Everything you see exists together in a delicate balance. As king, you need to understand that balance and respect all the creatures, from the crawling ant to the leaping antelope. ")));
		id_show.put(6, new Shows_Quotes("The Fast and The Furious: Tokyo Drift (2006)", Arrays.asList("Life’s simple. You make choices and you don’t look back.")));

	}	

	@GetMapping("api/quote")
	public Quotes quote(@RequestParam(value = "show", required = false) String show_id) {
		Random rand = new Random();
		int random_id = rand.nextInt(id_show.size());
		List<String> quotes = id_show.get(random_id).getQuotes();
		int random_quote = rand.nextInt(quotes.size());
		return new Quotes(quotes.get(random_quote));
	}

	@GetMapping("api/quotes")
	public Quote_Show quotes(@RequestParam(value = "show", required = false) String show_id) {

		List<String> quotes = new ArrayList<>();

		int id = Integer.parseInt(show_id);
		if (id_show.containsKey(id)) {
			for (String quote: id_show.get(id).getQuotes()) {
				quotes.add(quote);
			}
			return new Quote_Show(id_show.get(id).getShow_name(), quotes);
		}
		return new Quote_Show("none",Arrays.asList("Show id not found"));
	}

	@GetMapping("api/shows")
	public List<Shows> show(){
		List<Shows> showList = new ArrayList<>();

		for (Integer id : id_show.keySet()) {
			showList.add(new Shows(id, id_show.get(id).getShow_name()));
		}

		return showList;
	}
}