# Score Keeper

## Die Idee hinter Score Keeper
Bei Gesellschaftsspielen ist das mitnotieren bestimmter Spielestatistiken oftmals aufwendig und spielflussunterbrechend. Der Alexa Skill soll Personen bei Gesellschaftsspielen unterstützen. Score Keeper ist ein Alexa Skill, welcher zur Punkteverwaltung zahlreicher Gesellschaftsspiele dient. Es können dabei mehrere Mitspieler erstellt und deren Punkte innerhalb einer Spielsitzung verwaltet werden.

Sprachbefehle, die von einem Alexa-fähigen Gerät akzeptiert werden, sind an der natürlichen Sprache angelehnt und ermöglichen es, den Spielfluss in netter Gesellschaft aufrecht zu erhalten.

## Features
- Eine Spielsitzung mit persistenter Speicherung starten oder beenden
- Einer Spielsitzung Spielteilnehmer hinzufügen
- Die Punkte eines Spielteilnehmers auf einen beliebigen ganzzahligen Wert setzen
- Die Punkte eines Spielteilnehmers um einen ganzzahligen Wert erhöhen oder vermindern
- Den aktuellen Punktestand aller Spielteilnehmer ausgeben lassen
- [Zehntausend](https://de.wikipedia.org/wiki/Zehntausend_(Spiel)) als voreingestelltes Spielprofil

## Beispiel-Ablauf

Anna und Otto wollen eine Runde Würfel spielen. Weil sie gerade keinen Stift oder Papier bei der Hand haben, legt Anna ihr Alexa-fähiges Mobiltelefon auf den Tisch und startet Alexa. Mit den Worten, _"Alexa, sage Score Keeper: neue Spielsitzung starten"_ eröffnet sie eine neue Spielsitzung und sagt anschließend: _"Otto spielt mit"_, und danach _"Anna spielt mit"_. Der Score Keeper bestätigt die erhaltenen Sprachbefehle und das Spiel kann losgehen.

Otto und Anna spielen die erste Runde und wollen ihre Punkte speichern. Weil seit dem letzten Sprachbefehl einige Zeit vergangen ist, hat sich der Skill beendet. Das macht aber nichts, denn so lange die Spielsitzung nicht vom Nutzer beendet wird, werden die Spieldaten persistent gespeichert. Also sagt Anna einfach: _"Alexa, sage Score Keeper: Otto hat 3 Punkte"_ und danach: _"Alexa, sage Score Keeper: Anna hat 45 Punkte"_. Alexa quittiert die gespeicherten Punkte und das Spiel geht weiter.

Nach einigen Runden möchte Otto wissen, wie es steht. Um das herauszufinden, sagt er: _"Alexa, sage Score Keeper: Punktestand ausgeben"_. Alexa antwortet: _"Spieler Otto: 313 Punkte; Spieler Anna: 99999 Punkte."_.

Otto hat keine Lust mehr. Er sagt: _"Alexa, sage Score Keeper: Spielsitzung beenden"_. Damit sind die Punktestände gelöscht, und der Skill ist bereit, beim nächsten Mal wieder eine neue Spielsitzung aufzunehmen.

## Aktivitäts-Diagramm
[![Aktivitäts-Diagramm](https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-fr_13/master/design/Sprint_3/Aktivit%C3%A4tsdiagramm_%C3%9Cbersicht.png)](https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-fr_13/master/design/Sprint_3/Aktivit%C3%A4tsdiagramm_%C3%9Cbersicht.png)

## Fachklassenmodell
![Fachklassenmodell](https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-fr_13/master/design/Fachklassenmodell.png)

## Erfahrungen im Praktikum Software Engineering
### Was hat sich bewährt?
- Github als Kommunikations- und Organisationsplattform
- Continuous Integration mit Travis-CI und Sonarcloud
- Scrumm als Vorgehensmodell
- Gewissenhafte Analyse, Planung und Dokumentation

### Was wollen wir in Zukunft anders machen?
- Zuständigkeiten von Anfang an klarer aufteilen
- Besseres Zeitmanagement während einzelner Sprints
- Öftere persönliche Treffen
