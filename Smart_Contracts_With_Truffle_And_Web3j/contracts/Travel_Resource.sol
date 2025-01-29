// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract Travel_Resource {
  
   struct ResourceState {
        uint identification;
        uint creationTimestamp;
        string creator;
        string birthPlace;
        string location;
        string owner;
        uint timestamp;
    }

    // Mapping from resource ID to its current state
    mapping(uint => ResourceState) public resourceStates;

    // Block number at the time of contract deployment
    uint256 public deploymentBlockNumber;

    // Event to log the creation of a new resource state
    event ResourceStateCreated(
        uint identification,
        uint creationTimestamp,
        string creator,
        string birthPlace,
        string location,
        string owner,
        uint timestamp
    );

    constructor() {
        deploymentBlockNumber = block.number;
    }

    // Function to record a new state for a resource
    function addResourceState(
        uint identification,
        uint creationTimestamp,
        string memory creator,
        string memory birthPlace,
        string memory location,
        string memory owner,
        uint timestamp
    ) public {
        resourceStates[identification] = ResourceState({
            identification: identification,
            creationTimestamp: creationTimestamp,
            creator: creator,
            birthPlace: birthPlace,
            location: location,
            owner: owner,
            timestamp: timestamp
        });
        
        emit ResourceStateCreated(identification, creationTimestamp, creator, birthPlace, location, owner, timestamp);
    }

    // Function to retrieve the current state of a resource by identification
    function getResourceState(uint256 identification) public view returns (ResourceState memory) {
        return resourceStates[identification];
    }

    // Function to retrieve the current block number
    function getCurrentBlockNumber() public view returns (uint256) {
        return block.number;
    }

}
