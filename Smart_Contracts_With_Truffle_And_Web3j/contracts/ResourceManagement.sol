// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract ResourceManagement {
    struct Resource {
        uint256 resourceId;
        uint256 creationDate;
        address creator;
        string originLocation;
        string currentLocation;
        address currentOwner;
        uint256 lastUpdateDate;
    }

    // Mapping de l'ID de la ressource vers les détails de la ressource
    mapping(uint256 => Resource) public resources;
    uint256 public resourceCounter;

    // Événements pour tracer les changements
    event ResourceCreated(
        uint256 indexed resourceId,
        address indexed creator,
        string originLocation,
        uint256 creationDate
    );
    
    event ResourceLocationUpdated(
        uint256 indexed resourceId,
        string oldLocation,
        string newLocation,
        uint256 updateDate
    );
    
    event ResourceOwnershipTransferred(
        uint256 indexed resourceId,
        address indexed previousOwner,
        address indexed newOwner,
        uint256 updateDate
    );

    // Fonction pour créer une nouvelle ressource
    function createResource(
        string memory _originLocation
    ) public returns (uint256) {
        resourceCounter++;
        
        resources[resourceCounter] = Resource({
            resourceId: resourceCounter,
            creationDate: block.timestamp,
            creator: msg.sender,
            originLocation: _originLocation,
            currentLocation: _originLocation,
            currentOwner: msg.sender,
            lastUpdateDate: block.timestamp
        });

        emit ResourceCreated(
            resourceCounter,
            msg.sender,
            _originLocation,
            block.timestamp
        );

        return resourceCounter;
    }

    // Fonction pour mettre à jour la localisation
    function updateLocation(
        uint256 _resourceId,
        string memory _newLocation
    ) public {
        require(
            resources[_resourceId].currentOwner == msg.sender,
            "Seul le proprietaire peut modifier la localisation"
        );
        
        string memory oldLocation = resources[_resourceId].currentLocation;
        resources[_resourceId].currentLocation = _newLocation;
        resources[_resourceId].lastUpdateDate = block.timestamp;

        emit ResourceLocationUpdated(
            _resourceId,
            oldLocation,
            _newLocation,
            block.timestamp
        );
    }

    // Fonction pour transférer la propriété
    function transferOwnership(
        uint256 _resourceId,
        address _newOwner
    ) public {
        require(
            resources[_resourceId].currentOwner == msg.sender,
            "Seul le proprietaire peut transferer la propriete"
        );
        require(
            _newOwner != address(0),
            "Adresse invalide"
        );

        address previousOwner = resources[_resourceId].currentOwner;
        resources[_resourceId].currentOwner = _newOwner;
        resources[_resourceId].lastUpdateDate = block.timestamp;

        emit ResourceOwnershipTransferred(
            _resourceId,
            previousOwner,
            _newOwner,
            block.timestamp
        );
    }

    // Fonction pour obtenir les détails d'une ressource
    function getResource(
        uint256 _resourceId
    ) public view returns (
        uint256 resourceId,
        uint256 creationDate,
        address creator,
        string memory originLocation,
        string memory currentLocation,
        address currentOwner,
        uint256 lastUpdateDate
    ) {
        Resource memory resource = resources[_resourceId];
        return (
            resource.resourceId,
            resource.creationDate,
            resource.creator,
            resource.originLocation,
            resource.currentLocation,
            resource.currentOwner,
            resource.lastUpdateDate
        );
    }
}
