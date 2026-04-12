package se.su.ovning2;

import java.util.*;

public class Searcher implements SearchOperations {
  private final Collection<Recording> recordings = new HashSet<>();
  private final TreeMap<Integer,Set<Recording>> recordingsByYear = new TreeMap<>();
  private final TreeMap<String, Set<Recording>> recordingsByArtist = new TreeMap<>();
  private final TreeMap<String, Set<Recording>> recordingsByGenre = new TreeMap<>();
  private final TreeMap<String, Set<Recording>> recordingsByTitle = new TreeMap<>();
  private final Set<Recording> result = new HashSet<>();

  public Searcher(Collection<Recording> data) {

    for (Recording r : data) {
      recordingsByArtist.computeIfAbsent(r.getArtist(), k -> new HashSet<>()).add(r);
      recordingsByTitle.computeIfAbsent(r.getTitle(), k -> new HashSet<>()).add(r);
      recordingsByYear.computeIfAbsent(r.getYear(), k -> new HashSet<>()).add(r);

      for (String genre : r.getGenre()) {
        recordingsByGenre.computeIfAbsent(genre, k -> new HashSet<>()).add(r);
      }
    }

    recordings.addAll(data);
  }

  @Override
  public long numberOfArtists() { //map
    return recordingsByArtist.size();
  }

  @Override
  public long numberOfGenres() { //map
    return recordingsByGenre.size();

  }

  @Override
  public long numberOfTitles() { //map
    return recordingsByTitle.size();
  }

  @Override
  public boolean doesArtistExist(String name) { //map
    return recordingsByArtist.containsKey(name);
  }

  @Override
  public Collection<String> getGenres() { //map
    return recordingsByGenre.keySet();
  }

  @Override
  public Recording getRecordingByName(String title) { //map

    if (recordingsByTitle.containsKey(title)) {
      return recordingsByTitle.get(title).iterator().next();
    }
    else return null;
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) { //set

    for (Set<Recording> set : recordingsByYear.tailMap(year).values()) {
      result.addAll(set);
    }
    return Collections.unmodifiableSet(result);
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {

    SortedSet<Recording> result = new TreeSet<>(Comparator.comparingInt(Recording::getYear));

    for (Recording r : recordings) {
      if (r.getArtist().equals(artist)) {
        result.add(r);
      }
    }
    return Collections.unmodifiableSortedSet(result);
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {

    if (!recordingsByGenre.containsKey(genre)) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableCollection(recordingsByGenre.get(genre));

  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {


//chatgpt
    for (Map.Entry<Integer, Set<Recording>> e :
            recordingsByYear.subMap(yearFrom, true, yearTo, true).entrySet()) {

      for (Recording r : e.getValue()) {
        if (r.getGenre().contains(genre)) {
          result.add(r);
        }
      }
    }

    return Collections.unmodifiableSet(result);
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> newRecordings) {

    for (Recording r : newRecordings) {
      if (!recordings.contains(r)) {
        result.add(r);
      }
    }

    return Collections.unmodifiableSet(result);
  }
}
