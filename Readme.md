### Note :
- Make sure to run `docker-compose` up to set up the database container then run the application.
- The test database is in memory H2 database, separated from the dev database.

# Système de Gestion de Flotte

## Introduction

La gestion d'une flotte de véhicules peut représenter un coût significatif pour une entreprise. Selon le secteur d'activité et la taille de l'entreprise, la flotte peut constituer le deuxième ou le troisième poste de dépenses le plus important. La gestion des affectations des conducteurs et des véhicules est un aspect crucial de la gestion de flotte. Un système efficace de gestion de flotte de véhicules doit permettre de planifier et d'optimiser les affectations en fonction de plusieurs facteurs tels que la disponibilité des conducteurs, les compétences requises, les caractéristiques des véhicules et les exigences des missions.

D'autre part, les coûts associés à la possession et à l'entretien des véhicules, tels que le carburant, l'assurance, les réparations et la maintenance, peuvent rapidement s'accumuler.

## Objectifs et Périmètre

Ce projet vise à atteindre les objectifs suivants :
- Planifier un voyage
- Automatiser les affectations des véhicules avec ou sans chauffeur
- Automatiser les affectations des conducteurs
- Rationaliser les dépenses liées à la flotte de véhicules (carburant, frais divers...)
- Avoir une visibilité sur les dépenses de chaque véhicule

## Module d'Affectation

### Profils des Conducteurs

Il est essentiel que l'administrateur crée un profil pour chaque conducteur, incluant des informations telles que les coordonnées personnelles, les compétences, l'expérience de conduite, la formation, les certifications et les permis de conduire.

- Nom
- Prénom
- Matricule
- Date de naissance
- CIN
- Numéro de Permis
- Date de Remise du Permis
- Type de Permis (Catégorie B-C ou D)

### Véhicule de la Flotte

Dans une flotte de véhicules, chaque véhicule peut être décrit par un ensemble de caractéristiques et d'attributs qui permettent de l'identifier de manière unique et de le gérer efficacement.

- Identifiant unique
- Marque et modèle
- Type de véhicule
- Kilométrage
- Disponibilité
- Type de permis requis
- Équipements spéciaux

Pour qu'un véhicule soit opérationnel dans une flotte, il doit disposer de plusieurs documents et autorisations.

- Carte grise
- Assurance automobile
- Contrôle technique
- Vignette

### Enregistrement des Voyages Planifiés

L'application permettra aux gestionnaires de saisir les détails des voyages planifiés, tels que la date, l'heure de départ et d'arrivée prévue, la destination, le type de véhicule nécessaire, etc.

Exemple de requête de création de voyage planifié via l'API :

```json
{
  "date_depart": "2024-02-15",
  "heure_depart": "09:00",
  "date_arrivee_prevue": "2024-02-16",
  "heure_arrivee_prevue": "12:00",
  "depart": "Agadir",
  "destination": "Madrid",
  "type_vehicule": "Fourgonnette",
  "Nombre de passagers": "0",
  "autres_details": "Livraison de marchandises"
}
```
Les données du voyage planifié guideront le choix du véhicule et du conducteur pour le trajet.

## Module de Consommation

L'objectif principal de l'application est de surveiller et de gérer la consommation de carburant de chaque véhicule de la flotte.

### Enregistrement de la Consommation de Carburant

La quantité de carburant ajoutée à chaque véhicule lors du ravitaillement.

### Génération de Rapports

Le module permet de générer des rapports pour chaque véhicule de la flotte en fonction de leur consommation de carburant et de leur utilisation des bons de consommation pour chaque voyage.

Exemples de rapports :

- Rapport de consommation par véhicule
- Rapport de consommation par conducteur
- Rapport de consommation par trajet
- Rapport de consommation par marque et modèle de véhicule

