# ada.08
## Santander Coders - Unit 08: Automated Testing (Bookstore)

## Statement
Implement a basic API with the Repository, Service and Controller layers of a Bookstore.

## Requirements
It is necessary to register a new book in the system. Every book must have the following attributes below:
- A title.
- An abstract of what will be found in the book.
- A free-size Summary.
- Price of the book.
- Number of pages.
- ISBN (book identifier).
- Date it should go released (of publication).

## Restrictions
- Title is required.
- Abstract is mandatory and has a maximum of 500 characters.
- Summarry is free-sized.
- Price is mandatory and the minimum is 20.
- Number of pages is mandatory and the minimum is 100.
- ISBN is mandatory, free format.
- Date that will go released needs to be in the future.

## Expected outcome
A new book needs to be created and status 200 returned.

## Mandatory requirements
- The application must have a minimum coverage of 80% of the API code.
- Perform at least 2 integration tests.
