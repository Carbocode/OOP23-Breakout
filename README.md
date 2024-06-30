# Progetto Esame OOP

## Partecipanti

 - giacomo.ruscelli2@studio.unibo.it
 - vincenzo.prisco@studio.unibo.it
 - sohail.mama@studio.unibo.it
 - yosberto.baro@studio.unibo.it

## Obiettivi

### Descrizione

Il gruppo di lavoro si pone come obiettivo di realizzare un gioco arcade in 2D prendendo spunto dal gioco breakout.

Lo scopo principale è quello di distruggere una serie di mattoni colorati che compongono un muro nella parte superiore dello schermo.

Il giocatore deve far rimbalzare la palla verso i mattoni, facendola rimbalzare contro di essi per abbatterli o danneggiarli. Ogni mattone distrutto contribuisce al punteggio del giocatore. Alcuni mattoni possono contenere power-up che influenzano il comportamento della palla o forniscono altri vantaggi temporanei. Il gioco diventa sempre più impegnativo man mano che si avanza nei livelli, con la velocità della palla che aumenta e la disposizione dei mattoni sempre più complessa.

Il giocatore controlla una barra situata nella parte inferiore dello schermo, la quale può essere spostata orizzontalmente per intercettare e far rimbalzare la palla. In caso la palla superi la barra senza essere intercettata si perde una vita.

A seconda del punto in cui la palla colpisce la barra, essa rimbalza con un angolo diverso, l'angolo varierà in base al punto di collisione con la barra.

Il giocatore avrà a disposizione 3 vite per superare tutti i livelli e a seconda delle vite rimaste e dai mattoni distrutti accumula un punteggio che verrà salvato all'interno di una scoreboard locale (eventualmente scoreboard online).

### Caratteristiche

Power up disponibili:
* Sdoppiamento: ogni pallina presente in gioco si divide in 2 palline
* Laser: dalla barra situata nella parte inferiore dello schermo vengono sparati 8 colpi laser che distruggono istantaneamente il primo cubetto che colpiscono
* Barra più grande: per 30 secondi la barra controllata dall'utente diventa più grande per poi tornare normale scaduto il tempo

Funzionalità minimali ritenute obbligatorie:
* Interfaccia utente.
* Visualizzazione del gioco con grafica minimale.
* Funzionamento della barra mossa dal giocatore.
* Scoreboard locale.
* Conteggio dei punti.
* Distruzione e danneggiamento cubetti.
* Fisica della palla.
* Power up (sdoppiamento, laser, barra).
* Fine gioco (per fine vite o fine livelli).
* Effetti sonori (distruzione cubetti, rimbalzi).

Funzionalità opzionali:
* Scoreboard online (mongoDB)
* Livelli generati casualmente
* Musica
* Grafica avanzata
* Altri power up

Challenge principali:
* Coordinazione team tramite git
* Fisica della palla e concorrenza degli eventi di gioco
* Scoreboard online tramite server
* Gestione palle multiple e power up

## Suddivisione tasks
 
- Ruscelli:
  1. Scoreboard Offline (opz Online),
  2. Barra,
  3. Controlli utente,
  4. Power up (Laser)
- Prisco:
  1. Cubetti,
  2. Spawn power up
  3. Generazione livelli
  4. (opz) livelli procedurali con muro che scorre verso il basso
- Mama:
  1. Gestione punteggio,
  2. Creazione GUI,
  3. Gestione effetti sonori,
  4. Power up (Ingrandimento barra).
- Baro:
  1. Fisica Palla,
  2. Controllo intersezione cubetti,
  3. Power up (Sdoppiamento)
