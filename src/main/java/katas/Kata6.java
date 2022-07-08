package katas;

import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .flatMap(element -> element.getBoxarts().stream())
                .reduce((acumulador, object) -> {
                    if (object.getHeight()+object.getWidth() > acumulador.getWidth()+acumulador.getHeight()){
                        return object;
                    }else{
                        return acumulador;
                    }

                }).get().getUrl();
    }
}
