# Account
## Create account
Endpoint: 54.67.72.192/create_account \ 
POST request with payload:
- `username`: username of account to be created
- `password`: password of account to be created
- `name`: user's name associated with account
- `phone`: user's phone associated with account
Returns: \
200: Account created successfully \
500: Username already exists

## Login
Endpoint: 54.67.72.192/login \
GET request with payload:
- `username`: username of account to log in to
- `password`: password of account to log in to
Returns: \ 
200: Login success \
201: Username or password incorrect \
202: Username does not exist 

# Patients / Lists
## Add a new patient/list
Endpoint: 54.67.72.192/add_patient \
POST request with payload:
- `name`: name of patient
- `birthday`: birthday of patient
- `gender`: gender of patient
- `contacts`: emergency contacts of patient. JSON object mapping emergency contact names  to emergency contact phone numbers
- `bio`: bio/notes of patient
- `user`: username of caretaker requesting to create a new patient 
Returns: \
200: Successfully added patient

## Get a list of all the patients
Endpoint: 54.67.72.192/get_patients \
This should be used when adding an existing patient to another caretaker's account. \
GET request \
Returns: \
A list of patient JSON objects, each with attributes `name`, `birthday`, `gender`, `contacts`, `bio`, `caretakers`.

## Get a list of all the patients in the caretaker's account
Endpoint: 54.67.72.192/get_user_patients \ 
This should be called after logging in to get all of the caretaker's patients/lists. \
GET request with payload:
- `user`: username of caretaker account
Returns: \
A list of patient JSON objects, each with attributes `name`, `birthday`, `gender`, `contacts`, `bio`, `caretakers`.

## Add a new caretaker to an existing patient
Endpoint: 54.67.72.192/add_caretaker \
POST request with payload:
- `user`: username of caretaker account to be added
- `patient`: patient name
Returns: \
200: Successfully added caretaker \
201: User is already a caretaker for patient

# Tasks/Todo Items
## Add a new task
Endpoint: 54.67.72.192/add_task \
POST request with payload:
- `patient`: name of patient
- `item_name`: string for the name of the task 
- `due_date`: due date of the task
- `notes`: notes for the task
- `caretakers`: username of caretaker that created the task
Returns: \
200: Successfully added task

## Update a task
Endpoint: 54.67.72.192/update_task \
POST request with payload:
-`patient`: name of patient
-`item_name`: string for the name of the task 
-`due_date`: due date of the task
-`notes`: notes for the task
-`caretakers`: username of caretaker that created the task
Returns: \
200: Successfully updated task

## Delete a task
Endpoint: 54.67.72.192/delete_task \
POST request with payload:
- `patient`: name of patient whose task will be deleted
- `item_name`: string for the name of the task to be deleted
Returns: \
200: Successfully deleted task
