package se.su.ovning2;

import java.util.*;

public class Searcher implements SearchOperations {
  //TreeSet<Recording> treeSet = new TreeSet<Recording>();
  //TreeMap<String, Recording> treeMap = new TreeMap <>();
  private final Collection<Recording> recordings =new HashSet<>();
  private final TreeMap<Integer,Set<Recording>> recordingsByYear = new TreeMap<>();

  private final TreeMap<String, Set<Recording>> recordingsByArtist = new TreeMap<>();
  private final TreeMap<String, Set<Recording>> recordingsByGenre = new TreeMap<>();
  private final TreeMap<String, Set<Recording>> recordingsByTitle = new TreeMap<>();





  public Searcher(Collection<Recording> data) {
    recordings.addAll(data);

    for (Recording r : data) {
      Set<Recording> byArtist = recordingsByArtist.get(r.getArtist());
      Set<Recording> byTitle = recordingsByTitle.get(r.getTitle());
      Set<Recording> byYear = recordingsByYear.get(r.getYear());

      if (byArtist == null) {
        byArtist = new HashSet<>();
        recordingsByArtist.put(r.getArtist(), byArtist);
      }
      byArtist.add(r);
      if (byYear == null) {
        byYear = new HashSet<>();
        recordingsByYear.put(r.getYear(), byYear);
      }
      byYear.add(r);



      if (byTitle == null) {
        byTitle = new HashSet<>();
        recordingsByTitle.put(r.getTitle(), byTitle);
      }
      byTitle.add(r);

      for (String genre : r.getGenre()) {
        recordingsByGenre
                .computeIfAbsent(genre, k -> new HashSet<>())
                .add(r);
      }

     /*for(String s: r.getGenre()){
       Set<Recording> byGenre = recordingsByGenre.get(s);
       if (byGenre == null) {
         byGenre = new HashSet<>();
         recordingsByGenre.put(s, byGenre);
       }

       byGenre.add(r);
     }*/

    }
  }

  @Override
  public long numberOfArtists() { //map
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'numberOfArtists'");
    return recordingsByArtist.size();
  }

  @Override
  public long numberOfGenres() { //map
    return recordingsByGenre.size();
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'numberOfGenres'");
  }

  @Override
  public long numberOfTitles() { //map
    return recordingsByTitle.size();
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'numberOfTitles'");
  }

  @Override
  public boolean doesArtistExist(String name) { //map
    return recordingsByArtist.containsKey(name);
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'doesArtistExist'");
  }

  @Override
  public Collection<String> getGenres() { //map
    return recordingsByGenre.keySet();
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'getGenres'");
  }

  @Override
  public Recording getRecordingByName(String title) { //map
    if (recordingsByTitle.containsKey(title)) {
      return recordingsByTitle.get(title).iterator().next();
    }
    else return null;


    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'getRecordingByName'");
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) { //set
    Set<Recording> result = new HashSet<>();

    for (Set<Recording> set : recordingsByYear.tailMap(year).values()) {
      result.addAll(set);
    }

    return Collections.unmodifiableSet(result);
   // vet inte om man får göra såhär, men vi behöver en collection som returtyp
    // TODO Auto-generated method stub
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    // TreeSet med comparator (år stigande)
    SortedSet<Recording> result = new TreeSet<>(Comparator.comparingInt(Recording::getYear));

    for (Recording r : recordings) {
      if (r.getArtist().equals(artist)) {
        result.add(r);
      }
    }

    // returnera omodifierbar samling
    return Collections.unmodifiableSortedSet(result);
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException "Unimplemented method 'getRecordingsByArtistOrderedByYearAsc'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {

    if (!recordingsByGenre.containsKey(genre)) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableCollection(recordingsByGenre.get(genre));


    // TODO Auto-generated method stub
   // Set<Recording> recording=new HashSet<>();


    //throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenre'");

  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(
          String genre, int yearFrom, int yearTo) {

    Set<Recording> results = new HashSet<>();
//chatgpt
    for (Map.Entry<Integer, Set<Recording>> e :
            recordingsByYear.subMap(yearFrom, true, yearTo, true).entrySet()) {

      for (Recording r : e.getValue()) {
        if (r.getGenre().contains(genre)) {
          results.add(r);
        }
      }
    }

    return Collections.unmodifiableSet(results);
  }


  @Override

  public Collection<Recording> offerHasNewRecordings(Collection<Recording> newRecordings) {

    Set<Recording> result = new HashSet<>();

    for (Recording r : newRecordings) {
      if (!recordings.contains(r)) {
        result.add(r);
      }
    }

    return Collections.unmodifiableSet(result);
  }
  // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
}
