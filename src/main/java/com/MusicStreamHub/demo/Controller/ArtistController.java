//package com.MusicStreamHub.demo.Controller;
//
//
//import com.MusicStreamHub.demo.service.*;
//import org.jmusixmatch.MusixMatchException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import static com.MusicStreamHub.demo.service.LyricsService.chosenTrack;
//
//
//@Controller
//@RequestMapping("/searchArtistQuery")
//public class ArtistController {
//
//    private final ArtistService artistService;
//    private final TrackService trackService;
//
//    private final SearchService searchService;
//    private final LyricsService lyricsService;
//    private final VideoService videoService;
//
//    public ArtistController(ArtistService artistService, TrackService trackService, SearchService searchService, LyricsService lyricsService, VideoService videoService) throws IOException {
//        this.artistService = artistService;
//        this.trackService = trackService;
//        this.searchService = searchService;
//        this.lyricsService = lyricsService;
//        this.videoService = videoService;
//    }
//
//@GetMapping()
//    public ModelAndView searchArtist(@RequestParam(value = "query", required = false) String query,
//                                     @RequestParam(value = "queryArtistImage", required = false) String queryArtistImage,
//                                     @RequestParam(value = "queryTrackArtist", required = false) String queryTrackArtist,
//                                     @RequestParam(value = "specificArtistQuery", required = false) String queryArtistDetails,
//                                     @RequestParam(value = "fetchLyrics", required = false) boolean fetchLyrics ,
//                                     @RequestParam(value = "trackName", required = false) String trackName,
//                                     @RequestParam(value = "artistName", required = false) String artistName) throws IOException, MusixMatchException {
//
//        if (fetchLyrics && query == null) {
//            // Handle request to fetch lyrics here
//            String lyrics = lyricsService.getTrackLyrics(trackName, artistName);
//            List<Map<String, Object>> trackList = null;
//            trackList = trackService.getArtistTracks(artistName);
//            ArtistService artistService1 = new ArtistService();
//            String chosenArtistImgUrl = artistService1.getArtistImageUrl(queryArtistImage);
//            String chosenArtist = LyricsService.getChosenArtist();
//            String artistNameDebug = queryTrackArtist;
//            List<Map<String, Object>> artistList = artistService.getArtist(artistName);
//            List<String>artistDetails = null;
//
//            String videoUrl = videoService.getVideoUrl(trackName,artistName);
//
//
//            if (queryTrackArtist != null) {
//                artistDetails = searchService.searchArtistDetails(queryArtistDetails);
//                trackList = trackService.getArtistTracks(queryTrackArtist); // Retrieve track profiles
//            }
//
//
//
//            ModelAndView modelAndView = new ModelAndView("index");
//            modelAndView.addObject("videoUrl", videoUrl);
////            System.out.println("videoUrl-> " + videoUrl);
//            modelAndView.addObject("artistNameDebug", artistNameDebug);
////            System.out.println("artistNameDebug-> " + artistNameDebug);
//            modelAndView.addObject("chosenArtistImgUrl",chosenArtistImgUrl);
////            System.out.println("chosenArtistImgUrl-> " + chosenArtistImgUrl);
//            modelAndView.addObject("artist", artistList);
//            modelAndView.addObject("specificArtist", artistDetails);
//            modelAndView.addObject("lyrics", lyrics);
//            modelAndView.addObject("chosenArtist", chosenArtist);
//            modelAndView.addObject("chosenTrack", chosenTrack);
//            modelAndView.addObject("track", trackList);
//            System.out.println("got here " + fetchLyrics);
//            return modelAndView;
//        } else {
//            String lyrics = lyricsService.getTrackLyrics(trackName, artistName);
//            // Handle the initial search request here
//            List<Map<String, Object>> artistList = artistService.getArtist(query);
//            List<Map<String, Object>> trackList = null;
//            trackList = trackService.getArtistTracks(artistName);
//            List<String> artistDetails = null;
//            String chosenArtist = LyricsService.getChosenArtist();
//            String chosenArtistImgUrl = artistService.getArtistImageUrl(queryArtistImage);
//            String artistNameDebug = queryTrackArtist;
//
//
//
//            if (queryTrackArtist != null) {
//                artistDetails = searchService.searchArtistDetails(queryArtistDetails);
//                trackList = trackService.getArtistTracks(queryTrackArtist); // Retrieve track profiles
//            }
//
//            if (artistList.isEmpty() && (trackList == null || trackList.isEmpty())) {
//                System.out.println("Error 404");
//                ModelAndView modelAndView = new ModelAndView("errorView");
//                return modelAndView;
//            } else {
//                ModelAndView modelAndView = new ModelAndView("index");
//
//                modelAndView.addObject("artistNameDebug", artistNameDebug);
////                System.out.println("artistNameDebug-> " + artistNameDebug);
//                modelAndView.addObject("chosenArtistImgUrl",chosenArtistImgUrl);
////                System.out.println("chosenArtistImgUrl: " + chosenArtistImgUrl);
//                modelAndView.addObject("artist", artistList);
//                modelAndView.addObject("track", trackList);
//                modelAndView.addObject("specificArtist", artistDetails);
//                modelAndView.addObject("lyrics", lyrics);
//                modelAndView.addObject("chosenArtist", chosenArtist);
////                System.out.println("chosenArtist: " + chosenArtist);
//                modelAndView.addObject("chosenTrack", chosenTrack);
////                System.out.println("chosenTrack: " + chosenTrack);
//                System.out.println("Model and View shows!");
//                return modelAndView;
//            }
//        }
//    }
//
//
//
//
//}


