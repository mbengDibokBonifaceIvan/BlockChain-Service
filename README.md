# Service Blockchain de Traçabilité 🔗
![Version](https://img.shields.io/badge/version-2.0.0-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)

## 📋 Description
Service blockchain développé dans le cadre du projet d'Ingénierie de Trafic Réseau (5GI 2024-2025). Ce service assure la traçabilité des opérations commerciales et des ressources via la technologie blockchain Ethereum.

## 🏗 Architecture

### Technologies Utilisées
- **Backend**: Spring Boot 3.4.2
- **Blockchain**: Ethereum (Go-Ethereum)
- **Conteneurisation**: Docker
- **Orchestration**: Kurtosis 2.1.0
- **Documentation API**: OpenAPI (Swagger)
- **Tests**: JUnit, Mockito

### Composants Principaux
- Smart Contract de traitement des opérations commerciales
- Smart Contract de traitement des ressources
- Interface web de gestion
- Machine Virtuelle Ethereum

## 🚀 Installation

### Prérequis
```bash
- Java JDK 17 ou supérieur
- Maven 3.8+
- Docker et Kurtosis
- Truffle et Web3j (pour les smart contracts)
```

### Configuration
1. Cloner le dépôt :
```bash
git clone https://github.com/mbengDibokBonifaceIvan/BlockChain-Service.git
cd BlockChain-Service
```

2. Installer les dépendances :
```bash
mvn clean install
```

3. Lancer les conteneurs :
```bash
docker-compose up -d
```

## 📚 Documentation API

La documentation de l'API est disponible via Swagger UI :
- Local : http://localhost:8080/swagger-ui.html
- Production : https://votre-domaine/swagger-ui.html

## 🔍 Tests

### Exécuter les tests
```bash
# Tests unitaires
mvn test

# Tests d'intégration
mvn verify
```

### Couverture de code
```bash
mvn test jacoco:report
```
Le rapport de couverture sera généré dans `target/site/jacoco/index.html`

## 🛠 Utilisation

### Endpoints principaux

#### Opérations Commerciales
- `POST /api/v2/operations` : Ajouter une opération
- `GET /api/v2/operations/states/{id}` : Obtenir l'historique d'une opération

#### Ressources
- `POST /api/v2/resource` : Ajouter une ressource
- `GET /api/v2/resource/states/{id}` : Obtenir l'historique d'une ressource

## 🤝 Contribution
1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit les changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📝 Licence
Ce projet est sous licence [Apache 2.0](http://springdoc.org)

## 👥 Équipe
- Développé par l'équipe Blockchain-Service
- Promotion 5GI 2024-2025
- École Nationale Supérieure Polytechnique de Yaoundé

## 📞 Contact
- Email : mbengivan63@gmail.com
- Issue Tracker : https://github.com/votre-organisation/BlockChain-Service/issues

## 🔄 État du Projet
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)
![Test Coverage](https://img.shields.io/badge/coverage-87%25-green.svg)
![Dependencies](https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg)