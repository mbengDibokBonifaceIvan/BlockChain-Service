# Service de Traçabilité Blockchain 🔗

[![Version](https://img.shields.io/badge/version-2.0.0-blue.svg)](https://github.com/mbengDibokBonifaceIvan/BlockChain-Service)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)
![Test Coverage](https://img.shields.io/badge/coverage-87%25-green.svg)

## 📋 Description du Projet

### Contexte
Service blockchain innovant développé dans le cadre du projet d'Ingénierie de Trafic Réseau (5GI 2024-2025), offrant une solution de traçabilité transparente et sécurisée pour les opérations commerciales et les ressources.

### Objectifs Principaux
- 🔐 Assurer la transparence des transactions
- 🔗 Traçabilité complète des opérations commerciales
- 📊 Gestion traçable des ressources
- 🌐 Utilisation de la technologie Ethereum

## 🏗️ Architecture Technique

### Stack Technologique
| Domaine            | Technologies           |
| ------------------ | ---------------------- |
| Backend            | Spring Boot 3.4.2      |
| Blockchain         | Ethereum (Go-Ethereum) |
| Conteneurisation   | Docker                 |
| Orchestration      | Kurtosis 2.1.0         |
| Documentation API  | OpenAPI (Swagger)      |
| Framework de Tests | JUnit, Mockito         |

### Configuration Réseau Ethereum Détaillée

#### Fichier `network_params.yaml`
```yaml
participants:
  # Configuration des nœuds Ethereum
  - el_type: geth      # Nœud de couche d'exécution Geth
    cl_type: lighthouse  # Client de couche de consensus Lighthouse
    count: 2            # Nombre de nœuds avec cette configuration

  - el_type: geth      # Autre configuration de nœud
    cl_type: teku      # Client de couche de consensus Teku

# Paramètres réseau spécifiques
network_params:
  network_id: "585858"  # Identifiant unique du réseau privé

# Services additionnels
additional_services:
  - dora  # Service supplémentaire (explorateur de blocs)
```

#### Explication de la Configuration
- **Participants**: Définit la topologie du réseau Ethereum
  - Deux types de nœuds avec des clients de consensus différents
  - Geth comme client d'exécution
  - Lighthouse et Teku comme clients de consensus
- **Network ID**: Réseau privé unique (585858)
- **Services Additionnels**: Inclusion de Dora, un explorateur de blocs

## 🚀 Guide d'Installation Détaillé

### Prérequis Techniques
- ☕ Java Development Kit (JDK) 17+
- 🏗️ Maven 3.8+
- 🐳 Docker
- 🌐 Kurtosis
- 🧩 Truffle
- 🔗 Web3j

### Étapes d'Installation

1. **Clonage du Dépôt**
   ```bash
   git clone https://github.com/mbengDibokBonifaceIvan/BlockChain-Service.git
   cd BlockChain-Service
   ```

2. **Installation des Dépendances**
   ```bash
   mvn clean install
   ```

3. **Configuration du Réseau Ethereum Privé**
   ```bash
   # Lancement du réseau avec la configuration personnalisée
    kurtosis run github.com/ethpandaops/ethereum-package --args-file ./network_params.yaml --image-download always --enclave Ethereum-Network
   ```

4. **Démarrage de l'Application**
   ```bash
   mvn spring-boot:run
   ```

## 🔍 Points d'Accès API

### Opérations Commerciales
- `POST /api/v2/operations` : Création d'une nouvelle opération
- `GET /api/v2/operations/states/{id}` : Historique détaillé d'une opération

### Gestion des Ressources
- `POST /api/v2/resource` : Enregistrement d'une ressource
- `GET /api/v2/resource/states/{id}` : Historique complet d'une ressource

## 📊 Tests et Qualité

### Stratégie de Tests
```bash
# Tests unitaires
mvn test

# Tests d'intégration
mvn verify

# Rapport de couverture de code
mvn test jacoco:report
```

### Couverture de Code
- Rapport généré : `target/site/jacoco/index.html`
- Couverture actuelle : 11%

## 🤝 Contribution

### Processus
1. Forker le projet
2. Créer une branche de fonctionnalité
3. Commiter les modifications
4. Soumettre une Pull Request

### Recommandations
- Respecter les conventions de codage
- Documenter les changements
- Fournir des tests unitaires

## 📝 Licence
Distribué sous licence Apache 2.0. Voir `LICENSE` pour plus de détails.

## 👥 Équipe de Développement
- **Organisation** : Équipe Blockchain-Service
- **Promotion** : 5GI 2024-2025
- **Institution** : École Nationale Supérieure Polytechnique de Yaoundé

## 📞 Contacts

### Développeurs
-  Mbeng Dibok Boniface Ivan: `mbengivan63@gmail.com`
- Sambo Mohammadou Bachirou  : `sambo.m.bachirou@gmail.com`

### Support
- Tracker des Issues : [Lien GitHub Issues](https://github.com/mbengDibokBonifaceIvan/BlockChain-Service/issues)

---

**🔍 État Actuel du Projet**
- ✅ Stabilité : Stable
- 📊 Couverture des Tests : 11%
- 🔄 Dépendances : À jour