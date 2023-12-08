# Progetto Esame OOP

Email dei componenti:
 - giacomo.ruscelli2@studio.unibo.it
 - vincenzo.prisco@studio.unibo.it
 - sohail.mama@studio.unibo.it
 - yosberto.baro@studio.unibo.it

Il gruppo di lavoro si pone come obiettivo di realizzare un gioco arcade in 2D prendendo spunto dal gioco breakout.

L'obiettivo principale è distruggere una serie di mattoni colorati che compongono un muro nella parte superiore dello schermo.

Il giocatore deve far rimbalzare la palla verso i mattoni, facendola rimbalzare contro di essi per abbatterli o danneggiarli. Ogni mattone distrutto contribuisce al punteggio del giocatore. Alcuni mattoni possono contenere power-up che influenzano il comportamento della palla o forniscono altri vantaggi temporanei. Il gioco diventa sempre più impegnativo man mano che si avanza nei livelli, con la velocità della palla che aumenta e la disposizione dei mattoni sempre più complessa.

Il giocatore controlla una barra situata nella parte inferiore dello schermo, la quale può essere spostata orizzontalmente per intercettare e far rimbalzare la palla. In caso la palla superi la barra senza essere intercettata si perde una vita.

A seconda del punto in cui la palla colpisce la barra rimbalza con un angolo diverso, l'angolo sarà stretto se colpisce il centro mentre più ampio se colpisce i bordi.

Il giocatore ha a disposizione 3 vite per superare tutti i livelli e a seconda delle vite rimaste e dai mattoni distrutti accumula un punteggio che verrà salvato all'interno di una scoreboard locale (eventualmente scoreboard online).

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
 
Suddivisione del lavoro:
* Ruscelli: Scoreboard Offline (opz Online), Barra e controlli utente, power up (Laser)
* Prisco: Cubetti, spawn power up e generazione livelli (opz livelli procedurali con muro che scorre verso il basso)
* Mama: Gestione punteggio, creazione GUI, gestione effetti sonori, power up (Ingrandimento barra).
* Baro: Fisica Palla, controllo intersezione cubetti e power up (Sdoppiamento)
