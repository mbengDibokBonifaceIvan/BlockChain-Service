pragma solidity ^0.8.21;

contract ResourceTraceability {
    
    struct ResourceState {
        uint256 identification;
        uint256 creationTimestamp;
        string creator;
        string creationLocation;
        string location;
        string owner;
        uint256 timestamp;
        string data; // Added data field
    }

    // Mapping from resource ID to an array of ResourceState
    mapping(uint256 => ResourceState[]) private resourceStates;

    // Event to log the creation of a new resource state
    event ResourceStateCreated(
        uint256 identification,
        uint256 creationTimestamp,
        string creator,
        string creationLocation,
        string location,
        string owner,
        uint256 timestamp,
        string data // Added data field
    );

    // Function to record a new state for a resource
    function addResourceState(
        uint256 identification,
        uint256 creationTimestamp,
        string memory creator,
        string memory creationLocation,
        string memory location,
        string memory owner,
        uint256 timestamp,
        string memory data // Added data parameter
    ) public {
        ResourceState memory newState = ResourceState({
            identification: identification,
            creationTimestamp: creationTimestamp,
            creator: creator,
            creationLocation: creationLocation,
            location: location,
            owner: owner,
            timestamp: timestamp,
            data: data // Added data field
        });

        resourceStates[identification].push(newState);

        emit ResourceStateCreated(identification, creationTimestamp, creator, creationLocation, location, owner, timestamp, data); // Added data field
    }

    // Function to retrieve all states of a resource by identification
    function getResourceStates(uint256 identification) public view returns (ResourceState[] memory) {
        return resourceStates[identification];
    }
}
