pragma solidity ^0.8.21;

contract CommercialOperations {

    struct OperationState {
        uint256 identification;
        string operationType;
        string status;
        string producer;
        string consumer;
        uint256 timestamp;
        string data;
    }

    // Mapping from operation ID to an array of OperationState
    mapping(uint256 => OperationState[]) private operationStates;

    // Event to log the creation of a new operation state
    event OperationStateCreated(
        uint256 identification,
        string operationType,
        string status,
        string producer,
        string consumer,
        uint256 timestamp,
        string data
    );

    // Function to record a new state for an operation
    function addOperationState(
        uint256 identification,
        string memory operationType,
        string memory status,
        string memory producer,
        string memory consumer,
        uint256 timestamp,
        string memory data
    ) public {
        OperationState memory newState = OperationState({
            identification: identification,
            operationType: operationType,
            status: status,
            producer: producer,
            consumer: consumer,
            timestamp: timestamp,
            data: data
        });

        operationStates[identification].push(newState);

        emit OperationStateCreated(identification, operationType, status, producer, consumer, timestamp, data);
    }

    // Function to retrieve all states of an operation by identification
    function getOperationStates(uint256 identification) public view returns (OperationState[] memory) {
        return operationStates[identification];
    }
}
