# JavaBasics
The project is a parser, parse site "https://www.moscowmap.ru/metro.html", by the Jsoup library. 
Then it should create a JSON file that looks like this:

{ "stations" :
{ 
"1" : [ stations name ],
"2" : [ stations name ]...etc 
},

"connections":
[
[
{ "line": line number, "station": "station name" }, 
{ "line": line number, "station": "station name" } 
], ...etc
],

"lines" : 
[ 
{ "number" : line number, 
"name" : "line name", 
}, ...etc 
]
}
