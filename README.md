# opensubtitles-searcher

Simple Java GUI to Search for Movie or Series/Shows Subtitles on opensubtitles.org website
+ Simply because I got bored and procrastinating

![GUI Screenshot](https://github.com/alpsayin/opensubtitles-searcher/blob/master/Screenshot_1.png?raw=true "GUI Screenshot")

To Run
-------
+ Have Java 6 or higher ([Java Download] (http://java.com/download/ "Java Download"))
+ Run the JAR file in dist folder with JAVA(TM) Platform SE Binary (Windows, tested)
+ If you're using linux or mac double click or type in "java -jar dist/SubtitleSearcher.jar" in terminal

Features
--------
+ Fixed to english language, couldn't bother to add languages, send updates if you want to add your language
+ SxxEyy auto text field completion, i.e. parses s02e12 text into relevant fields
+ Search TV Series or Movies with their title and/or season+episode and/or year
+ Search for subtitles by selecting a movie file(submits its file size) and year(optional)
+ Parses filenames into title, season and episodes
+ It will remember where you picked your last movie file from, so you don't have to browse to your movie/seris directory everytime
+ Opens the default web-browser and takes you to search results page

TODO
----
+ Language Selection
+ UTF character to HTML conversion (e.g. ' -> %27f)
