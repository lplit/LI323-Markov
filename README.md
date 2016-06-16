Project: LI323-MarkovChains
=============

L’une des applications célèbres du modèle des Chaînes de Markov est le PageRank de Google. PageRank est l’algorithme d’analyse des liens concourant au système de classement des pages Web utilisé par le moteur de recherche Google. Il mesure quantitativement la popularité d’une page web. Le PageRank n’est qu’un indicateur parmi d’autres dans l’algorithme qui permet de classer les pages du Web dans les résultats de recherche de Google. Son principe de base est d’attribuer à chaque page une valeur (ou score) proportionnelle au nombre de fois que passerait par cette page un utilisateur parcourant le graphe du Web en cliquant aléatoirement, sur un des liens apparaissant sur chaque page. Ainsi, une page a un PageRank d’autant plus important qu’est grande la somme des PageRanks des pages qui pointent vers elle (elle comprise, s’il y a des liens internes). Le but de ce projet est de simuler les calculs de PageRank en Java sur de petites instances des nano Webs.

## Makefile directives
| directive | description |
| ------- | ----------- |
| run  | Automatically (re)compiles in case any of the source files has been modified, then launches the application. |
| all | Virtually the same as run but doesn't run |
| nano | NanoWeb simulations |
| doc | Compiles javadoc |
| plot | Plots all (nanoWeb[1,2,3]) |
| clean | Cleaning! |
