###INF2015 – Développement de logiciels dans un environnement Agile
===================================================================
###Projet de session – Automne 2014


####Demande initiale

Votre client vous demande de lui livrer ces fonctionnalités pour le 8 octobre 2014, avant 9h30. La
date de livraison n'est pas négociable.
L'application à développer est un logiciel qui effectuera la validation des déclarations d'activités de
formation continue pour les membres d'un ordre professionnel.
Chaque ordre professionnel impose à ses membres d'effectuer un certain nombre d'heures d'activité
de formation pour une période donnée. La période en question est appelée un cycle. Dans ce cas-ci,
le cycle dure 2 ans et les membres de l'Ordre doivent effectuer un minimum de 40 heures de
formation continue durant le cycle.
Le logiciel ne possèdera pas d'interface utilisateur car il est destiné à être invoqué à partir d'une
application web. Le contrat ne consiste donc qu'au développement du moteur de validation de
l'application.
Fonctionnalités
Le fichier d'entrée, en format JSON, aura l'air de ceci :

{ "numero_de_permis": "A0001",
 "cycle": "2012-2014",
 "heures_transferees_du_cycle_precedent": 2,
 "activites": [
 {
 "description": "Cours sur la déontologie",
 "categorie": "cours",
 "heures": 14,
 "date": "2013-03-20"
 },
 {
 "description": "Séminaire sur l'architecture contemporaine",
 "categorie": "séminaire",
 "heures": 10,
 "date": "2014-01-07"
 },
 {
 "description": "Rédaction pour le magazine Architecture moderne",
 "categorie": "rédaction professionnelle",
 "heures": 6,
 "date": "2012-10-22"
 },
 {
 "description": "Participation à un groupe de discussion sur le
 partage des projets architecturaux de plus de 20 ans",
 "categorie": "groupe de discussion",
 "heures": 6,
 "date": "2013-04-01"
 },
 {
 "description": "Visite d'établissements architecturaux",
 "categorie": "voyage",
 "heures": 2,
 "date": "2014-02-02"
 }
 ]
}

Le fichier de résultat généré par le logiciel devra ressembler à ceci :

{
 "complet": false,
 "erreurs": [
 "L'activité Visite d'établissements architecturaux est dans une
 catégorie non reconnue. Elle sera ignorée.",
 "Il manque 2 heures de formation pour compléter le cycle."
 ]
}

Le programme devra prendre ce fichier comme argument lors de l'exécution du logiciel dans une
console. Le fichier où sera placé le résultat devra également être spécifié à la console. Exemple :
java -jar FormationContinue.jar declaration.json resultat.json
Voici la liste des catégories reconnues pour les activités de formation continue :
* cours
* atelier
* séminaire
* colloque
* conférence
* lecture dirigée
* présentation
* groupe de discussion
* projet de recherche
* rédaction professionnelle
Voici les règles d'affaires à valider :
* Dans un premier temps, uniquement le cycle "2012-2014" sera supporté. L'utilisation de tout
autre cycle devra produire un message d'erreur dans le fichier de sortie.
* Toutes les activités déclarées pour le cycle 2012-2014 doivent avoir été complétées entre le
1er avril 2012 et le 1er avril 2014 inclusivement. Si une activité déclarée a été complétée à
l'extérieur de cet intervalle, un message doit être produit dans le fichier de sortie et l'activité
sera ignorée des calculs. Toutes les dates sont indiquées en format ISO 8601.
* Toutes les activités doivent appartenir à une des catégories reconnues mentionnées
précédemment. Si une activité utilise une catégorie non reconnue, un message doit être
produit dans le fichier de sortie et l'activité sera ignorée des calculs.
* Le champ "heures_transferees_du_cycle_precedent" contient des heures de formation en
surplus qui ont été complétées lors du cycle précédent et qui peuvent être utilisées dans le
cycle courant. Ce nombre ne peut pas être négatif et ne doit pas être supérieur à 7. S'il est
supérieur à 7, un message doit être produit dans le fichier de sortie et uniquement 7 heures
seront considérées lors des calculs.
* Un minimum de 40 heures de formation doivent être déclarées dans le cycle. Il n'y a pas de
maximum. En dessous du 40 heures, un message doit être produit dans le fichier de sortie.
* Un minimum de 17 heures doivent être déclarées dans les catégories suivantes : cours,
atelier, séminaire, colloque, conférence, lecture dirigée. Autrement dit, la somme des heures
des activités appartenants à l'ensemble de ces catégories doit être supérieure ou égale à 17
heures. En dessous du 17 heures, un message doit être produit dans le fichier de sortie. Les
heures transférées du cycle précédent sont comptabilisées dans cette somme.
* Un maximum de 23 heures peuvent être déclarées dans la catégorie présentation. Au-delà de
23 heures, les heures supplémentaires sont ignorées des calculs mais aucun message n'est
produit dans le fichier de sortie.
* Un maximum de 17 heures peuvent être déclarées dans la catégorie groupe de discussion.
Au-delà de 17 heures, les heures supplémentaires sont ignorées des calculs mais aucun
message n'est produit dans le fichier de sortie.
* Un maximum de 23 heures peuvent être déclarées dans la catégorie projet de recherche. Audelà de 23 heures, les heures supplémentaires sont ignorées des calculs mais aucun message
n'est produit dans le fichier de sortie.
* Un maximum de 17 heures peuvent être déclarées dans la catégorie rédaction
professionnelle. Au-delà de 17 heures, les heures supplémentaires sont ignorées des calculs
mais aucun message n'est produit dans le fichier de sortie.
* Les heures d'une activité doivent être supérieures ou égales à 1.
Contraintes technologiques
Voici les contraintes que vous devez respecter :
* Le logiciel doit être développé avec le langage de programmation Java (JDK8).
* Il est impératif d'utiliser l'environnement de développement intégré NetBeans 7. 4 ou plus.
* Les fichiers d'entrées et de sorties doivent être des documents JSON.
* Les sources doivent être entreposées dans un dépôt GIT sous github.


