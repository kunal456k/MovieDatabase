package com.kunal456k.moviedatabase.helpers;

import androidx.annotation.NonNull;

import com.kunal456k.moviedatabase.models.Country;
import com.kunal456k.moviedatabase.models.Language;
import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.models.MovieGenre;
import com.kunal456k.moviedatabase.models.ProductionCompany;

import java.util.List;

public class ResponseUpdateHelper {

    public static MovieDetails  updatedMovieDetailsResponseForBinding(@NonNull MovieDetails movieDetails) {
        List<MovieGenre> movieGenres = movieDetails.getGenres();
        if (movieGenres != null && !movieGenres.isEmpty()){
            StringBuilder commaSeparatedGeneres = new StringBuilder();
            for (int i = 0; i < movieGenres.size(); i++){
                commaSeparatedGeneres.append(movieGenres.get(i).getGenreName());
                if (i != movieGenres.size() - 1){
                    commaSeparatedGeneres.append(", ");
                }
            }
            movieDetails.setCommaSeparatedGeneres(commaSeparatedGeneres.toString());
        }
        List<ProductionCompany> movieCompanies = movieDetails.getProductionCompanies();
        if (movieCompanies != null && !movieCompanies.isEmpty()){
            StringBuilder commaSeparatedCompanies = new StringBuilder();
            for (int i = 0; i < movieCompanies.size(); i++){
                commaSeparatedCompanies.append(movieCompanies.get(i).getName());
                if (i != movieCompanies.size() - 1){
                    commaSeparatedCompanies.append(", ");
                }
            }
            movieDetails.setCommaSeparatedCompanies(commaSeparatedCompanies.toString());
        }
        List<Country> movieCountries = movieDetails.getCountries();
        if (movieCountries != null && !movieCountries.isEmpty()){
            StringBuilder commaSeparatedCountries = new StringBuilder();
            for (int i = 0; i < movieCountries.size(); i++){
                commaSeparatedCountries.append(movieCountries.get(i).getName());
                if (i != movieCountries.size() - 1){
                    commaSeparatedCountries.append(", ");
                }
            }
            movieDetails.setCommaSeparatedCountries(commaSeparatedCountries.toString());
        }
        List<Language> movieLanguages = movieDetails.getLanguages();
        if (movieLanguages != null && !movieLanguages.isEmpty()){
            StringBuilder commaSeparatedLanguages = new StringBuilder();
            for (int i = 0; i < movieLanguages.size(); i++){
                commaSeparatedLanguages.append(movieLanguages.get(i).getName());
                if (i != movieLanguages.size() - 1){
                    commaSeparatedLanguages.append(", ");
                }
            }
            movieDetails.setCommaSeparatedLanguages(commaSeparatedLanguages.toString());
        }
        return movieDetails;
    }
}
