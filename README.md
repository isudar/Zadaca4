# Zadaca4
zadatak ove zadaće bio je: 
Kreirajte aplikaciju koja omogućuje prikaz vijesti iz RSS feed-a na
http://www.bug.hr/rss/vijesti/ te omogućuje prikaz po kategorijama. Potrebno je
prikazati sliku, vijest, vrijeme objave i omogućiti čitanje vijesti klikom na element liste. 

nisam uspio samostalno rješiti zadaću te sam pomoć potražio na sljedećem linku: https://www.youtube.com/watch?v=19NSla4FNdQ&list=PLOvzGCa-rsH-9QjlFBVHfBNUzPGHGzj-5
prikaz elemenata vijesti je bio vrlo jednostavan za prikazati također sam to naučio na navedenom linku
Bilo je potrebno koristiti klasu u kojoj su spremljeni objekti pojedinih vijesi koje se iščitaju iz xml-a, adapter za ubacivanje podataka u listu te definiranje holdera, u holderu  je implementirana aktivnost da klikom na vijest odemo pregledati čitav članak na webu. 
u mainActivity implementirane su akcije na određeni gumb preko kojih osvježavamo listu ili tražimo kategorije. 
