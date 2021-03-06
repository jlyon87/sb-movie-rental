package io.jlyon.movierental.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class MovieSearchResponse extends Paginateable {
	private List<MovieItem> results = new ArrayList<>();

	public List<MovieItem> getResults() {
		return results;
	}

	public void setResults(List<MovieItem> results) {
		this.results = results;
	}
}
