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
This section of readme contains list of basic Git commands together with their SVN equivalent.

###Create Repository
####Git
```
git init    //creates new local repository
git add .   //selects all contents of current dir for commit
git commit -m <commit_msg> //commits the changes with message <commit_msg>
git remote add origin <url> //adds new remote repository location with name "origin"
git push origin master //sends all new commits of branch master to the "origin" repository
```
####SVN
```
svn import <folder> <url> -m <commit_msg>      //initializes <folder> as new repository and sends it to the <url>
```

###Download Repository
####Git
```
git clone <url>
```
####SVN
```
svn checkout <url>
```

###Make a Commit
####Git
```
git add (--all | <path>) //select changes to commit
git commit -m <commit msg> //create new commit locally from the selected changes
git push origin master //send new commits in master branch to origin
```
####SVN
```
svn add <path> //select changes to commit
svn commit -m <commit msg> //make a commit (sends it to the server right away)
```
###Download Other People's Work
####Git
```
git fetch   //downloads changes, but doesnt modify current working branch
git pull -r //updates current working branch
```
####SVN
```
svn update //updates current working branch
```
###Working with Branches
####Git
```
git checkout -b <name>  //creates new branch
git checkout <name>     //switches to the branch
git branch -d <name>    //deletes a branch (must be merged else where, or use -D instead of -d)
```
####SVN
```
svn copy <source url> <target url> -m <commit msg>  //makes a copy of the branch in different folder...
svn checkout <url>  //you have to make a checkout of the new branch
svn rm <url> //deletes a folder/branch/copy
```
###Merge Branches
Merge expects you to be on the branch TO which you want to merge another branch.
####Git
```
git merge <branch name>
```
####SVN
```
svn merge <path | url>
```
###Creating Tags
####Git
```
git tag -a <tag name> -m <commit msg>
git checkout <tag name> //switch to tag revision, cant commit changes
git checkout -b <new name> <tag name> //create new branch based on tag revision
```
####SVN
```
svn copy <source url> <target url> //same as creating branches...
```
