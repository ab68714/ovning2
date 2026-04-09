package se.su.ovning2;

import java.util.Collection;
import java.util.Set;

public class Recording {
  private final int year;
  private final String artist;
  private final String title;
  private final String type;
  private final Set<String> genre;

  public Recording(String title, String artist, int year, String type, Set<String> genre) {
    this.title = title;
    this.year = year;
    this.artist = artist;
    this.type = type;
    this.genre = genre;

  }

  public String getArtist() {
    return artist;
  }

  public Collection<String> getGenre() {
    return genre;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
  }

  @Override
  public boolean equals(Object o) {
    //om objektet är samma returnera true
    if (this == o) return true;
    //om objektet är tomt eller objektsklassen är en annan returerna false
    if (o == null || getClass() != o.getClass()) return false;
    //förenklar kodläsning med lokal variabel
    Recording o1 = (Recording) o;
    //kollar om objekt e samma
    return year==(o1.getYear()) && artist.equals(o1.getArtist()) && title.equals(o1.getTitle());
  }

  /*@Override
  public int compareTo (Recording recording){

  }*/
}
