# werehouse-lite
This is lightweight POC for warehouse system

## Assumptions and points

1. This design is very high level without understanding of any NFRs, based on NFRs design and technical choices might be totally changed.
2. I have used in memory database H2 for RDBMS and embedded MongoDB for document-based NoSQL.
3. I have created a service for Article also, just to demonstrate my design, in my opinion Product and Article information can go in NoSQL DB.
4. I have not implemented the verification of article mentioned in the Inventory is actually there in Articles.
5. Junit and error handling is not completely done in this version.
6. I am calling Inventory service directly from product service but in real world this should be done via discovery mechanism.
7. For this implementation, we don’t have descriptive functional and non-functional requirements, so I skipped service layer and calling DB layer directly form controller.
