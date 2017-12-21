# Evnote

| URL Path                                 | Request methods | Result                                              |
|------------------------------------------|-----------------|-----------------------------------------------------|
| /create                                  | POST            | Create, update user                                 |
| /users/{id}                              | GET             | Get all users/Get by id                             |
| /notepads                                | GET             | Get all notepads of current user                    |
| /notepad/{title}                         | GET,POST        | Get, create, update notepad by title of current user|
| /notepad/{id}                            | DELETE          | Delete notepad by id                                |
| /notepads/{id}/notes                     | GET             | Get all notes of notebook by its ID                 |
| /notes{id}                               | GET,DELETE      | Get,DELETE note by id                               |
| /mark/{title}/notes                      | GET             | Get notes by mark                                   |
| /mark/note                               | POST,DELETE     | Add,Delete mark to note                             |
| /marks                                   | GET,POST        | Get all marks by user. Create,update                |
| /marks/{id}                              | DELETE          | Get all marks by user. Create,update                |
|------------------------------------------|-----------------|-----------------------------------------------------|