package com.MusicStreamHub.demo.Controller;

import com.MusicStreamHub.demo.service.*;
import org.jmusixmatch.MusixMatchException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.MusicStreamHub.demo.service.LyricsService.chosenTrack;

@Controller
@RequestMapping("/searchArtistQuery")
public class ArtistController {

    private final ArtistService artistService;
    private final TrackService trackService;
    private final SearchService searchService;
    private final LyricsService lyricsService;
    private final VideoService videoService;

    public ArtistController(ArtistService artistService, TrackService trackService, SearchService searchService, LyricsService lyricsService, VideoService videoService) throws IOException {
        this.artistService = artistService;
        this.trackService = trackService;
        this.searchService = searchService;
        this.lyricsService = lyricsService;
        this.videoService = videoService;
    }

    @GetMapping()
    public ModelAndView searchArtist(@RequestParam(value = "query", required = false) String query,
                                     @RequestParam(value = "queryArtistImage", required = false) String queryArtistImage,
                                     @RequestParam(value = "queryTrackArtist", required = false) String queryTrackArtist,
                                     @RequestParam(value = "specificArtistQuery", required = false) String queryArtistDetails,
                                     @RequestParam(value = "fetchLyrics", required = false) boolean fetchLyrics,
                                     @RequestParam(value = "trackName", required = false) String trackName,
                                     @RequestParam(value = "artistName", required = false) String artistName) throws IOException, MusixMatchException {

        if (fetchLyrics && query == null) {
            // Handle request to fetch lyrics here
            String lyrics = lyricsService.getTrackLyrics(trackName, artistName);
            List<Map<String, Object>> trackList = null;
            trackList = trackService.getArtistTracks(artistName);
            ArtistService artistService1 = new ArtistService();
            String chosenArtistImgUrl = artistService1.getArtistImageUrl(queryArtistImage);
            String chosenArtist = LyricsService.getChosenArtist();
            String artistNameDebug = queryTrackArtist;
            List<Map<String, Object>> artistList = artistService.getArtist(artistName);
            List<String> artistDetails = null;

            String videoUrl = videoService.getVideoUrl(trackName, artistName);

            if (queryTrackArtist != null) {
                artistDetails = searchService.searchArtistDetails(queryArtistDetails);
                trackList = trackService.getArtistTracks(queryTrackArtist); // Retrieve track profiles
            }

            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("videoUrl", videoUrl);
            modelAndView.addObject("artistNameDebug", artistNameDebug);
            modelAndView.addObject("chosenArtistImgUrl", chosenArtistImgUrl);
            modelAndView.addObject("artist", artistList);
            modelAndView.addObject("track", trackList);
            modelAndView.addObject("specificArtist", artistDetails);
            modelAndView.addObject("lyrics", lyrics);
            modelAndView.addObject("chosenArtist", chosenArtist);
            modelAndView.addObject("chosenTrack", chosenTrack);
            System.out.println("got here " + fetchLyrics);
            return modelAndView;
        } else {
            String lyrics = lyricsService.getTrackLyrics(trackName, artistName);
            // Handle the initial search request here
            List<Map<String, Object>> artistList = artistService.getArtist(query);
            List<Map<String, Object>> trackList = null;
            trackList = trackService.getArtistTracks(artistName);
            List<String> artistDetails = null;
            String chosenArtist = LyricsService.getChosenArtist();
            String chosenArtistImgUrl = artistService.getArtistImageUrl(queryArtistImage);
            String artistNameDebug = queryTrackArtist;

            if (queryTrackArtist != null) {
                artistDetails = searchService.searchArtistDetails(queryArtistDetails);
                trackList = trackService.getArtistTracks(queryTrackArtist); // Retrieve track profiles
            }

            if (artistList.isEmpty() && (trackList == null || trackList.isEmpty())) {
                System.out.println("Error 404");
                ModelAndView modelAndView = new ModelAndView("errorView");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("index");
                modelAndView.addObject("artistNameDebug", artistNameDebug);
                modelAndView.addObject("chosenArtistImgUrl", chosenArtistImgUrl);
                modelAndView.addObject("artist", artistList);
                modelAndView.addObject("track", trackList);
                modelAndView.addObject("specificArtist", artistDetails);
                modelAndView.addObject("lyrics", lyrics);
                modelAndView.addObject("chosenArtist", chosenArtist);
                modelAndView.addObject("chosenTrack", chosenTrack);
                System.out.println("Model and View shows!");
                return modelAndView;
            }
        }
    }
}
