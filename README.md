// A gyakorlatom alatt kapott feladatnak a leírása - Junior szintű feladat

Megvalósítandó feladat leírása
Autókölcsönző alkalmazás
Egy egyszerű autókölcsönző alkalmazást kell elkészíteni. Pontozás súlyozása az alábbiak szerint alakul:

Egy feladatra kapható maximális pontszám akkor érhető el, ha a feladat teljes egészében elkészült

Minden feladatra az adott feladat mellet található pontérték érhető el.

Részpont van!

Javasoljuk: Inkább a feladatot addig vidd el, amíg tudod, mint hogy semmit nem csinálsz meg belőle.

Publikus felület max. 150 pont
Feladat leírása
A feleadat egy publikus felület készítése, hogy ahol

keresni tud az autók között

le tudja foglalni az adott autót az adatai megadásával. ( warning Egy autó ugyanabban az időszakban nem foglalható kétszer.)

Megvalósítandó feladatok és pontjaik
Kereső felület

Publikus felületen a főoldalon a felhasználó kiválaszt egy valamilyen daterange picker-ből egy -tól és egy -ig dátumot. (20 pont)

Ekkor elmegy egy kérés a szerver felé. (10 pont)

A válaszban egy lista az abban az időszakban szabad autókról, képpel, napi árral. (30 pont)

Foglalás kezelése

A kiválasztott autóra kattintva egy felületen megadja az adatait: (50 pont)

Név,

email cím,

cím,

telefonszám

foglalandó napok száma

foglalás teljes összege (a foglalandó napok számától függ!)

Majd egy submit gomb megnyomásával véglegesíti a rendelést. (10 pont)

Tesztek

min. 60%-os teszt lefedettség (30 pont)

AdminUse-case max. 150 pont
Feladat leírása
Admin felület egy minimális adminisztrációhoz.

belépő felületre nincs szükség! Az admin adatok jöhetnek akár config-ból és automatikusan admin joggal leszünk a /admin path-on.
Az admin oldalon szeretnénk

látni a foglalásokat egy listában

szerkeszteni autókat (akár újakat felvenni, deaktiválni nem törölni! :)

Megvalósítandó feladatok és pontjaik
Foglalási adatok megjelenítése

Admin belépés (config-ból), admin jogosultságokkal (20 pont)

Felület ahol láthatjuk a foglalásokat (10 pont)

Foglalásokat kiszolgáló service (20 pont)

Autók szerkesztése

Meglévő autók szerkesztése (25p) - képek nélkül

Új autó felvitele (25p) képek nélkül

Képek feltöltésének kezelése a szerkesztés a felvitel felületekhez (20p)

Autók deaktiválása (warning meglévő foglalások kezelése!)

Tesztek

min. 60%-os teszt lefedettség (30p)
Bónusz feladatok max. 50 pont
Bónuszként egy REST API amivel a szabad autókat le lehet kérdezni és egy foglalást leadni. (20 pont)

Dupla bónusz, docker plugin, docker konténer, adatbázis is külön docker, (20 pont)

Minden a 12.-ik pontban elkészített docker konténer közös hálózatban (10 pont)

Javasolt technológiai stack:
Spring boot 2

Spring mvc

Spring security

Valamilyen adatbázis engine (javasolt PostgreSQL, de lehet bármi más is)

Ha ezt elolvasod, küldj egy üzenetet és kapsz tíz pontot ;)

Frontend:

HTML5

Bootstrap vagy Bulma CSS

Thymeleaf
