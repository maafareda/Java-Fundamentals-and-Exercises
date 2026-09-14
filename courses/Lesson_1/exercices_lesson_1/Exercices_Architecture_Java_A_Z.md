# Exercices — Architecture Java (A à Z)

26 exercices progressifs, couvrant l'ensemble du résumé : POO, Collections, Stream API, interfaces fonctionnelles, gestion des exceptions et concurrence. Difficulté croissante à l'intérieur de chaque bloc.

---

## Bloc 1 — POO (A à E)

**A. Interface `Forme`** *(Facile)*
Crée une interface `Forme` avec une méthode `double surface()`. Implémente-la dans deux classes `Cercle` et `Rectangle`. Chaque classe doit calculer sa propre surface.

**B. Classe abstraite `Vehicule`** *(Facile)*
Crée une classe abstraite `Vehicule` avec un attribut `vitesseMax` et une méthode abstraite `demarrer()`. Fais hériter `Voiture` et `Moto` de cette classe, chacune avec son propre message dans `demarrer()`.

**C. Polymorphisme simple** *(Facile)*
Crée un tableau (ou une `List`) de type `Forme` contenant des `Cercle` et des `Rectangle`. Parcours-le et appelle `surface()` sur chaque élément : observe que le bon comportement s'exécute selon l'objet réel.

**D. Contrat multiple** *(Moyen)*
Crée une interface `Payable` avec une méthode `payer(double montant)`. Fais-la implémenter par `Client` et `Fournisseur`, avec un comportement différent (ex. `Client` affiche "paiement reçu", `Fournisseur` affiche "paiement envoyé").

**E. Combinaison classe abstraite + interface** *(Avancé)*
Crée une classe abstraite `Employe` (attributs `nom`, `salaireBase`, méthode abstraite `calculerSalaire()`) et une interface `Prime` avec `double bonus()`. Fais une classe `Manager` qui hérite de `Employe` **et** implémente `Prime`, où `calculerSalaire()` additionne `salaireBase` et `bonus()`.

---

## Bloc 2 — Collections (F à K)

**F. ArrayList basique** *(Facile)*
Crée une `ArrayList<String>` de 5 villes marocaines. Affiche la liste, puis affiche uniquement la ville à l'index 2.

**G. Doublons dans une List** *(Facile)*
Ajoute plusieurs fois le même élément dans une `ArrayList<Integer>`. Vérifie que les doublons sont bien conservés en affichant la taille de la liste avant/après ajout.

**H. HashSet et unicité** *(Facile)*
Crée un `HashSet<String>` et ajoute 6 prénoms dont 2 identiques. Affiche le `Set` et vérifie que le doublon a disparu.

**I. HashMap étudiants** *(Moyen)*
Crée une `HashMap<Integer, String>` associant un ID à un nom d'étudiant. Ajoute 4 entrées, puis affiche le nom correspondant à un ID donné avec `get()`.

**J. List vers Set** *(Moyen)*
À partir d'une `ArrayList<String>` contenant des doublons, crée un `HashSet<String>` à partir de cette liste pour éliminer les doublons, puis affiche le résultat.

**K. Mini-annuaire** *(Avancé)*
Crée une `HashMap<String, List<String>>` représentant un annuaire (nom → liste de numéros de téléphone). Ajoute 3 personnes ayant chacune 2 numéros, puis affiche l'annuaire complet avec une boucle sur les entrées (`entrySet()`).

---

## Bloc 3 — Stream API (L à N)

**L. filter() simple** *(Facile)*
À partir d'une `List<Integer>` de 10 nombres, utilise `filter()` pour n'afficher que les nombres pairs.

**M. map() simple** *(Facile)*
À partir d'une `List<String>` de prénoms, utilise `map()` pour afficher chaque prénom en majuscules (`String::toUpperCase`).

**N. filter() + map() combinés** *(Moyen)*
À partir d'une `List<Integer>` de notes sur 20, utilise `filter()` pour garder les notes ≥ 10, puis `map()` pour les convertir en pourcentage (note × 5), et affiche le résultat avec `forEach()`.

---

## Bloc 4 — Interfaces fonctionnelles (O à Q)

**O. Predicate personnalisé** *(Facile)*
Crée un `Predicate<String>` qui teste si une chaîne contient plus de 5 caractères. Teste-le sur 3 mots différents.

**P. Function personnalisée** *(Moyen)*
Crée une `Function<String, Integer>` qui retourne la longueur d'une chaîne. Utilise-la dans un `map()` appliqué à une `List<String>`.

**Q. Consumer personnalisé** *(Moyen)*
Crée un `Consumer<Integer>` qui affiche "Nombre pair" ou "Nombre impair" selon la valeur. Applique-le à chaque élément d'une `List<Integer>` avec `forEach()`.

---

## Bloc 5 — Gestion des exceptions (R à U)

**R. Division par zéro gérée** *(Facile)*
Écris un programme qui demande deux nombres et effectue une division. Entoure l'opération d'un `try-catch` pour éviter que le programme plante si le diviseur est 0.

**S. Exception personnalisée (unchecked)** *(Moyen)*
Crée une classe `AgeInvalideException` qui hérite de `RuntimeException`. Écris une méthode `verifierAge(int age)` qui lève cette exception si `age < 0` ou `age > 120`.

**T. Checked exception avec fichier** *(Moyen)*
Écris une méthode qui tente de lire un fichier texte avec `FileReader`. Gère l'`IOException` avec un message clair si le fichier n'existe pas.

**U. Gestion globale** *(Avancé)*
Crée une méthode `executerAction(Runnable action)` qui exécute n'importe quelle action passée en paramètre à l'intérieur d'un `try-catch` global, et affiche "Erreur : " + le message si une exception survient. Teste-la avec une action qui lève une exception et une qui ne lève rien.

---

## Bloc 6 — Concurrence et synchronisation (V à Z)

**V. Deux threads simples** *(Facile)*
Crée deux `Thread` qui affichent chacun un message différent 5 fois (avec une boucle). Lance-les avec `start()` et observe l'entrelacement des affichages.

**W. CompletableFuture asynchrone** *(Moyen)*
Utilise `CompletableFuture.runAsync()` pour exécuter une tâche qui simule un traitement (ex. `Thread.sleep(1000)` puis affichage d'un message), pendant que le programme principal continue d'afficher autre chose.

**X. synchronized sur un compte bancaire** *(Moyen)*
Crée une classe `CompteBancaire` avec une méthode `synchronized retirer(int montant)`. Lance 2 threads qui retirent de l'argent en même temps et vérifie que le solde final est cohérent (pas de valeur incorrecte due à la concurrence).

**Y. ReentrantLock vs synchronized** *(Avancé)*
Reprends l'exercice X, mais remplace `synchronized` par un `ReentrantLock` (avec `lock()` / `unlock()` dans un `try-finally`). Compare le comportement avec la version `synchronized`.

**Z. ConcurrentHashMap multi-thread** *(Avancé)*
Crée un `ConcurrentHashMap<Integer, String>` partagé. Lance 3 threads qui ajoutent chacun plusieurs entrées en même temps. Vérifie qu'aucune donnée n'est perdue ni corrompue, contrairement à ce qui pourrait arriver avec une `HashMap` classique.

---

*Conseil : fais les exercices dans l'ordre à l'intérieur de chaque bloc — chacun réutilise les notions du précédent. N'hésite pas à demander une correction ou un indice pour un exercice précis.*
