// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract CommercialOperations {
    struct Operation {
        uint256 id;
        string operationType;
        string status;
        string seller;
        string buyer;
        uint256 blockNumber;
        uint256 blockTimestamp;
    }

    mapping(uint256 => Operation) public operations;
    
    uint256[] public operationIds;

    event OperationCreated(
        uint256 indexed id, 
        string operationType, 
        string seller, 
        string buyer,
        uint256 blockNumber
    );

    function createOperation(
        uint256 _id, 
        string memory _type, 
        string memory _status, 
        string memory _seller, 
        string memory _buyer
    ) public {
        require(operations[_id].id == 0, "Operation with this ID already exists");

        operations[_id] = Operation({
            id: _id,
            operationType: _type,
            status: _status,
            seller: _seller,
            buyer: _buyer,
            blockNumber: block.number,
            blockTimestamp: block.timestamp
        });

        operationIds.push(_id);

        emit OperationCreated(_id, _type, _seller, _buyer, block.number);
    }

    function getOperationBlockInfo(uint256 _id) public view returns (uint256 blockNumber, uint256 blockTimestamp) {
        require(operations[_id].id != 0, "Operation does not exist");
        return (
            operations[_id].blockNumber,
            operations[_id].blockTimestamp
        );
    }

    function getOperation(uint256 _id) public view returns (Operation memory) {
        require(operations[_id].id != 0, "Operation does not exist");
        return operations[_id];
    }

    function getAllOperationIds() public view returns (uint256[] memory) {
        return operationIds;
    }

    function updateOperationStatus(uint256 _id, string memory _newStatus) public {
        require(operations[_id].id != 0, "Operation does not exist");
        operations[_id].status = _newStatus;
    }
}