# Werehouse
This is lightweight POC for warehouse system

## Design

![IMG-0021](https://user-images.githubusercontent.com/55003223/158689852-c46f838e-aacc-4417-aa28-7013e74233b2.jpg)


## Assumptions and points

1. This design is very high level without understanding of any NFRs, based on NFRs design and technical choices might be totally changed.
2. I have used in memory database H2 for RDBMS and embedded MongoDB for document-based NoSQL.
3. I have created a service for Article also, just to demonstrate my design, in my opinion Product and Article information can go in NoSQL DB.
4. I have not implemented the verification of article mentioned in the Inventory is actually there in Articles.
5. Junit and error handling is not completely done in this version.
6. I am calling Inventory service directly from product service but in real world this should be done via discovery mechanism.
7. For this implementation, we don’t have descriptive functional and non-functional requirements, so I skipped service layer and calling DB layer directly form controller.

## Swagger : http://localhost:8080/swagger-ui.html#/

![sw1](https://user-images.githubusercontent.com/55003223/158690046-f87f5b77-fa56-4bae-bb4e-ef3efec046fd.PNG)

## Product Service:

![sw2](https://user-images.githubusercontent.com/55003223/158690196-22dd7a4b-939d-4683-bbe8-b3a1dbbd5f8e.PNG)

## Inventories Service:

![sw3](https://user-images.githubusercontent.com/55003223/158690333-f6bf6fee-3f3e-45cb-954a-28d7f5683e89.PNG)

## Article Service:

![sw4](https://user-images.githubusercontent.com/55003223/158690555-c024c3c0-4886-404f-a12b-87632aded5eb.PNG)
