# ECOtoll - OOSD
Utilizzo del software:
Il software necessità di una base di dati popolata con dei veicoli 
e un utente amministratore per funzionare.
Sarà poi possibile dal software inserire autostrade, relative tariffe e caselli.

Assieme al software è fornito un database già popolato a sufficienza per permettere 
un utilizzo dimostrativo del programma.
Sarà sufficiente, se in locale, importare il database contenuto nel file sql 
assegnando al database il nome ecotoll.
Sarà altrimenti necessario modificare il file di configurazione Settings.config 
per accedere ad un database remoto o con un nome differente.

All'apertura del software comparirà una schermata di scelta tra uso utente e uso amministratore.
Solo per il secondo sarà necessario effettuare un login.

Nella vista fornita per l'uso utente sarà possibile calcolare il pedaggio.
Mentre nella vista fornita per l'amministratore sarà possibile accedere in lettura e scrittura 
ad autostrade, tariffe e caselli.
