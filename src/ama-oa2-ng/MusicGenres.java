import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    You are given a map, called userSongs, with user names as keys and a list of all the songs that the user has listened to as values.

    You are also given a map, called songGenres, with song genre as keys and a list of all the songs within that genre as values.
    The song can only belong to only one genre.

    Write a function to return a map, where the key is a user name and the value is a a list of the user's favourite genre(s).
    Favourite genre is the most listened to genre. A User can have more than one favourite genre if he/she has listened to the
    same number of songs per each of the genres.

    Example 1

    Input:
    userSongs = {  
        "David": ["song1", "song2", "song3", "song4", "song8"],
        "Emma":  ["song5", "song6", "song7"]
    },

    songGenres = {  
        "Rock":    ["song1", "song3"],
        "Dubstep": ["song7"],
        "Techno":  ["song2", "song4"],
        "Pop":     ["song5", "song6"],
        "Jazz":    ["song8", "song9"]
    }

    Output: {  
        "David": ["Rock", "Techno"],
        "Emma":  ["Pop"]
    }

    Explanation:
    David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
    Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.

    Example 2:

    Input:
    userSongs = {  
        "David": ["song1", "song2"],
        "Emma":  ["song3", "song4"]
    },
    songGenres = {}

    Output: {  
        "David": [],
        "Emma":  []
    }
*/
public class MusicGenres {
    
    public void Test()
    {
        Map<String, List<String>> testUserSongs = new HashMap<String, List<String>>();
        testUserSongs.put("David", new ArrayList<String>(Arrays.asList("song1", "song2", "song3", "song4", "song8")));
        testUserSongs.put("Emma", new ArrayList<String>(Arrays.asList("song5", "song6", "song7")));

        Map<String, List<String>> testSongGenres = new HashMap<String, List<String>>();
        testSongGenres.put("Rock", new ArrayList<String>(Arrays.asList("song1", "song3")));
        testSongGenres.put("Dubstep", new ArrayList<String>(Arrays.asList("song7")));
        testSongGenres.put("Techno", new ArrayList<String>(Arrays.asList("song2", "song4")));
        testSongGenres.put("Pop", new ArrayList<String>(Arrays.asList("song5", "song6")));
        testSongGenres.put("Jazz", new ArrayList<String>(Arrays.asList("song8", "song9")));

        DisplayResults(FindFavouriteGenres(testUserSongs, testSongGenres));
    }

    public void DisplayResults(Map<String, List<String>> results)
    {
        Set<String> userNames = results.keySet();
        for (String userName : userNames)
        {
            System.out.println(userName);
            for (String favouriteGenre : results.get(userName))
                System.out.print(favouriteGenre + " ");
            System.out.println();
            System.out.println();
        }
    }

    /*
        Time Complexity: O(g.s) + O(u.[s + g]), where g refers to total number of genres, s refers to total number of songs, and u
        refers to total number of users.

        Space Complexity: O(s.g) + O(u.g), where s refers to number of songs, g refers to number of genres, and u refers to number of users.
    */
    public Map<String, List<String>> FindFavouriteGenres(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres)
    {
        // Initialise map that will be returned as the result
        Map<String, List<String>> results = new HashMap<>();
        
        // Create map to allow quick queries from song to genre
        Map<String, String> songToGenre = new HashMap<String, String>();
        for (String genre : songGenres.keySet())
        {
            for (String song : songGenres.get(genre))
                songToGenre.put(song, genre);
        }

        // Iterate through user names
        for (String userName : userSongs.keySet())
        {
            // Create a map that tracks user genre and their song count
            Map<String, Integer> userGenreToCount = new HashMap<String, Integer>();

            // Also need to maintain the 
            int maxGenreCount = 0;

            // Iterate through user songs
            for (String userSong : userSongs.get(userName))
            {
                // Get genre of the user song
                String songGenre = songToGenre.get(userSong);

                // Get current song genre count + 1
                int songGenreCount = userGenreToCount.getOrDefault(songGenre, 0) + 1;

                // Update song genre count in map + maintain max genre count
                userGenreToCount.put(songGenre, songGenreCount);
                maxGenreCount = Math.max(maxGenreCount, songGenreCount);
            }

            results.put(userName, new ArrayList<String>());

            // Add all song genres that have max genre count into user map
            for (String genreInUserCountMap : userGenreToCount.keySet())
                if (userGenreToCount.get(genreInUserCountMap) == maxGenreCount)
                    results.get(userName).add(genreInUserCountMap);
        }   

        return results;
    }
}
