package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)

*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();


        return movieLists.stream()
                .flatMap(e->e.getVideos().stream())
                .map(e -> {
                    String urlBoxArt = e.getBoxarts().stream()
                            .reduce((acumulador, object) -> {
                                if (object.getHeight() + object.getWidth() < acumulador.getWidth() + acumulador.getHeight()) {
                                    return object;
                                } else {
                                    return acumulador;
                                }
                            }).get().getUrl();
                    return ImmutableMap.of("id", e.getId(), "title", e.getTitle(), "boxart", urlBoxArt);
                }).collect(Collectors.toList());
    }
}