####Demande de changement #1
============================
Votre client vous demande de lui livrer ces fonctionnalités pour le 27 octobre 2014, avant 9h30. 
La date de livraison n'est pas négociable.
Le contenu de ce document s'ajoute à la demande initiale du client.

####Exigences fonctionnelles

Le logiciel doit maintenant être en mesure de valider les règles de formation continue de plusieurs
ordres professionnels. Un nouveau champ "ordre" sera ajouté dans l'objet contenu dans le fichier
d'entrée. Ce champ pourra avoir 3 valeurs possibles, dépendamment de l'ordre professionnel pour
lequel la déclaration a été faite :
* architectes
* géologues
* psychologues
Toutes les règles développées jusqu'à date s'appliquent uniquement aux architectes. Les catégories
d'activités sont les mêmes pour tous les ordres professionnels.

####Les architectes
Voici quelques modifications à apporter aux règles de validation pour l'ordre des architectes.
* Nous ajoutons la validation de deux nouveaux cycles. Le cycle "2010-2012" pourra contenir
des activités effectuées du 1er avril 2010 au 1er avril 2012 inclusivement. Le cycle "2008-
2010" pourra contenir des activités effectuées du 1er avril 2008 au 1er juillet 2010
inclusivement (oui, juillet). Pour ces deux nouveaux cycles, ce sont les mêmes règles que le
cycle "2012-2014" à l'exception que le seuil minimal d'heures de formation continue est de
42 heures.

####Les géologues
La durée d'un cycle de formation continue pour les géologues est de 3 ans. Voici les règles à
considérer.
* Le seul cycle supporté pour l'instant est le cycle "2013-2016" qui doit contenir des activités
effectuées entre le 1er juin 2013 et le 1er juin 2016 inclusivement.
* Les géologues doivent effectuer un minimum de 55 heures de formation continue dans un
cycle.
* Un minimum de 22 heures par cycle sont nécessaires dans la catégorie "cours".
* Un minimum de 3 heures par cycle sont nécessaires dans la catégorie "projet de recherche".
* Un minimum d'une heure par cycle est nécessaire dans la catégorie "groupe de discussion".
* Il n'existe aucun transfert d'heures vers un autre cycle. Le champ
"heures_transferees_du_cycle_precedent" ne devrait pas exister.
Les psychologues
La durée d'un cycle de formation continue pour les psychologues est de 5 ans. Voici les règles à
considérer.
* Le seul cycle supporté pour l'instant est le cycle "2010-2015" qui doit contenir des activités
effectuées entre le 1er janvier 2010 et le 1er janvier 2015 inclusivement.
* Les psychologues doivent effectuer un minimum de 90 heures de formation continue dans
un cycle.
* Un minimum de 25 heures par cycle sont nécessaires dans la catégorie "cours".
* Il n'existe aucun transfert d'heures vers un autre cycle. Le champ
"heures_transferees_du_cycle_precedent" ne devrait pas exister.
* Un maximum de 15 heures peuvent être déclarées dans la catégorie "conférence". Au-delà
de ce seuil, les heures ne sont plus comptabilisées mais aucun message d'erreur n'est produit.
Autres
Voici également quelques validations à faire qui n'avaient pas été spécifiées au début du projet :
* Toutes les heures d'activités sont des entiers positifs.
* Le numéro de permis doit être une lettre majuscule suivie de 4 chiffres. La lettre doit être A,
R, S ou Z.
* Une description de plus de 20 caractères doit être fournie pour chaque activité.
* La date doit être spécifiée en format ISO 8601 (AAAA-MM-JJ).
* Si la structure du fichier d'entrée n'est pas respectée, par exemple s'il manque un champ, ou
si les 4 règles précédentes ne sont pas satisfaites, vous terminez l'exécution en affichant un
message d'erreur à la console. Dans un tel cas, votre programme doit quand même produire
un fichier JSON de sortie, ce fichier contiendra un message indiquant que le fichier d'entrée
est invalide et que le cycle est incomplet.
Exigences non fonctionnelles
Voici quelques contraintes à respecter qui touchent votre code :
* Votre équipe doit s'entendre sur un style uniforme à appliquer au code. Les particularités de
votre style doivent être documentées dans un fichier style.md à la racine de votre projet. Il
est permis de faire référence à un document déjà existant. Le style inclut également la
langue utilisée pour nommer vos variables, méthodes ou classes ou pour rédiger vos
commentaires dans le code. Le format de cette documentation doit être en markdown.
* Une fois votre style défini, tout votre code doit être modifié pour le respecter.
* Tous commentaires dans le code doivent être pertinents, c'est-à-dire qu'un commentaire doit
documenter ce que le code ne décrit pas déjà de façon évidente. Vous appliquerez les
recommandations présentées dans le chapitre 4 du livre Coder proprement.
* Vos méthodes ne devraient pas dépasser 10 lignes de code et chaque méthode devrait
respecter le "Principe de responsabilité unique" décrit dans le chapitre 3 du livre Coder proprement.

