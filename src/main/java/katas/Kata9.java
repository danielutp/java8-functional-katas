package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(e->e.getVideos().stream())
                .map(e->{
                    String urlBoxArt = e.getBoxarts().stream()
                            .reduce((acumulador, object) -> {
                                if (object.getHeight() + object.getWidth() < acumulador.getWidth() + acumulador.getHeight()) {
                                    return object;
                                } else {
                                    return acumulador;
                                }
                            }).get().getUrl();

                    return ImmutableMap.of("id", e.getId(), "title", e.getTitle(), "time", new Date(), "url", urlBoxArt);
                }).collect(Collectors.toList());
    }
}
