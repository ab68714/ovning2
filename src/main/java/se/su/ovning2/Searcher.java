package se.su.ovning2;

import java.util.*;

public class Searcher implements SearchOperations {
  //TreeSet<Recording> treeSet = new TreeSet<Recording>();
  //TreeMap<String, Recording> treeMap = new TreeMap <>();
  private final Set<Recording> recordings =new HashSet<>();
  private final TreeMap<Integer,Set<Recording>> recordingsByYear = new TreeMap<>();

  private final Map<String, Set<Recording>> recordingsByArtist = new HashMap<>();
  private final Map<String, Set<Recording>> recordingsByGenre = new HashMap<>();
  private final Map<String, Set<Recording>> recordingsByTitle = new HashMap<>();

  public Searcher(Collection<Recording> data) {

    for (Recording r : data){
      Set<Recording> byArtist = recordingsByArtist.get(r.getArtist());
      Set<Recording> byTitle = recordingsByTitle.get(r.getTitle());
      Set<Recording> byYear = recordingsByYear.get(r.getYear());

      if (byArtist == null) {
        byArtist = new HashSet<>();
        recordingsByArtist.put(r.getArtist(), byArtist);
      }
      byArtist.add(r);
      if(byYear == null) {
        byYear = new HashSet<>();
        recordingsByYear.put(r.getYear(), byYear);
      }
      byYear.add(r);

     if (byTitle == null) {
       byTitle = new HashSet<>();
       recordingsByTitle.put(r.getTitle(), byTitle);
     }
     byTitle.add(r);

     for(String s: r.getGenre()){
       Set<Recording> byGenre = recordingsByGenre.get(s);
       if (byGenre == null) {
         byGenre = new HashSet<>();
       }
       recordingsByGenre.put(s, byGenre);
     }
    }
  }

  @Override
  public long numberOfArtists() {
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'numberOfArtists'");
    return recordingsByArtist.size();
  }

  @Override
  public long numberOfGenres() {
    return recordingsByGenre.size();
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'numberOfGenres'");
  }

  @Override
  public long numberOfTitles() {
    return recordingsByTitle.size();
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'numberOfTitles'");
  }

  @Override
  public boolean doesArtistExist(String name) {
    return recordingsByArtist.containsKey(name);
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'doesArtistExist'");
  }

  @Override
  public Collection<String> getGenres() {
    return recordingsByGenre.keySet();
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'getGenres'");
  }

  @Override
  public Recording getRecordingByName(String title) {
    return recordingsByTitle.get(title).iterator().next();
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'getRecordingByName'");
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    List<Recording> result = new ArrayList<>();

    for (Set<Recording> set : recordingsByYear.tailMap(year).values()) {
      result.addAll(set);
    }

    return result;
   // vet inte om man får göra såhär, men vi behöver en collection som returtyp
    // TODO Auto-generated method stub
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getRecordingsByArtistOrderedByYearAsc'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    // TODO Auto-generated method stub
   // Set<Recording> recording=new HashSet<>();

    for (Recording r : this.recordingsByArtist.get(genre)) {
      if (r.getGenre().contains(genre)) {
        recordings.add(r);
      }
    }
    return recordings;

    //throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenre'");

  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenreAndYear'");
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
  }
}