####Demande de changement #2
============================
La date de livraison est pour le 19 novembre 2014 à 9h30.
Le contenu de ce document s'ajoute à la demande initiale du client et à la première demande de
changement.
####Exigences fonctionnelles
*Nouveaux champs*
La déclaration contiendra maintenant 3 nouveaux champs :
* nom
* prenom
* sexe
Les champs nom et prenom contiendront des chaînes de caractères.
Le champ sexe contiendra une valeur numérique. La valeur correspondante au sexe sera déterminée
selon la norme ISO 5218. Uniquement les valeurs 0, 1 ou 2 sont acceptées dans ce champ. En cas
d'invalidité, un message d'erreur est produit dans le fichier de sortie.
Numéros de permis
Vous devez valider le format du numéro de permis et produire un message d'erreur dans le fichier de
sortie si le numéro de permis est invalide.
Architectes : Le numéro de permis est composé d'une lettre suivie de 4 chiffres. La lettre peut être
un A ou un T et c'est toujours une lettre majuscule. Ex : T3443
Psychologues : Le numéro de permis est composé de 5 chiffres, d'un trait d'union et de 2 autres
chiffres. Ex : 83723-34
Géologues : Le numéro de permis est composé de 2 lettres suivies de 4 chiffres. La première lettre
du numéro de permis correspond à la première lettre du nom du membre en majuscule. La
deuxième lettre du numéro de permis correspond à la première lettre du prénom du membre en
majuscule. Ex. BJ3822
Statistiques
Le logiciel devra maintenant accumuler des statistiques lors de chaque exécution. Vous devrez
conserver les statistiques dans un fichier. Le fichier peut être de la nature que vous voulez (texte,
JSON, XML ou autre, tant que ça ne nécessite aucune installation).
À chaque exécution, vous devrez calculer les statistiques suivantes :
* le nombre total de déclarations traitées;
* le nombre total de déclarations complètes;
* le nombre total de déclarations incomplètes ou invalides;
* le nombre total de déclarations faites par des hommes;
* le nombre total de déclarations faites par des femmes;
* le nombre total de déclarations faites par des gens de sexe inconnu;
* le nombre total d'activités valides dans les déclarations;
* le nombre d'activités valides par catégorie.
Pour produire la liste des statistiques, l'utilisateur exécutera le logiciel en spécifiant, en paramètres,
l'option "-S" et les statistiques seront affichées à la console. Par exemple :
java -jar FormationContinue -S
L'option "-SR" doit réinitialiser les statistiques, c'est-à-dire les remettre à 0. Un message de
confirmation doit être affiché à la console.
java -jar FormationContinue -SR
Podiatres
Le logiciel doit valider les déclarations pour l'Ordre des podiatres. Les règles pour les podiatres sont
exactement les mêmes que pour les géologues. Les seules exceptions sont celles-ci :
* Les podiatres doivent effectuer un minimum de 60 heures de formation continue dans un
cycle.
* Le numéro de permis est composé de 5 chiffres. Ex : 83453

####Demande de changement #3
============================
La date de livraison est pour le 8 décembre 2014 avant 9h30.
Le contenu de ce document s'ajoute à la demande initiale du client et à toutes les demandes de
changement précédentes.
####Exigences fonctionnelles
Voici quelques statistiques à ajouter à l'application :
* le nombre total de déclarations valides et complètes par type d'ordre professionnel;
* le nombre total de déclarations valides et incomplètes par type d'ordre professionnel;
* le nombre de déclarations soumises avec un numéro de permis invalide.

####Exigences non fonctionnelles
Voici quelques contraintes à respecter qui touchent votre code :
* Tout le nouveau code écrit pour cette DDC doit être rédigé selon la méthodologie TestDriven Development.
* Vous devez utiliser Maven dans votre projet pour gérer la construction du logiciel et pour
gérer les dépendances de votre projet.
* Vous devez continer de respecter le «Single Responsibility Principle» au niveau des
méthodes de classe.
* Chacune de vos classes doit également respecter le «Single Responsibility Principle» tel
qu'indiqué dans le chapitre 10 du livre «Coder proprement.»
* Vous devez rédiger suffisamment de «bons» tests unitaires afin d'acquérir une couverture de
tests d'au moins 80% de tout votre projet. Vos tests doivent suivre les règles vues en classe et
le code de vos tests doit également être propre.


