package com.kunal456k.moviedatabase.helpers;

import androidx.annotation.NonNull;

import com.kunal456k.moviedatabase.db.entities.Country;
import com.kunal456k.moviedatabase.db.entities.Language;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.db.entities.Genre;
import com.kunal456k.moviedatabase.db.entities.Company;

import java.util.List;

public class ResponseUpdateHelper {

    public static Movie updatedMovieDetailsResponseForBinding(@NonNull Movie movie) {
        List<Genre> genres = movie.getGenres();
        if (genres != null && !genres.isEmpty()){
            StringBuilder commaSeparatedGeneres = new StringBuilder();
            for (int i = 0; i < genres.size(); i++){
                commaSeparatedGeneres.append(genres.get(i).getGenreName());
                if (i != genres.size() - 1){
                    commaSeparatedGeneres.append(", ");
                }
            }
            movie.setCommaSeparatedGeneres(commaSeparatedGeneres.toString());
        }
        List<Company> movieCompanies = movie.getProductionCompanies();
        if (movieCompanies != null && !movieCompanies.isEmpty()){
            StringBuilder commaSeparatedCompanies = new StringBuilder();
            for (int i = 0; i < movieCompanies.size(); i++){
                commaSeparatedCompanies.append(movieCompanies.get(i).getCompanyName());
                if (i != movieCompanies.size() - 1){
                    commaSeparatedCompanies.append(", ");
                }
            }
            movie.setCommaSeparatedCompanies(commaSeparatedCompanies.toString());
        }
        List<Country> movieCountries = movie.getCountries();
        if (movieCountries != null && !movieCountries.isEmpty()){
            StringBuilder commaSeparatedCountries = new StringBuilder();
            for (int i = 0; i < movieCountries.size(); i++){
                commaSeparatedCountries.append(movieCountries.get(i).getCountryName());
                if (i != movieCountries.size() - 1){
                    commaSeparatedCountries.append(", ");
                }
            }
            movie.setCommaSeparatedCountries(commaSeparatedCountries.toString());
        }
        List<Language> movieLanguages = movie.getLanguages();
        if (movieLanguages != null && !movieLanguages.isEmpty()){
            StringBuilder commaSeparatedLanguages = new StringBuilder();
            for (int i = 0; i < movieLanguages.size(); i++){
                commaSeparatedLanguages.append(movieLanguages.get(i).getLanguageName());
                if (i != movieLanguages.size() - 1){
                    commaSeparatedLanguages.append(", ");
                }
            }
            movie.setCommaSeparatedLanguages(commaSeparatedLanguages.toString());
        }
        return movie;
    }
}
