<<<<<<< HEAD
# gitworkshop
This project serves as a practice scenario for Git workshop we organize for our students
at [The Department of Computer Science](http://kiv.zcu.cz) of
[The Faculty of Applied Sciences](http://fav.zcu.cz) at [The University of West Bohemia](http://zcu.cz) in
Pilsen, Czech Republic.
Goal of the workshop is to give the students basic guidelines in using Git and versioning software in general.
The application itself was created back in 2010 during the second year of my bachelor degree studies in proceedings
of KIV/JXT (Java and XML Technologies).

##Application Documentation
The application itself takes XML export of scheduled timetable actions(rozvrhove-akce.xml) and student's
registration events (prezapisove-akce-test.xml) and outputs student's timetable.

To run the application use the following command, modify paths to the files accordingly to your local setup.

```
java jxt.Hlavni -xpredzapisove-akce-test.xml -yrozvrhove-akce.xml -iA09B0765P -d<output_dir>
Legend:
-x file with student registraction actions
-y file with scheduled timetable actions
-i selected student's registration number
-d directory to write results into
```

###Implementation
The application is implemented in Java and uses JAXB to manipulate XML files. The packages `jxtrozvrh` and `jxtsp`
contain JAXB generated classes for working with the supplied XML schemas.

The package `jxt` contains the actual application code.

##Git Cheatsheet
Git-SVN comparison cheatsheet available at http://danekja.github.io/gitworkshop/
=======
<h1>Monitoring koponent</h1>

<strong>Následující projekt obsahuje semestrální práci na předmět ZSWI. Náplní semestrální práce je:</strong><br>
JAVA aplikace pro monitoring běhu komponent/instancí DMS PeerFile

<h3>členové týmu:</h3>
• Luboš Hubáček <br>
• Ondřej Pittl <br>
• Jiří Homolka <br>
• Jan Kohlíček <br>
• Petr Kozler
>>>>>>> 452359a8bf6f12ef3a4c50121b7d544a298d9835
