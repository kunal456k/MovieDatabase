package com.kunal456k.moviedatabase.helpers;

import androidx.annotation.Nullable;

import com.kunal456k.moviedatabase.db.entities.Company;
import com.kunal456k.moviedatabase.db.entities.Country;
import com.kunal456k.moviedatabase.db.entities.Genre;
import com.kunal456k.moviedatabase.db.entities.Language;

import java.math.BigDecimal;
import java.util.List;

public class MovieDetailsConvertHelper {
    private static final int THOUSAND = 1000;
    private static final int MILLION = THOUSAND * THOUSAND;
    private static final int BILLION = MILLION * THOUSAND;

    @Nullable
    public static String getCommaSeparatedLanguages(List<Language> languages){
        if (languages == null || languages.isEmpty()) return null;
        StringBuilder commaSeparatedLanguages = new StringBuilder();
        for (int i = 0; i < languages.size(); i++){
            commaSeparatedLanguages.append(languages.get(i).getLanguageName());
            if (i != languages.size() - 1){
                commaSeparatedLanguages.append(", ");
            }
        }
        return commaSeparatedLanguages.toString();
    }

    @Nullable
    public static String getCommaSeparatedGenres(List<Genre> genres){
        if (genres == null || genres.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++){
            builder.append(genres.get(i).getGenreName());
            if (i != genres.size() - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    @Nullable
    public static String getCommaSeparatedCompanies(List<Company> companies){
        if (companies == null || companies.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < companies.size(); i++){
            builder.append(companies.get(i).getCompanyName());
            if (i != companies.size() - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    @Nullable
    public static String getCommaSeparatedCountries(List<Country> countries){
        if (countries == null || countries.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < countries.size(); i++){
            builder.append(countries.get(i).getCountryName());
            if (i != countries.size() - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    @Nullable
    public static String getPresentableRevenue(int revenue){
        if (revenue == 0) return null;
        if (revenue < THOUSAND) return ""+revenue+" $";
        if (revenue > THOUSAND && revenue < MILLION){
            BigDecimal bigDecimal = new BigDecimal((double) revenue / THOUSAND);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()+" thousand $";
        }
        if (revenue > MILLION && revenue < BILLION){
            BigDecimal bigDecimal = new BigDecimal((double) revenue / MILLION);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()+" million $";
        }
        BigDecimal bigDecimal = new BigDecimal((double) revenue / BILLION);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()+" billion $";
    }
}
