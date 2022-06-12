# The methodology of programming I. EC - C1 group, assignment

## Health care registry program

This is a registry program, that can be used in a hospital.
The main goal of this project is to help to store and arrange the doctors and their patients of the
hospital. You can login as an admin or as an individual. With the menu you can easly list all the current patients, add a new one, modify them,
remove them, modify them, list all doctors, add a new one, delete or modify them. IMPORTANT!!! --> If you login as an administrator you can modify
all the data, but if you login as an individual, you can only access to your own patients except if the access is true.

## Instructions

After you start the program, you should see the menu and the choosable numbers with the task of what they are doing.
* 0 - Save and exit
* 1 - List all patients
* 2 - Add new patient
* 3 - Remove patient
* 4 - Modify patient
* 5 - Enumerate doctors
* 6 - Add new doctor
* 7 - Remove doctor
* 8 - Modify doctor

    If you have choosen the task you want to execute, click next to the bottom text and enter it's number.

The number and the tasks for it:

* 0 - Save and exit --> The program will save any modified informations and exit.
* 1 - List all patients --> All the current patients in the database with their infos will be displayed
* 2 - Add new patient --> You can add a new patient to the database
* 3 - Remove patient --> You can remove a patient from the database
* 4 - Modify patient --> You can modify a patient that is in the database
* 5 - Enumerate doctors --> All the current doctors in the database with their infos will be displayed
* 6 - Add new doctor --> You can add a new doctor to the database
* 7 - Remove doctor --> You can remove a patient from the database
* 8 - Modify doctor -->You can modify a doctor that is in the database

There is 5 information that a doctor has:
* ID >>> The doctor's unique identityfier.
* Name >>> The doctor's name.
* Password >>> The doctor's password for login.
* Patients >>> The patients' id that pertain for the current doctor.
* Access >>> Can all data be handled by that doctor.

There is 6 information that a patient has:
* ID >>> The patient unique identityfier.
* Name >>> The patient's name.
* Birth Year >>> The patient's year of birth.
* Blood-type >>> The patient's blood-type.
* Check-in-date >>> The date that the patient checked in to the hospital.
* Diseases >>> The problems of the patients.

##Technical data
The program saves the doctors and the patients to xml files named as "doctors" and "patients". It uses it to
read from it too, meaning that what once is saved in that file, is accessable anytime from the program,
or even from the xml itself. The program uses the console and not a GUI.




* The program's strucure:



* Person:
  * It is an abstract class. It is the superclass of the Doctor class and the Patient class.
  * The format of data used in the person class
    * ID >>> String
    * name >>> String

* Doctor:
  * It is a class that contains the information to create a new doctor object. It is the subclass of the Person class.This includes all the getters and setters.
  * The format of the data used in the doctor class:
    * ID >>> Inherit from Person
    * name >>> Inherit from Person
    * password >>> String
    * patients >>> ArrayList<String>
    * access >>> Boolean

* Patient
  * It is a class that contains the information to create a new patient object. The person class is its superclass.This includes all the getters and setters.
  * The format of the data used in the patient class
    * ID >>> Inherit from Person
    * name >>> Inherit from Person
    * birthYear >>> Integer
    * bloodType >>> BloodTypeEnum
    * checkInDate >>> Date
    * diseases >>> ArrayList<String>

* BloodTypeEnum:
    * It is an enum that includes all the bloodtypes.

* FileName:
  * It is an annotation that contains the name of the file that contains the infarmation about the objects of the class.

* GetterFuncitonName:
  * It is an annotation that contains the name of the getter function of a field of the class.  

* ReadMethodName:
  * It is an annotation that contains the name of the function that generates the objects from the read values from the Fio class.

* SaveMethodName:
  * It is an annotation that contains the name of the function that invoke the save method in the Fio class.

* Fio:
    * It is a generic class that can read and save to and from the files.

* Service:
    * It is a generic class that contains methods that is the same for the classes.

* ProgModBeadando:
    * It is the main part of the program. It writes and reads to and from the console. It creates the menu, then invokes
        the right methods.
    

## Author

* Authors names: Andics Gergely, Lukács Bence, Uti Richárd
