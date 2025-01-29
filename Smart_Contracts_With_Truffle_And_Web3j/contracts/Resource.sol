// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract Resource {
    struct ResourceData {
        uint256 resourceId;
        uint256 creationDate;
        string creator;
        string originLocation;
        string currentLocation;
        string currentOwner;
        uint256 lastUpdateDate;
        uint256 blockNumber;
        uint256 blockTimestamp;
    }

    ResourceData[] public resources;
    mapping(uint256 => bool) public resourceExists;
    
    event ResourceCreated(
        uint256 indexed resourceId,
        string creator,
        string originLocation
    );
    
    event ResourceUpdated(
        uint256 indexed resourceId,
        string currentLocation,
        string currentOwner,
        uint256 lastUpdateDate
    );

    modifier onlyValidResource(uint256 _resourceId) {
        require(resourceExists[_resourceId], "Resource does not exist");
        _;
    }

    function createResource(
        uint256 _resourceId,
        string memory _creator,
        string memory _originLocation
    ) public returns (bool) {
        require(!resourceExists[_resourceId], "Resource already exists");
        
        ResourceData memory newResource = ResourceData({
            resourceId: _resourceId,
            creationDate: block.timestamp,
            creator: _creator,
            originLocation: _originLocation,
            currentLocation: _originLocation,
            currentOwner: _creator,
            lastUpdateDate: block.timestamp,
            blockNumber: block.number,
            blockTimestamp: block.timestamp
        });
        
        resources.push(newResource);
        resourceExists[_resourceId] = true;
        
        emit ResourceCreated(_resourceId, _creator, _originLocation);
        return true;
    }

    function updateResource(
        uint256 _resourceId,
        string memory _newLocation,
        string memory _newOwner
    ) public onlyValidResource(_resourceId) returns (bool) {
        for (uint i = 0; i < resources.length; i++) {
            if (resources[i].resourceId == _resourceId) {
                resources[i].currentLocation = _newLocation;
                resources[i].currentOwner = _newOwner;
                resources[i].lastUpdateDate = block.timestamp;
                resources[i].blockNumber = block.number;
                resources[i].blockTimestamp = block.timestamp;
                
                emit ResourceUpdated(
                    _resourceId,
                    _newLocation,
                    _newOwner,
                    block.timestamp
                );
                return true;
            }
        }
        return false;
    }

    function getResource(uint256 _resourceId) 
        public 
        view 
        onlyValidResource(_resourceId) 
        returns (ResourceData memory) 
    {
        for (uint i = 0; i < resources.length; i++) {
            if (resources[i].resourceId == _resourceId) {
                return resources[i];
            }
        }
        revert("Resource not found");
    }
}
